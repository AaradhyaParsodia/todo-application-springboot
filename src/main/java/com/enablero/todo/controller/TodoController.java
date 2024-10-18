package com.enablero.todo.controller;

import com.enablero.todo.dal.entity.Todo;
import com.enablero.todo.dal.repository.TodoRepository;
import com.enablero.todo.model.TodoInput;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.List;

@Controller
public class TodoController {

    private final TodoRepository todoRepository;

    public TodoController(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @QueryMapping
    public List<Todo> getTodos(@Argument("email") String email) {
        return todoRepository.getAllTodos(email);
    }

    @MutationMapping
    public Todo createOrUpdateTodo(@Argument("input") TodoInput todo) {
        return todoRepository.createOrUpdateTodo(todo);
    }

    @MutationMapping
    public String deleteTodo(@Argument("id") String todoId) {
        return todoRepository.deleteTodo(todoId);
    }
}