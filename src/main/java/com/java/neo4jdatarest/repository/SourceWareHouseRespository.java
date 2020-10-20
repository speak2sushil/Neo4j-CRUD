package com.java.neo4jdatarest.repository;


import com.java.neo4jdatarest.model.NodeData;
import com.java.neo4jdatarest.model.SourceWareHouse;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface SourceWareHouseRespository extends Neo4jRepository<SourceWareHouse,Long> {

/*    @Query("MATCH p=(a)\n" +
            "WITH NODES(p) AS nodes\n" +
            "UNWIND nodes AS n\n" +
            "WITH n\n" +
            "WHERE $0 IN LABELS(n) and n[$1]=$2 \n" +
            "RETURN n ")
    SourceWareHouse findByPropertyAndLabel(String label, String propertyName, String propertyValue);*/
}
