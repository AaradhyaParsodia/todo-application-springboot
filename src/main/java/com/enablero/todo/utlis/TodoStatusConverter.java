package com.enablero.todo.utlis;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.enablero.todo.model.TodoStatus;

public class TodoStatusConverter implements DynamoDBTypeConverter<String, TodoStatus> {

    @Override
    public String convert(TodoStatus object) {
        return object.name();
    }

    @Override
    public TodoStatus unconvert(String object) {
        return TodoStatus.valueOf(object);
    }
}
