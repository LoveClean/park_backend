package com.springboot.framework.dao.mapper;

import com.springboot.framework.dao.entity.Information;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface InformationMapper{

    @Update("update tb_information set status = -1 where id = #{id} ")
    int deleteById(@Param("id") Integer id);

    int deleteByPrimaryKey(Integer id);

    int insert(Information record);

    int insertSelective(Information record);

    Information selectByPrimaryKey(Integer id);

    @Select("select * from tb_information where park_id = #{parkId} and status != -1 ORDER BY create_date DESC")
    List<Information> listByParkId(@Param("parkId") Integer parkId);

    int updateByPrimaryKeySelective(Information record);

    int updateByPrimaryKey(Information record);
}