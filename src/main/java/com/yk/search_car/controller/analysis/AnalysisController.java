package com.yk.search_car.controller.analysis;

import com.yk.search_car.service.analysis_service.api.AnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Satani on 2017/2/17.
 */
@Controller
public class AnalysisController {

    @Autowired
    private AnalysisService analysisService;
    /**
     *
     * @param description 车辆描述
     * @return 车辆分析结果
     */

    @RequestMapping("/getAnalysisResult")
    @ResponseBody
    public Object getAnalysisResult(@RequestParam("description") String description){
        return this.analysisResult(description);
    }

    private synchronized Object analysisResult(String description){
        return this.analysisService.analyse(description);
    }


}
