package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.CommonResult;
import com.example.demo.pojo.Student;
import com.example.demo.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/** 学生信息控制器
 * @author super
 * @date 2023/05/22
 */
@RestController
@RequestMapping("/student")
@Slf4j
public class StudentController {
    @Autowired
    StudentService studentService;

    @GetMapping("/getAllStudent")
    public CommonResult<IPage<Student>> getAllStudent(Student student, @RequestParam("limit") int pageSize,
                                                      @RequestParam("page") int pageNum) {
        Page<Student> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<Student> qw = new LambdaQueryWrapper<>();
        Page<Student> page1 = studentService.page(page);
        return CommonResult.generateSuccessResult(page1.getSize(), page1);
    }
}
