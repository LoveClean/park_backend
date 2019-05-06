package com.springboot.framework.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboot.framework.contants.Errors;
import com.springboot.framework.controller.response.PageResponseBean;
import com.springboot.framework.dao.entity.Admin;
import com.springboot.framework.dao.mapper.AdminMapper;
import com.springboot.framework.service.AdminService;
import com.springboot.framework.util.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Resource
    private AdminMapper adminMapper;

    @Override
    public ResponseEntity<Integer> deleteByPrimaryKey(Integer id, String updateBy) {
        return ResponseEntityUtil.success(adminMapper.deleteByPrimaryKey(id, updateBy));
    }

    @Override
    public ResponseEntity<Integer> insertSelective(Admin record) {
        //校验
        Admin validResponse = adminMapper.selectByPhone(record.getPhone());
        if (adminMapper.selectByPhone(record.getPhone()) != null) {
            return ResponseEntityUtil.fail(Errors.USER_MOBILE_EXISTS);
        }
        if (adminMapper.selectByAccount(record.getAccount()) != null) {
            return ResponseEntityUtil.fail("此用户名已被注册");
        }
        //密码通过MD5加密
        record.setPassword(MD5Util.MD5(record.getPassword()));
        int resultCount = adminMapper.insertSelective(record);
        if (resultCount == 0) {
            return ResponseEntityUtil.fail("添加管理员失败");
        }
        return ResponseEntityUtil.success(resultCount);
    }

    @Override
    public ResponseEntity<Admin> login(String phone, String password) {
        if (StringUtil.isBlank(phone) || StringUtil.isBlank(password)) {
            return ResponseEntityUtil.fail(Errors.SYSTEM_REQUEST_PARAM_ERROR);
        }
//        if (!AccountValidatorUtil.isMobile(phone)) {
//            return ResponseEntityUtil.fail("请输入正确的手机号");
//        }
        String MD5Password = MD5Util.MD5(password);
        Admin record = adminMapper.login(phone, MD5Password);
        if (record == null) {
            return ResponseEntityUtil.fail(Errors.USER_LOGIN_ERROR);
        }
        if (record.getStatus() == 0) {
            return ResponseEntityUtil.fail(Errors.SYSTEM_NO_ACCESS);
        }
        if (record.getParkId() != null) {
            return ResponseEntityUtil.fail(Errors.SYSTEM_NO_ACCESS);
        }
        return ResponseEntityUtil.success(record);
    }

    @Override
    public ResponseEntity<Admin> loginParkAdmin(String phone, String password) {
        if (StringUtil.isBlank(phone) || StringUtil.isBlank(password)) {
            return ResponseEntityUtil.fail(Errors.SYSTEM_REQUEST_PARAM_ERROR);
        }
//        if (!AccountValidatorUtil.isMobile(phone)) {
//            return ResponseEntityUtil.fail("请输入正确的手机号");
//        }
        String MD5Password = MD5Util.MD5(password);
        Admin record = adminMapper.login(phone, MD5Password);
        if (record == null) {
            return ResponseEntityUtil.fail(Errors.USER_LOGIN_ERROR);
        }
        if (record.getParkId() == null) {
            return ResponseEntityUtil.fail(Errors.SYSTEM_NO_ACCESS);
        }
        return ResponseEntityUtil.success(record);
    }

    @Override
    public ResponseEntity<Admin> selectByPrimaryKey(Integer id) {
        return ResponseEntityUtil.success(adminMapper.selectByPrimaryKey(id));
    }

    @Override
    public PageResponseBean selectList(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Admin> adminList = adminMapper.selectList();
        PageInfo pageInfo = new PageInfo(adminList);
        pageInfo.setList(adminList);

        PageResponseBean page = new PageResponseBean<Admin>(pageInfo);
        page.setCode(0);
        page.setHttpStatus(200);
        return page;
    }

    @Override
    public PageResponseBean selectListByPhone(String phone, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Admin> adminList = adminMapper.selectListByPhone(phone);
        PageInfo pageInfo = new PageInfo(adminList);
        pageInfo.setList(adminList);

        PageResponseBean page = new PageResponseBean<Admin>(pageInfo);
        page.setCode(0);
        page.setHttpStatus(200);
        return page;
    }

    @Override
    public ResponseEntity<Integer> selectCount() {
        return ResponseEntityUtil.success(adminMapper.selectCount());
    }

    @Override
    public ResponseEntity<Integer> updateByPrimaryKeySelective(Admin record) {
        Admin validResponse = adminMapper.selectByPhone(record.getPhone());
        if (validResponse != null && validResponse.getId() != record.getId()) {
            return ResponseEntityUtil.fail(Errors.USER_MOBILE_EXISTS);
        }
        return ResponseEntityUtil.success(adminMapper.updateByPrimaryKeySelective(record));
    }

    @Override
    public ResponseEntity<Integer> updateByPassword(Integer id, String oldPassword, String newPassword, String updateBy) {
        String MD5OldPassword = MD5Util.MD5(oldPassword);
        String MD5NewPassword = MD5Util.MD5(newPassword);
        int updateCount = adminMapper.updateByPassword(id, MD5OldPassword, MD5NewPassword, updateBy);
        if (updateCount == 0) {
            return ResponseEntityUtil.fail(Errors.USER_OLD_PASSWORD_ERROR);
        }
        return ResponseEntityUtil.success(updateCount);
    }

    @Override
    public ResponseEntity<Integer> updateByPhone(Integer id, String phone, String updateBy) {
        if (adminMapper.selectByPhone(phone) != null) {
            return ResponseEntityUtil.fail(Errors.USER_MOBILE_EXISTS);
        }
        Admin record = new Admin();
        record.setId(id);
        record.setPhone(phone);
        record.setUpdateBy(updateBy);
        return ResponseEntityUtil.success(adminMapper.updateByPrimaryKeySelective(record));
    }

    @Override
    public ResponseEntity<Integer> updateByStatus(Integer id, Byte status, String updateBy) {
        Admin record = new Admin();
        record.setId(id);
        record.setStatus(status);
        record.setUpdateBy(updateBy);
        return ResponseEntityUtil.success(adminMapper.updateByPrimaryKeySelective(record));
    }
}
