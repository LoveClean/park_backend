package com.springboot.framework.dao.mapper;

import com.springboot.framework.dao.entity.App;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface AppMapper {
    @Update("UPDATE tb_app SET status = -1, update_by = #{updateBy} WHERE id = #{id}")
    int deleteByPrimaryKey(@Param("id") Integer id, @Param("updateBy") String updateBy);

    int insert(App record);

    int insertSelective(App record);

    App selectByPrimaryKey(Integer id);

    @Select("SELECT * FROM tb_app WHERE status != -1 ORDER BY create_date DESC")
    List<App> selectList();

//    @Select("SELECT * FROM tb_app WHERE status != -1 AND  ORDER BY create_date DESC")
//    List<App> selectListByParkId(Integer parkId);

    int updateByPrimaryKeySelective(App record);

    int updateByPrimaryKey(App record);
}