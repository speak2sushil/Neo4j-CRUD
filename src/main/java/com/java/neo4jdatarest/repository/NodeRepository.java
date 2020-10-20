package com.java.neo4jdatarest.repository;

import com.java.neo4jdatarest.model.Node;
import com.java.neo4jdatarest.model.NodeData;
import com.java.neo4jdatarest.model.WareHouse;
import org.neo4j.ogm.model.Result;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

public interface NodeRepository extends Neo4jRepository<Node, Long> {

   /* @Query("MATCH p=(a)\n" +
            "WITH NODES(p) AS nodes\n" +
            "UNWIND nodes AS n\n" +
            "WITH n\n" +
            "WHERE $0 IN LABELS(n) and n[$1]=$2 \n" +
            "RETURN properties(n) as props , id(n) as id ")
    NodeData findByPropertyAndLabel(String label, String propertyName, String propertyValue);*/

    @Query("match (n:Node)-[s:SubType]->(sub:NodeStore) detach delete n,s,sub")
    void deleteAllNode();

    @Query("match (n:Node)-[s:SubType]->(sub:NodeStore) where id(n)=$0 detach delete n,sub")
    void deleteNode(Long id);

    @Query("match (n:Node) where n[$0]=$1 return n")
    Node findNodeByProperty(String propertyType, Object propertyValue);

    @Query("MATCH ()-[r1:`3PL`]-(n:Node )-[r:SubType]-(n1:NodeStore) where id(n)=$0\n" +
            "            RETURN n,n1,r1")
    Result findNodeAndRelationshipById(Long id);

}
