package com.mybatis.pojo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class User {

    private int id;
    private String username;
    private String avatar;
    private String email;
    private String password;
    private int status;

}
