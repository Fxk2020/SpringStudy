package com.transaction.pojo;


import lombok.Data;

@Data
public class Blog {

    private int id;
    private int user_id;
    private String title;
    private String description;
    private String content;

    private String created;
    private int status;

}
