package com.schindler.ioee.tm_service.controller;

import com.schindler.ioee.gdcsv3.common.model.pojo.list.PageQuery;
import com.schindler.ioee.tm_service.mybatis.model.User;
import com.schindler.ioee.tm_service.response.RestErrorResponse;
import com.schindler.ioee.tm_service.response.RestResponseEnum;
import com.schindler.ioee.tm_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * @author weihao2
 */
@RestController
public class UserController {





    @Autowired
    private UserService userService;

    @PutMapping("/v1/user")
    public Object insert(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) {
        if (userService.insert(user)) {
            response.setStatus(RestResponseEnum.SUCCESS.getCode());
            return user;
        }
        RestResponseEnum userError = RestResponseEnum.USER_ERROR;
        return RestErrorResponse.build().setErrorCode(userError.getCode()).setMessage(userError.getMsg()).getResult();
    }

    @DeleteMapping("/v1/user")
    public Object delete(@RequestParam("username") String username, HttpServletRequest request, HttpServletResponse response) {
        if (userService.deleteByName(username)) {
            response.setStatus(RestResponseEnum.SUCCESS.getCode());
            return username;
        }
        RestResponseEnum userError = RestResponseEnum.USER_ERROR;
        return RestErrorResponse.build().setErrorCode(userError.getCode()).setMessage(userError.getMsg()).getResult();
    }

    @PostMapping("/v1/user")
    public Object edit(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) {
        if (userService.update(user)) {
            response.setStatus(RestResponseEnum.SUCCESS.getCode());
            return user;
        }
        RestResponseEnum userError = RestResponseEnum.USER_ERROR;
        return RestErrorResponse.build().setErrorCode(userError.getCode()).setMessage(userError.getMsg()).getResult();
    }

    @GetMapping("/v1/user")
    public Object getUser(@RequestParam("search") String search, @RequestParam("pageIndex") Integer pageIndex, @RequestParam("pageSize") Integer pageSize, HttpServletRequest request, HttpServletResponse response) {
        Optional<PageQuery> userOptional = userService.query(search, pageIndex, pageSize);
        if (userOptional.isPresent()) {
            response.setStatus(RestResponseEnum.SUCCESS.getCode());
            return userOptional;
        }
        RestResponseEnum userError = RestResponseEnum.USER_ERROR;
        return RestErrorResponse.build().setErrorCode(userError.getCode()).setMessage(userError.getMsg()).getResult();
    }


}
