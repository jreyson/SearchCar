package com.yk.search_car.controller.house;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Satani on 2017/2/28.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/spring-common.xml","/spring-servlet.xml"})
public class HouseControllerTest {
    @Autowired
    private HouseController houseController;
    @Test
    public void getUsedHouseTable() throws Exception {
        System.out.println(houseController.getUsedHouseTable("逸翠庄园"));
    }

    @Test
    public void getUsedHouseVisual() throws Exception {
        System.out.println(houseController.getUsedHouseVisual("逸翠庄园"));
    }


    @Test
    public void getAllUsedHouse() throws Exception {
        System.out.println(houseController.getAllUsedHouse());
    }

    @Test
    public void addOneHouse() throws Exception {
        String title="云凯";
        String house_name="云凯饭馆";
        String address="水星";
        String house_type="茅草屋";
        String area="点点大";
        float total_price=0.05F;
        String unit_price="白送，倒贴钱";
        this.houseController.addOneHouse(title,house_name,address,house_type,area,total_price,unit_price);
    }

    @Test
    public void updateOneHouse() throws Exception {
        int id=5941;
        String title="云凯";
        String house_name="云凯饭馆";
        String address="水星B座";
        String house_type="茅草屋";
        String area="点点大";
        float total_price=0.05F;
        String unit_price="不要钱";
        this.houseController.updateOneHouse(id,title,house_name,address,house_type,area,total_price,unit_price);
    }

    @Test
    public void deleteBatchHouse() throws Exception {
        String idList=5943+" "+5942+" "+5941;
        this.houseController.deleteBatchHouse(idList);
    }

}