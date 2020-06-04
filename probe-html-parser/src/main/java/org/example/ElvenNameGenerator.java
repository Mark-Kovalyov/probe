package org.example;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ElvenNameGenerator implements NameGenerator {

    @Override
    public String next() {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("JavaScript");
        // read script file
        try {
            engine.eval(Files.newBufferedReader(Paths.get("js/1.js"), StandardCharsets.UTF_8));
            Invocable inv = (Invocable) engine;
            // call function from script file
            inv.invokeFunction("yourFunction", "param");
            return "";

        } catch (ScriptException | NoSuchMethodException e) {

        } catch (IOException e) {

        }

        return "";

    }
}
