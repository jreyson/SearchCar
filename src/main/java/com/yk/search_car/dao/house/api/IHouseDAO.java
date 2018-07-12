package com.yk.search_car.dao.house.api;

import com.yk.search_car.model.used_house.UsedHouseModel;

import java.util.List;

/**
 * Created by Satani on 2017/2/21.
 */
public interface IHouseDAO {
    /**
     * 获取所有房源信息
     * @return 房源信息队列
     */
    List<UsedHouseModel> getAllUsedHouse();

    /**
     *
     * @param addressList 二手房房源地址
     * @return 二手房房源信息
     */
    List<UsedHouseModel> selectHouseByAddress(List<String> addressList);

    /**
     * 批量删除房源信息
     * @param houseModels 待删除的房源
     */
    void deleteHouse(List<UsedHouseModel> houseModels);

    /**
     * 批量更新房源信息
     * @param houseModels 待更新房源信息
     */
    void updateHouse(List<UsedHouseModel> houseModels);

    /**
     * 批量添加房源信息
     * @param houseModels 待添加房源信息
     */
    void addHouse(List<UsedHouseModel> houseModels);

}
