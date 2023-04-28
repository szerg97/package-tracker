package com.szalai.packagetracker.model.node;

import com.szalai.packagetracker.model.relationship.Distribution;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.ArrayList;
import java.util.List;

@Data
@Node("DistributionPoint")
@NoArgsConstructor
public class DistributionPoint {
    @Id
    private String id;
    @Property("name")
    private String name;
    @Property("location")
    private String location;
    @Relationship(type = "DISTRIBUTED", direction = Relationship.Direction.OUTGOING)
    private List<Distribution> distributions = new ArrayList<>();

    public DistributionPoint(String id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }

    public void addDistribution(Distribution distribution){
        this.distributions.add(distribution);
    }
}
