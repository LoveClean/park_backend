package com.springboot.framework.controller;

import com.springboot.framework.controller.request.InformationInsertRequestBean;
import com.springboot.framework.controller.request.InformationUpdateRequestBean;
import com.springboot.framework.controller.response.PageResponseBean;
import com.springboot.framework.dao.entity.Admin;
import com.springboot.framework.service.InformationService;
import com.springboot.framework.util.ResponseEntity;
import com.springboot.framework.util.ResponseEntityUtil;
import com.springboot.framework.vo.InformationInfoVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author SWF
 * @Date 2019/5/10 14:25
 **/
@Api(tags = "资讯", produces = "application/json")
@RestController
@RequestMapping("/information")
public class InformationController extends BaseController{

    @Autowired
    private InformationService informationService;


    @ApiOperation(value = "查看列表", notes = "查看轮播图列表")
    @GetMapping(value = "/selectList")
    public PageResponseBean listByParkId(Integer parkId , Integer pageNum , Integer pageSize){
        return informationService.listByParkId(parkId,pageNum,pageSize);
    }

    @ApiOperation(value = "查看列表", notes = "查看轮播图列表")
    @GetMapping(value = "/selectInfo")
    public ResponseEntity<InformationInfoVO> selectInfo(Integer id){
        return informationService.selectInfo(id);
    }

    @ApiOperation(value = "新增", notes = "新增")
    @PostMapping(value = "/insert")
    public ResponseEntity<Object> insert(@RequestBody InformationInsertRequestBean bean , HttpServletRequest request){
        Admin admin = super.getSessionUser(request);
        if(bean.getParkId().equals(admin.getParkId())){
            return informationService.insert(bean,admin.getName());
        }
        return ResponseEntityUtil.fail("没有权限");
    }

    @ApiOperation(value = "修改", notes = "修改")
    @PostMapping(value = "/update")
    public ResponseEntity<Object> update(@RequestBody InformationUpdateRequestBean bean ,HttpServletRequest request){
        Admin admin = super.getSessionUser(request);
        if(bean.getParkId().equals(admin.getParkId())){
            return informationService.update(bean,admin.getName());
        }
        return ResponseEntityUtil.fail("没有权限");
    }

    @ApiOperation(value = "删除", notes = "删除")
    @DeleteMapping(value = "/delete")
    public ResponseEntity<Object> delete(Integer id , HttpServletRequest request){
        return informationService.delete(id);
    }

}
