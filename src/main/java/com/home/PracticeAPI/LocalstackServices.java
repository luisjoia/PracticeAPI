package com.home.PracticeAPI;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.model.*;
import com.amazonaws.services.dynamodbv2.xspec.S;
import org.testcontainers.containers.localstack.LocalStackContainer;
import org.testcontainers.utility.DockerImageName;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.regions.Regions;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class LocalstackServices {

    public static final String SHOPPING_LIST = "ShoppingList";
    public static final String CREATION_DATE = "CreationDate";
    public static final String ID = "Id";

    protected void startUp(){
        LocalStackContainer container = new LocalStackContainer(DockerImageName.parse("localstack/localstack:0.11.3"))
                .withServices(LocalStackContainer.Service.DYNAMODB);
        container.start();
        setUpDynamo();
    }

    private void setUpDynamo() {
       final AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
                .withRegion(Regions.EU_CENTRAL_1)
                .build();;
        client.createTable(new CreateTableRequest(SHOPPING_LIST, getKeySchemaElement()));
        populateDynamo(client);
    }

    private List<KeySchemaElement> getKeySchemaElement() {
       List<KeySchemaElement> keySchemaElementList = new LinkedList<>();
       keySchemaElementList.add(new KeySchemaElement(ID, KeyType.HASH));
       keySchemaElementList.add(new KeySchemaElement(CREATION_DATE, KeyType.RANGE));
       keySchemaElementList.add(new KeySchemaElement(SHOPPING_LIST, KeyType.HASH));
       return keySchemaElementList;
    }

    private void populateDynamo(AmazonDynamoDB client) {
        //TODO
        /*PutItemRequest putItemRequest = new PutItemRequest();
        putItemRequest.setTableName(SHOPPING_LIST);
        Map<String, AttributeValue> item = new HashMap<>();
        item.put("test", new AttributeValue());
        putItemRequest.setItem(item);
        client.putItem(putItemRequest);*/
    }
}
