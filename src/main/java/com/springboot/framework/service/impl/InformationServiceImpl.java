package com.springboot.framework.service.impl;

import com.github.pagehelper.PageHelper;
import com.springboot.framework.controller.request.InformationInsertRequestBean;
import com.springboot.framework.controller.request.InformationUpdateRequestBean;
import com.springboot.framework.controller.response.PageResponseBean;
import com.springboot.framework.dao.entity.Information;
import com.springboot.framework.dao.entity.InformationInfo;
import com.springboot.framework.dao.mapper.InformationInfoMapper;
import com.springboot.framework.dao.mapper.InformationMapper;
import com.springboot.framework.service.InformationService;
import com.springboot.framework.util.PageUtil;
import com.springboot.framework.util.ResponseEntity;
import com.springboot.framework.util.ResponseEntityUtil;
import com.springboot.framework.vo.InformationInfoVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author SWF
 * @Date 2019/5/9 17:10
 **/
@Service
public class InformationServiceImpl implements InformationService {
    @Resource
    private InformationMapper informationMapper;

    @Resource
    private InformationInfoMapper informationInfoMapper;

    @Override
    public PageResponseBean listByParkId(Integer parkId , Integer pageNum , Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<Information> list = informationMapper.listByParkId(parkId);
        return PageUtil.page(list);
    }

    @Override
    public ResponseEntity<InformationInfoVO> selectInfo(Integer id){
        Information information = informationMapper.selectByPrimaryKey(id);
        InformationInfo informationInfo = informationInfoMapper.selectByPrimaryKey(id);
        if(null == information){
            return ResponseEntityUtil.fail("查询不到该资讯");
        }
        InformationInfoVO informationInfoVO = new InformationInfoVO();
        BeanUtils.copyProperties(information,informationInfoVO);
        informationInfoVO.setInformationInfo(informationInfo);
        return ResponseEntityUtil.success(informationInfoVO);
    }

    @Override
    public ResponseEntity<Object> insert(InformationInsertRequestBean bean , String createBy){
        // 添加到information表
        Information information = new Information();
        BeanUtils.copyProperties(bean,information);
        information.setCreateBy(createBy);
        int informationId = informationMapper.insertSelective(information);
        if(0 == informationId){
            return ResponseEntityUtil.fail("添加资讯失败");
        }
        // 添加文章内容到informationInfo表
        InformationInfo informationInfo = new InformationInfo();
        informationInfo.setInformationId(information.getId());
        Integer resultCount = informationInfoMapper.insertSelective(informationInfo);
        return ResponseEntityUtil.addMessage(resultCount);
    }

    @Override
    public ResponseEntity<Object> update(InformationUpdateRequestBean bean , String updateBy){
        Information information = new Information();
        BeanUtils.copyProperties(bean,information);
        information.setUpdateBy(updateBy);
        Integer result1 = informationMapper.updateByPrimaryKeySelective(information);
        if(0 == result1){
            return ResponseEntityUtil.fail("修改资讯失败");
        }

        InformationInfo informationInfo = new InformationInfo();
        informationInfo.setInformationId(bean.getId());
        informationInfo.setContent(bean.getContent());
        Integer resultCount = informationInfoMapper.updateByPrimaryKeySelective(informationInfo);
        return ResponseEntityUtil.updMessage(resultCount);
    }

    @Override
    public ResponseEntity<Object> delete(Integer id){
        Integer resultCount = informationMapper.deleteById(id);
        return ResponseEntityUtil.delMessage(resultCount);
    }
}
