package com.springboot.framework.dao.mapper;

import com.springboot.framework.dao.entity.AppDetail;
import org.apache.ibatis.annotations.Select;

public interface AppDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AppDetail record);

    int insertSelective(AppDetail record);

    AppDetail selectByPrimaryKey(Integer id);

    @Select("SELECT * FROM tb_app_detail WHERE app_id = #{appId} AND park_id = #{parkId}")
    AppDetail selectByAppIdAndParkId(Integer appId, Integer parkId);

    int updateByPrimaryKeySelective(AppDetail record);

    int updateByPrimaryKeyWithBLOBs(AppDetail record);

    int updateByPrimaryKey(AppDetail record);
}