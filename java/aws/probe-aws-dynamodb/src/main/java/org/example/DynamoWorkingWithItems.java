package org.example;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.GetItemRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DynamoWorkingWithItems {

    final AmazonDynamoDB ddb = AmazonDynamoDBClientBuilder.defaultClient();

    String table_name = "mytable";

    String projection_expression = "";

    String name = "attr1";

    public void getItemFromTable() {

        Map<String, AttributeValue> key_to_get = new HashMap<>();

        key_to_get.put("DATABASE_NAME", new AttributeValue(name));

        GetItemRequest request = null;

        if (projection_expression != null) {
            request = new GetItemRequest()
                    .withKey(key_to_get)
                    .withTableName(table_name)
                    .withProjectionExpression(projection_expression);
        } else {
            request = new GetItemRequest()
                    .withKey(key_to_get)
                    .withTableName(table_name);
        }

        try {
            Map<String, AttributeValue> returned_item = ddb.getItem(request).getItem();
            if (returned_item != null) {
                Set<String> keys = returned_item.keySet();
                for (String key : keys) {
                    System.out.format("%s: %s\n", key, returned_item.get(key).toString());
                }
            } else {
                System.out.format("No item found with the key %s!\n", name);
            }
        } catch (AmazonServiceException e) {
            System.err.println(e.getErrorMessage());
            System.exit(1);

        }
    }

    public static void main(String[] args) {

    }

}
