package com.example.demo.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
    private LocalDateTime releaseTime;

    /**
     * 投递人数
     */
    private int deliveryNum;
}
