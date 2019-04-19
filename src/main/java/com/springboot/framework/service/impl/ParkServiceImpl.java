package com.springboot.framework.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.springboot.framework.controller.response.PageResponseBean;
import com.springboot.framework.dao.entity.AppDetail;
import com.springboot.framework.dao.entity.Area;
import com.springboot.framework.dao.entity.Connection;
import com.springboot.framework.dao.entity.Park;
import com.springboot.framework.dao.mapper.AppDetailMapper;
import com.springboot.framework.dao.mapper.AreaMapper;
import com.springboot.framework.dao.mapper.ConnectionMapper;
import com.springboot.framework.dao.mapper.ParkMapper;
import com.springboot.framework.service.ParkService;
import com.springboot.framework.util.ResponseEntity;
import com.springboot.framework.util.ResponseEntityUtil;
import com.springboot.framework.vo.ParkVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ParkServiceImpl implements ParkService {
    @Resource
    private ParkMapper parkMapper;
    @Resource
    private AreaMapper areaMapper;
    @Resource
    private ConnectionMapper connectionMapper;
    @Resource
    private AppDetailMapper appDetailMapper;

    @Override
    public ResponseEntity<Integer> deleteByPrimaryKey(Integer id, String updateBy) {
        return ResponseEntityUtil.success(parkMapper.deleteByPrimaryKey(id, updateBy));
    }

    @Override
    public ResponseEntity<Integer> insertSelective(Park record, Integer[] appIds) {
        if (parkMapper.insertSelective(record) != 1) {
            return ResponseEntityUtil.fail("园区添加失败");
        }
        Integer parkId = record.getId();
        for (Integer appId : appIds) {
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
    public ResponseEntity<Park> selectByPrimaryKey(Integer id) {
        return ResponseEntityUtil.success(parkMapper.selectByPrimaryKey(id));
    }

    @Override
    public PageResponseBean selectList(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Park> parkList = parkMapper.selectList();
        List<ParkVO> parkVOList = Lists.newArrayList();
        for (Park park : parkList) {
            Area area = areaMapper.selectByAreaCode(park.getLocation());
            ParkVO parkVO = new ParkVO(park, area);
            parkVOList.add(parkVO);
        }
        PageInfo pageInfo = new PageInfo(parkList);
        pageInfo.setList(parkVOList);
        PageResponseBean page = new PageResponseBean<Park>(pageInfo);
        page.setCode(0);
        page.setHttpStatus(200);
        return page;
    }

    @Override
    public PageResponseBean selectListByAppId(Integer pageNum, Integer pageSize, Integer appId) {
        PageHelper.startPage(pageNum, pageSize);
        List<Integer> parkIdList = connectionMapper.selectListByAppIdInteger(appId);
        List<ParkVO> parkVOList = Lists.newArrayList();
        for (Integer parkId : parkIdList) {
            Park park = parkMapper.selectByPrimaryKey(parkId);
            Area area = areaMapper.selectByAreaCode(park.getLocation());
            ParkVO parkVO = new ParkVO(park, area);
            if (parkVO.getStatus() != -1) {
                parkVOList.add(parkVO);
            }
        }
        PageInfo pageInfo = new PageInfo(parkIdList);
        pageInfo.setList(parkVOList);
        PageResponseBean page = new PageResponseBean<Park>(pageInfo);
        page.setCode(0);
        page.setHttpStatus(200);
        return page;
    }

    @Override
    public ResponseEntity<Integer> updateByPrimaryKeySelective(Park record) {
        if (parkMapper.updateByPrimaryKeySelective(record) != 1) {
            return ResponseEntityUtil.fail("园区更新失败");
        }
        return ResponseEntityUtil.success();
    }

    @Override
    public ResponseEntity<Integer> updateStatus(Integer id, Byte status, String updateBy) {
        Park record = new Park();
        record.setId(id);
        record.setStatus(status);
        record.setUpdateBy(updateBy);
        return ResponseEntityUtil.success(parkMapper.updateByPrimaryKeySelective(record));
    }
}
