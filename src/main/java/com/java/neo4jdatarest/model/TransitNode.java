package com.java.neo4jdatarest.model;

import lombok.Data;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.typeconversion.Convert;

import java.util.HashMap;
import java.util.Map;

@NodeEntity(label= "Transit")
@Data
public class TransitNode extends  NodeStore{

   /* @Id
    @GeneratedValue
    private Long id;

    @Convert(NoPrefixPropertiesConverter.class)
    private Map<String, Object> properties = new HashMap<>();*/
}
