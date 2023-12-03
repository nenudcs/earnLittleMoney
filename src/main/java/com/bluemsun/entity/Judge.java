package com.bluemsun.entity;

import lombok.Data;

/**
 * 评委
 */
@Data
public class Judge {
    private Integer id;
    private String name;
    private String password;

    public Judge() {
    }

    public Judge(Integer id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public Judge(String name, String password) {
        this.name = name;
        this.password = password;
    }
}
