package com.java.neo4jdatarest.controller;


import com.java.neo4jdatarest.dto.ItemDTO;
import com.java.neo4jdatarest.dto.WareHouseProviderDTO;
import com.java.neo4jdatarest.model.Provider;
import com.java.neo4jdatarest.model.SKU;
import com.java.neo4jdatarest.model.Unit;
import com.java.neo4jdatarest.model.WareHouse;
import com.java.neo4jdatarest.dto.WareHouseItemDTO;
import com.java.neo4jdatarest.repository.SkuRepository;
import com.java.neo4jdatarest.repository.WareHouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class WareHouseController {

    @Autowired
    WareHouseRepository wareHouseRepository;

    @Autowired
    SkuRepository skuRepository;


    @PostMapping("/warehouses")
    Iterable<WareHouse> createWareHouseList(@RequestBody List<WareHouse> warehouseList) {
        System.err.println(warehouseList);
        Iterable<WareHouse> wareHouseList =
                wareHouseRepository.saveAll(warehouseList);
        return wareHouseList;
    }

    @PutMapping("/warehouses/{id}")
    WareHouse updateWareHouse(@PathVariable Long id,@RequestBody WareHouse warehouse) {
        System.err.println(warehouse);
        Optional<WareHouse> warehouseOldOpt=wareHouseRepository.findById(id);
        WareHouse updatedWareHouse=warehouseOldOpt.get();
        updatedWareHouse.setName(warehouse.getName());
        updatedWareHouse.setLocation(warehouse.getLocation());
        updatedWareHouse.setHoldingCapacity(warehouse.getHoldingCapacity());
        updatedWareHouse=wareHouseRepository.save(updatedWareHouse);
        return updatedWareHouse;
    }

    @PostMapping("/addItemToWareHouse")
    WareHouse addItem(@RequestBody WareHouseItemDTO wareHouseItemDTO) {
        System.err.println(wareHouseItemDTO);
        SKU sku = skuRepository.findBySku(wareHouseItemDTO.getSku());
        WareHouse wareHouse = wareHouseRepository.findByLocation(wareHouseItemDTO.getLocation());
        Unit unit = new Unit();
        unit.setQuantity(wareHouseItemDTO.getQuantity());
        unit.setWareHouse(wareHouse);
        unit.setSku(sku);
        wareHouse.hasItem(unit);
        wareHouse = wareHouseRepository.save(wareHouse);

        System.err.println(wareHouse);
        return wareHouse;
    }

    @PostMapping("/createAndAddItemToWareHouse")
    WareHouse createAndAddItemToWareHouse(@RequestBody ItemDTO itemDTO) {
        System.err.println(itemDTO);
        List<Unit> unitList=new ArrayList<>();
        final WareHouse wareHouse = wareHouseRepository.findByLocation(itemDTO.getLocation());
        itemDTO.getSkuDTOList().forEach(sku->{
            SKU skuObj = skuRepository.findBySku(sku.getSku());
            if(skuObj==null){
                skuObj=skuRepository.save(new SKU(sku.getName(),sku.getSku()));
            }else{
                skuObj.setName(sku.getName());
                skuObj.setSku(sku.getSku());
            }
            Unit unit = new Unit();
            unit.setQuantity(sku.getQuantity());
            unit.setWareHouse(wareHouse);
            unit.setSku(skuObj);
            unitList.add(unit);
        });
        wareHouse.setUnits(unitList);
        WareHouse wareHouseSaved = wareHouseRepository.save(wareHouse);
        System.err.println(wareHouseSaved);
        return wareHouseSaved;
    }

    @PostMapping("/addProviderToWareHouse")
    WareHouse add3PL(@RequestBody WareHouseProviderDTO wareHouseProviderDTO) {
        System.err.println(wareHouseProviderDTO);
        WareHouse startingPoint = wareHouseRepository.findByLocation(wareHouseProviderDTO.getStartlocation());
        WareHouse endPoint = wareHouseRepository.findByLocation(wareHouseProviderDTO.getEndlocation());
        Provider provider = new Provider();
        provider.setWareHouseStart(startingPoint);
        provider.setWareHouseEnd(endPoint);
        provider.setDeliveryTime(wareHouseProviderDTO.getDeliveryTime());
        provider.setName(wareHouseProviderDTO.getName());
        provider.setShippingCost(wareHouseProviderDTO.getShippingCost());
        Set<Provider> provderSet=new HashSet<>();
        provderSet.add(provider);
        startingPoint.setProviders(provderSet);
        startingPoint = wareHouseRepository.save(startingPoint);
        System.err.println(startingPoint);
        return startingPoint;
    }

    @GetMapping("/warehouses")
    Iterable<WareHouse> getAllWareHouses() {

        return wareHouseRepository.findAll();
    }



    @DeleteMapping("/warehouses")
    ResponseEntity deleteAllWareHouses() {
        wareHouseRepository.deleteAll();
        return new ResponseEntity(
                "All are deleted ",
                HttpStatus.OK);
    }
}

