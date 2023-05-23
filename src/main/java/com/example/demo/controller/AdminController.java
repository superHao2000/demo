package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.CommonResult;
import com.example.demo.pojo.Admin;
import com.example.demo.pojo.User;
import com.example.demo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 管理员控制器
 *
 * @author super
 * @date 2023/05/22
 */
@CrossOrigin
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
        adminService.page(page,null);
        return CommonResult.generateSuccessResult(1, page);
    }

    @PostMapping("getOne")
    public CommonResult<Object> getOne(User user) {
        LambdaQueryWrapper<Admin> qw = new LambdaQueryWrapper<>();
        qw.eq(Admin::getUserId, user.getUserId());
        Admin one = adminService.getOne(qw);
        return CommonResult.generateSuccessResult(1, one);
    }
}
