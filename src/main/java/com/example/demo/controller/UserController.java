package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.CommonResult;
import com.example.demo.common.R;
import com.example.demo.pojo.Student;
import com.example.demo.pojo.User;
import com.example.demo.service.AdminService;
import com.example.demo.service.UserService;
import com.example.demo.utlis.BaseContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.pojo.*;
import com.example.demo.service.*;


import java.time.LocalDateTime;
import java.util.List;

/**
 * 用户控制器
 *
 * @author super
 * @date 2023/05/21
 */
@CrossOrigin
@RestController
@RequestMapping("/user")
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

    /**
     * 获取所有用户
     *
     * @param pageSize
     * @param pageNum
     * @return {@link CommonResult}<{@link IPage}<{@link User}>>
     */
    @GetMapping("/getAllUser/{pageNum}/{pageSize}")
    public R getAllUser(@PathVariable("pageNum") Integer pageNum,
                        @PathVariable("pageSize") Integer pageSize,
                        String str) {
        Page<User> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<User> qw = new LambdaQueryWrapper<>();
        qw.like(User::getUserName, str);
        userService.page(page, qw);
        return new R(200, "获取分页数据", page);
    }

    /**
     * 修改用户密码
     *
     * @param oldPassword
     * @param newPassword
     * @return {@link R}
     */
    @PostMapping("/updatePassword")
    public R updatePassword(@RequestParam String oldPassword,
                            @RequestParam String newPassword) {
        User user = BaseContext.getUser();

        if (!user.getPassword().equals(oldPassword)) {
            return new R(200, "密码错误", false);
        }
        if (user.getPassword().equals(newPassword)) {
            return new R(200, "新密码和旧密码不可相同", false);
        }
        user.setPassword(newPassword);
        userService.updateById(user);
        return new R(200, "修改成功，请重新登录", true);
    }

    /**
     * 添加用户
     *
     * @param user
     * @return {@link CommonResult}<{@link Boolean}>
     */
    @PostMapping("/saveUser")
    public R saveUser(@RequestBody User user) {
        int type = user.getType();
        User one = userService.getOne(new LambdaQueryWrapper<User>()
                                              .eq(User::getUserName, user.getUserName()));
        if (one != null) {
            return new R(200, "用户已存在", false);
        }
        user.setPassword("123456");
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
                student.setSno(user.getUserName());
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
        return new R(200, "添加成功", true);
    }

    @PostMapping("update")
    public R update(@RequestBody User user) {
        user.setCreateTime(LocalDateTime.now());
        boolean b = userService.updateById(user);
        return new R(200, "修改成功", b);
    }

    @PostMapping("delete")
    public R delete(@RequestBody User user) {
        log.info("删除的user{}", user);
        int type = user.getType();
        boolean b = userService.removeById(user);
        switch (type) {
            case 0:
                adminService.remove(
                        new LambdaQueryWrapper<Admin>()
                                .eq(Admin::getUserId, user.getUserId()));
                break;
            case 1:
                studentService.remove(
                        new LambdaQueryWrapper<Student>()
                                .eq(Student::getUserid, user.getUserId()));
                break;
            case 2:
                teacherService.remove(
                        new LambdaQueryWrapper<Teacher>()
                                .eq(Teacher::getUserid, user.getUserId()));
                break;
            case 3:
                companyService.remove(
                        new LambdaQueryWrapper<Company>()
                                .eq(Company::getUserid, user.getUserId()));
                break;
        }
        return new R(200, "删除成功", b);
    }

    @PostMapping("deleteAll")
    public R deleteAll(@RequestBody List<User> users) {
        for (User user : users) {
            int type = user.getType();
            boolean b = userService.removeById(user);
            switch (type) {
                case 0:
                    adminService.remove(
                            new LambdaQueryWrapper<Admin>()
                                    .eq(Admin::getUserId, user.getUserId()));
                    break;
                case 1:
                    studentService.remove(
                            new LambdaQueryWrapper<Student>()
                                    .eq(Student::getUserid, user.getUserId()));
                    break;
                case 2:
                    teacherService.remove(
                            new LambdaQueryWrapper<Teacher>()
                                    .eq(Teacher::getUserid, user.getUserId()));
                    break;
                case 3:
                    companyService.remove(
                            new LambdaQueryWrapper<Company>()
                                    .eq(Company::getUserid, user.getUserId()));
                    break;
            }
        }
        return new R(200, "修改成功", 1);
    }
}
