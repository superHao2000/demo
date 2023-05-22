package com.example.demo.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 就业信息表
 *
 * @author super
 * @date 2023/05/21
 */
@Data
@TableName("employment")
public class Employment {
    /**
     * 就业信息编号
     */
    @TableId(type = IdType.AUTO)
    private Integer jid;
    /**
     * 学生编号
     */
    private Integer sid;
    /**
     * 公司姓名
     */
    private String cname;
    /**
     * 公司性质
     */
    private String nature;
    /**
     * 部门
     */
    private String department;
    /**
     * 岗位
     */
    private String position;
    /**
     * 工作年限
     */
    private String workAge;
    /**
     * 薪资
     */
    private String salary;
    /**
     * 工作情况
     */
    private String situation;
    /**
     * 录入时间
     */
    private LocalDateTime insertTime;
}
