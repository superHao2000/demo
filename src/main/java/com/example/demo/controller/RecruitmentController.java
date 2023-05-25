package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.CommonResult;
import com.example.demo.common.R;
import com.example.demo.pojo.Company;
import com.example.demo.pojo.Recruitment;
import com.example.demo.pojo.Resume;
import com.example.demo.pojo.User;
import com.example.demo.service.CompanyService;
import com.example.demo.service.RecruitmentService;
import com.example.demo.utlis.BaseContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 招聘信息控制器
 *
 * @author super
 * @date 2023/05/22
 */
@RestController
@CrossOrigin
@RequestMapping("/recruitment")
public class RecruitmentController {
    @Autowired
    RecruitmentService recruitmentService;
    @Autowired
    CompanyService companyService;

    /**
     * 查看所有招聘信息
     *
     * @param pageNum
     * @param pageSize
     * @return {@link CommonResult}<{@link IPage}<{@link Recruitment}>>
     */
    @GetMapping("/getAllRecruitment/{pageNum}/{pageSize}")
    public R getAllRecruitment(@RequestParam("userId") Integer userId,
                               @RequestParam("type") Integer type,
                               @PathVariable("pageNum") Integer pageNum,
                               @PathVariable("pageSize") Integer pageSize,
                               @RequestParam("str") String str) {
        Page<Recruitment> page = new Page<>(pageNum, pageSize);

        if (type == 0 || type==1) {
            // 管理员查询
            // 学生查询
            LambdaQueryWrapper<Recruitment> qw = new LambdaQueryWrapper<>();
            qw.like(Recruitment::getPosition, str)
              .or().like(Recruitment::getLocation, str)
              .or().like(Recruitment::getEducation, str)
              .or().like(Recruitment::getMajor, str)
              .or().like(Recruitment::getSalary, str);
            recruitmentService.page(page, qw);
            return new R(200, "管理员查询招聘信息", page);
        } else if (type == 3) {
            Company company = companyService.getOne(new LambdaQueryWrapper<Company>().eq(Company::getUserid, userId));
            recruitmentService.page(page,
                                    new LambdaQueryWrapper<Recruitment>()
                                            .eq(Recruitment::getEid, company.getEid()));
            return new R(200, "企业查询招聘信息", page);
        }
        return new R(200, "管理员查询招聘信息", page);
    }

    /**
     * 根据企业id查询信息
     *
     * @param user
     * @param pageNum
     * @param pageSize
     * @return {@link CommonResult}<{@link Page}<{@link Recruitment}>>
     */
    @GetMapping("/getForId")
    public CommonResult<Page<Recruitment>> getForId(User user,
                                                    @RequestParam("limit") int pageNum,
                                                    @RequestParam("page") int pageSize) {
        Page<Recruitment> page = new Page<>(pageNum, pageSize);
        Company company = companyService.getOne(
                new LambdaQueryWrapper<Company>()
                        .eq(Company::getUserid, user.getUserId()));
        LambdaQueryWrapper<Recruitment> qw = new LambdaQueryWrapper<>();
        qw.eq(Recruitment::getRecruitmentId, company.getEid());
        recruitmentService.page(page, qw);
        return CommonResult.generateSuccessResult(1, page);
    }

    /**
     * 增加一条招聘信息信息
     *
     * @param recruitment
     * @return {@link CommonResult}<{@link Boolean}>
     */
    @PostMapping("save")
    public CommonResult<Boolean> save(@RequestBody Recruitment recruitment) {
        User user = BaseContext.getUser();
        Company company = companyService.getOne(
                new LambdaQueryWrapper<Company>()
                        .eq(Company::getUserid, user.getUserId()));
        recruitment.setEid(company.getEid());
        boolean b = recruitmentService.save(recruitment);
        return CommonResult.generateSuccessResult(1, b);
    }

    /**
     * 修改一条招聘信息
     *
     * @param recruitment
     * @return {@link CommonResult}<{@link Boolean}>
     */
    @PostMapping("update")
    public CommonResult<Boolean> updateById(@RequestBody Recruitment recruitment) {
        boolean b = recruitmentService.updateById(recruitment);
        return CommonResult.generateSuccessResult(1, b);
    }

    /**
     * 根据招聘信息id删除信息
     *
     * @param recruitment
     * @return {@link CommonResult}<{@link Boolean}>
     */
    @PostMapping("delete")
    public CommonResult<Boolean> removeById(@RequestBody Recruitment recruitment) {
        boolean b = recruitmentService.removeById(recruitment);
        return CommonResult.generateSuccessResult(1, b);
    }

    /**
     * 批量删除招聘信息信息
     *
     * @param recruitment
     * @return {@link CommonResult}<{@link Boolean}>
     */
    @PostMapping("deleteAll")
    public CommonResult<Boolean> removeBatchByIds(@RequestBody List<Recruitment> recruitment) {
        boolean b = recruitmentService.removeBatchByIds(recruitment);
        return CommonResult.generateSuccessResult(1, b);
    }

}
