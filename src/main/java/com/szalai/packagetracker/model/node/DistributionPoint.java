package com.szalai.packagetracker.model.node;

import com.szalai.packagetracker.model.relationship.DestinationStatus;
import com.szalai.packagetracker.model.relationship.DistributionPointStatus;
import com.szalai.packagetracker.model.relationship.ShopStatus;
import lombok.Data;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.ArrayList;
import java.util.List;

@Data
@Node("DistributionPoint")
public class DistributionPoint {
    @Id
    @Property("name")
    private String name;
    @Property("location")
    private String location;
    @Relationship(type = "DISTRIBUTED", direction = Relationship.Direction.OUTGOING)
    private List<DistributionPointStatus> distributions = new ArrayList<>();

    public DistributionPoint(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public void addDistribution(DistributionPointStatus distributionPointStatus){
        this.distributions.add(distributionPointStatus);
    }
}
