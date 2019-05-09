package com.springboot.framework.controller;

import com.springboot.framework.annotation.ACS;
import com.springboot.framework.constant.Errors;
import com.springboot.framework.controller.request.ParkInsertSelective;
import com.springboot.framework.controller.request.ParkApply;
import com.springboot.framework.controller.request.ParkUpdateSelective;
import com.springboot.framework.controller.request.UpdateByStatus;
import com.springboot.framework.controller.response.PageResponseBean;
import com.springboot.framework.dao.entity.Park;
import com.springboot.framework.dto.ParkDTO;
import com.springboot.framework.service.ParkService;
import com.springboot.framework.util.ResponseEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Api(tags = {"园区操作接口"}, produces = "application/json")
@RestController
@RequestMapping("/park/")
public class ParkController extends BaseController {
    @Resource
    private ParkService parkService;

    @ApiOperation(value = "删除", notes = "删除园区")
    @DeleteMapping(value = "deleteByPrimaryKey")
    public ResponseEntity<Errors> deleteByPrimaryKey(@RequestParam Integer id, HttpServletRequest request) {
        ParkDTO recordDTO = new ParkDTO(id, super.getSessionUser(request).getAccount());
        return parkService.deleteByPrimaryKey(recordDTO);
    }

    @ApiOperation(value = "新增", notes = "新增园区")
    @PostMapping(value = "insertSelective")
    public ResponseEntity<Errors> insertSelective(@RequestBody ParkInsertSelective bean, HttpServletRequest request) {
        ParkDTO recordDTO = new ParkDTO(bean.getName(), bean.getLogo(), bean.getLocation(), bean.getAddress(), bean.getLongitude(), bean.getLatitude(), bean.getIntroduction(), bean.getSort(), super.getSessionUser(request).getAccount(), bean.getAppIds());
        return parkService.insertSelective(recordDTO);
    }

    @ACS(allowAnonymous = true)
    @ApiOperation(value = "游客申请新增", notes = "游客申请新增")
    @PostMapping(value = "apply")
    public ResponseEntity<Errors> apply(@RequestBody ParkApply bean) {
        ParkDTO recordDTO = new ParkDTO(bean.getParkName(), bean.getLogo(), bean.getLocation(), bean.getAddress(), bean.getLongitude(), bean.getLatitude(), bean.getIntroduction(), bean.getSort(), "游客", null);
        return parkService.apply(recordDTO,bean.getAdminPhone());
    }

    @ApiOperation(value = "查看", notes = "查看园区")
    @GetMapping(value = "selectByPrimaryKey")
    public ResponseEntity<Park> selectByPrimaryKey(@RequestParam Integer id) {
        return parkService.selectByPrimaryKey(id);
    }

    @ApiOperation(value = "查看", notes = "查看自己园区")
    @GetMapping(value = "selectPark")
    public ResponseEntity<Park> selectPark(HttpServletRequest request) {
        return parkService.selectByPrimaryKey(super.getSessionUser(request).getParkId());
    }

    @ACS(allowAnonymous = true)
    @ApiOperation(value = "查看列表", notes = "查看应用列表")
    @GetMapping(value = "selectList")
    public PageResponseBean selectList(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        return parkService.selectList(pageNum, pageSize);
    }

    @ApiOperation(value = "根据AppId查看列表", notes = "根据AppId查看列表")
    @GetMapping(value = "selectListByAppId")
    public PageResponseBean selectListByAppId(@RequestParam Integer pageNum, @RequestParam Integer pageSize, @RequestParam Integer appId) {
        return parkService.selectListByAppId(pageNum, pageSize, appId);
    }

    @ApiOperation(value = "更新", notes = "更新应用")
    @PutMapping(value = "updateByPrimaryKeySelective")
    public ResponseEntity<Errors> updateByPrimaryKeySelective(@RequestBody ParkUpdateSelective bean, HttpServletRequest request) {
        ParkDTO recordDTO = new ParkDTO(bean.getId(), bean.getName(), bean.getLogo(), bean.getLocation(), bean.getAddress(), bean.getLongitude(), bean.getLatitude(), bean.getIntroduction(), bean.getSort(), super.getSessionUser(request).getAccount(), bean.getAppIds2(), bean.getAppIds3());
        return parkService.updateByPrimaryKeySelective(recordDTO);
    }

    @ApiOperation(value = "更新状态", notes = "更新查看应用状态")
    @PutMapping(value = "updateStatus")
    public ResponseEntity<Errors> updateStatus(@RequestBody UpdateByStatus bean, HttpServletRequest request) {
        ParkDTO recordDTO = new ParkDTO(bean.getId(), super.getSessionUser(request).getAccount(), bean.getStatus());
        return parkService.updateStatus(recordDTO);
    }
}
