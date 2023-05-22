package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.pojo.Notice;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author super
 * @date 2023/05/21
 */
@Mapper
public interface NoticeMapper extends BaseMapper<Notice> {
}
