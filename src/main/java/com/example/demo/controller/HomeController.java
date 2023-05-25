package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.demo.common.CommonResult;
import com.example.demo.pojo.User;
import com.example.demo.service.UserService;
import com.example.demo.utlis.BaseContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * 页面跳转
 *
 * @author super
 * @date 2023/05/21
 */
@Controller
@CrossOrigin
@Slf4j
public class HomeController {
    @Autowired
    UserService userService;

    @ResponseBody
    @PostMapping("/employment/login")
    public CommonResult<Object> login(@RequestBody User user) {
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
        emp.setLastLoginTime(LocalDateTime.now());
        userService.updateById(emp);
        BaseContext.setUser(emp);
        return CommonResult.generateSuccessResult(emp.getUserName() + "登录成功", 1, emp);
    }

    /**
     * 登录界面
     *
     * @return {@link String}
     */
    @RequestMapping({"/", "/employment"})
    public String index() {
        return "system/login";
    }

    @GetMapping("/employment/bulletin")
    public String getBulletin() {
        return "business/bulletin";
    }

    @RequestMapping("/employment/employmentinfo")
    public String employmentinfo() {
        return "system/employmentinfo/employmentinfo";
    }

    @RequestMapping("/employment/studentmanage")
    public String studentmanage() {
        return "system/usermanage/studentmanage";
    }

    @RequestMapping("/employment/usermanage")
    public String usermanage() {
        return "system/usermanage/usermanage";
    }

    @RequestMapping("/employment/statistic")
    public String employmentstatistic() {
        return "system/employmentstatistic/employmentstatistic";
    }

}
