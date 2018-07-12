package com.yk.search_car.model.used_house;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by Satani on 2017/2/21.
 */
@Entity
@Table(name = "used_house", schema = "crawler_test")
public class UsedHouseModel {
    private int id;
    private String title;
    private String houseName;
    private String address;
    private String houseType;
    private String area;
    private BigDecimal totalPrice;
    private String unitPrice;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "house_name")
    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    @Basic
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "house_type")
    public String getHouseType() {
        return houseType;
    }

    public void setHouseType(String houseType) {
        this.houseType = houseType;
    }

    @Basic
    @Column(name = "area")
    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    @Basic
    @Column(name = "total_price")
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Basic
    @Column(name = "unit_price")
    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsedHouseModel that = (UsedHouseModel) o;

        if (id != that.id) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (houseName != null ? !houseName.equals(that.houseName) : that.houseName != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (houseType != null ? !houseType.equals(that.houseType) : that.houseType != null) return false;
        if (area != null ? !area.equals(that.area) : that.area != null) return false;
        if (totalPrice != null ? !totalPrice.equals(that.totalPrice) : that.totalPrice != null) return false;
        if (unitPrice != null ? !unitPrice.equals(that.unitPrice) : that.unitPrice != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (houseName != null ? houseName.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (houseType != null ? houseType.hashCode() : 0);
        result = 31 * result + (area != null ? area.hashCode() : 0);
        result = 31 * result + (totalPrice != null ? totalPrice.hashCode() : 0);
        result = 31 * result + (unitPrice != null ? unitPrice.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UsedHouseModel{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", houseName='" + houseName + '\'' +
                ", address='" + address + '\'' +
                ", houseType='" + houseType + '\'' +
                ", area='" + area + '\'' +
                ", totalPrice=" + totalPrice +
                ", unitPrice='" + unitPrice + '\'' +
                '}';
    }
}
