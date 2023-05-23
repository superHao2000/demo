package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.CommonResult;
import com.example.demo.common.R;
import com.example.demo.pojo.Admin;
import com.example.demo.pojo.User;
import com.example.demo.service.AdminService;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AdminService adminService;

    /**
     * TODO 获取所有管理员信息
     *
     * @return {@link CommonResult}<{@link Object}>
     */
    @GetMapping("/getAllAdmin/{pageNum}/{pageSize}")
    public R getAllAdmin(
            @PathVariable("pageNum") Integer pageNum,
            @PathVariable("pageSize") Integer pageSize) {
        log.info("pageNum: {},pageSize: {}", pageNum, pageSize);
        // 通过服务层查询
        Page<Admin> page = new Page<>(pageNum, pageSize);
        IPage info = adminService.page(page);
        log.info("page: {}", page);
        return new R(200, "获取分页数据", info);
    }

    /**
     * 根据user表id获取管理员信息
     *
     * @param user
     * @return {@link CommonResult}<{@link Admin}>
     */
    @PostMapping("/getOne")
    public CommonResult<Admin> getOne(User user) {
        LambdaQueryWrapper<Admin> qw = new LambdaQueryWrapper<>();
        qw.eq(Admin::getUserId, user.getUserId());
        Admin one = adminService.getOne(qw);
        return CommonResult.generateSuccessResult(1, one);
    }

    /**
     * 根据管理员id修改管理员信息
     *
     * @param admin
     * @return {@link CommonResult}<{@link Admin}>
     */
    @PostMapping("/update")
    public CommonResult<Boolean> update(@RequestBody Admin admin) {
        log.info("aid: {}",admin);
        boolean b = adminService.updateById(admin);
        return CommonResult.generateSuccessResult(1, b);
    }

    /**
     * 根据管理员id删除信息
     *
     * @param admin
     * @return {@link CommonResult}<{@link Admin}>
     */
    @PostMapping("/deleteOne")
    public CommonResult<Boolean> deleteOne(@RequestBody Admin admin) {
        log.info("id: {}", admin.getAid());
        boolean b = adminService.removeById(admin);
        return CommonResult.generateSuccessResult(1, b);
    }

}
