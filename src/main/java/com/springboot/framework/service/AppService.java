package com.springboot.framework.service;

import com.springboot.framework.controller.response.PageResponseBean;
import com.springboot.framework.dao.entity.App;
import com.springboot.framework.dao.entity.AppDetail;
import com.springboot.framework.dto.AppDTO;
import com.springboot.framework.util.ResponseEntity;
import com.springboot.framework.vo.AppDetailVO;

import java.util.List;

public interface AppService {
    ResponseEntity<Integer> deleteByPrimaryKey(AppDTO recordDTO);

    ResponseEntity<Integer> insertSelective(App record, Integer[] parkIds);

    ResponseEntity<AppDetailVO> selectByPrimaryKey(Integer id);

    PageResponseBean selectList(Integer pageNum, Integer pageSize);

    PageResponseBean selectListByParkId(Integer pageNum, Integer pageSize, Integer parkId);

    ResponseEntity<List<App>> selectList();

    ResponseEntity<Integer> updateByPrimaryKeySelective(App record, Integer[] parkIds2, Integer[] parkIds3);

    ResponseEntity<Integer> updateStatus(Integer id, Byte status, String updateBy);

    /////////////////应用详情/////////////////
    ResponseEntity<Object> selectByPrimaryKeyForDetail(Integer appId, Integer parkId);

    ResponseEntity<Integer> updateByPrimaryKeySelectiveForDetail(AppDetail record);
}
