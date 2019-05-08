package com.springboot.framework.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.springboot.framework.contants.Errors;
import com.springboot.framework.controller.response.PageResponseBean;
import com.springboot.framework.dao.entity.App;
import com.springboot.framework.dao.entity.AppDetail;
import com.springboot.framework.dao.entity.Connection;
import com.springboot.framework.dao.mapper.AppDetailMapper;
import com.springboot.framework.dao.mapper.AppMapper;
import com.springboot.framework.dao.mapper.ConnectionMapper;
import com.springboot.framework.dto.AppDTO;
import com.springboot.framework.service.AppService;
import com.springboot.framework.util.PageUtil;
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
    public ResponseEntity<Integer> deleteByPrimaryKey(AppDTO recordDTO) {
        appDetailMapper.deleteByAppId(recordDTO.getId());
        connectionMapper.deleteByAppId(recordDTO.getId());
        return ResponseEntityUtil.success(appMapper.deleteByPrimaryKey(recordDTO.getId(), recordDTO.getUpdateBy()));
    }

    @Override
    public ResponseEntity<Integer> insertSelective(AppDTO recordDTO) {
        App record = new App(recordDTO);
        if (appMapper.insertSelective(record) != 1) {
            return ResponseEntityUtil.fail("应用添加失败");
        }
        Integer appId = record.getId();
        Integer[] parkIds = recordDTO.getParkIds();
        if (parkIds.length > 0) {
            connectionForApp(appId, parkIds);
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
        return PageUtil.page(appList);
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
        return PageUtil.page(appIdList, appList);
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
    public ResponseEntity<Integer> updateByPrimaryKeySelective(AppDTO recordDTO) {
        //2.创建entity
        App record = new App(recordDTO);
        //3.响应校验
        if (appMapper.updateByPrimaryKeySelective(record) != 1) {
            return ResponseEntityUtil.fail("应用更新失败");
        }
        Integer appId = record.getId();
        Integer[] parkIds = recordDTO.getParkIds();
        if (parkIds.length > 0) {
            connectionForApp(appId, parkIds);
        }
        Integer[] parkIds3 = recordDTO.getParkIds3();
        if (parkIds3.length > 0) {
            for (Integer parkId3 : parkIds3) {
                connectionMapper.deleteByParkIdAndAppId(parkId3, appId);
                appDetailMapper.deleteByParkIdAndAppId(parkId3, appId);
            }
        }
        return ResponseEntityUtil.success();
    }

    @Override
    public ResponseEntity<Errors> updateStatus(AppDTO recordDTO) {
        //2.创建entity
        App record = new App(recordDTO);
        //3.响应校验
        if (appMapper.updateByPrimaryKeySelective(record) != 1) {
            return ResponseEntityUtil.fail("更新失败");
        }
        return ResponseEntityUtil.success(Errors.SUCCESS);
    }

    @Override
    public ResponseEntity<Object> selectByPrimaryKeyForDetail(Integer appId, Integer parkId) {
        return ResponseEntityUtil.success(appDetailMapper.selectByAppIdAndParkId(appId, parkId));
    }

    @Override
    public ResponseEntity<Integer> updateByPrimaryKeySelectiveForDetail(AppDetail record) {
        if (appDetailMapper.updateByPrimaryKeySelective(record) != 1) {
            return ResponseEntityUtil.fail("应用详情更新失败");
        }
        return ResponseEntityUtil.success();
    }

    private void connectionForApp(Integer appId, Integer[] parkIds) {
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
    }
}