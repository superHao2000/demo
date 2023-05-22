package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.CommonResult;
import com.example.demo.pojo.Employment;
import com.example.demo.service.EmploymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 就业信息
 *
 * @author super
 * @date 2023/05/22
 */
@RestController
@RequestMapping("/employment")
@Slf4j
public class EmploymentController {
    @Autowired
    EmploymentService employmentService;

    @GetMapping("/getAllEmployment")
    public CommonResult<IPage<Employment>> getAllEmployment(Employment employment,
                                                            @RequestParam("limit") int pageSize,
                                                            @RequestParam("page") int pageNum) {
        Page<Employment> page = new Page<>(pageSize, pageNum);
        LambdaQueryWrapper<Employment> qw = new LambdaQueryWrapper<>();
        Page<Employment> page1 = employmentService.page(page, qw);
        return CommonResult.generateSuccessResult(1, page1);
    }

    public CommonResult<Integer> addEmployment(Employment employment) {
        return CommonResult.generateSuccessResult(1, null);
    }
}
