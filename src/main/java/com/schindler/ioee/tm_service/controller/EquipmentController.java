package com.schindler.ioee.tm_service.controller;

import com.schindler.ioee.gdcsv3.common.model.pojo.list.PageQuery;
import com.schindler.ioee.tm_service.mybatis.model.EquipmentOnlineStatus;
import com.schindler.ioee.tm_service.response.RestErrorResponse;
import com.schindler.ioee.tm_service.response.RestResponseEnum;
import com.schindler.ioee.tm_service.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * @author Jensen
 */
@RestController
public class EquipmentController {

    @Autowired
    private EquipmentService equipmentService;

    @PutMapping("/v1/equipment")
    public Object addEquipment(HttpServletRequest request, HttpServletResponse response, @RequestBody EquipmentOnlineStatus equipment) {
        boolean isSuccess = equipmentService.insert(equipment);
        if (isSuccess) {
            response.setStatus(RestResponseEnum.SUCCESS.getCode());
            return equipment;
        }
        return RestErrorResponse.build().setErrorCode(RestResponseEnum.EQUIPMENT_ERROR.getCode()).setMessage(RestResponseEnum.EQUIPMENT_ERROR.getMsg()).getResult();
    }

    @DeleteMapping("/v1/equipment")
    public Object deleteEquipment(HttpServletRequest request, HttpServletResponse response, @RequestParam("equipmentId") String equipmentId) {
        boolean isSuccess = equipmentService.delete(equipmentId);
        if (isSuccess) {
            response.setStatus(RestResponseEnum.SUCCESS.getCode());
            return equipmentId;
        }
        return RestErrorResponse.build().setErrorCode(RestResponseEnum.EQUIPMENT_ERROR.getCode()).setMessage(RestResponseEnum.EQUIPMENT_ERROR.getMsg()).getResult();
    }

    @PostMapping("/v1/equipment")
    public Object updateEquipment(HttpServletRequest request, HttpServletResponse response, @RequestBody EquipmentOnlineStatus equipment) {
        boolean isSuccess = equipmentService.update(equipment);
        if (isSuccess) {
            response.setStatus(RestResponseEnum.SUCCESS.getCode());
            return equipment;
        }
        return RestErrorResponse.build().setErrorCode(RestResponseEnum.EQUIPMENT_ERROR.getCode()).setMessage(RestResponseEnum.EQUIPMENT_ERROR.getMsg()).getResult();
    }

    @GetMapping("/v1/equipment")
    public Object queryEquipmentById(HttpServletRequest request, HttpServletResponse response, @RequestParam("equipmentId") String equipmentId) {
        Optional<EquipmentOnlineStatus> equipmentOnlineStatus = equipmentService.getEquipmentById(equipmentId);
        if (equipmentOnlineStatus.isPresent()) {
            response.setStatus(RestResponseEnum.SUCCESS.getCode());
            return equipmentOnlineStatus;
        }
        return RestErrorResponse.build().setErrorCode(RestResponseEnum.EQUIPMENT_ERROR.getCode()).setMessage(RestResponseEnum.EQUIPMENT_ERROR.getMsg()).getResult();
    }

    @PostMapping("/v1/equipments/query")
    public Object queryEquipment(HttpServletRequest request, HttpServletResponse response, @RequestBody PageQuery<EquipmentOnlineStatus> pageQuery) {
        PageQuery<EquipmentOnlineStatus> queryResult = equipmentService.query(pageQuery);
        response.setStatus(RestResponseEnum.SUCCESS.getCode());
        return queryResult;
    }


}
