package com.szalai.packagetracker.model.node;

import java.util.UUID;

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
        return new DistributionPoint(UUID.randomUUID().toString(), name, location);
    }
}
