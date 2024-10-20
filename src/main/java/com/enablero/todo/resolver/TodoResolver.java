package com.enablero.todo.resolver;

import com.enablero.todo.dal.entity.Todo;
import com.enablero.todo.model.TodoInput;
import com.enablero.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TodoResolver {

//  TODO Never ever use field injection. Always use Constructor Injection. Remove @Autowired

    @Autowired
    private TodoService todoService;

    @MutationMapping
    public Todo createOrUpdateTodo(@Argument("todoInput") TodoInput todo) {
        return todoService.createOrUpdateTodo(todo);
    }

    @QueryMapping
    public List<Todo> getTodos() {
        return todoService.getAllTodos("aman@enablero.com");
    }

    @MutationMapping
    public String deleteTodo(@Argument("id") String todoId) {
        return todoService.deleteTodo("aman@enablero.com", todoId);
    }
}
