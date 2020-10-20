package com.java.neo4jdatarest.dto;


import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class _3plDTO {

    private Map<String, Object> properties = new HashMap<>();
    private String sourceType;
    private String sourcePropertyName;
    private String sourcePropertyValue;
    private String targetType;
    private String targetPropertyName;
    private String targetPropertyValue;
}
