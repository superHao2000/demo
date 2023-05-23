package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.CommonResult;
import com.example.demo.pojo.Student;
import com.example.demo.pojo.User;
import com.example.demo.service.StudentService;
import com.example.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 学生信息控制器
 *
 * @author super
 * @date 2023/05/22
 */
@RestController
@RequestMapping("/student")
@Slf4j
public class StudentController {
    @Autowired
    StudentService studentService;

    /**
     * 查看所有学生信息
     *
     * @param pageSize
     * @param pageNum
     * @return {@link CommonResult}<{@link IPage}<{@link Student}>>
     */
    @GetMapping("/getAllStudent")
    public CommonResult<IPage<Student>> getAllStudent(@RequestParam("limit") int pageSize,
                                                      @RequestParam("page") int pageNum) {
        Page<Student> page = new Page<>(1, 10);
        LambdaQueryWrapper<Student> qw = new LambdaQueryWrapper<>();
        qw.orderByDesc(Student::getCreateTime);
        studentService.page(page, qw);
        return CommonResult.generateSuccessResult(page.getPages(), page);
    }

    /**
     * 根据userid查询信息
     *
     * @param user
     * @return {@link CommonResult}<{@link Student}>
     */
    @GetMapping("/getOne")
    public CommonResult<Student> getOne(User user) {
        LambdaQueryWrapper<Student> qw = new LambdaQueryWrapper<>();
        qw.eq(Student::getUserid, user.getUserId());
        Student one = studentService.getOne(qw);
        return CommonResult.generateSuccessResult(1, one);
    }

    /**
     * 增加一条学生信息
     *
     * @param student
     * @return {@link CommonResult}<{@link Boolean}>
     */
    @PostMapping("save")
    public CommonResult<Boolean> save(Student student) {
        boolean b = studentService.save(student);
        return CommonResult.generateSuccessResult(1, b);
    }

    /**
     * 修改一条学生信息
     *
     * @param student
     * @return {@link CommonResult}<{@link Boolean}>
     */
    @PostMapping("update")
    public CommonResult<Boolean> updateById(Student student) {
        boolean b = studentService.updateById(student);
        return CommonResult.generateSuccessResult(1, b);
    }

    /**
     * 根据学生id删除信息
     *
     * @param student
     * @return {@link CommonResult}<{@link Boolean}>
     */
    @PostMapping("delete")
    public CommonResult<Boolean> removeById(Student student) {
        boolean b = studentService.removeById(student);
        return CommonResult.generateSuccessResult(1, b);
    }

    /**
     * 批量删除学生信息
     *
     * @param students
     * @return {@link CommonResult}<{@link Boolean}>
     */
    @PostMapping("deleteba")
    public CommonResult<Boolean> removeBatchByIds(List<Student> students) {
        boolean b = studentService.removeBatchByIds(students);
        return CommonResult.generateSuccessResult(1, b);
    }
}