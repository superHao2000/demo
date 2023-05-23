package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.CommonResult;
import com.example.demo.pojo.Company;
import com.example.demo.pojo.Resume;
import com.example.demo.pojo.Student;
import com.example.demo.pojo.User;
import com.example.demo.service.ResumeService;
import com.example.demo.service.ResumeService;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 简历控制器
 *
 * @author super
 * @date 2023/05/22
 */
@RestController
@RequestMapping("Resume")
public class ResumeController {
    @Autowired
    ResumeService resumeService;
    @Autowired
    StudentService studentService;

    /**
     * 查看所有简历信息
     *
     * @param pageNum
     * @param pageSize
     * @return {@link CommonResult}<{@link IPage}<{@link Resume}>>
     */
    @GetMapping("/getAllStudent")
    public CommonResult<IPage<Resume>> getAllStudent(@RequestParam("limit") int pageNum,
                                                     @RequestParam("page") int pageSize) {
        Page<Resume> page = new Page<>(pageNum, pageSize);
        resumeService.page(page, null);
        return CommonResult.generateSuccessResult(page.getPages(), page);
    }

    /**
     * 根据学生id查询简历信息
     *
     * @param user
     * @param pageNum
     * @param pageSize
     * @return {@link CommonResult}<{@link Page}<{@link Resume}>>
     */
    @GetMapping("/getForId")
    public CommonResult<Page<Resume>> getForId(User user,
                                               @RequestParam("limit") int pageNum,
                                               @RequestParam("page") int pageSize) {
        Page<Resume> page = new Page<>(pageNum, pageSize);
        Student student = studentService.getOne(
                new LambdaQueryWrapper<Student>().eq(Student::getUserid, user.getUserId()));
        LambdaQueryWrapper<Resume> qw = new LambdaQueryWrapper<>();
        qw.eq(Resume::getResumeId, student.getSid());
        resumeService.page(page, qw);
        return CommonResult.generateSuccessResult(1, page);
    }

    /**
     * 增加一条简历信息信息
     *
     * @param resume
     * @return {@link CommonResult}<{@link Boolean}>
     */
    @PostMapping("save")
    public CommonResult<Boolean> save(User user, Resume resume) {
        resume.setSid(user.getUserId());
        resume.setCreateTime(LocalDateTime.now());
        boolean b = resumeService.save(resume);
        return CommonResult.generateSuccessResult(1, b);
    }

    /**
     * 修改一条简历信息信息
     *
     * @param resume
     * @return {@link CommonResult}<{@link Boolean}>
     */
    @PostMapping("update")
    public CommonResult<Boolean> updateById(Resume resume) {
        boolean b = resumeService.updateById(resume);
        return CommonResult.generateSuccessResult(1, b);
    }

    /**
     * 根据简历信息id删除信息
     *
     * @param resume
     * @return {@link CommonResult}<{@link Boolean}>
     */
    @PostMapping("delete")
    public CommonResult<Boolean> removeById(Resume resume) {
        boolean b = resumeService.removeById(resume);
        return CommonResult.generateSuccessResult(1, b);
    }

    /**
     * 批量删除简历信息
     *
     * @param resume
     * @return {@link CommonResult}<{@link Boolean}>
     */
    @PostMapping("deleteba")
    public CommonResult<Boolean> removeBatchByIds(List<Resume> resume) {
        boolean b = resumeService.removeBatchByIds(resume);
        return CommonResult.generateSuccessResult(1, b);
    }

}
