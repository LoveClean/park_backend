package com.springboot.framework.controller;

import com.springboot.framework.annotation.ACS;
import com.springboot.framework.controller.request.ParkInsertSelective;
import com.springboot.framework.controller.request.ParkUpdateSelective;
import com.springboot.framework.controller.request.UpdateByStatus;
import com.springboot.framework.controller.response.PageResponseBean;
import com.springboot.framework.dao.entity.Park;
import com.springboot.framework.service.ParkService;
import com.springboot.framework.util.ResponseEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Api(description = "园区操作接口", produces = "application/json")
@RestController
@RequestMapping("/park/")
public class ParkController extends BaseController {
    @Resource
    private ParkService parkService;

    @ApiOperation(value = "删除", notes = "删除园区")
    @DeleteMapping(value = "deleteByPrimaryKey")
    public ResponseEntity<Integer> deleteByPrimaryKey(@RequestParam Integer id, HttpServletRequest request) {
        return parkService.deleteByPrimaryKey(id, super.getSessionUser(request).getName());
    }

    @ApiOperation(value = "新增", notes = "新增园区")
    @PostMapping(value = "insertSelective")
    public ResponseEntity<Integer> insertSelective(@RequestBody ParkInsertSelective bean, HttpServletRequest request) {
        Park record = new Park(bean.getName(), bean.getLogo(), bean.getLocation(), bean.getAddress(), bean.getLongitude(), bean.getLatitude(), bean.getIntroduction(), bean.getSort(), super.getSessionUser(request).getName());
        return parkService.insertSelective(record, bean.getAppIds());
    }

    @ACS(allowAnonymous = true)
    @ApiOperation(value = "游客申请新增", notes = "游客申请新增")
    @PostMapping(value = "insertSelectiveForMember")
    public ResponseEntity<Integer> insertSelectiveForMember(@RequestBody ParkInsertSelective bean, HttpServletRequest request) {
        Park record = new Park(bean.getName(), bean.getLogo(), bean.getLocation(), bean.getAddress(), bean.getLongitude(), bean.getLatitude(), bean.getIntroduction(), bean.getSort(), super.getSessionUser(request).getName());
        record.setStatus((byte) 0);
        return parkService.insertSelective(record, bean.getAppIds());
    }

    @ApiOperation(value = "查看", notes = "查看园区")
    @GetMapping(value = "selectByPrimaryKey")
    public ResponseEntity<Park> selectByPrimaryKey(@RequestParam Integer id) {
        return parkService.selectByPrimaryKey(id);
    }

    @ApiOperation(value = "查看", notes = "查看园区")
    @GetMapping(value = "selectPark")
    public ResponseEntity<Park> selectPark(HttpServletRequest request) {
        Integer parkId = super.getSessionUser(request).getParkId();
        return parkService.selectByPrimaryKey(parkId);
    }

    @ApiOperation(value = "查看列表", notes = "查看应用列表")
    @GetMapping(value = "selectList")
    public PageResponseBean selectList(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        return parkService.selectList(pageNum, pageSize);
    }

    @ApiOperation(value = "根据parkId查看列表", notes = "根据parkId查看列表")
    @GetMapping(value = "selectListByAppId")
    public PageResponseBean selectListByAppId(@RequestParam Integer pageNum, @RequestParam Integer pageSize, @RequestParam Integer appId) {
        return parkService.selectListByAppId(pageNum, pageSize, appId);
    }

    @ApiOperation(value = "更新", notes = "更新应用")
    @PutMapping(value = "updateByPrimaryKeySelective")
    public ResponseEntity<Integer> updateByPrimaryKeySelective(@RequestBody ParkUpdateSelective bean, HttpServletRequest request) {
        Park record = new Park(bean.getName(), bean.getLogo(), bean.getLocation(), bean.getAddress(), bean.getLongitude(), bean.getLatitude(), bean.getIntroduction(), bean.getSort(), null);
        record.setId(bean.getId());
        record.setUpdateBy(super.getSessionUser(request).getName());
        return parkService.updateByPrimaryKeySelective(record, bean.getAppIds2(), bean.getAppIds3());
    }

    @ApiOperation(value = "更新状态", notes = "更新查看应用状态")
    @PutMapping(value = "updateStatus")
    public ResponseEntity<Integer> updateStatus(@RequestBody UpdateByStatus bean, HttpServletRequest request) {
        return parkService.updateStatus(bean.getId(), bean.getStatus(), super.getSessionUser(request).getName());
    }
}
