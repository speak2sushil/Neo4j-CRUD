package com.java.neo4jdatarest.dto;

import java.util.List;

public class ItemDTO {

    private String location;
    private List<SkuDTO>  skuDTOList;



    @Override
    public String toString() {
        return "ItemDTO{" +
                "location='" + location + '\'' +
                ", skuDTOList=" + skuDTOList +
                '}';
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<SkuDTO> getSkuDTOList() {
        return skuDTOList;
    }

    public void setSkuDTOList(List<SkuDTO> skuDTOList) {
        this.skuDTOList = skuDTOList;
    }
}

