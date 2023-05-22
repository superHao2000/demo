package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.pojo.Student;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author super
 * @date 2023/05/21
 */
@Mapper
public interface StudentMapper extends BaseMapper<Student> {
}
