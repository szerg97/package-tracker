package com.szalai.packagetracker.model;

import com.szalai.packagetracker.model.node.Customer;

import java.util.UUID;

public class PackageFactory {
    private static PackageFactory instance;

    private PackageFactory(){}

    public static PackageFactory getInstance(){
        if (instance == null){
            return new PackageFactory();
        }
        return instance;
    }

    public Package createPackage(double weight, Customer customer){
        return new Package(UUID.randomUUID().toString(), weight, customer);
    }
}
