package com.yk.search_car.dao.car.api;

import com.yk.search_car.model.used_car.MakerTypeModel;

import java.util.List;

/**
 * Created by Satani on 2017/2/15.
 */
public interface MakerTypeDAO {
    /**
     * 查询所有车辆制造商对应车系
     * @return 所有车辆制造商对应车系
     */
    List<MakerTypeModel> selectAllMakers();

    /**
     * 查询某一制造商对应车系
     * @param maker 车辆制造商
     * @return 该制造商对应车系
     */
    List<MakerTypeModel> selectTypesByMaker(String maker);

}
