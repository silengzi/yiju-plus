package com.silengzi.usercenter.model.dto;

import lombok.Data;

// 租房查询条件
@Data
public class RenthouseSearchCriteria {
//    private String xzqhCode; // 县级行政区编码
//    private String projname; // 楼盘名称

    private String type; // 户型
    private String floor; // 楼层
    private String direction; // 朝向
    private String decoration; // 装修
    private String mode; // 出租方式
    private String minPrice; // 最低价格区间
    private String maxPrice; // 最高价格区间
}
