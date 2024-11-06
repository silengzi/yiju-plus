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
 * @TableName renthouse
 */
@TableName(value ="renthouse")
@Data
public class Renthouse implements Serializable {
    /**
     * 租房编号
     */
    @TableId(value = "rid", type = IdType.AUTO)
    private Long rid;

    /**
     * 
     */
    private Long pid;

    /**
     * 展示图
     */
    private String picture;

    /**
     * 价格
     */
    private String price;

    /**
     * 出租方式
     */
    private String mode;

    /**
     * 户型
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
     * 楼层
     */
    private String floor;

    /**
     * 楼层总数
     */
    private String sumfloor;

    /**
     * 装修
     */
    private String decoration;

    /**
     * 小区名称
     */
    private String xiaoqu;

    /**
     * 地址
     */
    private String addr;

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
     * 逻辑删除  0正常 1删除
     */
    private Byte isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}