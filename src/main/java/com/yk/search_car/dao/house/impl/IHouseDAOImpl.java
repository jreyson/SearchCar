package com.yk.search_car.dao.house.impl;

import com.yk.search_car.dao.house.api.IHouseDAO;
import com.yk.search_car.model.used_house.UsedHouseModel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Satani on 2017/2/21.
 */
@Repository("houseImpl")
public class IHouseDAOImpl implements IHouseDAO {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession(){
        return this.sessionFactory.openSession();
    }

    /**
     * 获取所有房源信息
     *
     * @return 房源信息队列
     */
    @Override
    public List<UsedHouseModel> getAllUsedHouse() {
        Session session=this.getCurrentSession();
        String hql="from UsedHouseModel";
        Query<UsedHouseModel> query=session.createQuery(hql);
        return query.getResultList();
    }

    /**
     * @param addressList 二手房房源地址
     * @return 二手房房源信息
     */
    @Override
    public List<UsedHouseModel> selectHouseByAddress(List<String> addressList) {
        Session session=this.getCurrentSession();
        Query<UsedHouseModel> query=session.createQuery(this.getAddressCondition(addressList));
        return query.getResultList();
    }

    /**
     * 批量删除房源信息
     *
     * @param houseModels 待删除的房源
     */
    @Override
    public void deleteHouse(List<UsedHouseModel> houseModels) {
        Session session=this.getCurrentSession();
        session.beginTransaction();
        for(UsedHouseModel model:houseModels){
            session.delete(model);
        }
        session.getTransaction().commit();
    }

    /**
     * 批量更新房源信息
     *
     * @param houseModels 待更新房源信息
     */
    @Override
    public void updateHouse(List<UsedHouseModel> houseModels) {
        Session session=this.getCurrentSession();
        session.beginTransaction();
        for(UsedHouseModel model:houseModels){
            session.update(model);
        }
        session.getTransaction().commit();

    }

    /**
     * 批量添加房源信息
     *
     * @param houseModels 待添加房源信息
     */
    @Override
    public void addHouse(List<UsedHouseModel> houseModels) {

        Session session=this.getCurrentSession();
        session.beginTransaction();
        for(UsedHouseModel model:houseModels){
            session.save(model);
        }
        session.getTransaction().commit();
    }

    /**
     *
     * @param addressList 二手房房源地址
     * @return hql查询语句
     */
    private String getAddressCondition(List<String> addressList){
        String hqlBase="from UsedHouseModel as house ";
        if(addressList.isEmpty())
            return hqlBase;
        String hqlCondition="where ";
        Iterator<String> iterator=addressList.iterator();
        while (iterator.hasNext()){
            String address=iterator.next();
            hqlCondition+="house.address like '%"+address+"%' or house.houseName like '%"+address+"%'";
            if(iterator.hasNext())
                hqlCondition+=" or ";
        }
        return hqlBase+hqlCondition;
    }

}
