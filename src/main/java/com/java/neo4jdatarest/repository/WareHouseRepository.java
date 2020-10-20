package com.java.neo4jdatarest.repository;

import com.java.neo4jdatarest.model.RouteDetail;
import com.java.neo4jdatarest.model.ShippingData;
import com.java.neo4jdatarest.model.WareHouse;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface WareHouseRepository extends CrudRepository<WareHouse,Long> {

    WareHouse findByName(String name);
    WareHouse findByLocation(String location);

   /* @Query("MATCH (end:WareHouse {location: $0}), (start:WareHouse)-[h:HAS_ITEM]->(inv:SKU) \n" +
            "            where inv.sku =$1 and h.quantity>0 and start <>end \n" +
            "            CALL gds.alpha.shortestPath.write({\n" +
            "           nodeQuery:'MATCH(n:WareHouse) WHERE n.holdingCapacity >0 RETURN id(n) AS id',\n" +
            "           relationshipQuery:'MATCH (n:WareHouse)-[r:Provider]->(m:WareHouse) where n.holdingCapacity>0 and n<> m RETURN id(n) AS source, id(m) AS target, r.deliveryTime AS weight',\n" +
            "           validateRelationships : false,\n" +
            "            startNode: start,\n" +
            "            endNode: end,\n" +
            "            relationshipWeightProperty: 'weight'\n" +
            "            })\n" +
            "            YIELD nodeCount, totalCost\n" +
            "RETURN start.name as sourceLocation,inv.name as item ,h.quantity as availableItem ,end.name as destinationLocation,nodeCount,totalCost as deliveryTime\n" +
            "order by deliveryTime , nodeCount limit 1\n")*/
    @Query("MATCH (start:WareHouse)-[h:HAS_ITEM]->(inv:SKU), (end:WareHouse {location: $0})\n" +
            "CALL apoc.algo.dijkstra(start, end, 'Provider', 'deliveryTime') YIELD path, weight\n" +
            "with path, weight, start, end , inv,h\n" +
            "where inv.sku =$1 and h.quantity>=$2 and start <>end and ALL(x in nodes(path) WHERE x.holdingCapacity > 0) \n" +
            "\n" +
            "RETURN start.name as sourceLocation , end.name as destinationLocation , weight as deliveryTime,reduce(output = [], n IN nodes(path) | output + n.location ) as transitRoot,inv.sku as sku,inv.name as productName,h.quantity as availableItem \n" +
            "ORDER BY deliveryTime ASC limit 1 ")
    ShippingData getFastestData(String destination, String sku, Integer quantity);




    /*@Query("MATCH (end:WareHouse {location: $0}), (start:WareHouse)-[h:HAS_ITEM]->(inv:SKU) \n" +
            "            where inv.sku =$1 and h.quantity>0 and start <>end \n" +
            "            CALL gds.alpha.shortestPath.write({\n" +
            "           nodeQuery:'MATCH(n:WareHouse) WHERE n.holdingCapacity >0 RETURN id(n) AS id',\n" +
            "           relationshipQuery:'MATCH (n:WareHouse)-[r:Provider]->(m:WareHouse) where n.holdingCapacity>0 and n<> m RETURN id(n) AS source, id(m) AS target, r.shippingCost AS weight',\n" +
            "           validateRelationships : false,\n" +
            "            startNode: start,\n" +
            "            endNode: end,\n" +
            "            relationshipWeightProperty: 'weight'\n" +
            "            })\n" +
            "            YIELD nodeCount, totalCost\n" +
            "RETURN start.name as sourceLocation,inv.name as item ,h.quantity as availableItem ,end.name as destinationLocation,nodeCount,totalCost as shippingCost\n" +
            "order by shippingCost , nodeCount limit 1\n")*/
    @Query("MATCH (start:WareHouse)-[h:HAS_ITEM]->(inv:SKU), (end:WareHouse {location: $0})\n" +
            "CALL apoc.algo.dijkstra(start, end, 'Provider', 'shippingCost') YIELD path, weight\n" +
            "with path, weight, start, end , inv,h\n" +
            "where inv.sku =$1 and h.quantity>=$2  and start <>end and ALL(x in nodes(path) WHERE x.holdingCapacity > 0) \n" +
            "\n" +
            "RETURN start.name as sourceLocation , end.name as destinationLocation , weight as shippingCost,reduce(output = [], n IN nodes(path) | output + n.location ) as transitRoot,inv.sku as sku,inv.name as productName,h.quantity as availableItem \n" +
            "ORDER BY shippingCost ASC limit 1 ")
    ShippingData getCheapestData(String destination, String sku, Integer quantity);

    @Query("MATCH (start:WareHouse)-[h:HAS_ITEM]->(inv:SKU)\n" +
            "            where inv.sku =$0 and h.quantity>0       \n" +
            "RETURN sum(h.quantity) as totalavailableItem")
   Integer totalAvailableSKU(String sku);

    @Query("MATCH (start:WareHouse)-[h:HAS_ITEM]->(inv:SKU), (end:WareHouse {location:$0 })\n" +
            "CALL apoc.algo.dijkstra(start, end, 'Provider', 'deliveryTime') YIELD path, weight\n" +
            "where inv.sku =$1 and h.quantity>=$2  and start <>end and ALL(x in nodes(path) WHERE x.holdingCapacity > 0) \n" +
            "with  distinct head(nodes(path)) as firstNode,path,weight AS dist, last(nodes(path)) as lastNode , reduce(output = [], n IN nodes(path) | output + n.location ) as transitRoute,size(nodes(path))  as transitRouteCount,inv.sku as sku ,inv.name as product,h.quantity as availableQuantity order by dist,transitRouteCount\n" +
            "MATCH (s:ServiceProvider)<-[hs:HasService]-(start:WareHouse{location: firstNode.location})\n" +
            "where  hs.date>=date() \n" +
            "and hs.date<=(date() + duration(\"P\"+toInteger(7)+\"D\")) \n" +
            "with distinct hs.date as sDate, hs.serviceDelay as delay,firstNode.name as sourceWareHouse, dist, (dist+hs.serviceDelay) as totalCost, lastNode.name as destination, transitRoute ,product, sku,availableQuantity\n" +
            "return toString(date(sDate) + duration(\"P\"+toInteger(totalCost)+\"D\"))  as deliveryDate, toString(date(sDate))  as shippingDate, sourceWareHouse ,destination,transitRoute ,product, sku,toInteger(availableQuantity) as quantity\n" +
            "order by deliveryDate , shippingDate asc")
    List<RouteDetail> getRouteDetail(String destination, String sku, Integer quantity);


}

