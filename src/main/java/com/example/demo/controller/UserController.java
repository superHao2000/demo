package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.CommonResult;
import com.example.demo.pojo.Admin;
import com.example.demo.pojo.Student;
import com.example.demo.pojo.User;
import com.example.demo.service.AdminService;
import com.example.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.nio.channels.Pipe;

/**
 * 用户控制器
 *
 * @author super
 * @date 2023/05/21
 */
@RestController
@RequestMapping("/employment")
@Slf4j
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    AdminService adminService;

    @RequestMapping("login")
    public CommonResult<Object> login(User user) {
        log.info("用户{}试图登录", user.getUserName());
        // 查询用户是否存在
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName, user.getUserName());
        User emp = userService.getOne(queryWrapper);
        if (emp == null) {
            return CommonResult.generateFailureResult("账号不存在", 1, null);
        }
        if (!emp.getPassword().equals(user.getPassword())) {
            return CommonResult.generateFailureResult("密码错误", 1, null);
        }
        // 登录者详细数据
        // switch (emp.getType()) {
        //     case 0:
        //         LambdaQueryWrapper<Admin> queryWrapper1 = new LambdaQueryWrapper<>();
        //         queryWrapper1.eq(Admin::getUserId, emp.getUserId());
        //         Admin admin = adminService.getOne(queryWrapper1);
        //         return CommonResult.generateSuccessResult(1, admin);
        //     case 1:
        //         break;
        //     case 2:
        //         break;
        //     case 3:
        //         break;
        // }
        return CommonResult.generateSuccessResult(1, emp);
    }

    @GetMapping("/getAllUser")
    public CommonResult<IPage<User>> getAllStudent(Student student, @RequestParam("limit") int pageSize,
                                                   @RequestParam("page") int pageNum) {
        Page<User> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<Student> qw = new LambdaQueryWrapper<>();
        Page<User> page1 = userService.page(page);
        return CommonResult.generateSuccessResult(page1.getSize(), page1);
    }
}
