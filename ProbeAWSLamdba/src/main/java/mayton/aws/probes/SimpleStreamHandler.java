package mayton.aws.probes;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.google.gson.Gson;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

public class SimpleStreamHandler implements RequestStreamHandler {

    @Override
    public void handleRequest(InputStream input, OutputStream output, Context context) throws IOException {
        JsonWriter writer = new JsonWriter(new PrintWriter(output));
        Gson gson = new Gson();
        Entity entity = new Entity();

        writer.flush();
    }

}
