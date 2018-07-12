package com.yk.search_car.dao.car.impl;

import com.yk.search_car.dao.car.api.MakerTypeDAO;
import com.yk.search_car.model.used_car.MakerTypeModel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Satani on 2017/2/15.
 */
@Repository("makerTypeDAO")
public class MakeTypeDAOImpl implements MakerTypeDAO {
    @Autowired
    private SessionFactory sessionFactory;
    private Session getCurrentSession(){
        return this.sessionFactory.openSession();
    }
    /**
     * 查询所有车辆制造商对应车系
     *
     * @return 所有车辆制造商对应车系
     */
    @Override
    public List<MakerTypeModel> selectAllMakers() {

        Session session=this.getCurrentSession();
        String hql="from MakerTypeModel ";
        Query<MakerTypeModel> query=session.createQuery(hql);
        return query.getResultList();
    }

    /**
     * 查询某一制造商对应车系
     *
     * @param maker 车辆制造商
     * @return 该制造商对应车系
     */
    @Override
    public List<MakerTypeModel> selectTypesByMaker(String maker) {
        return null;
    }
}
