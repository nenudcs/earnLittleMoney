package com.bluemsun.entity;

import lombok.Data;

/**
 * 评委
 */
@Data
public class Judge {
    private String name;
    private String password;

    public Judge() {
    }

    public Judge(String name, String password) {
        this.name = name;
        this.password = password;
    }
}
