package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/** 页面跳转
 * @author super
 * @date 2023/05/21
 */
@Controller
public class HomeController {
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
