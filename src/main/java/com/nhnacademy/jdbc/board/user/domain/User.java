package com.nhnacademy.jdbc.board.user.domain;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class User {
    private String id;
    private String password;
    private String name;
    private int userSeparateCode;

    public User(String id, String password, String name, int userSeparateCode) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.userSeparateCode = userSeparateCode;
    }
}