package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.CommonResult;
import com.example.demo.pojo.*;
import com.example.demo.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

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
    @Autowired
    StudentService studentService;
    @Autowired
    TeacherService teacherService;
    @Autowired
    CompanyService companyService;

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
        /*        登录者详细数据
                switch (emp.getType()) {
                    case 0:
                        LambdaQueryWrapper<Admin> queryWrapper1 = new LambdaQueryWrapper<>();
                        queryWrapper1.eq(Admin::getUserId, emp.getUserId());
                        Admin admin = adminService.getOne(queryWrapper1);
                        return CommonResult.generateSuccessResult(1, admin);
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                }*/
        emp.setLastLoginTime(LocalDateTime.now());
        userService.updateById(emp);
        return CommonResult.generateSuccessResult(1, emp);
    }

    /**
     * 获取所有用户
     *
     * @param pageSize
     * @param pageNum
     * @return {@link CommonResult}<{@link IPage}<{@link User}>>
     */
    @GetMapping("/getAllUser")
    public CommonResult<IPage<User>> getAllUser(@RequestParam("limit") int pageSize,
                                                @RequestParam("page") int pageNum) {
        Page<User> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<User> qw = new LambdaQueryWrapper<>();
        userService.page(page, null);
        return CommonResult.generateSuccessResult(page.getPages(), page);
    }

    /**
     * 修改用户密码
     *
     * @param user
     * @param newPassword
     * @return {@link CommonResult}<{@link Object}>
     */
    @PostMapping("/updatePassword")
    public CommonResult<Object> updatePassword(User user, String newPassword) {
        user.setPassword(newPassword);
        user.setCreateTime(LocalDateTime.now());
        userService.updateById(user);
        return CommonResult.generateSuccessResult(1, null);
    }

    /**
     * 添加用户
     *
     * @param user
     * @return {@link CommonResult}<{@link Boolean}>
     */
    @PostMapping("/saveUser")
    public CommonResult<Integer> saveUser(User user) {
        int type = user.getType();
        user.setPassword("123456");
        user.setCreateTime(LocalDateTime.now());
        userService.save(user);
        switch (type) {
            case 0:
                Admin admin = new Admin();
                admin.setUserId(user.getUserId());
                adminService.save(admin);
                break;
            case 1:
                Student student = new Student();
                student.setUserid(user.getUserId());
                studentService.save(student);
                break;
            case 2:
                Teacher teacher = new Teacher();
                teacher.setUserid(user.getUserId());
                teacherService.save(teacher);
                break;
            case 3:
                Company company = new Company();
                company.setUserid(user.getUserId());
                companyService.save(company);
                break;
        }
        return CommonResult.generateSuccessResult(1, null);
    }
}
