package com.springboot.framework.dao.mapper;

import com.springboot.framework.dao.entity.Area;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface AreaMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Area record);

    int insertSelective(Area record);

    Area selectByPrimaryKey(Integer id);

    @Select("SELECT * FROM tb_area WHERE area_code = #{areaCode}")
    Area selectByAreaCode(@Param("areaCode") String areaCode);

    int updateByPrimaryKeySelective(Area record);

    int updateByPrimaryKey(Area record);
}