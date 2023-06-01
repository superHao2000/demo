package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.pojo.Delivery;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface DeliveryService extends IService<Delivery> {
    List<String> selectResumeIdsByRecruitmentIds(@Param("recruitmentIds") List recruitmentIds);
}
