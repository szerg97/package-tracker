package com.szalai.packagetracker.model.node;

import com.szalai.packagetracker.model.relationship.ShopStatus;
import com.szalai.packagetracker.model.relationship.WarehouseStatus;
import lombok.Data;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

@Data
@Node("Warehouse")
public class Warehouse {
    @Id
    @Property("name")
    private String name;
    @Property("location")
    private String location;
    @Relationship(type = "SENT", direction = Relationship.Direction.OUTGOING)
    private List<WarehouseStatus> sends;

    public Warehouse(String name, String location) {
        this.name = name;
        this.location = location;
    }
}
