package com.java.neo4jdatarest.model;


import com.fasterxml.jackson.annotation.*;
import lombok.*;
import org.neo4j.ogm.annotation.*;
import org.neo4j.ogm.annotation.typeconversion.Convert;


import java.util.*;


@NodeEntity

//@JsonIgnoreProperties({"_3pl"})
//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "id")
public class Node {

    @Id
    @GeneratedValue
    private Long id;

    @Convert(NoPrefixPropertiesConverter.class)
    private Map<String, Object> properties = new HashMap<>();

    /*@Labels
    private List<NodeStore> labels = new ArrayList<>();*/

//    @JsonIgnoreProperties({"source"})
    @Relationship(type = "3PL", direction = Relationship.UNDIRECTED)
   // @JsonBackReference
    private Set<_3PL> _3pl;

    @Relationship("SubType")
    private Set<NodeStore> nodeCapabilities;

    public Node() {
    }

    @Override
    public String toString() {
        return "Node{" +
                "id=" + id +
                ", properties=" + properties +
                ", _3pl=" + _3pl +
                ", nodeCapabilities=" + nodeCapabilities +
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

    public Set<_3PL> get_3pl() {
        return _3pl;
    }

    public void set_3pl(Set<_3PL> _3pl) {
        this._3pl = _3pl;
    }

    public Set<NodeStore> getNodeCapabilities() {
        return nodeCapabilities;
    }

    public void setNodeCapabilities(Set<NodeStore> nodeCapabilities) {
        this.nodeCapabilities = nodeCapabilities;
    }
}





