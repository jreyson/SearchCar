package com.yk.search_car.service.analysis_service.impl;

import com.yk.search_car.service.analysis_service.api.AnalysisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

/**
 * Created by Satani on 2017/2/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/spring-common.xml","/spring-servlet.xml"})
public class AnalysisServiceImplTest {
    @Autowired
    private AnalysisService analysisService;
    @Test
    public void analyse() throws Exception {
        System.out.println(analysisService.analyse("长安逸动"));
    }

}