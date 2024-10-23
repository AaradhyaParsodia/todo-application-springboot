package com.enablero.todo.resolver;

import com.enablero.todo.dal.entity.Todo;
import com.enablero.todo.model.TodoInput;

import java.util.List;

public interface TodoResolverInterface {
    Todo createOrUpdateTodo(TodoInput todoInput);

    List<Todo> getTodos();

    String deleteTodo(String todoId);
}
