package com.springboot.framework.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot.framework.controller.response.PageResponseBean;
import com.springboot.framework.dao.entity.Enterprise;
import com.springboot.framework.dao.entity.House;
import com.springboot.framework.dao.entity.HousePicture;
import com.springboot.framework.dao.mapper.EnterpriseMapper;
import com.springboot.framework.util.ResponseEntity;
import com.springboot.framework.util.ResponseEntityUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author SWF
 * @Date 2019/5/5 21:28
 **/
@Service
public class EnterpriseAppServiceImpl {
    @Resource
    private EnterpriseMapper enterpriseMapper;


    public PageResponseBean selectListByParkId(int pageNum, int pageSize, Integer parkId) {
        PageHelper.startPage(pageNum, pageSize);
        List<Enterprise> enterpriseList = enterpriseMapper.selectListByParkId(parkId);
        PageInfo pageInfo = new PageInfo(enterpriseList);

        PageResponseBean bean = new PageResponseBean(pageInfo);
        bean.setCode(0);
        bean.setHttpStatus(200);
        return bean;
    }


    public ResponseEntity<Object> deleteById(int houseId) {
        if (enterpriseMapper.deleteById(houseId) == 0) {
            return ResponseEntityUtil.fail("企业删除失败");
        }
        return ResponseEntityUtil.success();
    }


    public ResponseEntity<Object> insert(Enterprise enterprise) {
        if (enterpriseMapper.insertSelective(enterprise) == 0) {
            return ResponseEntityUtil.fail("企业新增失败");
        }
        return ResponseEntityUtil.success();
    }


    public ResponseEntity<Object> update(Enterprise enterprise) {
        if (enterpriseMapper.updateByPrimaryKeySelective(enterprise) == 0) {
            return ResponseEntityUtil.fail("企业修改失败");
        }
        return ResponseEntityUtil.success();
    }
}
