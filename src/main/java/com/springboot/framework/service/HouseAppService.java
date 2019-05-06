package com.springboot.framework.service;

import com.springboot.framework.controller.response.PageResponseBean;
import com.springboot.framework.dao.entity.House;
import com.springboot.framework.util.ResponseEntity;

import java.util.List;

/**
 * @Author SWF
 * @Date 2019/5/5 21:28
 **/
public interface HouseAppService {

    PageResponseBean selectListByParkId(int pageNum, int pageSize, Integer parkId);

    ResponseEntity<Object> deleteByHouseId(int houseId);

    ResponseEntity<Object> insert(House house, List<String> imgs);

    ResponseEntity<Object> update(House house, List<String> newImg, List<String> delImg);

    ResponseEntity<Object> selectImg(Integer houseId);
}
