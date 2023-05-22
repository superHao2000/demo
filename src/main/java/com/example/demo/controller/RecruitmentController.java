package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.CommonResult;
import com.example.demo.pojo.Company;
import com.example.demo.pojo.Recruitment;
import com.example.demo.pojo.User;
import com.example.demo.service.RecruitmentService;
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
@RequestMapping("/recruitment")
public class RecruitmentController {
    @Autowired
    RecruitmentService recruitmentService;

    /**
     * 查看所有招聘信息
     *
     * @param pageNum
     * @param pageSize
     * @return {@link CommonResult}<{@link IPage}<{@link Recruitment}>>
     */
    @GetMapping("/getAllStudent")
    public CommonResult<IPage<Recruitment>> getAllStudent(@RequestParam("limit") int pageNum,
                                                          @RequestParam("page") int pageSize) {
        Page<Recruitment> page = new Page<>(pageNum, pageSize);
        recruitmentService.page(page, null);
        return CommonResult.generateSuccessResult(page.getPages(), page);
    }

    /**
     * 根据企业id查询信息
     *
     * @param company
     * @return {@link CommonResult}<{@link Recruitment}>
     */
    @GetMapping("/getForId")
    public CommonResult<Page<Recruitment>> getForId(Company company,
                                                    @RequestParam("limit") int pageNum,
                                                    @RequestParam("page") int pageSize) {
        Page<Recruitment> page = new Page<>(1, 10);
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
    public CommonResult<Boolean> save(Recruitment recruitment) {
        recruitment.setReleaseTime(LocalDateTime.now());
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
    public CommonResult<Boolean> updateById(Recruitment recruitment) {
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
    public CommonResult<Boolean> removeById(Recruitment recruitment) {
        boolean b = recruitmentService.removeById(recruitment);
        return CommonResult.generateSuccessResult(1, b);
    }

    /**
     * 批量删除招聘信息信息
     *
     * @param recruitment
     * @return {@link CommonResult}<{@link Boolean}>
     */
    @PostMapping("deleteba")
    public CommonResult<Boolean> removeBatchByIds(List<Recruitment> recruitment) {
        boolean b = recruitmentService.removeBatchByIds(recruitment);
        return CommonResult.generateSuccessResult(1, b);
    }

}
