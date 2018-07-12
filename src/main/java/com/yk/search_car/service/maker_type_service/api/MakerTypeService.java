package com.yk.search_car.service.maker_type_service.api;

import java.util.List;

/**
 * Created by Satani on 2017/2/15.
 */
public interface MakerTypeService {
    /**
     * 
     * @param maker 车辆制造商
     * @return 该制造商名下所有车系
     */
    List<String> getTypeByMaker(String maker);

    /**
     * 
     * @return 所有车辆制造商
     */
    List<String> getAllMakers();
}
