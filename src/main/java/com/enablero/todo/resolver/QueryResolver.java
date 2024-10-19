package com.enablero.todo.resolver;

import com.enablero.todo.dal.entity.Todo;
import com.enablero.todo.service.TodoService;
import com.enablero.todo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QueryResolver {

    @Autowired
    private TodoService todoService;

    @Autowired
    private UserService userService;

    @QueryMapping
    public List<Todo> getTodos() {
        return todoService.getAllTodos("aman@enablero.com");
    }

    @QueryMapping
    public List<String> getAllowListUsers() {
        return userService.getAllowListUsers();
    }
}
