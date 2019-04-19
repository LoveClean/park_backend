package com.springboot.framework.service;

import com.springboot.framework.controller.response.PageResponseBean;
import com.springboot.framework.dao.entity.Park;
import com.springboot.framework.util.ResponseEntity;

public interface ParkService {
    ResponseEntity<Integer> deleteByPrimaryKey(Integer id, String updateBy);

    ResponseEntity<Integer> insertSelective(Park record, Integer[] appIds);

    ResponseEntity<Park> selectByPrimaryKey(Integer id);

    PageResponseBean selectList(Integer pageNum, Integer pageSize);

    PageResponseBean selectListByAppId(Integer pageNum, Integer pageSize, Integer appId);

    ResponseEntity<Integer> updateByPrimaryKeySelective(Park record);

    ResponseEntity<Integer> updateStatus(Integer id, Byte status, String updateBy);
}
