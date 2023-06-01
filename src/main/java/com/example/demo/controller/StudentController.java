package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.CommonResult;
import com.example.demo.common.R;
import com.example.demo.pojo.Student;
import com.example.demo.pojo.User;
import com.example.demo.service.StudentService;
import com.example.demo.service.UserService;
import com.example.demo.utlis.BaseContext;
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
@CrossOrigin
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
    @GetMapping("/getAllStudent/{pageNum}/{pageSize}")
    public R getAllStudent(@PathVariable("pageNum") Integer pageNum,
                           @PathVariable("pageSize") Integer pageSize) {
        Page<Student> page = new Page<>(pageNum, pageSize);
        // LambdaQueryWrapper<Student> qw = new LambdaQueryWrapper<>();
        // qw.orderByDesc(Student::getCreateTime);
        IPage<Student> page1 = studentService.page(page);
        return new R(200, "获取分页数据", page1);
    }

    /**
     * 根据userid查询信息
     *
     * @param user
     * @return {@link CommonResult}<{@link Student}>
     */
    @GetMapping("/getOne")
    public R getOne() {
        User user = BaseContext.getUser();
        LambdaQueryWrapper<Student> qw = new LambdaQueryWrapper<>();
        qw.eq(Student::getUserid, user.getUserId());
        Student one = studentService.getOne(qw);
        return new R(200, "获取一条学生数据", one);
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
    public R updateById(@RequestBody Student student) {
        boolean b = studentService.updateById(student);
        return new R(200, "修改成功", b);
    }

    /**
     * 根据学生id删除信息
     *
     * @param student
     * @return {@link CommonResult}<{@link Boolean}>
     */
    @PostMapping("deleteOne")
    public CommonResult<Boolean> removeById(@RequestBody Student student) {
        boolean b = studentService.removeById(student);
        return CommonResult.generateSuccessResult("修改成功",1, b);
    }

    /**
     * 批量删除学生信息
     *
     * @param students
     * @return {@link CommonResult}<{@link Boolean}>
     */
    @PostMapping("deleteBatch")
    public CommonResult<Boolean> removeBatchByIds(List<Student> students) {
        boolean b = studentService.removeBatchByIds(students);
        return CommonResult.generateSuccessResult(1, b);
    }
}
