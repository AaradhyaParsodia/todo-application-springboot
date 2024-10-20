package com.enablero.todo.service;

import com.enablero.todo.dal.entity.Todo;
import com.enablero.todo.dal.repository.TodoRepository;
import com.enablero.todo.model.TodoInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

//    TODO Always code to Interface not to an actual class. Make impl package and have implementation in that

//    TODO Use Lombok for basic operations such as @Data, @RequiredArgsConstructor

//    TODO Never ever call repository from service. Have Data Providers.

    /*
            DAL -> Data Access Layer

            DAL will expose only Data Providers. Data Providers are contracts between Service and Repos.
     */
    private final TodoRepository todoRepository;

    @Autowired
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> getAllTodos(String email) {
        return todoRepository.getAllTodos(email);
    }

    public Todo createOrUpdateTodo(String email, TodoInput todo) {
        return todoRepository.createOrUpdateTodo(email , todo);
    }

    public String deleteTodo(String email, String todoId) {
        return todoRepository.deleteTodo(email, todoId);
    }
}
