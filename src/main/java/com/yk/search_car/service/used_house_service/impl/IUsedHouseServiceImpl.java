package com.yk.search_car.service.used_house_service.impl;

import com.yk.search_car.dao.house.api.IHouseDAO;
import com.yk.search_car.export.ExportExcel;
import com.yk.search_car.model.used_house.UsedHouseModel;
import com.yk.search_car.model.visual.HouseVisualModel;
import com.yk.search_car.service.used_house_service.api.IUsedHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by Satani on 2017/2/21.
 */
@Service("usedHouseService")
public class IUsedHouseServiceImpl implements IUsedHouseService {

    @Autowired
    private IHouseDAO houseDAO;

    private Pattern pattern=Pattern.compile(" ");

    private ExportExcel exportExcel =new ExportExcel();//用于转变为Excel
    /**
     * 获取所有房源信息
     *
     * @return 房源信息队列
     */
    @Override
    public List<UsedHouseModel> getAllUsedHouse() {
        return this.houseDAO.getAllUsedHouse();
    }

    /**
     * 获取二手房房源视图信息
     *
     * @param houseAddress 二手房房源地址
     * @return 二手房房源视图信息
     */
    @Override
    public List<HouseVisualModel> getUsedHouseViewByAddress(String houseAddress) {
        List<HouseVisualModel> models=new ArrayList<>();
        if(houseAddress!=null&&!houseAddress.isEmpty()){
            List<String> addressList=new ArrayList<>();
            Collections.addAll(addressList, pattern.split(houseAddress));
            for(UsedHouseModel usedHouseModel:this.houseDAO.selectHouseByAddress(addressList)){
                HouseVisualModel houseVisualModel=new HouseVisualModel();
                houseVisualModel.setHouseAddress(usedHouseModel.getAddress());
                houseVisualModel.setHouseDescription(usedHouseModel.getTitle());
                houseVisualModel.setHouseType(usedHouseModel.getHouseType());
                houseVisualModel.setHouseUnitPrice(usedHouseModel.getUnitPrice());
                houseVisualModel.setHouseLocation(usedHouseModel.getHouseName());
                models.add(houseVisualModel);
            }
        }
        return models;
    }

    /**
     * 获取二手房房源表格信息
     *
     * @param houseAddress 二手房房源地址
     * @return 二手房房源表格信息
     */
    @Override
    public List<UsedHouseModel> getUsedHouseTableByAddress(String houseAddress) {
        List<UsedHouseModel> models=new ArrayList<>();
        if(houseAddress!=null&&!houseAddress.isEmpty()) {
            List<String> addressList = new ArrayList<>();
            Collections.addAll(addressList, pattern.split(houseAddress));
            return this.houseDAO.selectHouseByAddress(addressList);
        }
        return models;
    }

    /**
     * 添加房源信息
     *
     * @param title       简介
     * @param house_name  房区
     * @param address     地址
     * @param house_type  房源类型
     * @param area        面积
     * @param total_price 售价
     * @param unit_price  单价
     */
    @Override
    public boolean addHouse(String title
            , String house_name
            , String address
            , String house_type
            , String area
            , float total_price
            , String unit_price) {

        UsedHouseModel houseModel=new UsedHouseModel();
        houseModel.setTitle(title);
        houseModel.setHouseName(house_name);
        houseModel.setAddress(address);
        houseModel.setHouseType(house_type);
        houseModel.setArea(area);
        houseModel.setTotalPrice(new BigDecimal(total_price));
        houseModel.setUnitPrice(unit_price);
        List<UsedHouseModel> models=new ArrayList<>();
        models.add(houseModel);
        try {
            this.houseDAO.addHouse(models);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 更新房源信息
     *
     * @param id          房源唯一标识
     * @param title       简介
     * @param house_name  房区
     * @param address     地址
     * @param house_type  房源类型
     * @param area        面积
     * @param total_price 售价
     * @param unit_price  单价
     */
    @Override
    public boolean updateHouse(int id
            , String title
            , String house_name
            , String address
            , String house_type
            , String area
            , float total_price
            , String unit_price) {
        UsedHouseModel houseModel = new UsedHouseModel();
        houseModel.setId(id);
        houseModel.setTitle(title);
        houseModel.setHouseName(house_name);
        houseModel.setAddress(address);
        houseModel.setHouseType(house_type);
        houseModel.setArea(area);
        houseModel.setTotalPrice(new BigDecimal(total_price));
        houseModel.setUnitPrice(unit_price);
        List<UsedHouseModel> models = new ArrayList<>();
        models.add(houseModel);
        try {
            this.houseDAO.updateHouse(models);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 批量删除房源信息
     * @param idList 房源唯一标识
     * @return 操作状态 true:操作成功，false:操作失败
     */
    @Override
    public boolean deleteHouse(List<Integer> idList) {
        List<UsedHouseModel> models=new ArrayList<>();
        for(Integer id:idList){
            UsedHouseModel model=new UsedHouseModel();
            model.setId(id);
            models.add(model);
        }
        try{
            this.houseDAO.deleteHouse(models);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 用于转为Excel格式
     *
     * @param address 根据模糊地址，查出一个list<UsedHouseModel>集合
     * @param url 导出数据的路径+文件名
     * @return 返回1，则操作成功。返回2，则操作失败
     */
    @Override
    public int getExportExcel(String address,String url) {
        List<UsedHouseModel> list = this.getUsedHouseTableByAddress(address);//查出一个list<UsedHouseModel>集合
        return this.exportExcel.Export(list,url);//执行导出操作，返回一个整数（状态码）
    }
}
