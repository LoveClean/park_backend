package com.springboot.framework.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.springboot.framework.contants.Errors;
import com.springboot.framework.controller.request.ParkInsertSelectiveForMember;
import com.springboot.framework.controller.response.PageResponseBean;
import com.springboot.framework.dao.entity.*;
import com.springboot.framework.dao.mapper.*;
import com.springboot.framework.dto.AdminDTO;
import com.springboot.framework.dto.ParkDTO;
import com.springboot.framework.service.ParkService;
import com.springboot.framework.util.MD5Util;
import com.springboot.framework.util.PageUtil;
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
    @Resource
    private AdminMapper adminMapper;

    @Override
    public ResponseEntity<Errors> deleteByPrimaryKey(ParkDTO parkDTO) {
        appDetailMapper.deleteByParkId(parkDTO.getId());
        connectionMapper.deleteByParkId(parkDTO.getId());
        //2.创建entity
        Park park = new Park(parkDTO);
        //3.响应校验
        if (parkMapper.deleteByPrimaryKey(park.getId(), park.getUpdateBy()) == 0) {
            return ResponseEntityUtil.fail("删除失败");
        }
        return ResponseEntityUtil.success(Errors.SUCCESS);
    }

    @Override
    public ResponseEntity<Errors> insertSelective(ParkDTO parkDTO) {
        //1.请求校验
        if (parkMapper.selectByName(parkDTO.getName()) != null) {
            return ResponseEntityUtil.fail("此园区已申请注册");
        }
        //2.创建entity
        Park park = new Park(parkDTO);
        //3.响应校验
        if (parkMapper.insertSelective(park) != 1) {
            return ResponseEntityUtil.fail("园区添加失败");
        }
        Integer parkId = park.getId();
        Integer[] appIds = parkDTO.getAppIds();
        if (appIds.length != 0) {
            connectionForPark(parkId, appIds);
        }
        return ResponseEntityUtil.success(Errors.SUCCESS);
    }

    @Override
    public ResponseEntity<Errors> insertSelectiveForMember(ParkInsertSelectiveForMember bean) {
        ParkDTO parkDTO = new ParkDTO(bean.getParkName(), bean.getLogo(), bean.getLocation(), bean.getAddress(), bean.getLongitude(), bean.getLatitude(), bean.getIntroduction(), bean.getSort(), "游客", null);
        Park park = new Park(parkDTO);
        park.setStatus((byte) 0);
        if (parkMapper.selectByName(park.getName()) != null) {
            return ResponseEntityUtil.fail("此园区已申请注册");
        }
        if (parkMapper.insertSelective(park) != 1) {
            return ResponseEntityUtil.fail("园区添加失败");
        }
        AdminDTO adminDTO = new AdminDTO(bean.getAccount(), bean.getPassword(), bean.getPhone(), bean.getUserName(), "游客", park.getId());
        Admin admin = new Admin(adminDTO);
        admin.setStatus((byte) 0);
        //校验
        if (adminMapper.selectByPhone(admin.getPhone()) != null) {
            return ResponseEntityUtil.fail(Errors.USER_MOBILE_EXISTS);
        }
        if (adminMapper.selectByAccount(admin.getAccount()) != null) {
            return ResponseEntityUtil.fail("此用户名已被注册");
        }
        //密码通过MD5加密
        admin.setPassword(MD5Util.MD5(admin.getPassword()));
        int resultCount = adminMapper.insertSelective(admin);
        if (resultCount == 0) {
            return ResponseEntityUtil.fail("管理员申请失败");
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
        return PageUtil.page(parkList, parkVOList);
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
        return PageUtil.page(parkIdList, parkVOList);
    }

    @Override
    public ResponseEntity<Errors> updateByPrimaryKeySelective(ParkDTO parkDTO) {
        //2.创建entity
        Park park = new Park(parkDTO);
        //3.响应校验
        if (parkMapper.updateByPrimaryKeySelective(park) != 1) {
            return ResponseEntityUtil.fail("园区更新失败");
        }
        Integer parkId = parkDTO.getId();
        Integer[] appIds = parkDTO.getAppIds2();
        if (appIds.length != 0) {
            connectionForPark(parkId, appIds);
        }
        Integer[] appIds3 = parkDTO.getAppIds3();
        if (appIds3.length != 0) {
            for (Integer appId3 : appIds3) {
                connectionMapper.deleteByParkIdAndAppId(parkId, appId3);
                appDetailMapper.deleteByParkIdAndAppId(parkId, appId3);
            }
        }
        return ResponseEntityUtil.success();
    }

    @Override
    public ResponseEntity<Errors> updateStatus(ParkDTO parkDTO) {
        //2.创建entity
        Park park = new Park(parkDTO);
        //3.响应校验
        if (parkMapper.updateByPrimaryKeySelective(park) != 1) {
            return ResponseEntityUtil.fail("更新失败");
        }
        return ResponseEntityUtil.success(Errors.SUCCESS);
    }

    private void connectionForPark(Integer parkId, Integer[] appIds) {
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
    }
}
