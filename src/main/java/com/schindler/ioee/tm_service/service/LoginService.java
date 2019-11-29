package com.schindler.ioee.tm_service.service;

import com.schindler.ioee.tm_service.model.UserBean;
import com.schindler.ioee.tm_service.mybatis.model.User;
import com.schindler.ioee.tm_service.util.Constant;
import com.schindler.ioee.tm_service.util.Md5EncryptionUtil;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Optional;


/**
 * @author weihao2
 */
@Service
@Slf4j
public class LoginService {

    @Autowired
    private UserService userService;

    @Value("${tm.service.admin.username}")
    String adminUsername;

    @Value("${tm.service.admin.password}")
    String adminPassword;

    public boolean checkLogin(UserBean userBean) {
        log.info("receive userBean is {}", userBean);
        Optional<User> userOptional = userService.selectByPrimaryKey(userBean.getUserName());
        if (!userOptional.isPresent()) {
            return false;
        } else if (!Md5EncryptionUtil.encrypt(userBean.getPassword()).equals(userOptional.get().getPassword())) {
            return false;
        } else {
            return true;
        }
    }

    public boolean checkAdminLogin(UserBean userBean) {
        log.info("receive userBean is {}", userBean);
        if (!adminUsername.equals(userBean.getUserName())) {
            return false;
        } else if (!adminPassword.equals(userBean.getPassword())) {
            return false;
        } else {
            return true;
        }
    }


    public UserBean getCurrentUser(HttpSession session) {
        UserBean userBean = (UserBean) session.getAttribute(Constant.CURRENT_USER);
        return userBean;
    }


}
