package com.techlab.app.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techlab.app.dto.ItemOrderDTO;

import jakarta.persistence.*;

@Converter
public class ItemsOrderConverter implements AttributeConverter<List<ItemOrderDTO>, String> {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(List<ItemOrderDTO> items) {
        try {
            return mapper.writeValueAsString(items);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error serializando itemsOrder");
        }
    }

    @Override
    public List<ItemOrderDTO> convertToEntityAttribute(String dbData) {
        try {
            return Arrays.asList(mapper.readValue(dbData, ItemOrderDTO[].class));
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }
    
}