package com.ioc.pojo;

public class Hello {

    private String str;
    private int a;

    public Hello(String str,int a) {
        this.str = str;
        this.a = a;
    }

    public Hello(){

    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }
}
