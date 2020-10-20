package com.java.neo4jdatarest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.neo4j.ogm.annotation.*;


@RelationshipEntity(type = "HAS_ITEM")
public class Unit {


    @Id
    @GeneratedValue
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public WareHouse getWareHouse() {
        return wareHouse;
    }

    public void setWareHouse(WareHouse wareHouse) {
        this.wareHouse = wareHouse;
    }

    public SKU getSku() {
        return sku;
    }

    public void setSku(SKU sku) {
        this.sku = sku;
    }

    private Integer quantity;

    public Unit() {

    }

    @StartNode
    @JsonIgnore
    private WareHouse wareHouse;

    @EndNode
    @JsonIgnore
    private SKU sku;

    @Override
    public String toString() {
        return "Unit{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", wareHouse=" + wareHouse +
                ", sku=" + sku +
                '}';
    }
}
