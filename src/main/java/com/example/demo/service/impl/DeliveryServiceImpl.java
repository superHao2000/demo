package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.mapper.DeliveryMapper;
import com.example.demo.pojo.Delivery;
import com.example.demo.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.soap.Addressing;
import java.util.List;

@Service
public class DeliveryServiceImpl extends ServiceImpl<DeliveryMapper, Delivery>implements DeliveryService {
    @Autowired
    DeliveryMapper deliveryMapper;
    @Override
    public List<String> selectResumeIdsByRecruitmentIds(List recruitmentIds) {
        return deliveryMapper.selectResumeIdsByRecruitmentIds(recruitmentIds);
    }
}
