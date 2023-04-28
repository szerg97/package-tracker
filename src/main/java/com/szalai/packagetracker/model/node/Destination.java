package com.szalai.packagetracker.model.node;

import com.szalai.packagetracker.model.relationship.Arrival;
import lombok.Data;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.ArrayList;
import java.util.List;

@Data
@Node("Destination")
public class Destination {
    @Id
    @Property("name")
    private String name;
    @Property("location")
    private String location;
    @Relationship(type = "ARRIVED", direction = Relationship.Direction.INCOMING)
    private List<Arrival> arrivals = new ArrayList<>();

    public Destination(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public void addArrival(Arrival arrival){
        this.arrivals.add(arrival);
    }
}
