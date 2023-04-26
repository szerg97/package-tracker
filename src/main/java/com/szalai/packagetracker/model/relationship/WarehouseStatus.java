package com.szalai.packagetracker.model.relationship;

import com.szalai.packagetracker.model.node.Destination;
import com.szalai.packagetracker.model.node.Warehouse;
import lombok.Data;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.RelationshipId;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

import java.time.LocalDateTime;

@Data
@RelationshipProperties
public class WarehouseStatus {
    @RelationshipId
    private Long id;
    @Property("status")
    private String status;
    @Property("dateTime")
    private LocalDateTime dateTime;
    @Property("packageId")
    private String packageId;
    @TargetNode
    private Destination warehouse;

    public WarehouseStatus(LocalDateTime dateTime, String packageId, Destination destination) {
        this.status = String.format("Package with id [%s] was forwarded", packageId);
        this.dateTime = dateTime;
        this.packageId = packageId;
        this.warehouse = destination;
    }
}
