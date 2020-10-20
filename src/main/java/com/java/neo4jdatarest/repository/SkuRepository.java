package com.java.neo4jdatarest.repository;

import com.java.neo4jdatarest.model.SKU;

import org.springframework.data.repository.CrudRepository;

public interface SkuRepository extends CrudRepository<SKU,Long> {

    SKU findBySku(String sku);
}
