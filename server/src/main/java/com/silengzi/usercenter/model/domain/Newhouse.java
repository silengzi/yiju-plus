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
 * @TableName newhouse
 */
@TableName(value ="newhouse")
@Data
public class Newhouse implements Serializable {
    /**
     * 房源编号
     */
    @TableId(value = "hid", type = IdType.AUTO)
    private Long hid;

    /**
     * 楼盘编号
     */
    private String pid;

    /**
     * 户型
     */
    private String type;

    /**
     * 建筑面积
     */
    private String harea;

    /**
     * 楼层
     */
    private String floor;

    /**
     * 价格
     */
    private String price;

    /**
     * 效果图
     */
    private String hpicture;

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