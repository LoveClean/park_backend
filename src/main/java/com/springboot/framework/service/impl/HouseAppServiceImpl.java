package com.springboot.framework.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot.framework.controller.response.PageResponseBean;
import com.springboot.framework.dao.entity.House;
import com.springboot.framework.dao.entity.HousePicture;
import com.springboot.framework.dao.mapper.HouseMapper;
import com.springboot.framework.dao.mapper.HousePictureMapper;
import com.springboot.framework.util.ResponseEntity;
import com.springboot.framework.util.ResponseEntityUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author SWF
 * @Date 2019/4/25 10:16
 **/
@Service
public class HouseAppServiceImpl {
    @Resource
    private HouseMapper houseMapper;

    @Resource
    private HousePictureMapper housePictureMapper;

    public PageResponseBean selectListByParkId(int pageNum , int pageSize , Integer parkId){
        PageHelper.startPage(pageNum,pageSize);
        List<House> houseList = houseMapper.selectListByParkId(parkId);
        PageInfo pageInfo =new PageInfo(houseList);

        PageResponseBean bean = new PageResponseBean(pageInfo);
        bean.setCode(0);
        bean.setHttpStatus(200);
        return bean;
    }

    public ResponseEntity<Object> deleteByHouseId(int houseId){
        if(houseMapper.deleteByHouseId(houseId) == 0){
            return ResponseEntityUtil.fail("房源删除失败");
        }
        return ResponseEntityUtil.success();
    }

    public ResponseEntity<Object> insert(House house , List<String> imgs){
        if( houseMapper.insertSelective(house) == 0){
            return ResponseEntityUtil.fail("房源新增失败");
        }
        for (String img : imgs){
            HousePicture housePicture = new HousePicture();
            housePicture.setHouseId(house.getId());
            housePicture.setPicture(img);
            housePictureMapper.insertSelective(housePicture);
        }
        return ResponseEntityUtil.success();
    }

    public ResponseEntity<Object> update(House house , List<String> imgs){
        if( houseMapper.updateByPrimaryKeySelective(house) == 0){
            return ResponseEntityUtil.fail("房源修改失败");
        }
//        for (String img : imgs){
//            HousePicture housePicture = new HousePicture();
//            housePicture.setHouseId(house.getId());
//            housePicture.setPicture(img);
//            housePictureMapper.insertSelective(housePicture);
//        }
        return ResponseEntityUtil.success();
    }

    public ResponseEntity<Object> selectImg(Integer houseId){
        return ResponseEntityUtil.success(housePictureMapper.selectListByHouseId(houseId));
    }
}
