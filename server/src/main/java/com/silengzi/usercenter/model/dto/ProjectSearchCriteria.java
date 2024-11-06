package com.silengzi.usercenter.model.dto;

import lombok.Data;

// 楼盘查询条件
@Data
public class ProjectSearchCriteria {
    private String xzqhCode; // 县级行政区编码
    private String minPrice; // 最低价格区间
    private String maxPrice; // 最高价格区间
    private String housetype; // 房屋类型
    private String projname; // 楼盘名称
}
