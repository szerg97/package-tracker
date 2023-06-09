package com.szalai.packagetracker.model.relationship;

import com.szalai.packagetracker.model.node.DistributionPoint;
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
public class Post {
    @RelationshipId
    private Long id;
    @Property("status")
    private String status;
    @Property("dateTime")
    private LocalDateTime dateTime;
    @Property("packageId")
    private String packageId;
    @TargetNode
    private DistributionPoint distributionPoint;

    public Post(LocalDateTime dateTime, String packageId, DistributionPoint distributionPoint) {
        this.status = "Package was posted";
        this.dateTime = dateTime;
        this.packageId = packageId;
        this.distributionPoint = distributionPoint;
    }
}
