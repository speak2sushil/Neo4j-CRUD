package com.java.neo4jdatarest.dto;

public class Item{
     String sku;
     Integer quantity;

    @Override
    public String toString() {
        return "Item{" +
                "sku='" + sku + '\'' +
                ", quantity=" + quantity +
                '}';
    }

    public Item(String sku, Integer quantity) {
        this.sku = sku;
        this.quantity = quantity;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
