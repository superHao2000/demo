package com.example.demo.common;

/**
 * 状态码枚举类
 *
 * @author super
 * @date 2023/05/08
 */
public enum ResultCode {

    /**
     * 成功的实例，为了兼容lay-ui table 写成0，最好是200
     */
    SUCCESS(0, "成功"),
    /**
     * 失败的实例
     */
    FAILURE(500, "失败");

    private final long code;

    private final String msg;

    /**
     * 枚举类必须先定义枚举实例在定义方法，且构造函数必须私有
     */
    ResultCode(long code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public long getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
