package com.szalai.packagetracker;

import com.szalai.packagetracker.model.Customer;
import com.szalai.packagetracker.model.Package;
import com.szalai.packagetracker.model.node.Destination;
import com.szalai.packagetracker.model.node.DistributionPoint;
import com.szalai.packagetracker.model.node.Shop;
import com.szalai.packagetracker.model.node.Warehouse;
import com.szalai.packagetracker.repository.ShopRepository;
import com.szalai.packagetracker.service.TransportService;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.UUID;

@SpringBootApplication
public class PackageTrackerApplication {
    private final TransportService service;
    private final ShopRepository shopRepository;

    public PackageTrackerApplication(ShopRepository shopRepository, TransportService service) {
        this.service = service;
        this.shopRepository = shopRepository;
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

            service.post(adidasShop, distributionPoint, LocalDateTime.of(2023, 4, 20, 8, 0), packA.getId());
            service.distribute(distributionPoint, warehouse, LocalDateTime.of(2023, 4, 21, 8, 0), packA.getId());
            service.forward(warehouse, destination, LocalDateTime.of(2023, 4, 22, 8, 0), packA.getId());
            service.arrive(warehouse, destination, LocalDateTime.of(2023, 4, 23, 8, 0), packA.getId());

        }
    }
}
