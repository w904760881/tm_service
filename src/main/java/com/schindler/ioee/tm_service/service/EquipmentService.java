package com.schindler.ioee.tm_service.service;

import com.github.pagehelper.PageRowBounds;
import com.schindler.ioee.gdcsv3.common.model.pojo.list.PageQuery;
import com.schindler.ioee.tm_service.mybatis.mapper.EquipmentOnlineStatusMapper;
import com.schindler.ioee.tm_service.mybatis.model.EquipmentOnlineStatus;
import com.schindler.ioee.tm_service.mybatis.model.EquipmentOnlineStatusExample;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author litim
 */
@Slf4j
@Service
public class EquipmentService {

    @Autowired
    private EquipmentOnlineStatusMapper equipmentOnlineStatusMapper;

    public boolean insert(EquipmentOnlineStatus equipmentOnlineStatus) {
        log.info("receive equipmentOnlineStatus is {}", equipmentOnlineStatus);
        if (equipmentOnlineStatus == null) {
            return false;
        }
        try {
            equipmentOnlineStatus.setCreateDate(new Date());
            int count = equipmentOnlineStatusMapper.insertSelective(equipmentOnlineStatus);
            return count == 1;
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.toString());
            return false;
        }
    }

    public boolean delete(String equipmentId) {
        log.info("receive equipmentId is {}", equipmentId);
        if (StringUtils.isBlank(equipmentId)) {
            return false;
        }
        try {
            int count = equipmentOnlineStatusMapper.deleteByPrimaryKey(equipmentId);
            return count == 1;
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.toString());
            return false;
        }
    }

    public boolean update(EquipmentOnlineStatus equipmentOnlineStatus) {
        log.info("receive equipmentOnlineStatus is {}", equipmentOnlineStatus);
        if (equipmentOnlineStatus == null) {
            return false;
        }
        try {
            equipmentOnlineStatus.setUpdateDate(new Date());
            int count = equipmentOnlineStatusMapper.updateByPrimaryKeySelective(equipmentOnlineStatus);
            return count == 1;
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.toString());
            return false;
        }
    }

    public Optional<EquipmentOnlineStatus> getEquipmentById(String equipmentId) {
        log.info("receive equipmentId is {}", equipmentId);
        if (StringUtils.isBlank(equipmentId)) {
            log.warn("query equipmentOnlineStatus failed, equipmentId is null");
        }
        EquipmentOnlineStatus equipmentOnlineStatus = equipmentOnlineStatusMapper.selectByPrimaryKey(equipmentId);
        if (equipmentOnlineStatus == null) {
            return Optional.empty();
        }
        return Optional.of(equipmentOnlineStatus);
    }

    public PageQuery<EquipmentOnlineStatus> query(PageQuery<EquipmentOnlineStatus> pageQuery) {
        log.info("receive pageQuery is {}", pageQuery);
        EquipmentOnlineStatus equipment = pageQuery.getQueryModel();
        if (equipment == null) {
            equipment = new EquipmentOnlineStatus();
        }
        EquipmentOnlineStatusExample example = new EquipmentOnlineStatusExample();
        EquipmentOnlineStatusExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(equipment.getEquipmentId())) {
            criteria.andEquipmentIdEqualTo(equipment.getEquipmentId());
        }
        if (StringUtils.isNotBlank(equipment.getContractNo())) {
            criteria.andContractNoEqualTo(equipment.getContractNo());
        }
        if (StringUtils.isNotBlank(equipment.getCubeNo())) {
            criteria.andCubeNoEqualTo(equipment.getCubeNo());
        }
        if (StringUtils.isNotBlank(equipment.getTechnician())) {
            criteria.andTechnicianEqualTo(equipment.getTechnician());
        }
        if (StringUtils.isNotBlank(equipment.getPhoneNumber())) {
            criteria.andPhoneNumberEqualTo(equipment.getPhoneNumber());
        }
        // todo 需要排序时打开
//        example.setOrderByClause("UpdateDate DESC");

        PageRowBounds rowBounds = new PageRowBounds(pageQuery.getPageIndex() * pageQuery.getPageSize(), pageQuery.getPageSize());

        List<EquipmentOnlineStatus> equipmentList = equipmentOnlineStatusMapper.selectByExampleWithRowbounds(example, rowBounds);
        pageQuery.setList(equipmentList);
        pageQuery.setTotalCount(rowBounds.getTotal());

        return pageQuery;
    }


}
