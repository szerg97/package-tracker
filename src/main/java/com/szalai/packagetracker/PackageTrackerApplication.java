package com.szalai.packagetracker;

import com.szalai.packagetracker.model.Package;
import com.szalai.packagetracker.model.PackageFactory;
import com.szalai.packagetracker.model.node.*;
import com.szalai.packagetracker.repository.ShopRepository;
import com.szalai.packagetracker.service.TransportService;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SpringBootApplication
public class PackageTrackerApplication {
    private final TransportService service;
    private final ShopRepository shopRepository;

    private List<Shop> shops;
    private List<DistributionPoint> distributionPoints;
    private List<Warehouse> warehouses;
    private List<Customer> customers;

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
            shops = createShops();
            distributionPoints = createDistributionPoints();
            warehouses = createWarehouses();
            customers = createCustomers(20);
            sendPackagesToCustomers();
        }
    }

    private void sendPackagesToCustomers() {
        for (Customer customer : customers) {
            Shop randomShop = getRandomShop();
            DistributionPoint randomDp = getRandomDistributionPoint();
            Warehouse randomWh = getRandomWarehouse();
            Package pack = createPackage(customer);
            service.post(randomShop, randomDp, LocalDateTime.of(2023, 4, 20, 8, 0), pack.getId());
            service.distribute(randomDp, randomWh, LocalDateTime.of(2023, 4, 21, 8, 0), pack.getId());
            //service.forward(randomWh, randomWh2, LocalDateTime.of(2023, 4, 22, 8, 0), pack.getId());
            service.arrive(randomWh, customer, LocalDateTime.of(2023, 4, 23, 8, 0), pack.getId());
        }
    }

    private Shop getRandomShop(){
        return shops.get(new Random().nextInt(0, shops.size()));
    }

    private DistributionPoint getRandomDistributionPoint(){
        return distributionPoints.get(new Random().nextInt(0, distributionPoints.size()));
    }

    private Warehouse getRandomWarehouse(){
        return warehouses.get(new Random().nextInt(0, warehouses.size()));
    }

    private List<Shop> createShops() {
        ShopFactory factory = ShopFactory.getInstance();
        return List.of(
                factory.createShop("Adidas Oakland", "3022 OA, 102. street 99."),
                factory.createShop("Nike New York", "1028 NY, 105. street 105."),
                factory.createShop("Adidas Chicago", "5089 CH, 88. street 103."));
    }

    private List<DistributionPoint> createDistributionPoints() {
        DistributionPointFactory factory = DistributionPointFactory.getInstance();
        return List.of(
                factory.createDistributionPoint("DP New York", "1028 NY, 15. street 1024."),
                factory.createDistributionPoint("DP Washington", "4000 WA, 106. street 29."));
    }

    private List<Warehouse> createWarehouses() {
        WarehouseFactory factory = WarehouseFactory.getInstance();
        return List.of(
                factory.createWarehouse("WH Munich", "90078 MUN, X. Y. street 66."),
                factory.createWarehouse("WH London", "88008 LON, X. Y. street 78."),
                factory.createWarehouse("WH Vienna", "7867 VIE, X. Y. street 99."),
                factory.createWarehouse("WH Budapest", "1082 BUD, X. Y. street 11."));
    }

    private List<Customer> createCustomers(int number) {
        List<Customer> listCreated = new ArrayList<>();
        CustomerFactory factory = CustomerFactory.getInstance();
        for (int i = 0; i < number; i++) {
            Customer customer = factory.createCustomer("Customer " + (i + 1), "Customer " + (i + 1) + "'s address");
            listCreated.add(customer);
        }
        return listCreated;
    }

    private Package createPackage(Customer customer) {
        PackageFactory factory = PackageFactory.getInstance();
        return factory.createPackage(24.0, customer);
    }
}
