package com.java.neo4jdatarest.model;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import lombok.Getter;
import org.neo4j.ogm.annotation.*;
import org.neo4j.ogm.annotation.typeconversion.Convert;

import java.util.HashMap;
import java.util.Map;

@RelationshipEntity(type = "3PL")
//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "id")
public class _3PL {
    public _3PL() {

    }

    @Id
    @GeneratedValue
    private Long id;

    @Convert(NoPrefixPropertiesConverter.class)
    private Map<String, Object> properties = new HashMap<>();


    @StartNode
    private Node source;


    @EndNode
    private Node target;

    @Override
    public String toString() {
        return "_3PL{" +
                "id=" + id +
                ", properties=" + properties +
                ", source=" + source +
                ", target=" + target +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Map<String, Object> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }

    public Node getSource() {
        return source;
    }

    public void setSource(Node source) {
        this.source = source;
    }

    public Node getTarget() {
        return target;
    }

    public void setTarget(Node target) {
        this.target = target;
    }
}
