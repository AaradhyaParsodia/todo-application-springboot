 package com.enablero.todo.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DynamoDBConfig {

//    TODO Read about ConfigurationProperties and club below variables in 1 model

    @Value("${aws.dynamodb.url}")
    private String dynamoDBUrl;

    @Value("${aws.region}")
    private String awsRegion;

    @Value("${aws.dynamodb.accessKey}")
    private String dynamoDBAccessKey;

    @Value("${aws.dynamodb.secretKey}")
    private String dynamoDBSecretKey;

    @Bean
    public DynamoDBMapper dynamoDBMapper() {
        return new DynamoDBMapper(buildDB());
    }

    private AmazonDynamoDB buildDB() {
        return AmazonDynamoDBClientBuilder
                .standard()
                .withEndpointConfiguration(
                        new AwsClientBuilder.EndpointConfiguration(dynamoDBUrl,awsRegion)
                )
                .withCredentials(new AWSStaticCredentialsProvider(
                        new BasicAWSCredentials(dynamoDBAccessKey,dynamoDBSecretKey)
                        )
                )
                .build();
    }

}
