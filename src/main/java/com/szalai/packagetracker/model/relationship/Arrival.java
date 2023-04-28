package com.szalai.packagetracker.model.relationship;

import com.szalai.packagetracker.model.node.Customer;
import com.szalai.packagetracker.model.node.Warehouse;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.RelationshipId;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@RelationshipProperties
public class Arrival {
    @RelationshipId
    private Long id;
    @Property("status")
    private String status;
    @Property("dateTime")
    private LocalDateTime dateTime;
    @Property("packageId")
    private String packageId;
    @TargetNode
    private Warehouse customer;

    public Arrival(LocalDateTime dateTime, String packageId, Warehouse customer) {
        this.status = "Package has arrived";
        this.dateTime = dateTime;
        this.packageId = packageId;
        this.customer = customer;
    }
}
