package com.java.neo4jdatarest.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.neo4j.ogm.annotation.*;

@RelationshipEntity(type = "Provider")
public class Provider {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Double deliveryTime;
    private Double shippingCost;

    public Provider(String name, Double deliveryTime, Double shippingCost) {
        this.name = name;
        this.deliveryTime = deliveryTime;
        this.shippingCost = shippingCost;
    }

    public Provider() {

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

    public Double getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Double deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public Double getShippingCost() {
        return shippingCost;
    }

    public void setShippingCost(Double shippingCost) {
        this.shippingCost = shippingCost;
    }

    public WareHouse getWareHouseStart() {
        return wareHouseStart;
    }

    public void setWareHouseStart(WareHouse wareHouseStart) {
        this.wareHouseStart = wareHouseStart;
    }

    public WareHouse getWareHouseEnd() {
        return wareHouseEnd;
    }

    public void setWareHouseEnd(WareHouse wareHouseEnd) {
        this.wareHouseEnd = wareHouseEnd;
    }
    @JsonIgnore
    @StartNode
    private WareHouse wareHouseStart;


    @JsonIgnore
    @EndNode
    private WareHouse wareHouseEnd;


}
