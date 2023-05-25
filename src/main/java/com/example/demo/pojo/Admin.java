package com.example.demo.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("admin")
public class Admin implements Serializable {

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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
