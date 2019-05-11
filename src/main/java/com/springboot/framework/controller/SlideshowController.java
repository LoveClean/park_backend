package com.springboot.framework.controller;

import com.springboot.framework.controller.request.SlideshowDeleteRequestBean;
import com.springboot.framework.controller.request.SlideshowInsertRequestBean;
import com.springboot.framework.controller.request.SlideshowUpdateRequestBean;
import com.springboot.framework.dao.entity.Admin;
import com.springboot.framework.dao.entity.Slideshow;
import com.springboot.framework.service.SlideshowService;
import com.springboot.framework.util.ResponseEntity;
import com.springboot.framework.util.ResponseEntityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author SWF
 * @Date 2019/5/9 17:20
 **/
@Api(tags = "轮播图", produces = "application/json")
@RestController
@RequestMapping("/slideshow")
public class SlideshowController extends BaseController {

    @Autowired
    private SlideshowService slideshowService;

    @ApiOperation(value = "查看列表", notes = "查看轮播图列表")
    @GetMapping(value = "/selectList")
    public ResponseEntity<List<Slideshow>> listByParkId(Integer parkId) {
        List<Slideshow> slideshows = slideshowService.listByParkId(parkId);
        return ResponseEntityUtil.success(slideshows);
    }

    @ApiOperation(value = "查看详情", notes = "查看轮播图详情")
    @GetMapping(value = "/select")
    public ResponseEntity<Slideshow> selectById(Integer id) {
        Slideshow slideshow = slideshowService.selectById(id);
        if (null == slideshow) {
            return ResponseEntityUtil.fail("查不到");
        }
        return ResponseEntityUtil.success(slideshow);
    }

    @ApiOperation(value = "添加轮播图", notes = "添加轮播图")
    @PostMapping(value = "/insert")
    public ResponseEntity<Object> insert(@RequestBody SlideshowInsertRequestBean bean, HttpServletRequest request) {
        // 权限校验 比对所修改园区和自己管辖的园区
        Admin admin = super.getSessionUser(request);
        if (bean.getParkId().equals(admin.getParkId())) {
            Slideshow slideshow = new Slideshow();
            BeanUtils.copyProperties(bean, slideshow);
            slideshow.setCreateBy(admin.getName());
            Integer resultCount = slideshowService.insert(slideshow);
            return ResponseEntityUtil.addMessage(resultCount);
        }
        return ResponseEntityUtil.fail("没有权限");
    }

    @ApiOperation(value = "修改轮播图", notes = "修改轮播图")
    @PostMapping(value = "/update")
    public ResponseEntity<Object> updateById(@RequestBody SlideshowUpdateRequestBean bean, HttpServletRequest request) {
        // 权限校验 比对所修改园区和自己管辖的园区
        Admin admin = super.getSessionUser(request);
        if (bean.getParkId().equals(admin.getParkId())) {
            Slideshow slideshow = new Slideshow();
            BeanUtils.copyProperties(bean, slideshow);
            slideshow.setUpdateBy(admin.getName());
            Integer resultCount = slideshowService.updateById(slideshow);
            return ResponseEntityUtil.updMessage(resultCount);
        }
        return ResponseEntityUtil.fail("没有权限");
    }

    @ApiOperation(value = "删除轮播图", notes = "删除轮播图")
    @DeleteMapping(value = "/delete")
    public ResponseEntity<Object> deleteById(@RequestBody SlideshowDeleteRequestBean bean, HttpServletRequest request) {

        // 权限校验 比对所修改园区和自己管辖的园区
        if (bean.getParkId().equals(super.getSessionUser(request).getParkId())) {
            Integer resultCount = slideshowService.deleteById(bean.getId());
            return ResponseEntityUtil.delMessage(resultCount);
        }
        return ResponseEntityUtil.fail("没有权限");
    }
}
