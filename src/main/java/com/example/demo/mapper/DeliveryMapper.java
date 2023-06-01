package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.pojo.Delivery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author super
 * @date 2023/05/21
 */
@Mapper
public interface DeliveryMapper extends BaseMapper<Delivery> {
    @Select("<script>SELECT resume_id FROM delivery WHERE recruitment_id IN " +
            "<foreach item='item' collection='recruitmentIds' open='(' separator=',' close=')'>" +
            "#{item}" +
            "</foreach></script>")
    List<String> selectResumeIdsByRecruitmentIds(@Param("recruitmentIds") List<Integer> recruitmentIds);
}
