package com.springboot.framework.service;

import com.springboot.framework.dao.entity.Slideshow;

import java.util.List;

/**
 * @Author SWF
 * @Date 2019/5/9 17:09
 **/
public interface SlideshowService {
    List<Slideshow> listByParkId(Integer parkId);

    Slideshow selectById(Integer id);

    int insert(Slideshow slideshow);

    int updateById(Slideshow slideshow);

    int deleteById(Integer id);
}
