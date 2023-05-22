package com.example.demo.pojo;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.demo.pojo.enumeration.UserType;
import lombok.Data;
import lombok.Value;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 用户表
 *
 * @author super
 * @date 2023/05/21
 */
@Data
@TableName("user")
public class User {
    /**
     * 用户编号
     */
    @TableId(type = IdType.AUTO)
    private Integer userId;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 密码
     */
    private String password;
    /**
     * 用户类型
     */
    private int type;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 最后登陆时间
     */
    private LocalDateTime lastLoginTime;
}
