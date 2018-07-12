package com.yk.search_car.controller.house;


import com.yk.search_car.service.used_house_service.api.IUsedHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by YK on 2017/2/24.
 */
@Controller
public class HouseController {
    @Autowired
    private IUsedHouseService usedHouseService;

    private Pattern pattern=Pattern.compile(" ");

    /**
     *
     * @param address 查询地址
     * @return 二手房房源视图信息
     */

    @RequestMapping("/getUsedHouseVisual")
    @ResponseBody
    public Object getUsedHouseVisual(@RequestParam("address") String address){
        return this.usedHouseVisual(address);
    }

    /**
     *
     * @param address 查询地址
     * @return 二手房房源表格信息
     */

    @RequestMapping("/getUsedHouseTable")
    @ResponseBody
    public Object getUsedHouseTable(@RequestParam("address") String address){
        return this.usedHouseTable(address);
    }

    /**
     *获取所有房源信息
     * @return 所有房源信息
     */
    @RequestMapping("/getAllUsedHouse")
    @ResponseBody
    public Object getAllUsedHouse(){

     return this.allUsedHouse();

    }

    /**
     *添加一个房源信息
     * @param title       简介
     * @param house_name  房区
     * @param address     地址
     * @param house_type  房源类型
     * @param area        面积
     * @param total_price 售价
     * @param unit_price  单价
     * @return 操作状态 true:操作成功，false:操作失败
     */
    @RequestMapping("/addOneHouse")
    @ResponseBody
    public Object addOneHouse(@RequestParam("title") String title
            ,@RequestParam("house_name") String house_name
            ,@RequestParam("address") String address
            ,@RequestParam("house_type") String house_type
            ,@RequestParam("area") String area
            ,@RequestParam("total_price") float total_price
            ,@RequestParam("unit_price") String unit_price){
        return this.addOneUsedHouse(title,house_name,address,house_type,area,total_price,unit_price);
    }
    /**
     * 更新房源信息
     * @param id          房源唯一标识
     * @param title       简介
     * @param house_name  房区
     * @param address     地址
     * @param house_type  房源类型
     * @param area        面积
     * @param total_price 售价
     * @param unit_price  单价
     * @return 操作状态 true:操作成功，false:操作失败
     */
    @RequestMapping("/updateOneHouse")
    @ResponseBody
    public Object updateOneHouse(@RequestParam("id") int id
            ,@RequestParam("title") String title
            ,@RequestParam("house_name") String house_name
            ,@RequestParam("address") String address
            ,@RequestParam("house_type") String house_type
            ,@RequestParam("area") String area
            ,@RequestParam("total_price") float total_price
            ,@RequestParam("unit_price") String unit_price){
        return this.updateOneUsedHouse(id,title,house_name,address,house_type,area,total_price,unit_price);    }

    /**
     * 删除房源信息
     * @param idList 房源标识队列
     * @return 操作状态 true:操作成功，false:操作失败
     */
    @RequestMapping("/deleteBatchHouse")
    @ResponseBody
    public Object deleteBatchHouse(@RequestParam("idList") String idList){
        List<Integer> houseIdList=new ArrayList<>();
        for(String id:this.pattern.split(idList)){
            houseIdList.add(Integer.valueOf(id));
        }
        return this.deleteBatchUsedHouse(houseIdList);
    }



    private synchronized Object usedHouseVisual(String address){
        return this.usedHouseService.getUsedHouseViewByAddress(address);
    }

    private synchronized Object usedHouseTable(String address){
        return this.usedHouseService.getUsedHouseTableByAddress(address);
    }

    private Object allUsedHouse(){
        return this.usedHouseService.getAllUsedHouse();
    }

    private synchronized Object addOneUsedHouse(String title
            , String house_name
            , String address
            , String house_type
            , String area
            , float total_price
            , String unit_price){
       return this.usedHouseService.addHouse(title,house_name,address,house_type,area,total_price,unit_price);
    }

    private synchronized Object updateOneUsedHouse(int id
            , String title
            , String house_name
            , String address, String house_type
            , String area
            , float total_price
            , String unit_price){
        return this.usedHouseService.updateHouse(id,title,house_name,address,house_type,area,total_price,unit_price);
    }

    private synchronized Object deleteBatchUsedHouse(List<Integer> idList){
       return this.usedHouseService.deleteHouse(idList);
    }

    /**
     *
     * @param address 根据模糊地址，查出一个list<UsedHouseModel>集合
     * @param excelUrl 导出数据的路径+文件名
     * @return 返回1，则操作成功。返回2，则操作失败
     */
    @RequestMapping("/ExportExcel")
    @ResponseBody
    public Object ExportExcel(@RequestParam("address")String address,@RequestParam("excelUrl")String excelUrl){
        return this.getExportExcel(address,excelUrl);
    }


    public synchronized int getExportExcel(String address,String excelUrl){
        return this.usedHouseService.getExportExcel(address,excelUrl);
    }
}
