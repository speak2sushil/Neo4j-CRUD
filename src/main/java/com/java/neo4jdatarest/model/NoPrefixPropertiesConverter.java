package com.java.neo4jdatarest.model;

import org.neo4j.ogm.typeconversion.CompositeAttributeConverter;

import java.util.Map;
import java.util.stream.Collectors;

public class NoPrefixPropertiesConverter implements CompositeAttributeConverter<Map<String, Object>> {


    @Override
    public Map<String, ?> toGraphProperties(Map<String, Object> value) {

        return value.entrySet()
                .stream()
                .collect(Collectors.toMap(e->removeStart(e.getKey(),"properties."), Map.Entry::getValue));
    }

    @Override
    public Map<String, Object> toEntityAttribute(Map<String, ?> value) {

        return value.entrySet()
                .stream()
                .collect(Collectors.toMap(e->removeStart(e.getKey(),"properties."), Map.Entry::getValue));
    }

    public static String removeStart(final String str, final String remove) {

        if (str.startsWith(remove)){
            return str.substring(remove.length());
        }
        return str;
    }

}
