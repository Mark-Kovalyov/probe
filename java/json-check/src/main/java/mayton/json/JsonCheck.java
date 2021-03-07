package mayton.json;

import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class JsonCheck {

    public static void main(String[] args) throws FileNotFoundException {
        int exitcode = 0;
        String inputJson = args[1];
        try {
            JSONObject jsonSchema  = new JSONObject(new JSONTokener(new FileInputStream(args[0])));
            JSONObject jsonSubject = new JSONObject(new JSONTokener(new FileInputStream(inputJson)));

            // ObjectSchema
            Schema schema = SchemaLoader.load(jsonSchema);
            schema.validate(jsonSubject);
            System.out.println("All is OK!");
        } catch (ValidationException ex) {
            System.err.println("Error during " + inputJson + " validating!");
            System.err.println(ex.getMessage());
            ex.getCausingExceptions().stream()
                    .map(ValidationException::getMessage)
                    .forEach(System.err::println);
            exitcode = 1;
        }
        System.exit(exitcode);
    }

}
