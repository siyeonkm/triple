package com.interpark.triple.global.util;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Converter
public class StringArrayConverter implements AttributeConverter<List<String>, String> {
    private static final String SPLIT_CHAR = ",";

    @Override
    public String convertToDatabaseColumn(List<String> attribute) {
        if(attribute == null) return null;
        return attribute.stream().map(String::valueOf).collect(Collectors.joining(SPLIT_CHAR));
    }

    @Override
    public List<String> convertToEntityAttribute(String dbData) {
        List<String> strings = new ArrayList<>();
        if(dbData == null) return strings;
        return Arrays.stream(dbData.split(SPLIT_CHAR))
                .map(String::valueOf)
                .collect(Collectors.toList());
    }
}
