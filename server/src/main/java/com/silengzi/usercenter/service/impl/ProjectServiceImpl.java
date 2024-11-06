package com.silengzi.usercenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.silengzi.usercenter.common.ErrorCode;
import com.silengzi.usercenter.exception.BusinessException;
import com.silengzi.usercenter.model.domain.Project;
import com.silengzi.usercenter.model.domain.User;
import com.silengzi.usercenter.model.dto.ProjectSearchCriteria;
import com.silengzi.usercenter.model.result.PageResult;
import com.silengzi.usercenter.service.ProjectService;
import com.silengzi.usercenter.mapper.ProjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 */
@Service
public class ProjectServiceImpl extends ServiceImpl<ProjectMapper, Project>
    implements ProjectService{

    @Override
    public PageResult<Project> projectList(ProjectSearchCriteria criteria, int page, int size) {
        QueryWrapper<Project> queryWrapper = new QueryWrapper<>();
        if(criteria != null) {
            // 行政区过滤
            if (StringUtils.isNotBlank(criteria.getXzqhCode())) {
                // 前两位表示省，前四位表示地市，前六位表示区县
                // 110114000000
                int level = calRegionLev(criteria.getXzqhCode()); // 行政区级别

                // 1 如果是省份，需要拿参数的前两位跟数据库中编码的前两位作比较
                // 2 如果是地市，需要拿参数的前四位跟数据库中编码的前四位作比较
                // 3 如果是区县，直接比较
                if(level == 1) {
                    // 省份级别，前两位比较
                    String provinceCode = criteria.getXzqhCode().substring(0, 2);
                    queryWrapper.likeRight("xzqhCode", provinceCode);  // 使用 likeRight 只匹配前缀
                } else if(level == 2) {
                    // 地市级别，前四位比较
                    String cityCode = criteria.getXzqhCode().substring(0, 4);
                    queryWrapper.likeRight("xzqhCode", cityCode);  // 使用 likeRight 只匹配前缀
                } else if(level == 3) {
                    queryWrapper.like("xzqhCode", criteria.getXzqhCode());
                }
            }
            // 最低价格区间
            if (StringUtils.isNotBlank(criteria.getMinPrice())) {
                queryWrapper.ge("CAST(prices AS DECIMAL)", criteria.getMinPrice()); // 大于等于
            }
            // 最高价格区间
            if (StringUtils.isNotBlank(criteria.getMaxPrice())) {
                queryWrapper.le("CAST(prices AS DECIMAL)", criteria.getMaxPrice()); // 小于等于
            }
            // 房屋类型
            if (StringUtils.isNotBlank(criteria.getHousetype())) {
                queryWrapper.like("housetype", criteria.getHousetype());
            }
            // 楼盘名称
            if (StringUtils.isNotBlank(criteria.getProjname())) {
                queryWrapper.like("projname", criteria.getProjname());
            }
        }

//        List<Project> projectlist = this.list(queryWrapper);
        IPage<Project> pageParam = new Page<>(page, size);
        IPage<Project> projectIPage = this.page(pageParam, queryWrapper);
        long total = projectIPage.getTotal();
        List<Project> projectlist = projectIPage.getRecords();
        return new PageResult<Project>(projectlist, total);
    }

    @Override
    public Project projectDetail(Long id) {
        if(id == null || id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "id 不能为空且必须大于0");
        }
        Project project = this.getById(id);
        return project;
    }

    @Override
    public Project projectUpdate(Project newProject) {
        if(newProject == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数不能为空");
        }
        boolean result = this.updateById(newProject);
        if(!result) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "修改失败");
        }
        Project project = this.projectDetail(newProject.getId());
        return project;
    }

    @Override
    public Long projectAdd(Project project) {
        if(project == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数不能为空");
        }

        boolean saveResult = this.save(project);
        if(!saveResult) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "插入失败");
        }

        return project.getId();
    }

    @Override
    public Boolean projectDelete(Long id) {
        if(id == null || id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "id 不能为空且必须大于0");
        }
        Boolean result = this.removeById(id);
        return result;
    }

    /**
     * 计算行政区级别（省：1 市：2 县：3）
     */
    public int calRegionLev(String xzqhCode) {
        // 检查行政区划代码的长度，至少为6位
        if (xzqhCode == null || xzqhCode.length() < 6) {
            throw new IllegalArgumentException("行政区划代码长度不足6位");
        }

        // 3 - 6 位是否都为 0    省份
        String subStr1 = xzqhCode.substring(2, 6);
        if ("0000".equals(subStr1)) {
            return 1;
        }

        // 5 - 6 位是否都为 0    地市
        String subStr2 = xzqhCode.substring(4, 6); // 索引从0开始，第五位是索引4，第六位是索引5
        if ("00".equals(subStr2)) {
            return 2;
        }

        // 区县
        return 3;
    }
}
