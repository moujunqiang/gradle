package com.mmrx.health.bean;

import com.lidroid.xutils.db.annotation.Table;

import mmrx.com.metrolayout.AbsMetroNode;

@Table(name="weight_bean")
public class WeightBean {
    private int id;
    private String ctime;
    private int weight;

    public WeightBean() {
    }

    public WeightBean(String ctime, int weight) {
        this.ctime = ctime;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Weight{" +
                "id=" + id +
                ", ctime=" + ctime +
                ", weight='" + weight + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
