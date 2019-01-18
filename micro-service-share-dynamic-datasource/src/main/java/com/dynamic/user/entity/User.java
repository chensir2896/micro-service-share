package com.dynamic.user.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable{

    private Integer userId;
    private String userName;
    private String dataBase;

}
