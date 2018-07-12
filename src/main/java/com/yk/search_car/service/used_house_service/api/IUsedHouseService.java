package com.yk.search_car.service.used_house_service.api;

import com.yk.search_car.model.used_house.UsedHouseModel;
import com.yk.search_car.model.visual.HouseVisualModel;

import java.util.List;

/**
 * Created by Satani on 2017/2/21.
 */
public interface IUsedHouseService {
    /**
     * 获取所有房源信息
     * @return 房源信息队列
     */
    List<UsedHouseModel> getAllUsedHouse();

    /**获取二手房房源视图信息
     * @param houseAddress 二手房房源地址
     * @return 二手房房源视图信息
     */
    List<HouseVisualModel> getUsedHouseViewByAddress(String houseAddress);

    /**
     * 获取二手房房源表格信息
     * @param houseAddress 二手房房源地址
     * @return 二手房房源表格信息
     */
    List<UsedHouseModel> getUsedHouseTableByAddress(String houseAddress);

    /**
     * 添加房源信息
     * @param title 简介
     * @param house_name 房区
     * @param address 地址
     * @param house_type 房源类型
     * @param area 面积
     * @param total_price 售价
     * @param unit_price 单价
     */
    boolean addHouse(String title, String house_name, String address, String house_type, String area, float total_price, String unit_price);

    /**
     * 更新房源信息
     * @param id 房源唯一标识
     * @param title 简介
     * @param house_name 房区
     * @param address 地址
     * @param house_type 房源类型
     * @param area 面积
     * @param total_price 售价
     * @param unit_price 单价
     */
    boolean updateHouse(int id,String title,String house_name,String address,String house_type,String area,float total_price,String unit_price);

    /**
     * 删除房源信息
     * @param idList 房源唯一标识
     */
    boolean deleteHouse(List<Integer> idList);

    /**
     * 用于转为Excel格式
     *
     * @param address 根据模糊地址，查出一个list<UsedHouseModel>集合
     * @param url 导出数据的路径+文件名
     * @return 返回1，则操作成功。返回2，则操作失败
     */
    public int getExportExcel(String address,String url);

}
