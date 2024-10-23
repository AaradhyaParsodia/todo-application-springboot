package com.enablero.todo.dal.dataprovider;

import com.enablero.todo.dal.entity.Todo;
import com.enablero.todo.model.TodoInput;

import java.util.List;

public interface TodoDataProviderInterface {
    List<Todo> getTodos();
    Todo save(TodoInput todoInput);
    String archive(String todoId);
}
