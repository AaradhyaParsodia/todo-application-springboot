package com.enablero.todo.resolver.impl;

import com.enablero.todo.dal.entity.Todo;
import com.enablero.todo.model.TodoInput;
import com.enablero.todo.resolver.TodoResolverInterface;
import com.enablero.todo.service.impl.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class TodoResolverImpl implements TodoResolverInterface {

//  TODO-Completed Never ever use field injection. Always use Constructor Injection. Remove @Autowired


    private final TodoService todoService;

    @Autowired
    public TodoResolverImpl(TodoService todoService) {
        this.todoService = todoService;
    }

    @MutationMapping
    @Override
    public Todo createOrUpdateTodo(@Argument("todoInput") TodoInput todo) {
        return todoService.createOrUpdateTodo(todo);
    }

    @QueryMapping
    @Override
    public List<Todo> getTodos() {
        return todoService.getAllTodos();
    }

    @MutationMapping
    @Override
    public String deleteTodo(@Argument("id") String todoId) {
        return todoService.deleteTodo(todoId);
    }
}
