package com.aop.pojo;

public class Host implements Rent {

    private String name;
    private String houseId;

    public Host() {
    }

    public Host(String name,String houseId) {
        this.name = name;
        this.houseId = houseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }

    @Override
    public void rent() {
        System.out.println(name+"出租房子");
    }
}
