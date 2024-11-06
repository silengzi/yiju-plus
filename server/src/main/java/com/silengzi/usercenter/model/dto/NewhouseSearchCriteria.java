package com.silengzi.usercenter.model.dto;

import lombok.Data;

// 新房查询条件
@Data
public class NewhouseSearchCriteria {
//    private String xzqhCode; // 县级行政区编码
//    private String projname; // 楼盘名称

    private String type; // 户型
    private String floor; // 楼层
    private String minPrice; // 最低价格区间
    private String maxPrice; // 最高价格区间
}
