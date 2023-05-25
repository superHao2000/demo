package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.CommonResult;
import com.example.demo.common.R;
import com.example.demo.pojo.Company;
import com.example.demo.pojo.Company;
import com.example.demo.pojo.User;
import com.example.demo.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 企业信息控制器
 *
 * @author super
 * @date 2023/05/22
 */
@RestController
@CrossOrigin
@RequestMapping("company")
public class CompanyController {
    @Autowired
    CompanyService companyService;

    /** 获取所有企业信息
     * @param pageSize
     * @param pageNum
     * @return {@link CommonResult}<{@link Page}<{@link Company}>>
     */
    @GetMapping("getAllCompany/{pageNum}/{pageSize}")
    public R getAllCompany(@PathVariable("pageNum") Integer pageNum,
                           @PathVariable("pageSize") Integer pageSize) {
        Page<Company> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<Company> qw = new LambdaQueryWrapper<>();
        Page<Company> page1 = companyService.page(page, qw);
        return new R(200, "获取分页数据", page1);
    }

    /**
     * 根据userid查询信息
     *
     * @param user
     * @return {@link CommonResult}<{@link Company}>
     */
    @GetMapping("/getOne")
    public CommonResult<Company> getOne(User user) {
        LambdaQueryWrapper<Company> qw = new LambdaQueryWrapper<>();
        qw.eq(Company::getUserid, user.getUserId());
        Company one = companyService.getOne(qw);
        return CommonResult.generateSuccessResult(1, one);
    }

    /**
     * 增加一条企业信息
     *
     * @param Company
     * @return {@link CommonResult}<{@link Boolean}>
     */
    @PostMapping("save")
    public CommonResult<Boolean> save(@RequestBody Company Company) {
        boolean b = companyService.save(Company);
        return CommonResult.generateSuccessResult(1, b);
    }

    /**
     * 修改一条企业信息
     *
     * @param Company
     * @return {@link CommonResult}<{@link Boolean}>
     */
    @PostMapping("update")
    public CommonResult<Boolean> updateById(@RequestBody Company Company) {
        boolean b = companyService.updateById(Company);
        return CommonResult.generateSuccessResult(1, b);
    }

    /**
     * 根据企业id删除信息
     *
     * @param Company
     * @return {@link CommonResult}<{@link Boolean}>
     */
    @PostMapping("delete")
    public CommonResult<Boolean> removeById(@RequestBody Company Company) {
        boolean b = companyService.removeById(Company);
        return CommonResult.generateSuccessResult(1, b);
    }

    /**
     * 批量删除企业信息
     *
     * @param Companys
     * @return {@link CommonResult}<{@link Boolean}>
     */
    @PostMapping("deleteba")
    public CommonResult<Boolean> removeBatchByIds(@RequestBody List<Company> Companys) {
        boolean b = companyService.removeBatchByIds(Companys);
        return CommonResult.generateSuccessResult(1, b);
    }
}
