package com.java.neo4jdatarest.model;

import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.Data;
import org.springframework.data.neo4j.annotation.QueryResult;

import java.util.Map;

@QueryResult
@Data
public class NodeData {
    private Map<String,Object> props;
    private Long id;

}
