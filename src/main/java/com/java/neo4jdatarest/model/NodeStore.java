package com.java.neo4jdatarest.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.typeconversion.Convert;

import java.util.HashMap;
import java.util.Map;

@NodeEntity
@Data
public class NodeStore {

    @Id
    @GeneratedValue
    @JsonIgnore
    private Long id;

    @Convert(NoPrefixPropertiesConverter.class)
    private Map<String, Object> properties = new HashMap<>();
}
