package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.CommonResult;
import com.example.demo.common.R;
import com.example.demo.pojo.*;
import com.example.demo.pojo.Employment;
import com.example.demo.service.EmploymentService;
import com.example.demo.service.StudentService;
import com.example.demo.service.TeacherService;
import com.example.demo.utlis.BaseContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 就业信息
 *
 * @author super
 * @date 2023/05/22
 */
@RestController
@CrossOrigin
@RequestMapping("/employment")
@Slf4j
public class EmploymentController {
    @Autowired
    EmploymentService employmentService;
    @Autowired
    StudentService studentService;
    @Autowired
    TeacherService teacherService;

    @GetMapping("/getAllEmployment/{pageNum}/{pageSize}")
    public R getAllEmployment(@RequestParam("userId") Integer userId,
                              @RequestParam("type") Integer type,
                              @PathVariable("pageNum") Integer pageNum,
                              @PathVariable("pageSize") Integer pageSize,
                              @RequestParam("str") String str) {
        Page<Employment> page = new Page(pageNum, pageSize);
        if (type == 0) {
            // 管理员查询
            LambdaQueryWrapper<Employment> qw = new LambdaQueryWrapper<Employment>()
                    .like(Employment::getCname, str);
            employmentService.page(page, qw);
            return new R(200, "获取1分页数据", page);
        } else if (type == 1) {
            // 学生查询
            Student student = studentService.getOne(
                    new LambdaQueryWrapper<Student>()
                            .eq(Student::getUserid, userId)
            );
            employmentService.page(page,
                                   new LambdaQueryWrapper<Employment>()
                                           .like(Employment::getCname, str)
                                           .eq(Employment::getSid, student.getSid()));
            return new R(200, "学生查询", page);
        } else {
            return new R(200, "教师查询", 1);
        }
    }

    @GetMapping("/getAllEmployment")
    public R getAllEmployment() {
        Page<Employment> page = new Page(1, 10);
        employmentService.page(page, null);
        return new R(200, "获取分页数据", page);
    }

    /**
     * 根据学生id查询就业信息
     *
     * @param user
     * @param pageNum
     * @param pageSize
     * @return {@link CommonResult}<{@link Page}<{@link Employment}>>
     */
    @GetMapping("/getForUserId/{pageNum}/{pageSize}")
    public CommonResult<Page<Employment>> getForId(@RequestParam("userId") Integer userId,
                                                   @PathVariable("pageNum") Integer pageNum,
                                                   @PathVariable("pageSize") Integer pageSize,
                                                   @RequestParam("str") String str) {
        Page<Employment> page = new Page<>(pageNum, pageSize);
        Student student = studentService.getOne(
                new LambdaQueryWrapper<Student>().eq(Student::getUserid, userId));
        employmentService.page(page,
                               new LambdaQueryWrapper<Employment>().eq(Employment::getSid, student.getSid()));
        return CommonResult.generateSuccessResult(1, page);
    }

    /**
     * 根据学生id增加一条就业信息
     *
     * @param student
     * @param employment
     * @return {@link CommonResult}<{@link Boolean}>
     */
    @PostMapping("save")
    public R addEmployment(@RequestBody Employment employment) {
        User user = BaseContext.getUser();
        Student student = studentService.getOne(
                new LambdaQueryWrapper<Student>().eq(Student::getUserid, user.getUserId()));
        employment.setSid(student.getSid());
        boolean b = employmentService.save(employment);
        return new R(200, "添加成功", b);
    }

    /**
     * 修改一条就业信息
     *
     * @param employment
     * @return {@link CommonResult}<{@link Boolean}>
     */
    @PostMapping("update")
    public CommonResult<Boolean> updateById(@RequestBody Employment employment) {
        boolean b = employmentService.updateById(employment);
        return CommonResult.generateSuccessResult(1, b);
    }

    /**
     * 根据就业信息id删除信息
     *
     * @param employment
     * @return {@link CommonResult}<{@link Boolean}>
     */
    @PostMapping("delete")
    public R removeById(@RequestBody Employment employment) {
        boolean b = employmentService.removeById(employment);
        return new R(200, "删除成功", b);
    }

    /**
     * 批量删除就业信息
     *
     * @param employment
     * @return {@link CommonResult}<{@link Boolean}>
     */
    @PostMapping("deletebAll")
    public R removeBatchByIds(@RequestBody List<Employment> employment) {
        boolean b = employmentService.removeBatchByIds(employment);
        return new R(200,"删除成功",b);
    }

}
