package com.szalai.packagetracker.model.node;

import com.szalai.packagetracker.model.relationship.ShopStatus;
import lombok.Data;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

@Data
@Node("Shop")
public class Shop {
    @Id
    @Property("name")
    private String name;
    @Property("location")
    private String location;
    @Relationship(type = "STARTED", direction = Relationship.Direction.OUTGOING)
    private List<ShopStatus> starts;

    public Shop(String name, String location) {
        this.name = name;
        this.location = location;
    }
}
