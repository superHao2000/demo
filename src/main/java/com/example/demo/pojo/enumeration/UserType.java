package com.example.demo.pojo.enumeration;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 用户类型枚举类
 *
 * @author super
 * @date 2023/05/21
 */
public enum UserType {
    Admin(0, "管理员"),
    Student(1, "学生"),
    Teacher(2, "老师"),
    Enterprise(3, "企业");
    @EnumValue
    private final int type;
    @JsonValue
    private final String name;

    UserType(int type, String name) {
        this.type = type;
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public String getName() {
        return name;
    }
}
