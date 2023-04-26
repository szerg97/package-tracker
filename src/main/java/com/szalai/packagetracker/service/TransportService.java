package com.szalai.packagetracker.service;

import com.szalai.packagetracker.model.node.Destination;
import com.szalai.packagetracker.model.node.DistributionPoint;
import com.szalai.packagetracker.model.node.Shop;
import com.szalai.packagetracker.model.node.Warehouse;
import com.szalai.packagetracker.model.relationship.DestinationStatus;
import com.szalai.packagetracker.model.relationship.DistributionPointStatus;
import com.szalai.packagetracker.model.relationship.ShopStatus;
import com.szalai.packagetracker.model.relationship.WarehouseStatus;
import com.szalai.packagetracker.repository.DestinationRepository;
import com.szalai.packagetracker.repository.DistributionPointRepository;
import com.szalai.packagetracker.repository.ShopRepository;
import com.szalai.packagetracker.repository.WarehouseRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TransportService {

    private final ShopRepository shopRepository;
    private final DistributionPointRepository distributionPointRepository;
    private final WarehouseRepository warehouseRepository;
    private final DestinationRepository destinationRepository;

    public TransportService(ShopRepository shopRepository, DistributionPointRepository distributionPointRepository, WarehouseRepository warehouseRepository, DestinationRepository destinationRepository) {
        this.shopRepository = shopRepository;
        this.distributionPointRepository = distributionPointRepository;
        this.warehouseRepository = warehouseRepository;
        this.destinationRepository = destinationRepository;
    }

    public void post(Shop from, DistributionPoint to, LocalDateTime dateTime, String packageId) {
        from.addPost(new ShopStatus(dateTime, packageId, to));
        shopRepository.save(from);
    }

    public void distribute(DistributionPoint from, Warehouse to, LocalDateTime dateTime, String packageId) {
        from.addDistribution(new DistributionPointStatus(dateTime, packageId, to));
        distributionPointRepository.save(from);
    }

    public void forward(Warehouse from, Destination to, LocalDateTime dateTime, String packageId) {
        from.addForward(new WarehouseStatus(dateTime, packageId, to));
        warehouseRepository.save(from);
    }

    public void arrive(Warehouse from, Destination to, LocalDateTime dateTime, String packageId) {
        to.addArrival(new DestinationStatus(dateTime, packageId, from));
        destinationRepository.save(to);
    }
}
