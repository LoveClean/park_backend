package com.springboot.framework.dao.mapper;

import com.springboot.framework.dao.entity.AppDetail;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface AppDetailMapper {
    int deleteByPrimaryKey(Integer id);

    @Delete("DELETE FROM tb_app_detail WHERE app_id = #{appId}")
    int deleteByAppId(@Param("appId") Integer appId);

    @Delete("DELETE FROM tb_app_detail WHERE park_id = #{parkId}")
    int deleteByParkId(@Param("parkId") Integer parkId);

    @Delete("DELETE FROM tb_app_detail WHERE app_id = #{appId} AND park_id = #{parkId}")
    int deleteByParkIdAndAppId(@Param("parkId") Integer parkId, @Param("appId") Integer appId);

    int insert(AppDetail record);

    int insertSelective(AppDetail record);

    AppDetail selectByPrimaryKey(Integer id);

    @Select("SELECT * FROM tb_app_detail WHERE app_id = #{appId} AND park_id = #{parkId}")
    AppDetail selectByAppIdAndParkId(Integer appId, Integer parkId);

    int updateByPrimaryKeySelective(AppDetail record);

    int updateByPrimaryKeyWithBLOBs(AppDetail record);

    int updateByPrimaryKey(AppDetail record);
}