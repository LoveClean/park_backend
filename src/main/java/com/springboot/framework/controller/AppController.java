package com.springboot.framework.controller;

import com.springboot.framework.annotation.ACS;
import com.springboot.framework.controller.request.*;
import com.springboot.framework.controller.response.PageResponseBean;
import com.springboot.framework.dao.entity.App;
import com.springboot.framework.dao.entity.AppDetail;
import com.springboot.framework.service.AppService;
import com.springboot.framework.util.ResponseEntity;
import com.springboot.framework.vo.AppDetailVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Api(description = "应用操作接口", produces = "application/json")
@RestController
@RequestMapping("/app/")
public class AppController extends BaseController {
    @Resource
    private AppService appService;

    @ApiOperation(value = "删除", notes = "删除应用")
    @DeleteMapping(value = "deleteByPrimaryKey")
    public ResponseEntity<Integer> deleteByPrimaryKey(@RequestParam Integer id, HttpServletRequest request) {
        return appService.deleteByPrimaryKey(id, super.getSessionUser(request).getName());
    }

    @ApiOperation(value = "新增", notes = "新增应用")
    @PostMapping(value = "insertSelective")
    public ResponseEntity<Integer> insertSelective(@RequestBody AppInsertSelective bean, HttpServletRequest request) {
        App record = new App(bean.getName(), bean.getIcon(), bean.getSort(), super.getSessionUser(request).getName());
        return appService.insertSelective(record, bean.getParkIds());
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
    public ResponseEntity<Integer> updateByPrimaryKeySelective(@RequestBody AppUpdateSelective bean, HttpServletRequest request) {
        App record = new App(bean.getName(), bean.getIcon(), bean.getSort(), null);
        record.setId(bean.getAppId());
        record.setUpdateBy(super.getSessionUser(request).getName());
        return appService.updateByPrimaryKeySelective(record, bean.getParkIds2(), bean.getParkIds3());
    }

    @ApiOperation(value = "更新状态", notes = "更新查看应用状态")
    @PutMapping(value = "updateStatus")
    public ResponseEntity<Integer> updateStatus(@RequestBody UpdateByStatus bean, HttpServletRequest request) {
        return appService.updateStatus(bean.getId(), bean.getStatus(), super.getSessionUser(request).getName());
    }

    /////////////////应用详情/////////////////
    @ACS(allowAnonymous = true)
    @ApiOperation(value = "查看应用详情", notes = "查看应用详情")
    @GetMapping(value = "selectByPrimaryKeyForDetail")
    public ResponseEntity<AppDetail> selectByPrimaryKeyForDetail(@RequestParam Integer appId, @RequestParam Integer parkId) {
        return appService.selectByPrimaryKeyForDetail(appId, parkId);
    }

    @ApiOperation(value = "更新应用详情", notes = "更新应用详情")
    @PutMapping(value = "updateByPrimaryKeySelectiveForDetail")
    public ResponseEntity<Integer> updateByPrimaryKeySelectiveForDetail(@RequestBody AppDetailUpdateSelective bean, HttpServletRequest request) {
        AppDetail record = new AppDetail(bean.getId(), bean.getCover(), bean.getAddress(), bean.getPrice(), bean.getModel(), bean.getColor(), bean.getContact(), bean.getDescription(), bean.getIntroduction(), bean.getStatus(), bean.getContent());
        return appService.updateByPrimaryKeySelectiveForDetail(record);
    }
}
