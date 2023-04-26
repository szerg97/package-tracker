package com.szalai.packagetracker.repository;

import com.szalai.packagetracker.model.node.Shop;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopRepository extends Neo4jRepository<Shop, String> {
}
