package com.java.neo4jdatarest.controller;


import com.java.neo4jdatarest.model.SKU;
import com.java.neo4jdatarest.repository.SkuRepository;
import com.java.neo4jdatarest.repository.WareHouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SKUController {



    @Autowired
    SkuRepository skuRepository;

    @Autowired
    WareHouseRepository wareHouseRepository;



    @PostMapping("/skus")
    Iterable<SKU> createSKUList(@RequestBody List<SKU> skuList ) {
        System.err.println(skuList);
        Iterable<SKU> skuLItr=
                skuRepository.saveAll(skuList);
        return skuLItr;
    }

    @GetMapping("/skus")
    Iterable<SKU> getAllSKUs() {

        return skuRepository.findAll();
    }

    @DeleteMapping("/skus")
    ResponseEntity deleteAllSKUs() {
        skuRepository.deleteAll();
        return new ResponseEntity(
                "All are deleted ",
                HttpStatus.OK);
    }
}

