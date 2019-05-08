package com.springboot.framework.service;

import com.springboot.framework.contants.Errors;
import com.springboot.framework.controller.response.PageResponseBean;
import com.springboot.framework.dao.entity.App;
import com.springboot.framework.dao.entity.AppDetail;
import com.springboot.framework.dto.AppDTO;
import com.springboot.framework.util.ResponseEntity;
import com.springboot.framework.vo.AppDetailVO;

import java.util.List;

public interface AppService {
    ResponseEntity<Errors> deleteByPrimaryKey(AppDTO recordDTO);

    ResponseEntity<Errors> insertSelective(AppDTO recordDTO);

    ResponseEntity<AppDetailVO> selectByPrimaryKey(Integer id);

    PageResponseBean selectList(Integer pageNum, Integer pageSize);

    PageResponseBean selectListByParkId(Integer pageNum, Integer pageSize, Integer parkId);

    ResponseEntity<List<App>> selectList();

    ResponseEntity<Errors> updateByPrimaryKeySelective(AppDTO recordDTO);

    ResponseEntity<Errors> updateStatus(AppDTO recordDTO);

    /////////////////应用详情/////////////////
    ResponseEntity<Object> selectByPrimaryKeyForDetail(Integer appId, Integer parkId);

    ResponseEntity<Errors> updateByPrimaryKeySelectiveForDetail(AppDetail record);
}
