package com.springboot.framework.vo;

import com.springboot.framework.dao.entity.InformationInfo;
import lombok.Data;

import java.util.Date;

/**
 * @Author SWF
 * @Date 2019/5/10 11:01
 **/
@Data
public class InformationInfoVO {
    private Integer id;

    private Integer parkId;

    private Integer categoryId;

    private String title;

    private String introduction;

    private String cover;

    private Integer readCount;

    private Integer likeCount;

    private Byte sort;

    private String createBy;

    private Date createDate;

    private String updateBy;

    private Date updateDate;

    private Byte status;

    private InformationInfo informationInfo;
}
