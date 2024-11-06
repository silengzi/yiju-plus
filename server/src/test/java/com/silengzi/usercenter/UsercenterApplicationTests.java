package com.silengzi.usercenter;

import com.silengzi.usercenter.mapper.UserMapper;
import com.silengzi.usercenter.model.domain.Project;
import com.silengzi.usercenter.model.domain.User;
import com.silengzi.usercenter.model.dto.ProjectSearchCriteria;
import com.silengzi.usercenter.model.result.PageResult;
import com.silengzi.usercenter.service.ProjectService;
import com.silengzi.usercenter.service.UserService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class UsercenterApplicationTests {

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserService userService;

    @Resource
    private ProjectService projectService;

    @Test
    void contextLoads() {
    }

    @Test
    void testUser() {
        System.out.println("-----------------test-------------------");
        List<User> userList = userMapper.selectList(null);
        Assert.assertEquals(5, userList.size());
        userList.forEach(System.out::println);
    }

    @Test
    void testRegister() {
        long userId = userService.userRegister("silengzi", "12345678", "12345678");
        Assert.assertEquals(1, userId);
    }

    @Test
    void testProject() {
        ProjectSearchCriteria projectSearchCriteria = new ProjectSearchCriteria();
//        projectSearchCriteria.setXzqhCode("140000000000");
//        projectSearchCriteria.setHousetype("写字楼");
//        projectSearchCriteria.setProjname("恒大时代广场");
        projectSearchCriteria.setMinPrice("10000");
        projectSearchCriteria.setMaxPrice("15000");
        PageResult<Project> projectList = projectService.projectList(projectSearchCriteria, 1, 10);
        System.out.println("===projectList===");
        System.out.println(projectList.getTotal());
        for (Project project : projectList.getRecords()) {
            System.out.println(project);
        }
    }
}