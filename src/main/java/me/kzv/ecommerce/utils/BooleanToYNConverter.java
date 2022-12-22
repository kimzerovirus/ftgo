package me.kzv.ecommerce.utils;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class BooleanToYNConverter implements AttributeConverter<Boolean, String> {

    @Override
    public String convertToDatabaseColumn(Boolean attribute) {
        // true -> "Y", false -> "N"
        return (attribute != null && attribute) ? "Y" : "N";
    }

    @Override
    public Boolean convertToEntityAttribute(String dbData) {
        /**
         * equalsIgnoreCase : 대소문자 구분안함.
         * equals : 대소문자 구분함.
         * 문자열이 같은경우 true 리턴
         * 문자열이 다른경우 false 리턴
         */
        return "Y".equalsIgnoreCase(dbData);
    }
}
