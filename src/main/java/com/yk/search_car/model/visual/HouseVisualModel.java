package com.yk.search_car.model.visual;

/**
 * Created by Satani on 2017/2/21.
 */
public class HouseVisualModel {

    private String houseLocation;// 房源地区
    private String houseAddress;// 房源地址
    private String houseType;// 房源类型
    private String houseUnitPrice;// 房源单价
    private String houseDescription;// 房源描述

    public String getHouseAddress() {
        return houseAddress;
    }

    public void setHouseAddress(String houseAddress) {
        this.houseAddress = houseAddress;
    }

    public String getHouseType() {
        return houseType;
    }

    public void setHouseType(String houseType) {
        this.houseType = houseType;
    }

    public String getHouseUnitPrice() {
        return houseUnitPrice;
    }

    public void setHouseUnitPrice(String houseUnitPrice) {
        this.houseUnitPrice = houseUnitPrice;
    }

    public String getHouseDescription() {
        return houseDescription;
    }

    public void setHouseDescription(String houseDescription) {
        this.houseDescription = houseDescription;
    }

    public String getHouseLocation() {
        return houseLocation;
    }

    public void setHouseLocation(String houseLocation) {
        this.houseLocation = houseLocation;
    }
}
