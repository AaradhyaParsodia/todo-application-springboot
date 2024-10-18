package com.enablero.todo.dal.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.Condition;
import com.enablero.todo.dal.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class UserRepository {

    private final Logger logger = LoggerFactory.getLogger(UserRepository.class);
    private DynamoDBMapper dynamoDBMapper;


    @Autowired
    public UserRepository(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    public List<String> getAllowListUsers(){
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();

        List<User> users = dynamoDBMapper.scan(User.class, scanExpression);

        if (users == null || users.isEmpty()) {
            logger.info("No users found in the database.");
            return List.of();
        }

        List<String> emails = users.stream()
                .map(User::getEmail)
                .filter(email -> email != null)
                .collect(Collectors.toList());
        return emails;
    }

    public User getUserByEmail(String email) {
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        scanExpression.addFilterCondition("email", new Condition()
                .withComparisonOperator(ComparisonOperator.EQ)
                .withAttributeValueList(new AttributeValue(email)));

        List<User> results = dynamoDBMapper.scan(User.class, scanExpression);

        if (results == null || results.isEmpty()) {
            logger.info("No user found with email: {}", email);
            return null;
        }

        return results.get(0);
    }
}
