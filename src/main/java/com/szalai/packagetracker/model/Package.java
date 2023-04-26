package com.szalai.packagetracker.model;

import lombok.Data;

@Data
public class Package {
    private String id;
    private double weight;
    private Customer customer;

    public Package(String id, double weight, Customer customer) {
        this.id = id;
        this.weight = weight;
        this.customer = customer;
    }
}
