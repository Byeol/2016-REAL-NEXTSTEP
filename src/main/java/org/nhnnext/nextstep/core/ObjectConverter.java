package org.nhnnext.nextstep.core;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.AttributeConverter;
import java.io.IOException;

public class ObjectConverter implements AttributeConverter<Object, String> {

    private final static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Object attribute) {
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    @Override
    public Object convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return new Object();
        }

        try {
            return objectMapper.readValue(dbData, Object.class);
        } catch (IOException e) {
            return null;
        }
    }
}
