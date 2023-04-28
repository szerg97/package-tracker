package com.szalai.packagetracker.service;

import com.szalai.packagetracker.controller.response.TrackResponse;
import com.szalai.packagetracker.model.node.Customer;
import com.szalai.packagetracker.model.relationship.Forward;
import com.szalai.packagetracker.model.relationship.Distribution;
import com.szalai.packagetracker.model.relationship.Arrival;
import com.szalai.packagetracker.model.relationship.Post;
import com.szalai.packagetracker.repository.CustomerRepository;
import com.szalai.packagetracker.repository.DistributionPointRepository;
import com.szalai.packagetracker.repository.ShopRepository;
import com.szalai.packagetracker.repository.WarehouseRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QueryService {

    private final RelationshipMapper mapper;
    private final CustomerRepository customerRepository;
    private final ShopRepository shopRepository;
    private final DistributionPointRepository distributionPointRepository;
    private final WarehouseRepository warehouseRepository;

    public QueryService(RelationshipMapper mapper, CustomerRepository customerRepository, ShopRepository shopRepository, DistributionPointRepository distributionPointRepository, WarehouseRepository warehouseRepository) {
        this.mapper = mapper;
        this.customerRepository = customerRepository;
        this.shopRepository = shopRepository;
        this.distributionPointRepository = distributionPointRepository;
        this.warehouseRepository = warehouseRepository;
    }

    public List<Customer> findAllCustomers(){
        return customerRepository.findAll();
    }

    public List<Arrival> findArrivalsByCustomer(String customerId){
        return customerRepository.findById(customerId).orElseThrow().getArrivals();
    }

    public TrackResponse findTrackByPackage(String packageId) {
        Post post = findPost(packageId);
        Distribution distribution = findDistribution(packageId);
        Forward forward = findForward(packageId);
        Arrival arrival = findArrival(packageId);
        return new TrackResponse(
                mapper.postToResponse(post),
                mapper.distributionToResponse(distribution),
                mapper.forwardToResponse(forward),
                mapper.arrivalToResponse(arrival));
    }

    private Post findPost(String packageId) {
        List<Post> postList = new ArrayList<>();
        shopRepository
                .findAll()
                .forEach(s -> {
                    List<Post> posts = s.getPosts();
                    postList.addAll(posts);
                });
        return postList.stream().filter(p -> p.getPackageId().equals(packageId)).findFirst().orElseThrow();
    }

    private Distribution findDistribution(String packageId) {
        List<Distribution> distributionList = new ArrayList<>();
        distributionPointRepository
                .findAll()
                .forEach(d -> {
                    List<Distribution> distributions = d.getDistributions();
                    distributionList.addAll(distributions);
                });
        return distributionList.stream().filter(p -> p.getPackageId().equals(packageId)).findFirst().orElseThrow();
    }

    private Arrival findArrival(String packageId) {
        List<Arrival> arrivalList = new ArrayList<>();
        customerRepository
                .findAll()
                .forEach(c -> {
                    List<Arrival> arrivals = c.getArrivals();
                    arrivalList.addAll(arrivals);
                });
        return arrivalList.stream().filter(p -> p.getPackageId().equals(packageId)).findFirst().orElseThrow();
    }

    private Forward findForward(String packageId) {
        List<Forward> forwardList = new ArrayList<>();
        warehouseRepository
                .findAll()
                .forEach(w -> {
                    List<Forward> forwards = w.getForwards();
                    forwardList.addAll(forwards);
                });
        return forwardList.stream().filter(p -> p.getPackageId().equals(packageId)).findFirst().orElseGet(() -> null);
    }
}
