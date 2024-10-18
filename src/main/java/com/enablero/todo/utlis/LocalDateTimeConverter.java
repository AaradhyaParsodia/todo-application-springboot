package com.enablero.todo.utlis;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeConverter implements DynamoDBTypeConverter<String, LocalDateTime> {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");

    @Override
    public String convert(LocalDateTime object) {
        return object.format(formatter);
    }

    @Override
    public LocalDateTime unconvert(String object) {
        try {
            return LocalDateTime.parse(object, formatter);
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse LocalDateTime from string: " + object, e);
        }
    }
}
