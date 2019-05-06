package com.springboot.framework.dao.mapper;

import com.springboot.framework.dao.entity.Enterprise;
import com.springboot.framework.dao.entity.House;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface EnterpriseMapper {
    int deleteByPrimaryKey(Integer id);

    @Update("update tb_enterprise set status = -1 where id = #{id} ")
    int deleteById(@Param("id") Integer id);

    int insert(Enterprise record);

    int insertSelective(Enterprise record);

    Enterprise selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Enterprise record);

    int updateByPrimaryKey(Enterprise record);

    @Select("select * from tb_enterprise where park_id = #{parkId} and status != -1 ORDER BY create_date DESC")
    List<Enterprise> selectListByParkId(@Param("parkId") Integer parkId);
}