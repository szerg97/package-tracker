package com.szalai.packagetracker.model.node;

import com.szalai.packagetracker.model.relationship.Post;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.ArrayList;
import java.util.List;

@Data
@Node("Shop")
@NoArgsConstructor
public class Shop {
    @Id
    private String id;
    @Property("name")
    private String name;
    @Property("location")
    private String location;
    @Relationship(type = "POSTED", direction = Relationship.Direction.OUTGOING)
    private List<Post> posts = new ArrayList<>();

    public Shop(String id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }

    public void addPost(Post post){
        this.posts.add(post);
    }
}
