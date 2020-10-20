package com.java.neo4jdatarest.dto;

import java.util.List;

public class Cart {

    private List<Item> items;
    private String destination;
    private String preference;

    public Cart(List<Item> items, String destination, String preference) {
        this.items = items;
        this.destination = destination;
        this.preference = preference;
    }

    public String getPreference() {
        return preference;
    }

    public void setPreference(String preference) {
        this.preference = preference;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "items=" + items +
                ", destination='" + destination + '\'' +
                ", preference='" + preference + '\'' +
                '}';
    }
}

