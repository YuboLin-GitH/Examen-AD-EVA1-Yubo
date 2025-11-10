package com.yubo.Model;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class SiNoBooleanConverter implements AttributeConverter<Boolean, String> {

    @Override
    public String convertToDatabaseColumn(Boolean attribute) {
        if (attribute == null) return null;
        return attribute ? "Si" : "No";
    }

    @Override
    public Boolean convertToEntityAttribute(String dbData) {
        if (dbData == null) return null;
        return dbData.equalsIgnoreCase("Si");
    }
}