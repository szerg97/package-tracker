package com.szalai.packagetracker.repository;

import com.szalai.packagetracker.model.node.Warehouse;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseRepository extends Neo4jRepository<Warehouse, String> {
}
