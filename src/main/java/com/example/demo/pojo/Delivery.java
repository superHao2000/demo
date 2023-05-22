package com.example.demo.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.demo.pojo.enumeration.Audit;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author super
 * @date 2023/05/21
 */
@Data
@TableName("delivery")
public class Delivery {

    /**
     * 投递编号
     */
    @TableId(type = IdType.AUTO)
    private Integer tid;
    /**
     * 学生编号
     */
    private Integer sid;
    /**
     * 招聘编号
     */
    private Integer recruitmentId;
    /**
     * 简历编号
     */
    private Integer resumeId;
    /**
     * 投递时间
     */
    private LocalDateTime deliveryTime;
    /**
     * 审核状态
     */
    private Audit auditStatus;
}
