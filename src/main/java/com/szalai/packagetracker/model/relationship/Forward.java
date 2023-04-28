package com.szalai.packagetracker.model.relationship;

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
public class Forward {
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

    public Forward(LocalDateTime dateTime, String packageId, Warehouse warehouse) {
        this.status = "Package was forwarded";
        this.dateTime = dateTime;
        this.packageId = packageId;
        this.warehouse = warehouse;
    }
}
