package com.springboot.framework.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot.framework.controller.response.PageResponseBean;
import com.springboot.framework.dao.entity.Connection;
import com.springboot.framework.dao.mapper.ConnectionMapper;
import com.springboot.framework.service.ConnectionService;
import com.springboot.framework.util.ResponseEntity;
import com.springboot.framework.util.ResponseEntityUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ConnectionServiceImpl implements ConnectionService {
    @Resource
    private ConnectionMapper connectionMapper;

    @Override
    public ResponseEntity<Integer> insertSelective(Connection record) {
        return ResponseEntityUtil.success(connectionMapper.insertSelective(record));
    }

    @Override
    public List<String> selectListByParkId(Integer parkId) {
        return connectionMapper.selectListByParkId(parkId);
    }

    @Override
    public List<String> selectListByAppId(Integer appId) {
        return connectionMapper.selectListByAppId(appId);
    }

}
