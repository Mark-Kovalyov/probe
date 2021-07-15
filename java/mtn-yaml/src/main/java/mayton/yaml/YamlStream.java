package mayton.yaml;

import java.io.Writer;
import java.util.Stack;

public class YamlStream implements AutoCloseable {

    private Stack<YamlNode> yamlNodeStack;

    private Writer writer;

    public YamlStream(Writer writer) {

    }

    public void addNode(String name) {
        
    }


    @Override
    public void close() throws Exception {

    }
}
