package com.enablero.todo.service.impl;

import com.enablero.todo.dal.dataprovider.TodoDataProviderInterface;
import com.enablero.todo.dal.entity.Todo;
import com.enablero.todo.model.TodoInput;
import com.enablero.todo.service.TodoServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService implements TodoServiceInterface {

//    TODO Always code to Interface not to an actual class. Make impl package and have implementation in that

//    TODO Use Lombok for basic operations such as @Data, @RequiredArgsConstructor

//    TODO Never ever call repository from service. Have Data Providers.

    /*
            DAL -> Data Access Layer

            DAL will expose only Data Providers. Data Providers are contracts between Service and Repos.
     */
    private final TodoDataProviderInterface todoDataProviderInterface;

    @Autowired
    public TodoService(TodoDataProviderInterface todoDataProviderInterface) {
        this.todoDataProviderInterface = todoDataProviderInterface;
    }

    @Override
    public List<Todo> getAllTodos() {
        return todoDataProviderInterface.getTodos();
    }

    @Override
    public Todo createOrUpdateTodo(TodoInput todoInput) {
        return todoDataProviderInterface.save(todoInput);
    }

    @Override
    public String deleteTodo(String todoId) {
        return todoDataProviderInterface.archive(todoId);
    }
}
