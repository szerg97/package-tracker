package com.szalai.packagetracker;

import com.szalai.packagetracker.model.Customer;
import com.szalai.packagetracker.model.Package;
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
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@SpringBootApplication
public class PackageTrackerApplication {
    private final ShopRepository shopRepository;
    private final DistributionPointRepository distributionPointRepository;
    private final WarehouseRepository warehouseRepository;
    private final DestinationRepository destinationRepository;

    public PackageTrackerApplication(ShopRepository shopRepository, DistributionPointRepository distributionPointRepository, WarehouseRepository warehouseRepository, DestinationRepository destinationRepository) {
        this.shopRepository = shopRepository;
        this.distributionPointRepository = distributionPointRepository;
        this.warehouseRepository = warehouseRepository;
        this.destinationRepository = destinationRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(PackageTrackerApplication.class, args);
    }

    @PostConstruct
    public void seed(){
        if (shopRepository.findAll().isEmpty()){
            Customer customerA = new Customer(UUID.randomUUID().toString(), "Gergo Szalai");
            Package packA = new Package(UUID.randomUUID().toString(), 24.0, customerA);

            Shop adidasShop = new Shop("Adidas Oakland", "1022 Oakland, 102. street 99.");
            DistributionPoint distributionPoint = new DistributionPoint("DP New York", "1028 NY, 15. street 1024.");
            Warehouse warehouse = new Warehouse("WH Munich", "90078 MUN, X. Y. street 66.");
            Destination destination = new Destination("Gergo's House","1088 Budapest, Y. Z. street 33.");

            adidasShop.setStarts(List.of(new ShopStatus("Started shipping", LocalDateTime.of(2023, 4, 20, 8, 0), packA.getId(), distributionPoint)));
            distributionPoint.setDistributions(List.of(new DistributionPointStatus("Distributing", LocalDateTime.of(2023, 4, 21, 8, 0), packA.getId(), warehouse)));
            warehouse.setSends(List.of(new WarehouseStatus("Sending", LocalDateTime.of(2023, 4, 22, 8, 0), packA.getId(), destination)));
            destination.setArrivals(List.of(new DestinationStatus("Sending", LocalDateTime.of(2023, 4, 23, 8, 0), packA.getId(), warehouse)));

            shopRepository.save(adidasShop);
            distributionPointRepository.save(distributionPoint);
            warehouseRepository.save(warehouse);
            destinationRepository.save(destination);
        }
    }
}
