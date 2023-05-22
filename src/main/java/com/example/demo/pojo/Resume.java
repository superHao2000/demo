package com.example.demo.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.demo.pojo.enumeration.Gender;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 简历信息
 *
 * @author super
 * @date 2023/05/21
 */
@Data
@TableName("resume")
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
    private Gender gender;
    /**
     * 生日
     */
    private LocalDateTime birthdate;
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
    private LocalDateTime createTime;

}
