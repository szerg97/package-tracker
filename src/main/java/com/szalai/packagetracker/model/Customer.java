package com.szalai.packagetracker.model;

import lombok.Data;

@Data
public class Customer {
    private String id;
    private String name;

    public Customer(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
