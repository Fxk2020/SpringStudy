package com.ioc.pojo;

import java.util.Arrays;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class Student {

    private String name;
    private int age;
    private Address address;

    private String[] books;
    private Map<String,String> hobbies;
    private Set<String> games;

    private Properties info;

    public Student() {
    }

    public Student(String name, int age, Address address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String[] getBooks() {
        return books;
    }

    public void setBooks(String[] books) {
        this.books = books;
    }

    public Map<String, String> getHobbies() {
        return hobbies;
    }

    public void setHobbies(Map<String, String> hobbies) {
        this.hobbies = hobbies;
    }

    public Set<String> getGames() {
        return games;
    }

    public void setGames(Set<String> games) {
        this.games = games;
    }

    public Properties getInfo() {
        return info;
    }

    public void setInfo(Properties info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", address=" + address +
                ", books=" + Arrays.toString(books) +
                ", hobbies=" + hobbies +
                ", games=" + games +
                ", info=" + info +
                '}';
    }
}
