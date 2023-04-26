package com.szalai.packagetracker.repository;

import com.szalai.packagetracker.model.node.DistributionPoint;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistributionPointRepository extends Neo4jRepository<DistributionPoint, String> {
}
