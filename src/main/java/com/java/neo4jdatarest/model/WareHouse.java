package com.java.neo4jdatarest.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@NodeEntity
public class WareHouse {

    public WareHouse() {

    }

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String location;
    private Integer holdingCapacity;
    @JsonIgnoreProperties("wareHouse")
    @Relationship(type = "HAS_ITEM")
    private List<Unit> Units;

    @JsonIgnoreProperties("wareHouseStart")
    @Relationship(type = "Provider", direction = Relationship.UNDIRECTED)
    private Set<Provider> Providers;

    public void shipThru(Provider Provider) {
        if (Providers == null) {
            Providers = new HashSet<>();
        }
        Providers.add(Provider);
    }

    public void hasItem(Unit unit) {
        if (Units == null) {
            Units = new ArrayList<>();
        }
        Units.add(unit);
    }

    @Override
    public String toString() {
        return "WareHouse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", holdingCapacity=" + holdingCapacity +
                '}';
    }

    public WareHouse(String name, String location, Integer holdingCapacity) {
        this.name = name;
        this.location = location;
        this.holdingCapacity = holdingCapacity;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getHoldingCapacity() {
        return holdingCapacity;
    }

    public void setHoldingCapacity(Integer holdingCapacity) {
        this.holdingCapacity = holdingCapacity;
    }

    public List<Unit> getUnits() {
        return Units;
    }

    public void setUnits(List<Unit> units) {
        Units = units;
    }

    public Set<Provider> getProviders() {
        return Providers;
    }

    public void setProviders(Set<Provider> providers) {
        Providers = providers;
    }
}
