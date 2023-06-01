package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.demo.common.R;
import com.example.demo.pojo.Delivery;
import com.example.demo.pojo.Recruitment;
import com.example.demo.pojo.Student;
import com.example.demo.pojo.User;
import com.example.demo.service.DeliveryService;
import com.example.demo.service.RecruitmentService;
import com.example.demo.service.StudentService;
import com.example.demo.utlis.BaseContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 简历和招聘信息
 *
 * @author super
 * @date 2023/05/22
 */
@RestController
@CrossOrigin
@RequestMapping("Delivery")
public class DeliveryController {
    @Autowired
    DeliveryService deliveryService;
    @Autowired
    StudentService studentService;
    @Autowired
    RecruitmentService recruitmentService;

    @PostMapping("/add")
    public R add(@RequestBody Delivery delivery) {
        User user = BaseContext.getUser();
        // 学生查询
        Student student = studentService.getOne(
                new LambdaQueryWrapper<Student>()
                        .eq(Student::getUserid, user.getUserId())
        );
        delivery.setSid(student.getSid());
        boolean save = deliveryService.save(delivery);

        Recruitment recruitment = recruitmentService.getOne(
                new LambdaQueryWrapper<Recruitment>()
                        .eq(Recruitment::getRecruitmentId, delivery.getRecruitmentId()));

        recruitment.setDeliveryNum(recruitment.getDeliveryNum() + 1);
        recruitmentService.updateById(recruitment);
        return new R(200, "简历和招聘对应信息增加成功", save);
    }
}
