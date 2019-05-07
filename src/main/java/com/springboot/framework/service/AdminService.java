package com.springboot.framework.service;

import com.springboot.framework.contants.Errors;
import com.springboot.framework.controller.response.PageResponseBean;
import com.springboot.framework.dao.entity.Admin;
import com.springboot.framework.dto.AdminDTO;
import com.springboot.framework.util.ResponseEntity;

public interface AdminService {
    ResponseEntity<Errors> deleteByPrimaryKey(AdminDTO adminDTO);

    ResponseEntity<Errors> insertSelective(AdminDTO adminDTO);

    ResponseEntity<Admin> login(AdminDTO adminDTO);

    ResponseEntity<Admin> selectByPrimaryKey(Integer id);

    PageResponseBean selectList(Integer pageNum, Integer pageSize);

    PageResponseBean selectListByPhone(String phone, Integer pageNum, Integer pageSize);

    ResponseEntity<Integer> selectCount();

    ResponseEntity<Errors> updateByPrimaryKeySelective(AdminDTO adminDTO);

    ResponseEntity<Errors> updateByPassword(Integer id, String oldPassword, String newPassword, String updateBy);
}
