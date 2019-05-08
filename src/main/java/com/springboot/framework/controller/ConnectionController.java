package com.springboot.framework.controller;


import com.springboot.framework.controller.request.ConnectionInsertSelective;
import com.springboot.framework.controller.response.PageResponseBean;
import com.springboot.framework.dao.entity.Connection;
import com.springboot.framework.service.ConnectionService;
import com.springboot.framework.util.ResponseEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Api(tags = {"园区应用连接操作接口"}, produces = "application/json")
@RestController
@RequestMapping("/connection/")
public class ConnectionController {
    @Resource
    private ConnectionService connectionService;

    @ApiOperation(value = "新增", notes = "新增")
    @PostMapping(value = "insertSelective")
    public ResponseEntity<Integer> insertSelective(@RequestBody ConnectionInsertSelective bean) {
        Connection record = new Connection();
        record.setParkId(bean.getParkId());
        record.setAppId(bean.getAppId());
        return connectionService.insertSelective(record);
    }

    @ApiOperation(value = "根据parkId查看列表", notes = "根据parkId查看列表")
    @GetMapping(value = "selectListByParkId")
    public List<String> selectListByParkId(@RequestParam Integer parkId) {
        return connectionService.selectListByParkId(parkId);
    }

    @ApiOperation(value = "根据appId查看列表", notes = "根据appId查看列表")
    @GetMapping(value = "selectListByAppId")
    public List<String> selectListByAppId(@RequestParam Integer appId) {
        return connectionService.selectListByAppId(appId);
    }
}
