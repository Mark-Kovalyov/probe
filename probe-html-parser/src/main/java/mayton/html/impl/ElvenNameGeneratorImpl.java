package mayton.html.impl;

import com.google.inject.Singleton;
import mayton.html.NameGenerator;
import org.jetbrains.annotations.NotNull;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

@Singleton
public class ElvenNameGeneratorImpl implements NameGenerator {

    @Override
    public @NotNull String next() {
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
