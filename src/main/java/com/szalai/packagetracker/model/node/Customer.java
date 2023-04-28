package com.szalai.packagetracker.model.node;

import com.szalai.packagetracker.model.relationship.Arrival;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Node("Customer")
@NoArgsConstructor
public class Customer {
    @Id
    private String id;
    @Property("name")
    private String name;
    @Property("location")
    private String location;
    @Relationship(type = "ARRIVED", direction = Relationship.Direction.INCOMING)
    private List<Arrival> arrivals = new ArrayList<>();

    public Customer(String id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }

    public void addArrival(Arrival forward){
        this.arrivals.add(forward);
    }
}
