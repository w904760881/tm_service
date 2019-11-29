package com.schindler.ioee.tm_service.controller;

import com.schindler.ioee.tm_service.model.UserBean;
import com.schindler.ioee.tm_service.response.RestErrorResponse;
import com.schindler.ioee.tm_service.response.RestResponseEnum;
import com.schindler.ioee.tm_service.service.LoginService;
import com.schindler.ioee.tm_service.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author weihao2
 */
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    /***
     * 管理员登陆
     * @param userBean
     * @param request
     * @param response
     * @return
     */
    @PostMapping("/v1/admin/login")
    public Object adminLogin(@RequestBody UserBean userBean, HttpServletRequest request, HttpServletResponse response) {
        if (loginService.checkAdminLogin(userBean)) {
            response.setStatus(RestResponseEnum.SUCCESS.getCode());
            request.setAttribute(Constant.CURRENT_USER, userBean);
            return userBean;
        }
        RestResponseEnum adminLoginError = RestResponseEnum.ADMIN_LOGIN_ERROR;
        return RestErrorResponse.build().setErrorCode(adminLoginError.getCode()).setMessage(adminLoginError.getMsg()).getResult();
    }

    /***
     * 普通用户登陆
     * @param userBean
     * @param request
     * @param response
     * @return
     */
    @PostMapping("/v1/login")
    public Object login(@RequestBody UserBean userBean, HttpServletRequest request, HttpServletResponse response) {
        if (loginService.checkLogin(userBean)) {
            response.setStatus(RestResponseEnum.SUCCESS.getCode());
            request.setAttribute(Constant.CURRENT_USER, userBean);
            return userBean;
        }
        RestResponseEnum loginError = RestResponseEnum.LOGIN_ERROR;
        return RestErrorResponse.build().setErrorCode(loginError.getCode()).setMessage(loginError.getMsg()).getResult();
    }

    /***
     * 登出
     * @param request
     * @param response
     * @return
     */
    @PostMapping(value = "/v1/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        if (loginService.getCurrentUser(request.getSession()) != null) {
            request.getSession().invalidate();
            response.setStatus(RestResponseEnum.SUCCESS.getCode());
        }
    }


}
