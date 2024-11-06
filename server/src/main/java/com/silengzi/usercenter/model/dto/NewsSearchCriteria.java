package com.silengzi.usercenter.model.dto;

import lombok.Data;

// 快讯查询条件
@Data
public class NewsSearchCriteria {
    private String text; // 内容（包含标题、摘要、正文）
    private String source; // 来源
}
