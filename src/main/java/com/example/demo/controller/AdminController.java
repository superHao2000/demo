package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.CommonResult;
import com.example.demo.pojo.Admin;
import com.example.demo.pojo.User;
import com.example.demo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 管理员控制器
 *
 * @author super
 * @date 2023/05/22
 */
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AdminService adminService;

    /**
     * 获取所有管理员信息
     *
     * @return {@link CommonResult}<{@link Object}>
     */
    @GetMapping("/getAllAdmin")
    public CommonResult<Object> getAllAdmin() {
        Page<Admin> page=new Page<>();
        adminService.list();
        return CommonResult.generateSuccessResult(1, null);
    }

    @PostMapping("getOne")
    public CommonResult<Object> getOne(User user) {
        LambdaQueryWrapper<Admin> qw = new LambdaQueryWrapper<>();
        qw.eq(Admin::getUserId, user.getUserId());
        Admin one = adminService.getOne(qw);
        return CommonResult.generateSuccessResult(1, one);
    }
}
