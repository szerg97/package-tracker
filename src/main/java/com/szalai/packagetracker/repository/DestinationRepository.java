package com.szalai.packagetracker.repository;

import com.szalai.packagetracker.model.node.Destination;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DestinationRepository extends Neo4jRepository<Destination, String> {
}
