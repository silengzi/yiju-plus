package com.silengzi.usercenter.service;

import com.silengzi.usercenter.model.domain.Project;
import com.baomidou.mybatisplus.extension.service.IService;
import com.silengzi.usercenter.model.dto.ProjectSearchCriteria;
import com.silengzi.usercenter.model.result.PageResult;
import com.sun.org.apache.xpath.internal.operations.Bool;

import javax.servlet.http.HttpServletRequest;

/**
 * 楼盘模块
 */
public interface ProjectService extends IService<Project> {
    /**
     * 查询楼盘列表
     *
     * @param criteria  楼盘搜索条件
     * @return  楼盘列表
     */
    PageResult<Project> projectList(ProjectSearchCriteria criteria, int page, int size);

    /**
     * 查询详情
     *
     * @param projectId id
     * @return  楼盘详情
     */
    Project projectDetail(Long projectId);

    /**
     * 修改
     */
    Project projectUpdate(Project newProject);

    /**
     * 新增
     */
    Long projectAdd(Project project);

    /**
     * 删除
     */
    Boolean projectDelete(Long id);
}
