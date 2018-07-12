package com.yk.search_car.service.maker_type_service.impl;

import com.yk.search_car.dao.car.api.MakerTypeDAO;
import com.yk.search_car.model.used_car.MakerTypeModel;
import com.yk.search_car.service.maker_type_service.api.MakerTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by Satani on 2017/2/15.
 */
@Service("makerTypeService")
public class MakerTypeServiceImpl implements MakerTypeService {

    private Map<String,List<String>> makerToTypes=new HashMap<>();

    @Autowired
    private MakerTypeDAO makerTypeDAO;


    /**
     * @param maker 车辆制造商
     * @return 该制造商名下所有车系
     */
    @Override
    public List<String> getTypeByMaker(String maker) {
        List<String> result=new ArrayList<>();
        if(this.getMakerToTypes().containsKey(maker)){
            result.addAll(this.getMakerToTypes().get(maker));
        }
      return result;
    }

    /**
     * @return 所有车辆制造商
     */
    @Override
    public List<String> getAllMakers() {
        List<String> result=new ArrayList<>();
        result.addAll(this.getMakerToTypes().keySet());
        return result;
    }

    /**
     *  获取所有车辆品牌车系信息
     * @return
     */
    private Map<String,List<String>> getMakerToTypes(){
        if(this.makerToTypes.isEmpty()){
            for(MakerTypeModel model:this.makerTypeDAO.selectAllMakers()){
                if(!this.makerToTypes.containsKey(model.getMaker())){
                    List<String> types=new ArrayList<>();
                    types.add(model.getType());
                    this.makerToTypes.put(model.getMaker(),types);
                }
                else {
                    this.makerToTypes.get(model.getMaker()).add(model.getType());
                }
            }
        }
        return this.makerToTypes;
    }
}
