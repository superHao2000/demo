package com.example.demo.utlis;

import com.example.demo.pojo.User;
import lombok.Data;

/**
 * @author super
 * @date 2023/05/24
 */
public class BaseContext {
    private static User user;

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        BaseContext.user = user;
    }
}
