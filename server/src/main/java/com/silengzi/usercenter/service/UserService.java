package com.silengzi.usercenter.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.silengzi.usercenter.model.domain.User;
import com.silengzi.usercenter.model.result.PageResult;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 *
 */
public interface UserService extends IService<User> {
    /**
     * 用户注册
     *
     * @param username       账号
     * @param password      密码
     * @param checkPassword     校验密码
     * @return  新用户id
     */
    long userRegister(String username, String password, String checkPassword);

    /**
     * 用户登录
     *
     * @param username       账号
     * @param password      密码
     * @param request
     * @return  脱敏后的用户信息
     */
    User userLogin(String username, String password, HttpServletRequest request);

    /**
     * 用户信息脱敏
     *
     * @param originUser
     * @return  脱敏后的用户信息
     */
    User getSafetyUser(User originUser);

    /**
     * 用户注销
     *
     * @param request
     * @return
     */
    int userLogout(HttpServletRequest request);

    /**
     * 查询用户列表
     *
     * @param username  用户名
     * @param request
     * @return  用户列表
     */
    PageResult<User> userList(String username, int page, int size, HttpServletRequest request);

    /**
     * 获取用户详情
     *
     * @param id
     * @param request
     * @return
     */
    User userDetail(Long id, HttpServletRequest request);

    /**
     * 删除用户
     *
     * @param id
     * @param request
     * @return
     */
    boolean deleteUser(Long id, HttpServletRequest request);

    /**
     * 更新用户信息
     *
     * @param newUser
     * @param request
     * @return
     */
    User updateUser(User newUser, HttpServletRequest request);
}
