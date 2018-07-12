package com.yk.search_car.model.used_car;

import javax.persistence.*;

/**
 * Created by Satani on 2017/2/15.
 */
@Entity
@Table(name = "maker_type", schema = "crawler")
public class MakerTypeModel {
    private String id;
    private String maker;
    private String type;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "maker")
    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    @Basic
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MakerTypeModel that = (MakerTypeModel) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (maker != null ? !maker.equals(that.maker) : that.maker != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (maker != null ? maker.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}
