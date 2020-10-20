package com.java.neo4jdatarest.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.java.neo4jdatarest.model.NodeStore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class NodeDTO {

    @JsonIgnore
    private Long id;

    private Map<String,List<NodeStore>> sourceWareHouseList;
    private Map<String,Object> properties ;


}
