package com.aop.pojo;

public class Farmer implements SellVegetables{
    @Override
    public void sellVegetableForMoney() {
        System.out.println("卖菜");
    }
}
