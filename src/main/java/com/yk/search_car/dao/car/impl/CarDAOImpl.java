package com.yk.search_car.dao.car.impl;

import com.yk.search_car.dao.car.api.CarDAO;
import com.yk.search_car.model.used_car.UsedCarModel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by Satani on 2017/2/13.
 */
@Repository("carDAO")
public class CarDAOImpl implements CarDAO{
    @Autowired
    private SessionFactory sessionFactory;
    private Session getCurrentSession(){
        return this.sessionFactory.openSession();
    }

    /**
     * 获取所有车辆信息
     *
     * @return 所有车辆信息
     */
    @Override
    public List<UsedCarModel> getALLCars() {
        String hql="from UsedCarModel ";
        Query<UsedCarModel> query=this.getCurrentSession().createQuery(hql);
        return query.getResultList();
    }

    /**
     * @param description 车辆信息描述
     * @return 符合该描述的所有车辆信息
     */
    @Override
    public List<UsedCarModel> getCarsByDescription(String description) {
        String hql="from UsedCarModel as model where model.description like '%"+description+"%'";
        Query<UsedCarModel> query=this.getCurrentSession().createQuery(hql);
        return query.getResultList();
    }

    /**
     * 通过车辆原始价格区间查询
     *
     * @param first  起始价格
     * @param second 终止价格
     * @return 该原始价格区间的所有车辆信息
     */
    @Override
    public List<UsedCarModel> getCarsByOriginalPriceRange(float first, float second) {
        String hql="from UsedCarModel where UsedCarModel.originalPrice>=:firstPrice and UsedCarModel .originalPrice<=:secondPrice";
        Query<UsedCarModel> query=this.getCurrentSession().createQuery(hql);
        query.setParameter("firstPrice",first);
        query.setParameter("secondPrice",second);
        return query.getResultList();
    }

    /**
     * 通过车辆当前价格区间查询
     *
     * @param first  起始价格
     * @param second 终止价格
     * @return 该当前价格区间的所有车辆信息
     */
    @Override
    public List<UsedCarModel> getCarsByPresentPriceRange(float first, float second) {
        String hql="from UsedCarModel where UsedCarModel.presentPrice>=:firstPrice and UsedCarModel .presentPrice<=:secondPrice";
        Query<UsedCarModel> query=this.getCurrentSession().createQuery(hql);
        query.setParameter("firstPrice",first);
        query.setParameter("secondPrice",second);
        return query.getResultList();
    }

    /**
     * 通过车辆所属城市查询
     *
     * @param cityName 城市名称
     * @return 该城市名称下的所有车辆信息
     */
    @Override
    public List<UsedCarModel> getCarsByCity(String cityName) {
        String hql="from UsedCarModel where UsedCarModel.city=:city";
        Query<UsedCarModel> query=this.getCurrentSession().createQuery(hql);
        query.setParameter("city",cityName);
        return query.getResultList();
    }

    /**
     * 通过车辆行驶里程区间查询
     *
     * @param first  起始里程
     * @param second 终止里程
     * @return 该里程区间的所有车辆信息
     */
    @Override
    public List<UsedCarModel> getCarsByMileageRange(int first, int second) {
        String hql="from UsedCarModel where UsedCarModel.mileage>=:firstMileage and UsedCarModel .mileage<=:secondMileage";
        Query<UsedCarModel> query=this.getCurrentSession().createQuery(hql);
        query.setParameter("firstMileage",first);
        query.setParameter("secondMileage",second);
        return query.getResultList();
    }

    /**
     * 通过车辆上牌日期查询
     *
     * @param date 上牌日期
     * @return 该日期内上牌的所有车辆信息
     */
    @Override
    public List<UsedCarModel> getCarsByRegisterTime(Date date) {
//        String hql="from UsedCarModel where UsedCarModel .";
//        Query<UsedCarModel> query=this.getCurrentSession().createQuery(hql);
//        return query.getResultList();
        return null;
    }

    /**
     * 通过车辆油箱容量区间查询
     *
     * @param first  起始容量
     * @param second 终止容量
     * @return 该容量区间的所有车辆信息
     */
    @Override
    public List<UsedCarModel> getCarsByCapacityRange(float first, float second) {
        return null;
    }
}
