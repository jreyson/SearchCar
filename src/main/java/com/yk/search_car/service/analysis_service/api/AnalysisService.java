package com.yk.search_car.service.analysis_service.api;

import java.util.Map;

/**
 * Created by Satani on 2017/2/17.
 */
public interface AnalysisService {
    /**
     *
     * @param description 车辆信息描述
     * @return 分析结果
     */
    Map<String,Object> analyse(String description);
}
