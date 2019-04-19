package com.springboot.framework.dao.mapper;

import com.springboot.framework.dao.entity.Connection;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ConnectionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Connection record);

    int insertSelective(Connection record);

    Connection selectByPrimaryKey(Integer id);

    @Select("SELECT app_id FROM tb_connection WHERE park_id = #{parkId}")
    List<String> selectListByParkId(@Param("parkId") Integer parkId);

    @Select("SELECT app_id FROM tb_connection WHERE park_id = #{parkId}")
    List<Integer> selectListByParkIdInteger(@Param("parkId") Integer parkId);

    @Select("SELECT park_id FROM tb_connection WHERE app_id = #{appId}")
    List<String> selectListByAppId(@Param("appId") Integer appId);

    @Select("SELECT park_id FROM tb_connection WHERE app_id = #{appId}")
    List<Integer> selectListByAppIdInteger(@Param("appId") Integer appId);

    int updateByPrimaryKeySelective(Connection record);

    int updateByPrimaryKey(Connection record);
}