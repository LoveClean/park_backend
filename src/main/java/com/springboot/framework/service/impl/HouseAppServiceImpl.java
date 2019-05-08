package com.springboot.framework.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot.framework.controller.response.PageResponseBean;
import com.springboot.framework.dao.entity.House;
import com.springboot.framework.dao.entity.HousePicture;
import com.springboot.framework.dao.mapper.HouseMapper;
import com.springboot.framework.dao.mapper.HousePictureMapper;
import com.springboot.framework.service.HouseAppService;
import com.springboot.framework.util.PageUtil;
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
public class HouseAppServiceImpl implements HouseAppService {
    @Resource
    private HouseMapper houseMapper;

    @Resource
    private HousePictureMapper housePictureMapper;

    @Override
    public PageResponseBean selectListByParkId(int pageNum, int pageSize, Integer parkId) {
        PageHelper.startPage(pageNum, pageSize);
        List<House> houseList = houseMapper.selectListByParkId(parkId);
        return PageUtil.page(houseList);
    }

    @Override
    public ResponseEntity<Object> deleteByHouseId(int houseId) {
        if (houseMapper.deleteByHouseId(houseId) == 0) {
            return ResponseEntityUtil.fail("房源删除失败");
        }
        return ResponseEntityUtil.success();
    }

    @Override
    public ResponseEntity<Object> insert(House house, List<String> imgs) {
        if (houseMapper.insertSelective(house) == 0) {
            return ResponseEntityUtil.fail("房源新增失败");
        }
        for (String img : imgs) {
            HousePicture housePicture = new HousePicture();
            housePicture.setHouseId(house.getId());
            housePicture.setPicture(img);
            housePictureMapper.insertSelective(housePicture);
        }
        return ResponseEntityUtil.success();
    }

    @Override
    public ResponseEntity<Object> update(House house, List<String> newImg, List<String> delImg) {
        if (houseMapper.updateByPrimaryKeySelective(house) == 0) {
            return ResponseEntityUtil.fail("房源修改失败");
        }
        // 新增图片
        if (newImg.size() > 0) {
            for (String img : newImg) {
                HousePicture housePicture = new HousePicture();
                housePicture.setHouseId(house.getId());
                housePicture.setPicture(img);
                housePictureMapper.insertSelective(housePicture);
            }
        }
        // 删除图片
        if (delImg.size() > 0) {
            housePictureMapper.batchDeleteByImgUrl(delImg);
        }
        return ResponseEntityUtil.success();
    }

    @Override
    public ResponseEntity<Object> selectImg(Integer houseId) {
        return ResponseEntityUtil.success(housePictureMapper.selectListByHouseId(houseId));
    }
}
