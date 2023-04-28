package com.szalai.packagetracker.model.node;

public class DistributionPointFactory {
    private static DistributionPointFactory instance;

    private DistributionPointFactory(){}

    public static DistributionPointFactory getInstance(){
        if (instance == null){
            return new DistributionPointFactory();
        }
        return instance;
    }

    public DistributionPoint createDistributionPoint(String name, String location){
        return new DistributionPoint(name, location);
    }
}
