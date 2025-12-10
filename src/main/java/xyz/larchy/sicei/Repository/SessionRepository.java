package xyz.larchy.sicei.Repository;

import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryEnhancedRequest;
import xyz.larchy.sicei.Models.SessionDynamoEntity;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.UUID;

@Repository
public class SessionRepository {

    private final DynamoDbTable<SessionDynamoEntity> sessionTable;

    public SessionRepository(DynamoDbEnhancedClient enhancedClient) {
        this.sessionTable = enhancedClient.table(
                "sicei-sessions",
                TableSchema.fromBean(SessionDynamoEntity.class)
        );
    }

    public SessionDynamoEntity save(SessionDynamoEntity session) {
        if(session.getId() == null){
            session.setId(UUID.randomUUID().toString());
        }

        sessionTable.putItem(session);
        return session;
    }

    public Optional<SessionDynamoEntity> findBySessionString(String sessionString){
        Key key = Key.builder()
                .partitionValue(sessionString)
                .build();

        QueryConditional query = QueryConditional.keyEqualTo(key);

        QueryEnhancedRequest queryRequest = QueryEnhancedRequest.builder()
                .queryConditional(query)
                .limit(1)
                .build();

        var index = sessionTable.index("sessionString-index");

        return index.query(query)
                .stream()
                .flatMap(page -> page.items().stream())
                .findFirst();

    }




}
