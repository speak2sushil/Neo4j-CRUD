package com.java.neo4jdatarest.model;


import lombok.Data;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.typeconversion.Convert;

import java.util.HashMap;
import java.util.Map;

@NodeEntity(label= "WareHouse")
@Data
public class SourceWareHouse  extends NodeStore{



}
