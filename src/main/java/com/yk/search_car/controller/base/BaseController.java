package com.yk.search_car.controller.base;

import com.yk.search_car.service.maker_type_service.api.MakerTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Satani on 2017/2/15.
 */
@Controller
public class BaseController {
    @Autowired
    private MakerTypeService makerTypeService;

    /**
     *
     * @return 请求所有车辆品牌
     */
    @RequestMapping("/getAllMakers")
    @ResponseBody
    public Object getAllMakers(){
        return this.allMakers();
    }

    /**
     *
     * @param maker 车辆品牌
     * @return 该品牌对应车系
     */
    @RequestMapping("/getTypesByMaker")
    @ResponseBody
    public Object getTypesByMaker(@RequestParam("maker") String maker){

        return this.typesByMaker(maker);
    }

    private synchronized Object allMakers(){
        Map<String,List<String>> feedBack=new HashMap<>();
        feedBack.put("makers",this.makerTypeService.getAllMakers());
        return feedBack;
    }

    private synchronized Object typesByMaker(String maker){
        Map<String,List<String>> feedback=new HashMap<>();
        feedback.put(maker,this.makerTypeService.getTypeByMaker(maker));
        return feedback;
    }

}
