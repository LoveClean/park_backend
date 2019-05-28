package com.springboot.framework.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot.framework.controller.response.PageResponseBean;
import com.springboot.framework.dao.entity.Enterprise;
import com.springboot.framework.dao.mapper.EnterpriseMapper;
import com.springboot.framework.util.PageUtil;
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
        return PageUtil.page(enterpriseList);
    }


    public ResponseEntity<Object> deleteById(int houseId) {
        if (enterpriseMapper.deleteById(houseId) == 0) {
            return ResponseEntityUtil.fail("企业删除失败");
        }
        return ResponseEntityUtil.success();
    }


    public ResponseEntity<Object> insert(Enterprise enterprise) {
//        // 判断企业名是否存在
//        if (!checkEnterpriseName(enterprise.getName(), null)) {
//            return ResponseEntityUtil.fail("该企业已存在");
//        }
        if (enterpriseMapper.insertSelective(enterprise) == 0) {
            return ResponseEntityUtil.fail("企业新增失败");
        }
        return ResponseEntityUtil.success();
    }


    public ResponseEntity<Object> update(Enterprise enterprise) {
        // 判断企业名是否重复
//        if (!checkEnterpriseName(enterprise.getName(), enterprise.getId())) {
//            return ResponseEntityUtil.fail("该企业已存在");
//        }
        if (enterpriseMapper.updateByPrimaryKeySelective(enterprise) == 0) {
            return ResponseEntityUtil.fail("企业修改失败");
        }
        return ResponseEntityUtil.success();
    }

    // 检查企业名称,通过true，不通过false
    private boolean checkEnterpriseName(String name, Integer id) {
        Enterprise enterprise = enterpriseMapper.selectByName(name);
        if (enterprise == null) {
            return true;
        } else {
            if (id != null && enterprise.getId().equals(id)) {
                return true;
            } else {
                return false;
            }
        }
    }
}
