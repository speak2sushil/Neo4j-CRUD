package com.java.neo4jdatarest.model;

import com.fasterxml.jackson.annotation.JsonFilter;
import org.springframework.data.neo4j.annotation.QueryResult;

import java.util.List;

@QueryResult
@JsonFilter("SomeBeanFilter")
public class ShippingData {
    private String  sourceLocation;
    private String  destinationLocation;
    private String  sku;
    private String  productName;
    private List<String> transitRoot;
    private String  item;
    private Integer availableItem;
    private Integer nodeCount;
    private Double deliveryTime;
    private Double shippingCost;

    @Override
    public String toString() {
        return "ShippingData{" +
                "sourceLocation='" + sourceLocation + '\'' +
                ", destinationLocation='" + destinationLocation + '\'' +
                ", sku='" + sku + '\'' +
                ", productName='" + productName + '\'' +
                ", transitRoot=" + transitRoot +
                ", item='" + item + '\'' +
                ", availableItem=" + availableItem +
                ", nodeCount=" + nodeCount +
                ", deliveryTime=" + deliveryTime +
                ", shippingCost=" + shippingCost +
                '}';
    }

    public String getSourceLocation() {
        return sourceLocation;
    }

    public void setSourceLocation(String sourceLocation) {
        this.sourceLocation = sourceLocation;
    }

    public String getDestinationLocation() {
        return destinationLocation;
    }

    public void setDestinationLocation(String destinationLocation) {
        this.destinationLocation = destinationLocation;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public List<String> getTransitRoot() {
        return transitRoot;
    }

    public void setTransitRoot(List<String> transitRoot) {
        this.transitRoot = transitRoot;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Integer getAvailableItem() {
        return availableItem;
    }

    public void setAvailableItem(Integer availableItem) {
        this.availableItem = availableItem;
    }

    public Integer getNodeCount() {
        return nodeCount;
    }

    public void setNodeCount(Integer nodeCount) {
        this.nodeCount = nodeCount;
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
}
