package com.silengzi.usercenter.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName oldhouse
 */
@TableName(value ="oldhouse")
@Data
public class Oldhouse implements Serializable {
    /**
     * 二手房编号
     */
    @TableId(value = "oid",type = IdType.AUTO)
    private Long oid;

    /**
     * 所在小区id
     */
    private Long pid;

    /**
     * 卖点
     */
    private String sellpoint;

    /**
     * 效果图
     */
    private String picture;

    /**
     * 价格
     */
    private String price;

    /**
     * 室厅卫
     */
    private String type;

    /**
     * 面积
     */
    private String narea;

    /**
     * 朝向
     */
    private String direction;

    /**
     * 小区
     */
    private String xiaoqu;

    /**
     * 楼层
     */
    private String floor;

    /**
     * 建筑年代
     */
    private String year;

    /**
     * 省
     */
    private String provice;

    /**
     * 市
     */
    private String city;

    /**
     * 区
     */
    private String area;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 逻辑删除  0 正常  1 删除
     */
    private Byte isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}