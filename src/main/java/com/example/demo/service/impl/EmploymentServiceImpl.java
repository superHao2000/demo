package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.mapper.EmploymentMapper;
import com.example.demo.pojo.Employment;
import com.example.demo.service.EmploymentService;
import org.springframework.stereotype.Service;

@Service
public class EmploymentServiceImpl extends ServiceImpl<EmploymentMapper, Employment> implements EmploymentService {
}
