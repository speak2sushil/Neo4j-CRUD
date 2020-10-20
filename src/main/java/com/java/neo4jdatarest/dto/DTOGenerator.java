package com.java.neo4jdatarest.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.neo4jdatarest.model.NodeStore;
import com.java.neo4jdatarest.model.SourceWareHouse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DTOGenerator {

    public static void main(String[] args) {
        List<NodeDTO> nodeList=new ArrayList<>();
        NodeDTO dto=new NodeDTO();
        //dto.setLabelsList(List.of("WareHouse","Retail"));
        Map<String,Object> map=new HashMap<>();
        map.put("city","Chennai");
        map.put("country","India");
        map.put("name","Apple Store");
        map.put("holdingCapacity",10);
        map.put("zone","south");
        dto.setProperties(map);

        Map<String,List<NodeStore>> sourceWareHouseList=new HashMap<>();
        List<NodeStore> sourceList=new ArrayList<>();
        Map<String,Object> nodeMap=new HashMap<>();
        map.put("name","Apple warehouse");
        SourceWareHouse wareHouse=new SourceWareHouse();
        wareHouse.setProperties(nodeMap);
        sourceList.add(wareHouse);
        sourceWareHouseList.put("WareHouse",sourceList);
        dto.setSourceWareHouseList(sourceWareHouseList);
        nodeList.add(dto);
        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(nodeList);
            System.out.println("ResultingJSONstring = " + json);
            //System.out.println(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();

        }
    }
}
