package com.springboot.framework.dao.mapper;

import com.springboot.framework.dao.entity.InformationInfo;

public interface InformationInfoMapper {
    int deleteByPrimaryKey(Integer informationId);

    int insert(InformationInfo record);

    int insertSelective(InformationInfo record);

    InformationInfo selectByPrimaryKey(Integer informationId);

    int updateByPrimaryKeySelective(InformationInfo record);

    int updateByPrimaryKeyWithBLOBs(InformationInfo record);

    int updateByPrimaryKey(InformationInfo record);
}