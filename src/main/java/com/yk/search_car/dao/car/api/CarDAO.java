package com.yk.search_car.dao.car.api;

import com.yk.search_car.model.used_car.UsedCarModel;

import java.util.Date;
import java.util.List;

/**
 * Created by Satani on 2017/2/13.
 */
public interface CarDAO {
    /**
     * 获取所有车辆信息
     * @return 所有车辆信息
     */
    List<UsedCarModel> getALLCars();

    /**
     *
     * @param description 车辆信息描述
     * @return 符合该描述的所有车辆信息
     */
    List<UsedCarModel> getCarsByDescription(String description);
    /**
     * 通过车辆原始价格区间查询
     * @param first 起始价格
     * @param second 终止价格
     * @return 该原始价格区间的所有车辆信息
     */
    List<UsedCarModel> getCarsByOriginalPriceRange(float first,float second);

    /**
     * 通过车辆当前价格区间查询
     * @param first 起始价格
     * @param second 终止价格
     * @return 该当前价格区间的所有车辆信息
     */
    List<UsedCarModel> getCarsByPresentPriceRange(float first,float second);

    /**
     * 通过车辆所属城市查询
     * @param cityName 城市名称
     * @return 该城市名称下的所有车辆信息
     */
    List<UsedCarModel> getCarsByCity(String cityName);

    /**
     * 通过车辆行驶里程区间查询
     * @param first 起始里程
     * @param second 终止里程
     * @return 该里程区间的所有车辆信息
     */
    List<UsedCarModel> getCarsByMileageRange(int first,int second);

    /**
     * 通过车辆上牌日期查询
     * @param date 上牌日期
     * @return 该日期内上牌的所有车辆信息
     */
    List<UsedCarModel> getCarsByRegisterTime(Date date);

    /**
     * 通过车辆油箱容量区间查询
     * @param first 起始容量
     * @param second 终止容量
     * @return 该容量区间的所有车辆信息
     */
    List<UsedCarModel> getCarsByCapacityRange(float first,float second);
}
