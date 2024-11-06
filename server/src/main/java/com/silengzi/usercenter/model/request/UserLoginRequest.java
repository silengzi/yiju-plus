package com.silengzi.usercenter.model.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserLoginRequest implements Serializable {
    private static final long serialVersionUID = 789319750247503859L;

    /**
     * 用户账号
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;
}
