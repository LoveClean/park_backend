package com.springboot.framework.controller.request;

public class AppInsertSelective {
    private String name;
    private String icon;
    private Byte sort;
    private Integer[] parkIds;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Byte getSort() {
        return sort;
    }

    public void setSort(Byte sort) {
        this.sort = sort;
    }

    public Integer[] getParkIds() {
        return parkIds;
    }

    public void setParkIds(Integer[] parkIds) {
        this.parkIds = parkIds;
    }
}
