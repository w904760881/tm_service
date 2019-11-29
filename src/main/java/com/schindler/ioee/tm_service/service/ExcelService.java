package com.schindler.ioee.tm_service.service;

import com.schindler.ioee.gdcsv3.common.model.pojo.list.PageQuery;
import com.schindler.ioee.tm_service.mybatis.mapper.EquipmentOnlineStatusMapper;
import com.schindler.ioee.tm_service.mybatis.model.EquipmentOnlineStatus;
import com.schindler.ioee.tm_service.mybatis.model.EquipmentOnlineStatusExample;
import com.schindler.ioee.tm_service.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author Jensen
 */
@Slf4j
@Service
@Transactional
public class ExcelService {
    @Autowired
    private EquipmentService equipmentService;

    @Autowired
    private EquipmentOnlineStatusMapper equipmentOnlineStatusMapper;


    public boolean download(HttpServletResponse response, PageQuery<EquipmentOnlineStatus> pageQuery) {
        log.info("started download excel, receive pageQuery is {}", pageQuery);
        if (pageQuery.getQueryModel() == null) {
            log.warn("download excel failed, pageQuery queryModel is null");
            return false;
        }
        pageQuery.setQueryModel(null);
        pageQuery.setPageIndex(0);
        pageQuery.setPageSize(100000);
        List<EquipmentOnlineStatus> equipmentList = equipmentService.query(pageQuery).getList();
        return writeExcel(response, equipmentList);

    }


    private boolean writeExcel(HttpServletResponse response, List<EquipmentOnlineStatus> equipmentList) {
        if (equipmentList == null || equipmentList.size() == 0) {
            log.warn("download excel failed, equipmentList is empty");
            return false;
        }
        String[] header = {"设备编号", "合同号", "网关编号", "联系人", "联系电话", "邮箱", "发送邮件时间", "心跳时间", "创建时间", "创建人", "最后修改时间"};

        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        // 添加标题
        Row headerRow = sheet.createRow(0);
        Stream.iterate(0, i -> i + 1).limit(header.length).forEach(i -> {
            headerRow.createCell(i).setCellValue(header[i]);
        });
        // 添加内容
        Stream.iterate(0, i -> i + 1).limit(equipmentList.size()).forEach(i -> {
            Row row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(equipmentList.get(i).getEquipmentId());
            row.createCell(1).setCellValue(equipmentList.get(i).getContractNo());
            row.createCell(2).setCellValue(equipmentList.get(i).getCubeNo());
            row.createCell(3).setCellValue(equipmentList.get(i).getTechnician());
            row.createCell(4).setCellValue(equipmentList.get(i).getPhoneNumber());
            row.createCell(5).setCellValue(equipmentList.get(i).getEmail());
            if (equipmentList.get(i).getNotifyTime() != null) {
                row.createCell(6).setCellValue(DateUtil.yyyyMMddHHmmss_Format(equipmentList.get(i).getNotifyTime()).get());
            }
            if (equipmentList.get(i).getHeartbeatTime() != null) {
                row.createCell(7).setCellValue(DateUtil.yyyyMMddHHmmss_Format(equipmentList.get(i).getHeartbeatTime()).get());
            }
            if (equipmentList.get(i).getCreateDate() != null) {
                row.createCell(8).setCellValue(DateUtil.yyyyMMddHHmmss_Format(equipmentList.get(i).getCreateDate()).get());
            }
            row.createCell(9).setCellValue(equipmentList.get(i).getCreateBy());
            if (equipmentList.get(i).getUpdateDate() != null) {
                row.createCell(10).setCellValue(DateUtil.yyyyMMddHHmmss_Format(equipmentList.get(i).getUpdateDate()).get());
            }
        });

        // 列宽随着内容自动适应
        for (int i = 0; i < header.length; i++) {
            int columnWidth = sheet.getColumnWidth(i) / 256;
            for (int j = 0; j < sheet.getLastRowNum(); j++) {
                Row row = sheet.getRow(j);
                if (row.getCell(i) != null) {
                    Cell cell = row.getCell(i);
                    int length = cell.getStringCellValue().getBytes().length;
                    if (columnWidth < length) {
                        columnWidth = length;
                    }
                }
            }
            sheet.setColumnWidth(i, (columnWidth + 4) * 256);
        }

        try {
            // todo 测试时本地生成excel
//            FileOutputStream stream = new FileOutputStream("C:/Users/zhanjens/Desktop/" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".xlsx");
            // todo 浏览器点击生成excel
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/msexcel;charset=utf-8");
            response.setHeader("Content-Disposition", "Attachment;filename=TMService.xlsx");
            OutputStream stream = response.getOutputStream();

            workbook.write(stream);
            stream.close();
            log.info("download excel is successfully");
            return true;
        } catch (IOException e) {
            log.error(e.toString());
            e.printStackTrace();
            return false;
        }
    }

    public int upload(MultipartFile multipartFile) {
        log.info("started upload excel, file name is {}", multipartFile.getOriginalFilename());
        String folderPath = System.getProperty("user.dir") + "/excel/";
        createFolder(new File(folderPath));
        clearFolder(new File(folderPath));
        String fileFullPath = folderPath + multipartFile.getOriginalFilename();
        File file = new File(fileFullPath);
        try {
            multipartFile.transferTo(file);
        } catch (IOException e) {
            log.error(e.toString());
            e.printStackTrace();
            return -1;
        }
        return readExcel(fileFullPath);
    }

    private int readExcel(String filePath) {
        try {
            FileInputStream file = new FileInputStream(new File(filePath));
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(0);
            for (int i = sheet.getFirstRowNum() + 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                // equipmentId, contractNo, cubeNo, email为空时直接返回false
                if (row.getCell(0) == null || row.getCell(1) == null || row.getCell(2) == null || row.getCell(5) == null) {
                    log.warn("upload excel is failed, equipmentId is {} contractNo is {} cubeNo is {} email is {}", row.getCell(0), row.getCell(1), row.getCell(2), row.getCell(5));
                    return -1;
                }
                // 查询数据库中是否有记录
                EquipmentOnlineStatusExample example = new EquipmentOnlineStatusExample();
                EquipmentOnlineStatusExample.Criteria criteria = example.createCriteria();
                criteria.andEquipmentIdEqualTo(row.getCell(0).toString());
                long recordCount = equipmentOnlineStatusMapper.countByExample(example);
                // 组装 EquipmentOnlineStatus 对象
                EquipmentOnlineStatus equipment = new EquipmentOnlineStatus();
                equipment.setEquipmentId(row.getCell(0).toString());
                equipment.setContractNo(row.getCell(1).toString());
                equipment.setCubeNo(row.getCell(2).toString());
                if (row.getCell(3) != null) {
                    equipment.setTechnician(row.getCell(3).toString());
                }
                if (row.getCell(4) != null) {
                    equipment.setPhoneNumber(row.getCell(4).toString());
                }
                equipment.setEmail(row.getCell(5).toString());
                // 插入记录或者更新记录
                boolean flag = insertOrUpdate(recordCount, equipment);
                if (flag) {
                    // 只要有一次数据库操作失败都会回滚事务
                    log.error("database insert or update is failed, rollback");
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    return -1;
                }
            }
            workbook.close();
            log.info("read excel is successfully, count is {}", sheet.getLastRowNum());
            return sheet.getLastRowNum();
        } catch (Exception e) {
            log.error(e.toString());
            e.printStackTrace();
            // 出现异常回滚事务
            log.error("catch exception, rollback");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return -1;
        }
    }

    private boolean insertOrUpdate(long recordCount, EquipmentOnlineStatus equipment) {
        if (recordCount == 1) {
            equipment.setUpdateDate(new Date());
            int count = equipmentOnlineStatusMapper.updateByPrimaryKeySelective(equipment);
            return count == 1;
        } else {
            equipment.setCreateDate(new Date());
            int count = equipmentOnlineStatusMapper.insertSelective(equipment);
            return count == 1;
        }
    }

    private static boolean createFolder(File folder) {
        if (!folder.exists()) {
            return folder.mkdirs();
        }
        return false;
    }

    private static boolean clearFolder(File folder) {
        if (!folder.exists()) {
            return false;
        }
        File[] files = folder.listFiles();
        if (files.length > 0) {
            for (File f : files) {
                if (f.isDirectory()) {
                    clearFolder(f);
                    f.delete();
                } else {
                    f.delete();
                }
            }
        }
        return true;
    }


}
