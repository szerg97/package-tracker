package com.szalai.packagetracker.model.node;

import com.szalai.packagetracker.model.relationship.Forward;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.ArrayList;
import java.util.List;

@Data
@Node("Warehouse")
@NoArgsConstructor
public class Warehouse {
    @Id
    private String id;
    @Property("name")
    private String name;
    @Property("location")
    private String location;
    @Relationship(type = "FORWARDED", direction = Relationship.Direction.OUTGOING)
    private List<Forward> forwards = new ArrayList<>();

    public Warehouse(String id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }

    public void addForward(Forward forward){
        this.forwards.add(forward);
    }
}
