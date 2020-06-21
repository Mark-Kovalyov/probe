package org.example;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.*;

import java.util.List;

/**
 * Hello world!
 */
public class DynamoWorkingWithTables {

    final AmazonDynamoDB ddb = AmazonDynamoDBClientBuilder.defaultClient();

    long read_capacity = 1;
    long write_capacity = 1;

    public void update() {
        ProvisionedThroughput table_throughput = new ProvisionedThroughput(
                read_capacity, write_capacity);



        try {
            ddb.updateTable("table_name", table_throughput);
        } catch (AmazonServiceException e) {
            System.err.println(e.getErrorMessage());
            System.exit(1);
        }
    }

    public void listTables()  {
        ListTablesRequest request;

        boolean more_tables = true;
        String last_name = null;

        while (more_tables) {
            if (last_name == null) {
                request = new ListTablesRequest().withLimit(10);
            } else {
                request = new ListTablesRequest()
                        .withLimit(10)
                        .withExclusiveStartTableName(last_name);
            }

            ListTablesResult table_list = ddb.listTables(request);
            List<String> table_names = table_list.getTableNames();

            if (table_names.size() > 0) {
                for (String cur_name : table_names) {
                    System.out.format("* %s\n", cur_name);
                }
            } else {
                System.out.println("No tables found!");
                System.exit(0);
            }

            last_name = table_list.getLastEvaluatedTableName();
            if (last_name == null) {
                more_tables = false;
            }
        }
    }

    public void createTableWithSimplePk() throws AmazonServiceException {

        CreateTableRequest request = new CreateTableRequest()
                .withAttributeDefinitions(new AttributeDefinition("Name", ScalarAttributeType.S))
                .withKeySchema(new KeySchemaElement("Name", KeyType.HASH))
                .withProvisionedThroughput(new ProvisionedThroughput(10L, 10L))
                .withTableName("table_name");

        CreateTableResult result = ddb.createTable(request);
        System.out.println(result.getTableDescription().getTableName());

    }

    public void createTableWithCompositePk() {
        CreateTableRequest request = new CreateTableRequest()
                .withAttributeDefinitions(
                        new AttributeDefinition("Language", ScalarAttributeType.S),
                        new AttributeDefinition("Greeting", ScalarAttributeType.S))
                .withKeySchema(
                        new KeySchemaElement("Language", KeyType.HASH),
                        new KeySchemaElement("Greeting", KeyType.RANGE))
                .withProvisionedThroughput(
                        new ProvisionedThroughput(new Long(10), new Long(10)))
                .withTableName("table_name");
    }

    public static void main(String[] args) {

    }
}
