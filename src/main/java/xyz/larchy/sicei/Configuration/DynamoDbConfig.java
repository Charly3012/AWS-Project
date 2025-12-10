package xyz.larchy.sicei.Configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsSessionCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

@Configuration
public class DynamoDbConfig {
    @Value("${aws.region}")
    private String awsRegion;

    @Value("${aws.accessKeyId}")
    private String accessKeyId;

    @Value("${aws.secretKey}")
    private String secretAccessKey;

    @Value("${aws.sessionToken}")
    private String sessionToken;

    @Bean
    public DynamoDbClient dynamoDbClient() {
        AwsSessionCredentials awsCreds = AwsSessionCredentials.create(
                accessKeyId,
                secretAccessKey,
                sessionToken
        );

        StaticCredentialsProvider credentialsProvider = StaticCredentialsProvider.create(awsCreds);

        return DynamoDbClient.builder()
                .region(Region.of(awsRegion))
                .credentialsProvider(credentialsProvider)
                .build();
    }

    @Bean
    public DynamoDbEnhancedClient dynamoDbEnhancedClient() {
        return DynamoDbEnhancedClient.builder()
                .dynamoDbClient(dynamoDbClient())
                .build();
    }

}
