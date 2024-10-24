 package com.enablero.todo.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DynamoDBConfig {

//    TODO Read about ConfigurationProperties and club below variables in 1 model

    private final DynamoDBConfigProperties dynamoDBConfigProperties;

    @Autowired
    public DynamoDBConfig(DynamoDBConfigProperties dynamoDBConfigProperties) {
        this.dynamoDBConfigProperties = dynamoDBConfigProperties;
    }

    @Bean
    public DynamoDBMapper dynamoDBMapper() {
        return new DynamoDBMapper(buildDB());
    }

    private AmazonDynamoDB buildDB() {
        return AmazonDynamoDBClientBuilder
                .standard()
                .withEndpointConfiguration(
                        new AwsClientBuilder.EndpointConfiguration(dynamoDBConfigProperties.getUrl(),dynamoDBConfigProperties.getRegion())
                )
                .withCredentials(new AWSStaticCredentialsProvider(
                        new BasicAWSCredentials(dynamoDBConfigProperties.getAccessKey(),dynamoDBConfigProperties.getSecretKey())
                        )
                )
                .build();
    }

}
