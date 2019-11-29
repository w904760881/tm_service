package com.schindler.ioee.tm_service.model;

import com.schindler.ioee.tm_service.mybatis.model.User;
import lombok.Data;

@Data
public class UserBean {

    /***
     * 用户登录名
     */
    private String userName;
    /***
     * 密码
     */
    private String password;
    /***
     * 新密码
     */
    private String newPassword;
    /***
     *昵称
     */
    private String nickName;
    /***
     *邮箱
     */
    private String email;
    /***
     * 验证登陆返回信息
     */
    private String resultMsg = "";
    /***
     * 登录时间
     */
    private Long loginTime;
    /***
     * 用户登录错误类型
     * 0 无误  1用户名有误  2密码有误
     */
    private Integer errorType;

    public UserBean() {

    }

    public UserBean(User user) {
        userName = user.getUserName();
        password = user.getPassword();
        newPassword = "";
        nickName = user.getNickname();
        email = user.getEmail();
        loginTime = System.currentTimeMillis();
    }

}
