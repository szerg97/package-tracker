package com.szalai.packagetracker;

import com.szalai.packagetracker.model.Package;
import com.szalai.packagetracker.model.node.Customer;
import com.szalai.packagetracker.model.node.DistributionPoint;
import com.szalai.packagetracker.model.node.Shop;
import com.szalai.packagetracker.model.node.Warehouse;
import com.szalai.packagetracker.repository.ShopRepository;
import com.szalai.packagetracker.service.TransportService;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.List;
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

            List<Shop> shops = createShops();
            List<DistributionPoint> distributionPoints = createDistributionPoints();
            List<Warehouse> warehouses = createWarehouses();
            List<Customer> customers = createCustomers();

            sendPackagesToCustomers(shops, distributionPoints, warehouses, customers);
        }
    }

    private void sendPackagesToCustomers(List<Shop> shops,
                                         List<DistributionPoint> distributionPoints,
                                         List<Warehouse> warehouses,
                                         List<Customer> customers) {
        for (Customer customer : customers) {
            Package pack = createPackage(customer);
            service.post(shops.get(0), distributionPoints.get(0), LocalDateTime.of(2023, 4, 20, 8, 0), pack.getId());
            service.distribute(distributionPoints.get(0), warehouses.get(0), LocalDateTime.of(2023, 4, 21, 8, 0), pack.getId());
            //service.forward(warehouse, customers.get(i), LocalDateTime.of(2023, 4, 22, 8, 0), pack.getId());
            service.arrive(warehouses.get(0), customer, LocalDateTime.of(2023, 4, 23, 8, 0), pack.getId());
        }
    }

    private List<Shop> createShops() {
        return List.of(new Shop("Adidas Oakland", "1022 Oakland, 102. street 99."));
    }

    private List<DistributionPoint> createDistributionPoints() {
        return List.of(new DistributionPoint("DP New York", "1028 NY, 15. street 1024."));
    }

    private List<Warehouse> createWarehouses() {
        return List.of(new Warehouse("WH Munich", "90078 MUN, X. Y. street 66."));
    }

    private List<Customer> createCustomers() {
        return List.of(
                new Customer("Adam Abraham","1088 Budapest, Y. Z. street 33."),
                new Customer("Betty Brown","8000 Warsaw, Y. Z. street 43."),
                new Customer("Charles Candy","7865 Vienna, Y. Z. street 22."),
                new Customer("Daniel Downing","66433 Berlin, Y. Z. street 65."),
                new Customer("Elizabeth Earnings","5345 Rome, Y. Z. street 98."));
    }

    private Package createPackage(Customer customer) {
        return new Package(UUID.randomUUID().toString(), 24.0, customer);
    }
}
