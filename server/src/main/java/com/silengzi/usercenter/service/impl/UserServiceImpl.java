package com.silengzi.usercenter.service.impl;
import java.util.Date;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.silengzi.usercenter.common.ErrorCode;
import com.silengzi.usercenter.exception.BusinessException;
import com.silengzi.usercenter.mapper.UserMapper;
import com.silengzi.usercenter.model.domain.User;
import com.silengzi.usercenter.model.result.PageResult;
import com.silengzi.usercenter.service.UserService;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.silengzi.usercenter.contant.UserConstant.USER_LOGIN_STATE;

/**
 *
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserMapper userMapper;

    private static final String SALT = "silengzi";

    /**
     * 新用户注册
     *
     * @param username       账号
     * @param password      密码
     * @param checkPassword     校验密码
     * @return 新用户 ID
     */
    @Override
    public long userRegister(String username, String password, String checkPassword) {
        // 校验参数是否非空
        if (StringUtils.isAnyBlank(username, password, checkPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数不能为空");
        }

        // 账户长度不小于4位
        if(username.length() < 4) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "账户长度不小于4位");
        }

        // 密码长度不小于八位
        if(password.length() < 8) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "密码长度不小于八位");
        }

        // 账户不能包含特殊字符
        String validPattern = "[`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Matcher matcher = Pattern.compile(validPattern).matcher(username);
        if (matcher.find()) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "账户不能包含特殊字符");
        }

        // 密码和校验密码必须相同
        if(!password.equals(checkPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "密码和校验密码必须相同");
        }

        // 账户不能重复
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        long count = userMapper.selectCount(queryWrapper);
        if(count > 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "账户不能重复");
        }

        // 加密
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + password).getBytes());
        // 插入数据
        User user = new User();
        user.setUsername(username);
        user.setPassword(encryptPassword);
        boolean saveResult = this.save(user);
        if(!saveResult) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "插入失败");
        }

        return user.getId();
    }

    /**
     * 用户登录
     *
     * @param username       账号
     * @param password      密码
     * @param request
     * @return 脱敏后的用户信息
     */
    @Override
    public User userLogin(String username, String password, HttpServletRequest request) {
        // 1. 校验
        // 校验参数是否为空
        if(StringUtils.isAnyBlank(username, password)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数不能为空");
        }

        // 账号长度不能小于四位
        if(username.length() < 4) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "账号长度不能小于四位");
        }

        // 密码长度不能小于8位
        if(password.length() < 8) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "密码长度不能小于8位");
        }

        // 账户不能包含特殊字符
        String validPattern = "[`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Matcher matcher = Pattern.compile(validPattern).matcher(username);
        if (matcher.find()) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "账户不能包含特殊字符");
        }

        // 2. 加密
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + password).getBytes());
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        queryWrapper.eq("password", encryptPassword);
        User user = userMapper.selectOne(queryWrapper);
        // 如果账号或密码不匹配
        if(user == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "账号或密码不匹配");
        }

        // 3. 用户信息脱敏
        User safetyUser = getSafetyUser(user);

        // 4. 记录用户的登录态
        request.getSession().setAttribute(USER_LOGIN_STATE, safetyUser);

        return safetyUser;
    }

    /**
     * 用户信息脱敏
     *
     * @param originUser
     * @return
     */
    @Override
    public User getSafetyUser(User originUser) {
        if(originUser == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数不能为空");
        }

        User safetyUser = new User();
        safetyUser.setId(originUser.getId());
        safetyUser.setEmail(originUser.getEmail());
        safetyUser.setUsername(originUser.getUsername());
        // safetyUser.setPassword("");
        safetyUser.setSex(originUser.getSex());
        safetyUser.setPhone(originUser.getPhone());
        safetyUser.setIdentification(originUser.getIdentification());
        safetyUser.setAddr(originUser.getAddr());
        safetyUser.setRealname(originUser.getRealname());
        safetyUser.setManager(originUser.getManager());
        safetyUser.setUserRole(originUser.getUserRole());
        safetyUser.setCreateTime(originUser.getCreateTime());
        safetyUser.setUpdateTime(originUser.getUpdateTime());

        return safetyUser;
    }

    /**
     * 用户注销
     *
     * @param request
     * @return
     */
    @Override
    public int userLogout(HttpServletRequest request) {
        // 移除登录态
        request.getSession().removeAttribute(USER_LOGIN_STATE);
        return 1;
    }

    /**
     * 查询用户列表
     *
     * @param username  用户名
     * @param request
     * @return
     */
    @Override
    public PageResult<User> userList(String username, int page, int size, HttpServletRequest request) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if(StringUtils.isNotBlank(username)) {
            queryWrapper.like("username", username);
        }
        // List<User> userlist = this.list(queryWrapper);
        IPage<User> pageParam = new Page<>(page, size);
        IPage<User> userIPage = this.page(pageParam, queryWrapper);
        long total = userIPage.getTotal();
        List<User> userlist = userIPage.getRecords();
        List<User> safetyUserList = userlist.stream().map(item -> getSafetyUser(item)).collect(Collectors.toList());
        return new PageResult<User>(safetyUserList, total);
    }

    /**
     * 查询用户详情
     *
     * @param id
     * @param request
     * @return
     */
    @Override
    public User userDetail(Long id, HttpServletRequest request) {
        if(id == null || id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "id 不能为空且必须大于0");
        }
        User user = this.getById(id);
        User safetyUser = getSafetyUser(user);
        return safetyUser;
    }

    /**
     * 删除用户
     *
     * @param id
     * @param request
     * @return
     */
    @Override
    public boolean deleteUser(Long id, HttpServletRequest request) {
        if(id == null || id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "id 不能为空且必须大于0");
        }
        boolean result = this.removeById(id);
        return result;
    }

    /**
     * 更新用户信息
     *
     * @param newUser
     * @param request
     * @return
     */
    @Override
    public User updateUser(User newUser, HttpServletRequest request) {
        if(newUser == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数不能为空");
        }
        boolean result = this.updateById(newUser);
        if(!result) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "修改失败");
        }
        User user = this.userDetail(newUser.getId(), request);
        User safetyUser = getSafetyUser(user);
        return safetyUser;
    }
}
