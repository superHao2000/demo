package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.CommonResult;
import com.example.demo.common.R;
import com.example.demo.pojo.*;
import com.example.demo.service.*;
import com.example.demo.utlis.BaseContext;
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
@CrossOrigin
@RequestMapping("resume")
public class ResumeController {
    @Autowired
    ResumeService resumeService;
    @Autowired
    StudentService studentService;
    @Autowired
    TeacherService teacherService;
    @Autowired
    CompanyService companyService;
    @Autowired
    RecruitmentService recruitmentService;
    @Autowired
    DeliveryService deliveryService;

    /**
     * 查看所有简历信息
     *
     * @param pageNum
     * @param pageSize
     * @return {@link CommonResult}<{@link IPage}<{@link Resume}>>
     */
    @GetMapping("/getAllResume/{pageNum}/{pageSize}")
    public R getAllResume(@RequestParam("userId") Integer userId,
                          @RequestParam("type") Integer type,
                          @PathVariable("pageNum") Integer pageNum,
                          @PathVariable("pageSize") Integer pageSize,
                          @RequestParam("str") String str) {
        Page<Resume> page = new Page<>(pageNum, pageSize);

        if (type == 0) {
            // 管理员查询
            LambdaQueryWrapper<Resume> qw = new LambdaQueryWrapper<>();
            qw.or().like(Resume::getEmail, str)
              .or().like(Resume::getName, str)
              .or().like(Resume::getTel, str)
              .or().like(Resume::getMajor, str)
              .or().like(Resume::getSkill, str);
            resumeService.page(page, qw);
            return new R(200, "管理员查询", page);
        } else if (type == 1) {
            // 学生查询
            Student student = studentService.getOne(
                    new LambdaQueryWrapper<Student>()
                            .eq(Student::getUserid, userId)
            );
            Integer sid = student.getSid();
            LambdaQueryWrapper<Resume> qw = new LambdaQueryWrapper<>();
            qw.eq(Resume::getSid, sid)
              .like(Resume::getName, str);
            resumeService.page(page, qw);
            return new R(200, "学生查询简历", page);
        } else if (type == 2) {
            Teacher teacher = teacherService.getOne(
                    new LambdaQueryWrapper<Teacher>()
                            .eq(Teacher::getUserid, userId)
            );
            List<Object> studentIds = studentService.listObjs(new LambdaQueryWrapper<Student>()
                                                                      .eq(Student::getTid, teacher.getTid())
                                                                      .select(Student::getSid)
            );
            resumeService.page(page,
                               new LambdaQueryWrapper<Resume>()
                                       .in(Resume::getSid, studentIds)
            );
            return new R(200, "教师查询简历", page);
        } else if (type == 3) {
            // 企业查询
            Company company = companyService.getOne(
                    new LambdaQueryWrapper<Company>()
                            .eq(Company::getUserid, userId));
            List<Object> recruitmentIds = recruitmentService.listObjs(
                    new LambdaQueryWrapper<Recruitment>()
                            .eq(Recruitment::getEid, company.getEid())
                            .select(Recruitment::getRecruitmentId));
            List<String> resumeIds = deliveryService.selectResumeIdsByRecruitmentIds(recruitmentIds);
            resumeService.page(page, new LambdaQueryWrapper<Resume>()
                    .in(Resume::getResumeId, resumeIds));
            return new R(200, "企业查询简历", page);

        } else {
            return new R(200, "查询简历", page);
        }
    }

    /**
     * 根据学生id查询简历信息
     *
     * @param user
     * @param pageNum
     * @param pageSize
     * @return {@link CommonResult}<{@link Page}<{@link Resume}>>
     */
    @PostMapping("/getForId")
    public CommonResult<Page<Resume>> getForId(User user,
                                               @RequestParam("limit") int pageNum,
                                               @RequestParam("page") int pageSize) {
        Page<Resume> page = new Page<>(pageNum, pageSize);
        if (user.getType() == 0) {
            resumeService.page(page, null);
        } else {
            Student student = studentService.getOne(
                    new LambdaQueryWrapper<Student>().eq(Student::getUserid, user.getUserId()));
            LambdaQueryWrapper<Resume> qw = new LambdaQueryWrapper<>();
            qw.eq(Resume::getResumeId, student.getSid());
            resumeService.page(page, qw);
        }
        return CommonResult.generateSuccessResult(1, page);
    }

    /**
     * 增加一条简历信息信息
     *
     * @param resume
     * @return {@link CommonResult}<{@link Boolean}>
     */
    @PostMapping("save")
    public R save(@RequestBody Resume resume) {
        User user = BaseContext.getUser();
        Student student = studentService.getOne(
                new LambdaQueryWrapper<Student>()
                        .eq(Student::getUserid, user.getUserId())
        );
        resume.setSid(student.getSid());
        boolean b = resumeService.save(resume);
        return new R(200, "添加成功", b);
    }

    /**
     * 修改一条简历信息信息
     *
     * @param resume
     * @return {@link CommonResult}<{@link Boolean}>
     */
    @PostMapping("update")
    public CommonResult<Boolean> updateById(@RequestBody Resume resume) {
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
    public CommonResult<Boolean> removeById(@RequestBody Resume resume) {
        boolean b = resumeService.removeById(resume);
        return CommonResult.generateSuccessResult(1, b);
    }

    /**
     * 批量删除简历信息
     *
     * @param resume
     * @return {@link CommonResult}<{@link Boolean}>
     */
    @PostMapping("deleteAll")
    public R removeBatchByIds(@RequestBody List<Resume> resume) {
        boolean b = resumeService.removeBatchByIds(resume);
        return new R(200, "删除成功", b);
    }

}
