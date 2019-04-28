package com.springboot.framework.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.springboot.framework.controller.response.PageResponseBean;
import com.springboot.framework.dao.entity.App;
import com.springboot.framework.dao.entity.AppDetail;
import com.springboot.framework.dao.entity.Connection;
import com.springboot.framework.dao.mapper.AppDetailMapper;
import com.springboot.framework.dao.mapper.AppMapper;
import com.springboot.framework.dao.mapper.ConnectionMapper;
import com.springboot.framework.service.AppService;
import com.springboot.framework.util.ResponseEntity;
import com.springboot.framework.util.ResponseEntityUtil;
import com.springboot.framework.vo.AppDetailVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AppServiceImpl implements AppService {
    @Resource
    private AppMapper appMapper;
    @Resource
    private AppDetailMapper appDetailMapper;
    @Resource
    private ConnectionMapper connectionMapper;

    @Override
    public ResponseEntity<Integer> deleteByPrimaryKey(Integer id, String updateBy) {
        appDetailMapper.deleteByAppId(id);
        connectionMapper.deleteByAppId(id);
        return ResponseEntityUtil.success(appMapper.deleteByPrimaryKey(id, updateBy));
    }

    @Override
    public ResponseEntity<Integer> insertSelective(App record, Integer[] parkIds) {
        if (appMapper.insertSelective(record) != 1) {
            return ResponseEntityUtil.fail("应用添加失败");
        }
        Integer appId = record.getId();
        for (Integer parkId : parkIds) {
            Connection connection = new Connection();
            connection.setAppId(appId);
            connection.setParkId(parkId);
            connectionMapper.insertSelective(connection);
            AppDetail appDetail = new AppDetail();
            appDetail.setAppId(appId);
            appDetail.setParkId(parkId);
            appDetailMapper.insertSelective(appDetail);
        }
        return ResponseEntityUtil.success();
    }

    @Override
    public ResponseEntity<AppDetailVO> selectByPrimaryKey(Integer id) {
        App app = appMapper.selectByPrimaryKey(id);
        AppDetail appDetail = appDetailMapper.selectByPrimaryKey(id);
        AppDetailVO appDetailVO = new AppDetailVO(app, appDetail);
        return ResponseEntityUtil.success(appDetailVO);
    }

    @Override
    public PageResponseBean selectList(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<App> appList = appMapper.selectList();
        PageInfo pageInfo = new PageInfo(appList);
        pageInfo.setList(appList);
        PageResponseBean page = new PageResponseBean<App>(pageInfo);
        page.setCode(0);
        page.setHttpStatus(200);
        return page;
    }

    @Override
    public PageResponseBean selectListByParkId(Integer pageNum, Integer pageSize, Integer parkId) {
        PageHelper.startPage(pageNum, pageSize);
        List<Integer> appIdList = connectionMapper.selectListByParkIdInteger(parkId);
        List<App> appList = Lists.newArrayList();
        for (Integer appId : appIdList) {
            App app = appMapper.selectByPrimaryKey(appId);
            if (app.getStatus() != -1) {
                appList.add(app);
            }
        }
        PageInfo pageInfo = new PageInfo(appIdList);
        pageInfo.setList(appList);
        PageResponseBean page = new PageResponseBean<App>(pageInfo);
        page.setCode(0);
        page.setHttpStatus(200);
        return page;
    }

    @Override
    public ResponseEntity<List<App>> selectList() {
        List<App> list = appMapper.selectList();
        if (list.size() == 0) {
            return ResponseEntityUtil.fail("没有任何数据!");
        }
        return ResponseEntityUtil.success(list);
    }

    @Override
    public ResponseEntity<Integer> updateByPrimaryKeySelective(App record, Integer[] parkIds2, Integer[] parkIds3) {
        if (appMapper.updateByPrimaryKeySelective(record) != 1) {
            return ResponseEntityUtil.fail("应用更新失败");
        }
        Integer appId = record.getId();
        if (parkIds2.length != 0) {
            for (Integer parkId2 : parkIds2) {
                Connection connection = new Connection();
                connection.setAppId(appId);
                connection.setParkId(parkId2);
                connectionMapper.insertSelective(connection);
                AppDetail appDetail = new AppDetail();
                appDetail.setAppId(appId);
                appDetail.setParkId(parkId2);
                appDetailMapper.insertSelective(appDetail);
            }
        }
        if (parkIds3.length != 0) {
            for (Integer parkId3 : parkIds3) {
                connectionMapper.deleteByParkIdAndAppId(parkId3, appId);
                appDetailMapper.deleteByParkIdAndAppId(parkId3, appId);
            }
        }
        return ResponseEntityUtil.success();
    }

    @Override
    public ResponseEntity<Integer> updateStatus(Integer id, Byte status, String updateBy) {
        App record = new App();
        record.setId(id);
        record.setStatus(status);
        record.setUpdateBy(updateBy);
        return ResponseEntityUtil.success(appMapper.updateByPrimaryKeySelective(record));
    }

    @Override
    public ResponseEntity<AppDetail> selectByPrimaryKeyForDetail(Integer appId, Integer parkId) {
        return ResponseEntityUtil.success(appDetailMapper.selectByAppIdAndParkId(appId, parkId));
    }

    @Override
    public ResponseEntity<Integer> updateByPrimaryKeySelectiveForDetail(AppDetail record) {
        if (appDetailMapper.updateByPrimaryKeySelective(record) != 1) {
            return ResponseEntityUtil.fail("应用详情更新失败");
        }
        return ResponseEntityUtil.success();
    }
}