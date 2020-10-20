package com.java.neo4jdatarest.controller;


import com.java.neo4jdatarest.dto.NodeDTO;
import com.java.neo4jdatarest.dto._3plDTO;
import com.java.neo4jdatarest.model.*;
import com.java.neo4jdatarest.repository.NodeRepository;
import com.java.neo4jdatarest.repository._3plRepository;
import org.neo4j.ogm.model.Result;
import org.neo4j.ogm.response.model.RelationshipModel;
import org.neo4j.ogm.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class NodeController {

    @Autowired
    NodeRepository nodeRepository;

    @Autowired
    Session session;


    @Autowired
    _3plRepository _3plRepository;

    @PostMapping("/api/node")
    public ResponseEntity<List<Node>> createNode(@RequestBody List<NodeDTO> requestWrapperList) {
        //Create Database Entity from DTO
        List<Node> nodeList = new ArrayList<>();

        requestWrapperList.forEach(requestWrapper -> {
            Node node = new Node();
            Set<NodeStore> nodeSet = new HashSet<>();
            requestWrapper.getSourceWareHouseList().forEach((key, value) -> {
                switch (key) {
                    case "WareHouse":
                        value.forEach(subNode -> {
                            SourceWareHouse wareHouse = new SourceWareHouse();
                            wareHouse.setProperties(subNode.getProperties());
                            nodeSet.add(wareHouse);

                        });
                        break;
                    case "Transit":
                        value.forEach(subNode -> {
                            TransitNode transit = new TransitNode();
                            transit.setProperties(subNode.getProperties());
                            nodeSet.add(transit);

                        });
                        break;
                    case "Retail":
                        value.forEach(subNode -> {
                            Retail retail = new Retail();
                            retail.setProperties(subNode.getProperties());
                            nodeSet.add(retail);

                        });
                        break;
                    default:
                        System.out.println("Unknown SubNode Type ::" + key);
                        break;
                }
            });

            node.setNodeCapabilities(nodeSet);
            requestWrapper.getProperties().forEach((k, v) -> node.getProperties().put(k, v));
            try {
                nodeRepository.save(node);
                nodeList.add(node);
            } catch (Exception e) {
                e.printStackTrace();
            }

        });

        return new ResponseEntity<List<Node>>(nodeList, HttpStatus.CREATED);
    }

    @GetMapping("/api/node")
    public ResponseEntity<Iterable<Node>> getAllNodes() {

        return new ResponseEntity<Iterable<Node>>(nodeRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/api/node/{id}")
    public ResponseEntity<List<Node>> getNode(@PathVariable Long id) {
        List<Node> nodeList=new ArrayList<>();
       Result result= nodeRepository.findNodeAndRelationshipById(id);
        result.forEach(val->{
           Node currentNode= (Node)val.get("n");
            if(!nodeList.contains(currentNode)){
                Set<NodeStore> newList=new HashSet<>();
                newList.add((NodeStore)val.get("n1"));
                Set<_3PL> new3pl=new HashSet<>();
                _3PL provider=new _3PL();
                RelationshipModel model = (RelationshipModel) val.get("r1");
                Map<String,Object> propMap=new HashMap<>();
                model.getPropertyList().forEach(prop->{
                    propMap.put(prop.getKey(),prop.getValue());
                });
                provider.setId(model.getId());
                provider.setSource(nodeRepository.findById(model.getStartNode()).get());
                provider.setTarget(nodeRepository.findById(model.getEndNode()).get());
                provider.setProperties(propMap);
                new3pl.add(provider);
                currentNode.set_3pl(new3pl);
                currentNode.setNodeCapabilities(newList);

                nodeList.add(currentNode);
            }else{
                Set<NodeStore> existingList=currentNode.getNodeCapabilities();
                //Set<_3PL> existing3pl=currentNode.get_3pl();
                existingList.add((NodeStore)val.get("n1"));
                //existing3pl.add((_3PL) val.get("r1"));
                currentNode.setNodeCapabilities(existingList);
                Set<_3PL> new3pl=currentNode.get_3pl();
                _3PL provider=new _3PL();
                RelationshipModel model = (RelationshipModel) val.get("r1");
                Map<String,Object> propMap=new HashMap<>();
                model.getPropertyList().forEach(prop->{
                    propMap.put(prop.getKey(),prop.getValue());
                });
                provider.setId(model.getId());
                provider.setSource(nodeRepository.findById(model.getStartNode()).get());
                provider.setTarget(nodeRepository.findById(model.getEndNode()).get());
                provider.setProperties(propMap);
                new3pl.add(provider);
                currentNode.set_3pl(new3pl);
            }


        });
        return new ResponseEntity<List<Node>>(nodeList, HttpStatus.OK);
    }

    @DeleteMapping("/api/node")
    public ResponseEntity<Void> deleteAllNodes() {
        // nodeRepository.deleteAll();
        nodeRepository.deleteAllNode();
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/api/node/{id}")
    public ResponseEntity<Void> deleteNode(@PathVariable Long id) {
        // nodeRepository.deleteAll();
        nodeRepository.deleteNode(id);
        return ResponseEntity.noContent().build();
    }


    @PostMapping("/api/node/3pl")
    public ResponseEntity<_3PL> createRelationship(@RequestBody _3plDTO _3plDto) {
        //Create Database Entity from DTO
        System.err.println(_3plDto);
        Node sourceNode = nodeRepository.findNodeByProperty(_3plDto.getSourcePropertyName(), _3plDto.getSourcePropertyValue());
        Node destinationNode = nodeRepository.findNodeByProperty(_3plDto.getTargetPropertyName(), _3plDto.getTargetPropertyValue());
        _3PL provider = new _3PL();
        provider.setSource(sourceNode);
        provider.setTarget(destinationNode);
        provider.setProperties(_3plDto.getProperties());
        _3plRepository.save(provider);
        return new ResponseEntity<_3PL>(provider, HttpStatus.CREATED);
    }

    @GetMapping("/api/node/3pl/{sourceId}/{destinationId}")
    public ResponseEntity<Result> get3PLBetweemSourceAndDestination(@PathVariable Long sourceId,@PathVariable Long destinationId) {
        //Create Database Entity from DTO
        List<_3PL> providerList=new ArrayList<>();
        Result result=_3plRepository.findRelationBetweenNodes(sourceId,destinationId);
//        result.queryResults().forEach(action->{
//            System.out.println(action.get("r"));
//            providerList.add((_3PL)action.get("r"));
//        });
        return new ResponseEntity<Result>(result, HttpStatus.OK);
    }
}
