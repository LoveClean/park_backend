package com.springboot.framework.dao.mapper;

import com.springboot.framework.dao.entity.House;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface HouseMapper {
    int deleteByPrimaryKey(Integer id);

    @Update("update tb_house set status = -1 where id = #{id} ")
    int deleteByHouseId(@Param("id") Integer id);

    int insert(House record);

    int insertSelective(House record);

    House selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(House record);

    int updateByPrimaryKey(House record);
    @Select("select * from tb_house where park_id = #{parkId} and status != -1 ORDER BY create_date DESC")
    List<House> selectListByParkId(@Param("parkId") Integer parkId);
}