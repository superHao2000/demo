package com.example.demo.pojo;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 招聘信息表
 *
 * @author super
 * @date 2023/05/21
 */
@Data
@TableName("recruitment")
public class Recruitment {
    /**
     * 招聘信息编号
     */
    @TableId(type = IdType.AUTO)
    private Integer recruitmentId;
    /**
     * 企业编号
     */
    private Integer eid;
    /**
     * 招聘岗位
     */
    private String position;
    /**
     * 招聘人数
     */
    private Integer num;
    /**
     * 工作地点
     */
    private String location;
    /**
     * 学历要求
     */
    private String education;
    /**
     * 经验要求
     */
    private String major;
    /**
     * 薪资水平
     */
    private String salary;
    /**
     * 发布时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime createTime;
    /**
     * 投递人数
     */
    private int deliveryNum;
}
