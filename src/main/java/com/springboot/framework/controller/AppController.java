package com.springboot.framework.controller;

import com.alibaba.fastjson.JSON;
import com.springboot.framework.annotation.ACS;
import com.springboot.framework.constant.Errors;
import com.springboot.framework.controller.request.*;
import com.springboot.framework.controller.response.PageResponseBean;
import com.springboot.framework.dao.entity.App;
import com.springboot.framework.dao.entity.AppDetail;
import com.springboot.framework.dto.AppDTO;
import com.springboot.framework.service.AppService;
import com.springboot.framework.util.ResponseEntity;
import com.springboot.framework.util.ResponseEntityUtil;
import com.springboot.framework.vo.AppDetailVO;
import com.springboot.framework.vo.ParkAppInfoVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Api(tags = {"应用操作接口"}, produces = "application/json")
@RestController
@RequestMapping("/app/")
public class AppController extends BaseController {
    @Resource
    private AppService appService;

    @ApiOperation(value = "删除", notes = "删除应用")
    @DeleteMapping(value = "deleteByPrimaryKey")
    public ResponseEntity<Errors> deleteByPrimaryKey(@RequestParam Integer id, HttpServletRequest request) {
        AppDTO recordDTO = new AppDTO(id, super.getSessionUser(request).getAccount());
        return appService.deleteByPrimaryKey(recordDTO);
    }

    @ApiOperation(value = "新增", notes = "新增应用")
    @PostMapping(value = "insertSelective")
    public ResponseEntity<Errors> insertSelective(@RequestBody AppInsertSelective bean, HttpServletRequest request) {
        AppDTO recordDTO = new AppDTO(bean.getName(), bean.getIcon(), bean.getSort(), super.getSessionUser(request).getName(), bean.getParkIds());
        return appService.insertSelective(recordDTO);
    }

    @ACS(allowAnonymous = true)
    @ApiOperation(value = "查看", notes = "查看应用")
    @GetMapping(value = "selectByPrimaryKey")
    public ResponseEntity<AppDetailVO> selectByPrimaryKey(@RequestParam Integer id) {
        return appService.selectByPrimaryKey(id);
    }

    @ACS(allowAnonymous = true)
    @ApiOperation(value = "查看列表", notes = "查看应用列表")
    @GetMapping(value = "selectList")
    public PageResponseBean selectList(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        return appService.selectList(pageNum, pageSize);
    }

    @ACS(allowAnonymous = true)
    @ApiOperation(value = "根据parkId查看列表", notes = "根据parkId查看列表")
    @GetMapping(value = "selectListByParkId")
    public PageResponseBean selectListByParkId(@RequestParam Integer pageNum, @RequestParam Integer pageSize, @RequestParam Integer parkId) {
        return appService.selectListByParkId(pageNum, pageSize, parkId);
    }

    @ACS(allowAnonymous = true)
    @ApiOperation(value = "查看自己园区下的应用列表", notes = "查看自己园区下的应用列表")
    @GetMapping(value = "selectAppListB")
    public PageResponseBean selectList(@RequestParam Integer pageNum, @RequestParam Integer pageSize, HttpServletRequest request) {
        Integer parkId = super.getSessionUser(request).getParkId();
        return appService.selectListByParkId(pageNum, pageSize, parkId);
    }

    @ApiOperation(value = "查看大图列表", notes = "查看轮播图列表")
    @GetMapping(value = "selectListIcon")
    public ResponseEntity<List<App>> selectListIcon() {
        return appService.selectList();
    }

    @ApiOperation(value = "更新", notes = "更新应用")
    @PutMapping(value = "updateByPrimaryKeySelective")
    public ResponseEntity<Errors> updateByPrimaryKeySelective(@RequestBody AppUpdateSelective bean, HttpServletRequest request) {
        AppDTO recordDTO = new AppDTO(bean.getAppId(), bean.getName(), bean.getIcon(), bean.getSort(), super.getSessionUser(request).getName(), bean.getParkIds2(), bean.getParkIds3());
        return appService.updateByPrimaryKeySelective(recordDTO);
    }

    @ApiOperation(value = "更新状态", notes = "更新查看应用状态")
    @PutMapping(value = "updateStatus")
    public ResponseEntity<Errors> updateStatus(@RequestBody UpdateByStatus bean, HttpServletRequest request) {
        AppDTO recordDTO = new AppDTO(bean.getId(), super.getSessionUser(request).getAccount(), bean.getStatus());
        return appService.updateStatus(recordDTO);
    }

    /////////////////园区-应用/////////////////
    @ACS(allowAnonymous = true)
    @ApiOperation(value = "查看应用详情", notes = "查看应用详情")
    @GetMapping(value = "selectByPrimaryKeyForDetail")
    public ResponseEntity<Object> selectByPrimaryKeyForDetail(@RequestParam Integer appId, @RequestParam Integer parkId) {
        return appService.selectByPrimaryKeyForDetail(appId, parkId);
    }

    @ApiOperation(value = "更新应用详情", notes = "更新应用详情")
    @PutMapping(value = "updateByPrimaryKeySelectiveForDetail")
    public ResponseEntity<Errors> updateByPrimaryKeySelectiveForDetail(@RequestBody AppDetailUpdateSelective bean, HttpServletRequest request) {
        AppDetail record = new AppDetail(bean.getId(), bean.getCover(), bean.getAddress(), bean.getPrice(), bean.getModel(), bean.getColor(), bean.getContact(), bean.getDescription(), bean.getIntroduction(), bean.getStatus(), bean.getContent());
        return appService.updateByPrimaryKeySelectiveForDetail(record);
    }

    @ACS(allowAnonymous = true)
    @ApiOperation(value = "查看应用详情改", notes = "查看应用详情")
    @GetMapping(value = "selectParkAppInfo")
    public ResponseEntity<Object> selectParkAppInfo(@RequestParam Integer appId, @RequestParam Integer parkId) {
        ResponseEntity<Object> response = appService.selectByPrimaryKeyForDetail(appId, parkId);
        if (response.isSuccess()) {
            AppDetail appDetail = (AppDetail) response.getData();
            ParkAppInfoVO parkAppInfoVo = new ParkAppInfoVO();

            BeanUtils.copyProperties(appDetail, parkAppInfoVo);
            try {
                List contactList = JSON.parseArray(appDetail.getContact());
                parkAppInfoVo.setContact(contactList);
            } catch (Exception e) {

            }
            return ResponseEntityUtil.success(parkAppInfoVo);
        }
        return response;
    }

    @ApiOperation(value = "更新应用详情改", notes = "更新应用详情")
    @PutMapping(value = "updateParkAppInfo")
    public ResponseEntity<Errors> updateParkAppInfo(@RequestBody ParkAppUpdateRequestBean bean, HttpServletRequest request) {
        String contact = JSON.toJSONString(bean.getContact());
        AppDetail record = new AppDetail(bean.getId(), bean.getCover(), bean.getAddress(), bean.getPrice(), bean.getModel(), bean.getColor(), contact, bean.getDescription(), bean.getIntroduction(), bean.getStatus(), bean.getContent());
        return appService.updateByPrimaryKeySelectiveForDetail(record);
    }
}
