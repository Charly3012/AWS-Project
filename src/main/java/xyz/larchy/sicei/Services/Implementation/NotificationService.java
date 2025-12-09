package xyz.larchy.sicei.Services.Implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import xyz.larchy.sicei.Services.INotificationService;

@Service
@RequiredArgsConstructor
public class NotificationService implements INotificationService {

    private final SnsClient snsClient;

    @Value("${sns.topic.arn}")
    private String topicArn;

    public void sendNotification(String subject, String body){
        PublishRequest request = PublishRequest.builder()
                .topicArn(topicArn)
                .message(body)
                .subject(subject)
                .build();

        snsClient.publish(request);
    }
}
