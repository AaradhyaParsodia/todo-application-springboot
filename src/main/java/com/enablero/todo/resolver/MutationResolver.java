package com.enablero.todo.resolver;

import com.enablero.todo.dal.entity.Todo;
import com.enablero.todo.model.TodoInput;
import com.enablero.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Component;

@Component
public class MutationResolver {

    @Autowired
    private TodoService todoService;

    @MutationMapping
    public Todo createOrUpdateTodo(@Argument("input") TodoInput todo) {
        return todoService.createOrUpdateTodo("aman@enablero.com", todo);
    }

    @MutationMapping
    public String deleteTodo(@Argument("id") String todoId) {
        return todoService.deleteTodo("aman@enablero.com", todoId);
    }
}
