package com.szalai.packagetracker.model.node;

public class WarehouseFactory {
    private static WarehouseFactory instance;

    private WarehouseFactory(){}

    public static WarehouseFactory getInstance(){
        if (instance == null){
            return new WarehouseFactory();
        }
        return instance;
    }

    public Warehouse createWarehouse(String name, String location){
        return new Warehouse(name, location);
    }
}
