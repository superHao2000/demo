package com.example.demo.pojo.enumeration;

/**
 * 审核枚举类
 *
 * @author super
 * @date 2023/05/21
 */
public enum Audit {
    Pending(0, "未审核"),
    Approved(1, "审核通过"),
    Rejected(2, "审核未通过");
    private final int type;
    private final String name;

    Audit(int type, String name) {
        this.type = type;
        this.name = name;
    }


}
