package com.szalai.packagetracker.controller;

import com.szalai.packagetracker.controller.response.RouteResponse;
import com.szalai.packagetracker.model.node.Customer;
import com.szalai.packagetracker.model.relationship.Arrival;
import com.szalai.packagetracker.service.QueryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PackageTrackerController {

    private final QueryService queryService;

    public PackageTrackerController(QueryService queryService) {
        this.queryService = queryService;
    }

    @GetMapping("/customers")
    public List<Customer> getAllCustomers(){
        return queryService.findAllCustomers();
    }

    @GetMapping("/customers/{customerId}/arrivals")
    public List<Arrival> getArrivalsByCustomer(
            @PathVariable String customerId){
        return queryService.findArrivalsByCustomer(customerId);
    }

    @GetMapping("/route/{packageId}")
    public RouteResponse getRouteByPackage(
            @PathVariable String packageId){
        return queryService.findRouteByPackage(packageId);
    }
}
