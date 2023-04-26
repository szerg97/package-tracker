package com.szalai.packagetracker.model.relationship;

import com.szalai.packagetracker.model.node.Warehouse;
import lombok.Data;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.RelationshipId;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

import java.time.LocalDateTime;

@Data
@RelationshipProperties
public class DestinationStatus {
    @RelationshipId
    private Long id;
    @Property("status")
    private String status;
    @Property("dateTime")
    private LocalDateTime dateTime;
    @Property("packageId")
    private String packageId;
    @TargetNode
    private Warehouse warehouse;

    public DestinationStatus(String status, LocalDateTime dateTime, String packageId, Warehouse warehouse) {
        this.status = status;
        this.dateTime = dateTime;
        this.packageId = packageId;
        this.warehouse = warehouse;
    }
}
