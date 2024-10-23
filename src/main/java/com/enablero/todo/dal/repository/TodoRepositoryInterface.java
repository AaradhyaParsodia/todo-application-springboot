package com.enablero.todo.dal.repository;

import com.enablero.todo.dal.entity.Todo;
import com.enablero.todo.model.TodoInput;

import java.util.List;

public interface TodoRepositoryInterface {
    List<Todo> getAllTodos();
    Todo createOrUpdateTodo(TodoInput todoInput);
    String deleteTodo(String todoId);
}
