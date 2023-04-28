package com.szalai.packagetracker.model.node;

public class ShopFactory {
    private static ShopFactory instance;

    private ShopFactory(){}

    public static ShopFactory getInstance(){
        if (instance == null){
            return new ShopFactory();
        }
        return instance;
    }

    public Shop createShop(String name, String location){
        return new Shop(name, location);
    }
}
