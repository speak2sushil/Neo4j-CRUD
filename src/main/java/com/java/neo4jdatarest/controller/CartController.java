package com.java.neo4jdatarest.controller;


import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.java.neo4jdatarest.dto.Cart;
import com.java.neo4jdatarest.model.RouteDetail;
import com.java.neo4jdatarest.model.ShippingData;
import com.java.neo4jdatarest.repository.SkuRepository;
import com.java.neo4jdatarest.repository.WareHouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
public class CartController {


    @Autowired
    WareHouseRepository wareHouseRepository;

    @Autowired
    SkuRepository skuRepository;

    @PostMapping("/cart")
    MappingJacksonValue findShippingLocation(@RequestBody Cart cart) {
        List<ShippingData> shippingList = new ArrayList<>();
        SimpleBeanPropertyFilter filter=null;

        if(!"Cheapest".equalsIgnoreCase(cart.getPreference())){
            filter=SimpleBeanPropertyFilter.filterOutAllExcept("sourceLocation","destinationLocation","productName","availableItem","transitRoot","deliveryTime","sku");
            cart.getItems().forEach(item -> {
                ShippingData shippingPoint = wareHouseRepository.getFastestData(cart.getDestination(), item.getSku(),item.getQuantity());
                int totalAvailableItem =wareHouseRepository.totalAvailableSKU(item.getSku());
                if (shippingPoint==null){
                    System.err.println("Enough "+item+" are not available or 3pl root doesn't exist between source and destination");
                }else if( item.getQuantity() <= totalAvailableItem ) {
                    shippingList.add(shippingPoint);
                }else{
                    System.err.println("Enough "+item+" are not available or 3pl root doesn't exist between source and destination");
                }
            });
        }else{
            filter=SimpleBeanPropertyFilter.filterOutAllExcept("sourceLocation","destinationLocation","productName","availableItem","transitRoot","shippingCost","sku");
            cart.getItems().forEach(item -> {
                ShippingData shippingPoint = wareHouseRepository.getCheapestData(cart.getDestination(), item.getSku(),item.getQuantity());
                int totalAvailableItem =wareHouseRepository.totalAvailableSKU(item.getSku());
                if (shippingPoint==null){
                    System.err.println("Enough "+item+" are not available or 3pl root doesn't exist between source and destination");
                }else if( item.getQuantity() <= totalAvailableItem ) {
                    shippingList.add(shippingPoint);
                }else{
                    System.err.println("Enough "+item+" are not available or 3pl root doesn't exist between source and destination");
                }
            });
        }
        FilterProvider filters=new SimpleFilterProvider().addFilter("SomeBeanFilter",filter);
        MappingJacksonValue mapping = new MappingJacksonValue(shippingList);
        mapping.setFilters(filters);
        return mapping;
    }

    @PostMapping("/cart/v1")
    Map<String,List<RouteDetail>> findRouteDetail(@RequestBody Cart cart) {
        List<RouteDetail> routeResult = new ArrayList<>();
        cart.getItems().forEach(item -> {
            List<RouteDetail>  routeList= wareHouseRepository.getRouteDetail(cart.getDestination(), item.getSku(),item.getQuantity());
            if (routeList==null || routeList.size()==0){
                System.err.println("Enough "+item+" are not available or 3pl root doesn't exist between source and destination");
            }else {
                routeResult.addAll(routeList);
            }
        });
        Map<String,List<RouteDetail>> resultMap=routeResult.stream().collect(Collectors.groupingBy(RouteDetail::getProduct));
        return resultMap;
    }
}
