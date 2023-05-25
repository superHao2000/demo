package com.example.demo.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.*;
import com.example.demo.pojo.enumeration.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * 简历信息
 *
 * @author super
 * @date 2023/05/21
 */
@Data
@TableName("resume")
@Slf4j
public class Resume {
    /**
     * 简历id
     */
    @TableId(type = IdType.AUTO)
    private Integer resumeId;
    /**
     * 学生id
     */
    private Integer sid;
    /**
     * 姓名
     */
    private String name;
    /**
     * 性别
     */
    private Integer gender;
    /**
     * 生日
     */
    // @JSONField(format = "yyyy-MM-dd")
    // @JsonFormat(pattern = "yyyy-MM-dd", locale = "zh", timezone = "GMT+8")
    // @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private String birthdate;
    /**
     * 联系方式
     */
    private String tel;
    /**
     * 联系邮箱
     */
    private String email;
    /**
     * 最高学历
     */
    private String education;
    /**
     * 毕业院校
     */
    private String school;
    /**
     * 专业
     */
    private String major;
    /**
     * 工作经验
     */
    private String experience;
    /**
     * 技能专长
     */
    private String skill;
    /**
     * 自我评价
     */
    private String comment;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime createTime;

}
