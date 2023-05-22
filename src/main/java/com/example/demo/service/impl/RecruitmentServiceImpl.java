package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.mapper.RecruitmentMapper;
import com.example.demo.pojo.Recruitment;
import com.example.demo.service.RecruitmentService;
import org.springframework.stereotype.Service;

@Service
public class RecruitmentServiceImpl extends ServiceImpl<RecruitmentMapper, Recruitment>implements RecruitmentService {
}
