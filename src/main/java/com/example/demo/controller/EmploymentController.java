package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.CommonResult;
import com.example.demo.pojo.Employment;
import com.example.demo.pojo.Employment;
import com.example.demo.pojo.Student;
import com.example.demo.pojo.User;
import com.example.demo.service.EmploymentService;
import com.example.demo.service.StudentService;
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
@RequestMapping("/employment")
@Slf4j
public class EmploymentController {
    @Autowired
    EmploymentService employmentService;
    @Autowired
    StudentService studentService;

    /**
     * 查看所有就业
     *
     * @param employment
     * @param pageSize
     * @param pageNum
     * @return {@link CommonResult}<{@link IPage}<{@link Employment}>>
     */
    @GetMapping("/getAllEmployment")
    public CommonResult<IPage<Employment>> getAllEmployment(Employment employment, @RequestParam("limit") int pageSize,
                                                            @RequestParam("page") int pageNum) {
        Page<Employment> page = new Page<>(pageSize, pageNum);
        LambdaQueryWrapper<Employment> qw = new LambdaQueryWrapper<>();
        employmentService.page(page, null);
        return CommonResult.generateSuccessResult(1, page);
    }


    /**
     * 根据学生id查询就业信息
     *
     * @param user
     * @param pageNum
     * @param pageSize
     * @return {@link CommonResult}<{@link Page}<{@link Employment}>>
     */
    @GetMapping("/getForId")
    public CommonResult<Page<Employment>> getForId(User user,
                                                   @RequestParam("limit") int pageNum,
                                                   @RequestParam("page") int pageSize) {
        Page<Employment> page = new Page<>(pageNum, pageSize);
        Student student = studentService.getOne(
                new LambdaQueryWrapper<Student>().eq(Student::getUserid, user.getUserId()));
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
    @PostMapping("getForUserid")
    public CommonResult<Boolean> addEmployment(Student student, Employment employment) {
        employment.setInsertTime(LocalDateTime.now());
        employment.setSid(student.getSid());
        boolean b = employmentService.save(employment);
        return CommonResult.generateSuccessResult(1, b);
    }

    /**
     * 修改一条就业信息
     *
     * @param employment
     * @return {@link CommonResult}<{@link Boolean}>
     */
    @PostMapping("update")
    public CommonResult<Boolean> updateById(Employment employment) {
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
    public CommonResult<Boolean> removeById(Employment employment) {
        boolean b = employmentService.removeById(employment);
        return CommonResult.generateSuccessResult(1, b);
    }

    /**
     * 批量删除就业信息
     *
     * @param employment
     * @return {@link CommonResult}<{@link Boolean}>
     */
    @PostMapping("deleteba")
    public CommonResult<Boolean> removeBatchByIds(List<Employment> employment) {
        boolean b = employmentService.removeBatchByIds(employment);
        return CommonResult.generateSuccessResult(1, b);
    }

}
