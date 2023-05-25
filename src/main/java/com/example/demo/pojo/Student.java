package com.example.demo.pojo;

import com.baomidou.mybatisplus.annotation.*;
import com.example.demo.pojo.enumeration.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * 学生信息表
 *
 * @author super
 * @date 2023/05/21
 */
@Data
@TableName("student")
public class Student {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer sid;
    /**
     * 用户表对应id
     */
    private Integer userid;
    /**
     * 教师表对应id
     */
    private Integer tid;
    /**
     * 学号
     */
    private String sno;
    /**
     * 姓名
     */
    private String sName;
    /**
     * 性别
     */
    private String gender;

    private String birther;
    /**
     * 院系
     */
    private String department;
    /**
     * 专业
     */
    private String major;
    /**
     * 学年
     */
    private String grade;
    /**
     * 联系电话
     */
    private String phone;
    /**
     * 联系地址
     */
    private String address;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime createTime;
}
