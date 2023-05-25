package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.CommonResult;
import com.example.demo.common.R;
import com.example.demo.pojo.Student;
import com.example.demo.pojo.Teacher;
import com.example.demo.pojo.User;
import com.example.demo.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 教师信息控制器
 *
 * @author super
 * @date 2023/05/22
 */
@RestController
@CrossOrigin
@RequestMapping("teacher")
public class TeacherController {
    @Autowired
    TeacherService teacherService;

    /**
     * 查看所有教师信息
     *
     * @param pageNum
     * @param pageSize
     * @return {@link CommonResult}<{@link IPage}<{@link Teacher}>>
     */
    @GetMapping("/getAllTeacher/{pageNum}/{pageSize}")
    public R getAllTeacher(@PathVariable("pageNum") Integer pageNum,
                           @PathVariable("pageSize") Integer pageSize) {
        Page<Teacher> page = new Page<>(pageNum, pageSize);
        Page<Teacher> page1 = teacherService.page(page);
        return new R(200, "获取分页数据", page1);
    }

    /**
     * 根据userid查询信息
     *
     * @param user
     * @return {@link CommonResult}<{@link Teacher}>
     */
    @GetMapping("/getOne")
    public CommonResult<Teacher> getOne(User user) {
        LambdaQueryWrapper<Teacher> qw = new LambdaQueryWrapper<>();
        qw.eq(Teacher::getUserid, user.getUserId());
        Teacher one = teacherService.getOne(qw);
        return CommonResult.generateSuccessResult(1, one);
    }

    /**
     * 增加一条教师信息
     *
     * @param teacher
     * @return {@link CommonResult}<{@link Boolean}>
     */
    @PostMapping("save")
    public CommonResult<Boolean> save(@RequestBody Teacher teacher) {
        boolean b = teacherService.save(teacher);
        return CommonResult.generateSuccessResult(1, b);
    }

    /**
     * 修改一条教师信息
     *
     * @param teacher
     * @return {@link CommonResult}<{@link Boolean}>
     */
    @PostMapping("update")
    public CommonResult<Boolean> updateById(@RequestBody Teacher teacher) {
        boolean b = teacherService.updateById(teacher);
        return CommonResult.generateSuccessResult(1, b);
    }

    /**
     * 根据教师id删除信息
     *
     * @param teacher
     * @return {@link CommonResult}<{@link Boolean}>
     */
    @PostMapping("delete")
    public CommonResult<Boolean> removeById(Teacher teacher) {
        boolean b = teacherService.removeById(teacher);
        return CommonResult.generateSuccessResult(1, b);
    }

    /**
     * 批量删除教师信息
     *
     * @param teachers
     * @return {@link CommonResult}<{@link Boolean}>
     */
    @PostMapping("deleteba")
    public CommonResult<Boolean> removeBatchByIds(List<Teacher> teachers) {
        boolean b = teacherService.removeBatchByIds(teachers);
        return CommonResult.generateSuccessResult(1, b);
    }
}
