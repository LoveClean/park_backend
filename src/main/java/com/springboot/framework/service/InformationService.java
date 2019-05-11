package com.springboot.framework.service;

import com.springboot.framework.controller.request.InformationInsertRequestBean;
import com.springboot.framework.controller.request.InformationUpdateRequestBean;
import com.springboot.framework.controller.response.PageResponseBean;
import com.springboot.framework.util.ResponseEntity;
import com.springboot.framework.vo.InformationInfoVO;

/**
 * @Author SWF
 * @Date 2019/5/10 10:45
 **/
public interface InformationService {
    PageResponseBean listByParkId(Integer parkId, Integer pageNum, Integer pageSize);

    ResponseEntity<InformationInfoVO> selectInfo(Integer id);

    ResponseEntity<Object> insert(InformationInsertRequestBean bean, String createBy);

    ResponseEntity<Object> update(InformationUpdateRequestBean bean, String updateBy);

    ResponseEntity<Object> delete(Integer id);
}
