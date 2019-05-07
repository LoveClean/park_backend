package com.springboot.framework.service;

import com.springboot.framework.contants.Errors;
import com.springboot.framework.controller.request.ParkInsertSelectiveForMember;
import com.springboot.framework.controller.response.PageResponseBean;
import com.springboot.framework.dao.entity.Admin;
import com.springboot.framework.dao.entity.Park;
import com.springboot.framework.dto.ParkDTO;
import com.springboot.framework.util.ResponseEntity;

public interface ParkService {
    ResponseEntity<Errors> deleteByPrimaryKey(ParkDTO recordDTO);

    ResponseEntity<Errors> insertSelective(ParkDTO recordDTO);

    ResponseEntity<Errors> insertSelectiveForMember(ParkInsertSelectiveForMember bean);

    ResponseEntity<Park> selectByPrimaryKey(Integer id);

    PageResponseBean selectList(Integer pageNum, Integer pageSize);

    PageResponseBean selectListByAppId(Integer pageNum, Integer pageSize, Integer appId);

    ResponseEntity<Errors> updateByPrimaryKeySelective(ParkDTO recordDTO);

    ResponseEntity<Errors> updateStatus(ParkDTO recordDTO);
}
