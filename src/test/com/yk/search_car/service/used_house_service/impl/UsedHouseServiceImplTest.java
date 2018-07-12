package com.yk.search_car.service.used_house_service.impl;

import com.yk.search_car.service.used_house_service.api.IUsedHouseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by Satani on 2017/2/21.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/spring-common.xml","/spring-servlet.xml"})
public class UsedHouseServiceImplTest {
    @Autowired
    private IUsedHouseService usedHouseService;
    @Test
    public void getUsedHouseByAddress() throws Exception {
        int expectation=165;
        String address="光华安纳溪湖 逸翠庄园";
        assertEquals(expectation,this.usedHouseService.getUsedHouseViewByAddress(address).size());
    }
}