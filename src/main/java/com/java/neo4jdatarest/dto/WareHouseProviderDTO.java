package com.java.neo4jdatarest.dto;

public class WareHouseProviderDTO {

    private String name;
    private Double deliveryTime;
    private Double shippingCost;
    private String startlocation;
    private String endlocation;

    public WareHouseProviderDTO(String name, Double deliveryTime, Double shippingCost, String startlocation, String endlocation) {
        this.name = name;
        this.deliveryTime = deliveryTime;
        this.shippingCost = shippingCost;
        this.startlocation = startlocation;
        this.endlocation = endlocation;
    }

    @Override
    public String toString() {
        return "WareHouseProviderDTO{" +
                "name='" + name + '\'' +
                ", deliveryTime=" + deliveryTime +
                ", shippingCost=" + shippingCost +
                ", startlocation='" + startlocation + '\'' +
                ", endlocation='" + endlocation + '\'' +
                '}';
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

    public String getStartlocation() {
        return startlocation;
    }

    public void setStartlocation(String startlocation) {
        this.startlocation = startlocation;
    }

    public String getEndlocation() {
        return endlocation;
    }

    public void setEndlocation(String endlocation) {
        this.endlocation = endlocation;
    }
}
