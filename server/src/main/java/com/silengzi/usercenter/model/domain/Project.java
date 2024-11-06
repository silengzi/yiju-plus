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
 * @TableName project
 */
@TableName(value ="project")
@Data
public class Project implements Serializable {
    /**
     * 楼盘编号
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 
     */
    private String provice;

    /**
     * 
     */
    private String city;

    /**
     * 
     */
    private String area;

    /**
     * 行政区划编码
     */
    private String xzqhCode;

    /**
     * 价格范围
     */
    private String prices;

    /**
     * 房屋类型
     */
    private String housetype;

    /**
     * 售楼地址
     */
    private String selladdr;

    /**
     * 房屋套数
     */
    private String housenum;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 项目名称
     */
    private String projname;

    /**
     * 项目地址
     */
    private String projaddr;

    /**
     * 开发商
     */
    private String devpeople;

    /**
     * 效果图
     */
    private String picture;

    /**
     * 点
     */
    private Integer the_geom;

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

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", provice='" + provice + '\'' +
                ", city='" + city + '\'' +
                ", area='" + area + '\'' +
                ", xzqhCode='" + xzqhCode + '\'' +
                ", prices='" + prices + '\'' +
                ", housetype='" + housetype + '\'' +
                ", selladdr='" + selladdr + '\'' +
                ", housenum='" + housenum + '\'' +
                ", phone='" + phone + '\'' +
                ", projname='" + projname + '\'' +
                ", projaddr='" + projaddr + '\'' +
                ", devpeople='" + devpeople + '\'' +
                ", picture='" + picture + '\'' +
                ", the_geom=" + the_geom +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", isDelete=" + isDelete +
                '}';
    }
}