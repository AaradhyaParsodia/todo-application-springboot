package com.enablero.todo.controller;

import com.enablero.todo.dal.entity.Todo;
import com.enablero.todo.dal.repository.TodoRepository;
import com.enablero.todo.model.TodoInput;
import jakarta.servlet.http.HttpServletRequest;
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
    public List<Todo> getTodos(HttpServletRequest request) {
        String email = (String) request.getAttribute("userEmail");
        return todoRepository.getAllTodos(email);
    }

    @MutationMapping
    public Todo createOrUpdateTodo(HttpServletRequest request, @Argument("input") TodoInput todo) {
        String email = (String) request.getAttribute("userEmail");
        return todoRepository.createOrUpdateTodo(email, todo);
    }

    @MutationMapping
    public String deleteTodo(HttpServletRequest request, @Argument("id") String todoId) {
        String email = (String) request.getAttribute("userEmail");
        return todoRepository.deleteTodo(email, todoId);
    }
}