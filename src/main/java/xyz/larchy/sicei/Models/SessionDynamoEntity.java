package xyz.larchy.sicei.Models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSecondaryPartitionKey;

import java.time.Instant;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamoDbBean
@Builder
public class SessionDynamoEntity {
    private String id;
    private Instant fecha;
    private int alumnoId;
    private boolean active;
    private String sessionString;


    @DynamoDbPartitionKey
    public String getId() {
        return id;
    }
    @DynamoDbSecondaryPartitionKey(indexNames = "sessionString-index")
    public String getSessionString() {
        return sessionString;
    }

}
