package mayton.probe;

import org.apache.parquet.schema.MessageType;
import org.apache.parquet.schema.MessageTypeParser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class Main {

    // See: https://towardsdatascience.com/how-to-generate-parquet-files-in-java-64cc5824a3ce
    private static List<List<String>> getDataForFile() {
        List<List<String>> data = new ArrayList<>();

        List<String> parquetFileItem1 = new ArrayList<>();
        parquetFileItem1.add("1");
        parquetFileItem1.add("Name1");
        parquetFileItem1.add("true");

        List<String> parquetFileItem2 = new ArrayList<>();
        parquetFileItem2.add("2");
        parquetFileItem2.add("Name2");
        parquetFileItem2.add("false");

        data.add(parquetFileItem1);
        data.add(parquetFileItem2);

        return data;
    }

    private static MessageType getSchemaForParquetFile() throws IOException {
        String schemaFilePath = "schemas/schema-example.txt";
        File resource = new File(schemaFilePath);
        String rawSchema = new String(Files.readAllBytes(resource.toPath()));
        return MessageTypeParser.parseMessageType(rawSchema);
    }

    public static void main(String[] args) throws IOException {
        List<List<String>> columns = getDataForFile();
        MessageType schema = getSchemaForParquetFile();

    }


}
