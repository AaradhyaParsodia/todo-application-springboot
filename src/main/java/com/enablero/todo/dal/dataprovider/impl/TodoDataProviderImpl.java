package com.enablero.todo.dal.dataprovider.impl;

import com.enablero.todo.dal.dataprovider.TodoDataProviderInterface;
import com.enablero.todo.dal.entity.Todo;
import com.enablero.todo.dal.repository.TodoRepositoryInterface;
import com.enablero.todo.model.TodoInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TodoDataProviderImpl implements TodoDataProviderInterface {

    private final TodoRepositoryInterface todoRepositoryInterface;

    @Autowired
    public TodoDataProviderImpl(TodoRepositoryInterface todoRepositoryInterface) {
        this.todoRepositoryInterface = todoRepositoryInterface;
    }

    @Override
    public List<Todo> getTodos() {
        return todoRepositoryInterface.getAllTodos();
    }

    @Override
    public Todo save(TodoInput todoInput) {
        return todoRepositoryInterface.createOrUpdateTodo(todoInput);
    }

    @Override
    public String archive(String todoId) {
        return todoRepositoryInterface.deleteTodo(todoId);
    }
}
