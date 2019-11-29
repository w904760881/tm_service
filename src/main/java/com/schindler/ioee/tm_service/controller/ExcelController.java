package com.schindler.ioee.tm_service.controller;

import com.schindler.ioee.gdcsv3.common.model.pojo.list.PageQuery;
import com.schindler.ioee.tm_service.mybatis.model.EquipmentOnlineStatus;
import com.schindler.ioee.tm_service.response.RestErrorResponse;
import com.schindler.ioee.tm_service.response.RestResponseEnum;
import com.schindler.ioee.tm_service.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * @author Jensen
 */
@RestController
public class ExcelController {

    @Autowired
    private ExcelService excelService;

    @PostMapping("/v1/excel/download")
    public Object downloadExcel(HttpServletRequest request, HttpServletResponse response, @RequestBody PageQuery<EquipmentOnlineStatus> pageQuery) {
        boolean isSuccess = excelService.download(response, pageQuery);
        if (isSuccess) {
            response.setStatus(RestResponseEnum.SUCCESS.getCode());
            return pageQuery;
        }
        return RestErrorResponse.build().setErrorCode(RestResponseEnum.EXCEL_DOWNLOAD_ERROR.getCode()).setMessage(RestResponseEnum.EXCEL_DOWNLOAD_ERROR.getMsg()).getResult();
    }

    @PostMapping("/v1/excel/upload")
    public Object uploadExcel(HttpServletRequest request, HttpServletResponse response, @RequestParam("multipartFile") MultipartFile multipartFile) {
        int count = excelService.upload(multipartFile);
        if (count != -1) {
            response.setStatus(RestResponseEnum.SUCCESS.getCode());
            return count;
        }
        return RestErrorResponse.build().setErrorCode(RestResponseEnum.EXCEL_UPLOAD_ERROR.getCode()).setMessage(RestResponseEnum.EXCEL_UPLOAD_ERROR.getMsg()).getResult();
    }

}
