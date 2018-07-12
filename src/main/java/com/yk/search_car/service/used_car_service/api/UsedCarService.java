package com.yk.search_car.service.used_car_service.api;

import com.yk.search_car.model.used_car.UsedCarModel;

import java.util.List;

/**
 * Created by Satani on 2017/2/14.
 */
public interface UsedCarService {
    /**
     *
     * @param maker 车辆制造商品牌
     * @param type  车辆系别类型
     * @param city  车辆所属城市
     * @param capacityRange 车辆排量范围
     * @param originalPriceRange 车辆原价价格范围
     * @param presentPriceRange 车辆现价价格范围
     * @param mileageRange 车辆行驶里程范围
     * @param registerDate 车辆注册日期
     * @return
     */
    List<Object> getUsedCars(String maker,String type,String city,String capacityRange,String originalPriceRange,String presentPriceRange,String mileageRange,String registerDate);

    /**
     *
     * @param maker 车辆品牌
     * @param type 车辆系别
     * @return 结果集
     */
    List<UsedCarModel> getUsedCars(String maker,String type);
}
