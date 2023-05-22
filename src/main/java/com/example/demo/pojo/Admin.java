package com.example.demo.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("admin")
public class Admin {

    /**
     * 管理员id
     */
    @TableId(type = IdType.AUTO)
    private Integer aid;
    /**
     * 用户表id
     */
    private Integer userId;
    /**
     * 名称
     */
    private String aName;
    /**
     * 联系电话
     */
    private String tel;
    /**
     * 联系邮箱
     */
    private String email;
}
