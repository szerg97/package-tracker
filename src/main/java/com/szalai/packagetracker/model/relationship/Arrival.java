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
public class Arrival {
    @RelationshipId
    private Long id;
    @Property("status")
    private final String status;
    @Property("dateTime")
    private LocalDateTime dateTime;
    @Property("packageId")
    private String packageId;
    @TargetNode
    private Warehouse warehouse;

    public Arrival(LocalDateTime dateTime, String packageId, Warehouse warehouse) {
        this.status = "Package has arrived";
        this.dateTime = dateTime;
        this.packageId = packageId;
        this.warehouse = warehouse;
    }
}
