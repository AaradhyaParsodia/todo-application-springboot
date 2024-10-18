package com.enablero.todo.dal.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.enablero.todo.dal.entity.Todo;
import com.enablero.todo.model.TodoInput;
import com.enablero.todo.model.TodoStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class TodoRepository {

    private final DynamoDBMapper dynamoDBMapper;

    @Autowired
    public TodoRepository(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    public List<Todo> getAllTodos(String email) {
        Map<String, AttributeValue> map = new HashMap<>();
        map.put(":email", new AttributeValue(email));

        DynamoDBQueryExpression<Todo> queryExpression = new DynamoDBQueryExpression<Todo>()
                .withIndexName("email")
                .withKeyConditionExpression("email = :email")
                .withExpressionAttributeValues(map)
                .withConsistentRead(false);

        return dynamoDBMapper.query(Todo.class, queryExpression);
    }

    public Todo createOrUpdateTodo(String email, TodoInput todoInput) {
        if (todoInput == null) {
            throw new RuntimeException("TodoInput object cannot be null");
        }

        Todo todo;

        if (todoInput.getId() != null) {
            todo = dynamoDBMapper.load(Todo.class, todoInput.getId());

            if (todo == null || !todo.getEmail().equals(email)) {
                throw new RuntimeException("Todo not found or unauthorized access");
            }
        } else {
            todo = new Todo();

            todo.setCreatedDt();
            todo.setStatus(TodoStatus.PENDING);
        }

        if (todoInput.getEmail() != null) {
            todo.setEmail(todoInput.getEmail());
        }

        if (todoInput.getTitle() != null) {
            todo.setTitle(todoInput.getTitle());
        }

        if (todoInput.getDescription() != null) {
            todo.setDescription(todoInput.getDescription());
        }

        if (todoInput.getStatus() != null) {
            if(todoInput.getStatus().toString().equalsIgnoreCase(TodoStatus.PENDING.toString())){
                todo.setStatus(TodoStatus.PENDING);
            }
            else if(todoInput.getStatus().toString().equalsIgnoreCase(TodoStatus.COMPLETED.toString())){
                todo.setStatus(TodoStatus.COMPLETED);
            }
        }

        todo.setUpdateDt();

        dynamoDBMapper.save(todo);

        return todo;
    }

    public String deleteTodo(String email, String todoId) {
//        String email = "email@email.com";
//        Map<String, AttributeValue> map = new HashMap<>();
//        map.put(":email", new AttributeValue(email));
//
//        DynamoDBQueryExpression<Todo> queryExpression = new DynamoDBQueryExpression<Todo>()
//                .withIndexName("email")
//                .withKeyConditionExpression("email = :email")
//                .withExpressionAttributeValues(map)
//                .withConsistentRead(false);

//        List<Todo> todos = dynamoDBMapper.query(Todo.class, queryExpression);
//
//        // Find the specific todo item by id among the results
//        Todo existingTodo = todos.stream()
//                .filter(todo -> todo.getId().equals(todoId))
//                .findFirst()
//                .orElse(null);

        System.out.println("HASHDDSAJDHSAD SA");

        Todo existingTodo = dynamoDBMapper.load(Todo.class, todoId);

        System.out.println("\nSOMETHING"+existingTodo.toString());
        if (existingTodo != null && existingTodo.getEmail().equals(email)) {
            existingTodo.setStatus(TodoStatus.ARCHIVED);

            System.out.println("SOMETHING"+existingTodo.toString());

            dynamoDBMapper.save(existingTodo);

            return "Todo marked as deleted successfully";
        } else {
            throw new RuntimeException("Todo not found or unauthorized access");
        }
    }
}
