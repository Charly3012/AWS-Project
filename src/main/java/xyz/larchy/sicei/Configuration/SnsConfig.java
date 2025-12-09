package xyz.larchy.sicei.Configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsSessionCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsClient;

@Configuration
public class SnsConfig {
    @Value("${aws.region}")
    private String awsRegion;

    @Value("${aws.accessKeyId}")
    private String accessKeyId;

    @Value("${aws.secretKey}")
    private String secretAccessKey;

    @Value("${aws.sessionToken}")
    private String sessionToken;

    @Bean
    public SnsClient snsClient() {
        AwsSessionCredentials awsCreds = AwsSessionCredentials.create(
                accessKeyId,
                secretAccessKey,
                sessionToken
        );

        StaticCredentialsProvider credentialsProvider = StaticCredentialsProvider.create(awsCreds);
        return SnsClient.builder()
                .region(Region.of(awsRegion))
                .credentialsProvider(credentialsProvider)
                .build();

    }
}

