package com.java.neo4jdatarest.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;

@NodeEntity
public class SKU {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String sku;
    @JsonIgnoreProperties("sku")
    @Relationship(type="HAS_ITEM", direction = Relationship.INCOMING)
    private List<Unit> units;

    public SKU(String name, String sku) {
        this.name = name;
        this.sku = sku;
    }

    public SKU() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public List<Unit> getUnits() {
        return units;
    }

    public void setUnits(List<Unit> units) {
        this.units = units;
    }

    @Override
    public String toString() {
        return "SKU{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sku='" + sku + '\'' +
                ", units=" + units +
                '}';
    }
}
