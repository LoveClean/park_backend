package com.springboot.framework.controller;

import com.springboot.framework.controller.request.EnterpriseInsertRequestBean;
import com.springboot.framework.controller.request.EnterpriseUpdateRequestBean;
import com.springboot.framework.controller.request.HouseInsertRequestBean;
import com.springboot.framework.controller.request.HouseUpdateRequestBean;
import com.springboot.framework.controller.response.PageResponseBean;
import com.springboot.framework.dao.entity.Admin;
import com.springboot.framework.dao.entity.Enterprise;
import com.springboot.framework.dao.entity.House;
import com.springboot.framework.service.impl.EnterpriseAppServiceImpl;
import com.springboot.framework.util.DateUtil;
import com.springboot.framework.util.ResponseEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.unit.DataUnit;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Api(tags = {"企业管理应用"}, produces = "application/json")
@RestController
@RequestMapping("/enterprise/")
public class EnterpriseAppController extends BaseController {

    @Resource
    private EnterpriseAppServiceImpl enterpriseAppService;

    // 分页查询
    @ApiOperation(value = "列表查看", notes = "列表查看")
    @GetMapping(value = "selectList")
    public PageResponseBean selectListByParkId(@RequestParam int pageNum, @RequestParam int pageSize, @RequestParam Integer parkId) {
        PageResponseBean page = enterpriseAppService.selectListByParkId(pageNum, pageSize, parkId);
        return page;
    }

    // 新增
    @ApiOperation(value = "新增企业", notes = "新增企业")
    @PostMapping(value = "insert")
    public ResponseEntity<Object> insert(@RequestBody EnterpriseInsertRequestBean bean, HttpServletRequest request) {
        Enterprise enterprise = new Enterprise();
        BeanUtils.copyProperties(bean, enterprise);
        enterprise.setBeginDate(DateUtil.stringToDate(bean.getBeginDate(), DateUtil.yyyy_MM_dd));
        Admin admin = super.getSessionUser(request);
        enterprise.setCreateBy(admin.getName());
        return enterpriseAppService.insert(enterprise);
    }

    // 修改
    @ApiOperation(value = "修改企业", notes = "修改企业")
    @PostMapping(value = "update")
    public ResponseEntity<Object> update(@RequestBody EnterpriseUpdateRequestBean bean, HttpServletRequest request) {
        Enterprise enterprise = new Enterprise();
        BeanUtils.copyProperties(bean, enterprise);
        enterprise.setBeginDate(DateUtil.stringToDate(bean.getBeginDate(), DateUtil.yyyy_MM_dd));
        enterprise.setUpdateBy(super.getSessionUser(request).getName());
        return enterpriseAppService.update(enterprise);
    }

    // 删除
    @ApiOperation(value = "删除", notes = "删除房源")
    @DeleteMapping(value = "delete")
    public ResponseEntity<Object> deleteByHouseId(@RequestParam int id) {
        return enterpriseAppService.deleteById(id);
    }
}
