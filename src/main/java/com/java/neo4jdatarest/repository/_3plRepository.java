package com.java.neo4jdatarest.repository;

import com.java.neo4jdatarest.model.Node;
import com.java.neo4jdatarest.model._3PL;
import org.neo4j.ogm.model.Result;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface _3plRepository extends Neo4jRepository<_3PL, Long> {

    @Query("match (s:Node)-[r:`3PL`]->(d:Node) where id(s)=$0 and id(d)=$1 return s,r,d")
    Result findRelationBetweenNodes(Long sourceId, Long destinationId);
}
