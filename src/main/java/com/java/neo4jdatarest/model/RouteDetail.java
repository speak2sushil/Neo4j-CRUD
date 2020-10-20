package com.java.neo4jdatarest.model;

import com.fasterxml.jackson.annotation.JsonFilter;
import org.springframework.data.neo4j.annotation.QueryResult;

import java.util.List;

@QueryResult
public class RouteDetail {

    private String  deliveryDate;
    private String  shippingDate;
    private String  sourceWareHouse;
    private String  destination;
    private List<String> transitRoute;
    private String  product;
    private String sku;
    private Integer quantity;

    @Override
    public String toString() {
        return "RouteDetail{" +
                "deliveryDate='" + deliveryDate + '\'' +
                ", shippingDate='" + shippingDate + '\'' +
                ", sourceWareHouse='" + sourceWareHouse + '\'' +
                ", destination='" + destination + '\'' +
                ", transitRoute=" + transitRoute +
                ", product='" + product + '\'' +
                ", sku=" + sku +
                ", quantity=" + quantity +
                '}';
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getShippingDate() {
        return shippingDate;
    }

    public void setShippingDate(String shippingDate) {
        this.shippingDate = shippingDate;
    }

    public String getSourceWareHouse() {
        return sourceWareHouse;
    }

    public void setSourceWareHouse(String sourceWareHouse) {
        this.sourceWareHouse = sourceWareHouse;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public List<String> getTransitRoute() {
        return transitRoute;
    }

    public void setTransitRoute(List<String> transitRoute) {
        this.transitRoute = transitRoute;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
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
