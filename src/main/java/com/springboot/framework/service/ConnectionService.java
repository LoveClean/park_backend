package com.springboot.framework.service;

import com.springboot.framework.controller.response.PageResponseBean;
import com.springboot.framework.dao.entity.Connection;
import com.springboot.framework.util.ResponseEntity;

import java.util.List;

public interface ConnectionService {
    ResponseEntity<Integer> insertSelective(Connection record);

    List<String> selectListByParkId(Integer parkId);

    List<String> selectListByAppId(Integer appId);
}
