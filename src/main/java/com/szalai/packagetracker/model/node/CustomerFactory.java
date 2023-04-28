package com.szalai.packagetracker.model.node;

public class CustomerFactory {
    private static CustomerFactory instance;

    private CustomerFactory(){}

    public static CustomerFactory getInstance(){
        if (instance == null){
            return new CustomerFactory();
        }
        return instance;
    }

    public Customer createCustomer(String name, String location){
        return new Customer(name, location);
    }
}
