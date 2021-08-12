package com.aop.proxy;

import com.aop.pojo.Farmer;
import com.aop.pojo.SellVegetables;

public class Greengrocer implements SellVegetables {

    private Farmer farmer;

    public Greengrocer(Farmer farmer) {
        this.farmer = farmer;
    }

    @Override
    public void sellVegetableForMoney() {
        System.out.println("进行质量检查");
        farmer.sellVegetableForMoney();
    }
}
