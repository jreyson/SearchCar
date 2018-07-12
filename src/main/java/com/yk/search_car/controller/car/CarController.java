package com.yk.search_car.controller.car;

import com.yk.search_car.service.used_car_service.api.UsedCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Satani on 2017/2/15.
 */
@Controller
public class CarController {

    @Autowired
    private UsedCarService usedCarService;

    @RequestMapping("/getUsedCars")
    @ResponseBody
    public Object getUsedCars(@RequestParam("maker") String maker,@RequestParam("type") String type){
        return this.usedCar(maker,type);
    }

    private synchronized Object usedCar(String maker, String type){
        return this.usedCarService.getUsedCars(maker,type);
    }

}
