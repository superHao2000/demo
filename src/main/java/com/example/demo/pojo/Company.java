package com.example.demo.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.demo.pojo.enumeration.Audit;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 企业信息表
 *
 * @author super
 * @date 2023/05/21
 */
@Data
@TableName("company")
public class Company {
    /**
     * 企业id
     */
    @TableId(type = IdType.AUTO)
    private Integer eid;
    /**
     * 用户表id
     */
    private Integer userid;
    /**
     * 企业名称
     */
    private String eName;
    /**
     * 企业性质
     */
    private String nature;
    /**
     * 注册地址
     */
    private String address;
    /**
     * 联系电话
     */
    private String tel;
    /**
     * 邮政编码
     */
    private String zipcode;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 网址
     */
    private String website;
    /**
     * 企业简介
     */
    private String profile;
    /**
     * 审核信息
     */
    private Audit auditStatus;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}
