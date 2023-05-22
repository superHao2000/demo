package com.example.demo.pojo.enumeration;

/**
 * 性别
 *
 * @author super
 * @date 2023/05/21
 */
public enum Gender {
    Man(0, "男"),
    Woman(1, "女");
    private final int type;
    private final String sex;

    Gender(int type, String gender) {
        this.type = type;
        this.sex = gender;
    }
}
