package com.springboot.framework.dao.mapper;

import com.springboot.framework.dao.entity.Slideshow;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface SlideshowMapper {
    int deleteByPrimaryKey(Integer id);

    @Update("update tb_slideshow set status = -1 where id = #{id} ")
    int deleteById(Integer id);

    int insert(Slideshow record);

    int insertSelective(Slideshow record);

    Slideshow selectByPrimaryKey(Integer id);

    @Select("select * from tb_slideshow where park_id = #{parkId} and status != -1 ORDER BY create_date DESC")
    List<Slideshow> listByParkId(@Param("parkId") Integer parkId);

    int updateByPrimaryKeySelective(Slideshow record);

    int updateByPrimaryKey(Slideshow record);
}