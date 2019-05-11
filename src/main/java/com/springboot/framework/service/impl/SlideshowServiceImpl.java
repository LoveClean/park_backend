package com.springboot.framework.service.impl;

import com.springboot.framework.dao.entity.Slideshow;
import com.springboot.framework.dao.mapper.SlideshowMapper;
import com.springboot.framework.service.SlideshowService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author SWF
 * @Date 2019/5/9 17:10
 **/
@Service
public class SlideshowServiceImpl implements SlideshowService {

    @Resource
    private SlideshowMapper slideshowMapper;

    @Override
    public List<Slideshow> listByParkId(Integer parkId){
        return slideshowMapper.listByParkId(parkId);
    }

    @Override
    public Slideshow selectById(Integer id){
        return slideshowMapper.selectByPrimaryKey(id);
    }

    @Override
    public int insert(Slideshow slideshow){
        return slideshowMapper.insertSelective(slideshow);
    }

    @Override
    public int updateById(Slideshow slideshow){
        return slideshowMapper.updateByPrimaryKeySelective(slideshow);
    }

    @Override
    public int deleteById(Integer id){
        return slideshowMapper.deleteById(id);
    }
}

