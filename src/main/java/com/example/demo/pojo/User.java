package com.example.demo.pojo;

import com.baomidou.mybatisplus.annotation.*;
import com.example.demo.pojo.enumeration.UserType;
import com.fasterxml.jackson.annotation.JsonFormat;
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
    private Integer type;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime createTime;
    /**
     * 最后登陆时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private LocalDateTime lastLoginTime;

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
}
