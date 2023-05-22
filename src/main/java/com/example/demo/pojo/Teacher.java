package com.example.demo.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.demo.pojo.enumeration.Gender;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * 教师信息表
 *
 * @author super
 * @date 2023/05/21
 */
@Data
@TableName("teacher")
public class Teacher {
    /**
     * 编号
     */
    @TableId(type = IdType.AUTO)
    private Integer tid;
    /**
     * 用户表id
     */
    private Integer userid;
    /**
     * 姓名
     */
    private String tName;
    /**
     * 性别
     */
    private Gender gender;
    /**
     * 院系
     */
    private String department;
    /**
     * 职称
     */
    private String titLe;
    /**
     * 联系电话
     */
    private String tel;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}
