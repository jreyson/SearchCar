package com.yk.search_car.service.analysis_service.impl;

import com.yk.search_car.dao.car.api.CarDAO;
import com.yk.search_car.model.used_car.UsedCarModel;
import com.yk.search_car.model.analysis.AnalysisItem;
import com.yk.search_car.service.analysis_service.api.AnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by Satani on 2017/2/17.
 */
@Service("analysisService")
public class AnalysisServiceImpl implements AnalysisService {

    @Autowired
    private CarDAO carDAO;

    private DecimalFormat decimalFormat=new DecimalFormat("#.00");
    /**
     * @param description 车辆信息描述
     * @return 分析结果
     */
    @Override
    public Map<String, Object> analyse(String description) {

        List<UsedCarModel> models=carDAO.getCarsByDescription(description);
        Map<String,Object> result=new HashMap<>();
        result.put("description",description);
        result.put("result",this.getResult(models));
        return result;
    }

    private Map<String,Object> getResult(List<UsedCarModel> models){

        int unit=5;
        Map<String,Object> result=new HashMap<>();
        result.put("originalPrice",this.getOriginalPriceItems(models,unit));
        result.put("presentPrice",this.getPresentPriceItems(models,unit));
        result.put("mileage",this.getMileageItems(models,unit));
        return result;
    }

    /**
     *
     * @param models 源数据
     * @param unit 统计分类数
     * @return 车辆原价统计结果
     */
    private List<AnalysisItem> getOriginalPriceItems(List<UsedCarModel> models,int unit){
        double highestPrice=-1;
        double lowestPrice=-1;
        for(UsedCarModel model:models){
            if(highestPrice<0&&lowestPrice<0){
                highestPrice=model.getOriginalPrice();
                lowestPrice=model.getOriginalPrice();
            }
            else {
                if(model.getOriginalPrice()>highestPrice)
                    highestPrice=model.getOriginalPrice();

                if(model.getOriginalPrice()<lowestPrice)
                    lowestPrice=model.getOriginalPrice();
            }

        }
        highestPrice+=0.0001;
        double p=(highestPrice-lowestPrice)/unit;
       Map<String,Integer> counterMap=new HashMap<>();
        for (int i = 0; i <unit ; i++) {
            double start=lowestPrice+i*p;
            double end=lowestPrice+(i+1)*p;
            counterMap.put(this.decimalFormat.format(start)+"-"+this.decimalFormat.format(end),0);
        }
        for(UsedCarModel model:models){
            for(String range:counterMap.keySet()){
                if(this.contain(model.getOriginalPrice(),range)){
                    counterMap.put(range,counterMap.get(range)+1);
                }
            }
        }

        return this.statisticItem(counterMap,models.size());
    }

    /**
     *
     * @param models 源数据
     * @param unit 统计分类数
     * @return 车辆现价统计结果
     */
    private List<AnalysisItem> getPresentPriceItems(List<UsedCarModel> models,int unit){
        double highestPrice=-1;
        double lowestPrice=-1;

        for(UsedCarModel model:models){
            if(highestPrice<0&&lowestPrice<0){
                highestPrice=model.getPresentPrice();
                lowestPrice=model.getPresentPrice();
            }
            else {
                if(model.getPresentPrice()>highestPrice)
                    highestPrice=model.getPresentPrice();
                if(model.getPresentPrice()<lowestPrice)
                    lowestPrice=model.getPresentPrice();
            }
        }
        highestPrice+=0.0001;
        double p=(highestPrice-lowestPrice)/unit;
        Map<String,Integer> counterMap=new HashMap<>();
        for (int i = 0; i <unit ; i++) {
            double start=lowestPrice+i*p;
            double end=lowestPrice+(i+1)*p;
            counterMap.put(this.decimalFormat.format(start)+"-"+this.decimalFormat.format(end),0);
        }

        for(UsedCarModel model:models){
            for(String range:counterMap.keySet()){
                if(this.contain(model.getPresentPrice(),range)){
                    counterMap.put(range,counterMap.get(range)+1);
                }
            }
        }

        return this.statisticItem(counterMap,models.size());
    }

    /**
     *
     * @param models 源数据
     * @param unit 统计分类数
     * @return 车辆行驶里程结果
     */
    private List<AnalysisItem> getMileageItems(List<UsedCarModel> models,int unit){
        double highestMileage=-1;
        double lowestMileage=-1;
        for(UsedCarModel model:models){
            if(highestMileage<0&&lowestMileage<0){
                highestMileage=model.getMileage();
                lowestMileage=model.getMileage();
            }
            else {
                if(model.getMileage()>highestMileage)
                    highestMileage=model.getMileage();
                if(model.getMileage()<lowestMileage)
                    lowestMileage=model.getMileage();
            }

        }
        highestMileage+=0.0001;
        double p=(highestMileage-lowestMileage)/unit;
        Map<String,Integer> counterMap=new HashMap<>();
        for (int i = 0; i <unit ; i++) {
            double start=lowestMileage+i*p;
            double end=lowestMileage+(i+1)*p;
            counterMap.put(this.decimalFormat.format(start)+"-"+this.decimalFormat.format(end),0);
        }
        for(UsedCarModel model:models){
            for(String range:counterMap.keySet()){
                if(this.contain(model.getMileage(),range)){
                    counterMap.put(range,counterMap.get(range)+1);
                }
            }
        }

        return this.statisticItem(counterMap,models.size());
    }

    /**
     * 数值是否在判断范围之内
     * @param number 待判断数值
     * @param range 判断范围
     * @return 判断结果
     */
    private boolean contain(double number,String range){

        Pattern patten=Pattern.compile("-");
        String[] priceArr=patten.split(range);
        return number>=Double.valueOf(priceArr[0])&&number<Double.valueOf(priceArr[1]);
    }

    /**
     *
     * @param counterMap 统计计数映射表
     * @param total 总计数
     * @return 各类百分占比
     */
    private List<AnalysisItem> statisticItem(Map<String,Integer> counterMap,int total){
        List<AnalysisItem> items=new ArrayList<>();
        for(Map.Entry<String,Integer> entry:counterMap.entrySet()){
            if(total>0) {
                AnalysisItem item=new AnalysisItem((double)entry.getValue()/total,entry.getKey());
                items.add(item);
            }
            else {
                AnalysisItem item=new AnalysisItem(0,entry.getKey());
                items.add(item);
            }
        }
        return items;
    }

}
