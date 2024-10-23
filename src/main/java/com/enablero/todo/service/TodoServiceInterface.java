package com.enablero.todo.service;

import com.enablero.todo.dal.entity.Todo;
import com.enablero.todo.model.TodoInput;

import java.util.List;

public interface TodoServiceInterface {
    List<Todo> getAllTodos();
    Todo createOrUpdateTodo(TodoInput todoInput);
    String deleteTodo(String todoId);
}
