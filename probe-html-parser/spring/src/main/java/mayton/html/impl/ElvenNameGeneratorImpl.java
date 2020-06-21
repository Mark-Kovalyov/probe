package mayton.html.impl;

import mayton.html.NameGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

@Component
public class ElvenNameGeneratorImpl implements NameGenerator {

    static Logger logger = LogManager.getLogger(ElvenNameGeneratorImpl.class);

    private ScriptEngine engine;

    @PostConstruct
    public void init() {
        engine = new ScriptEngineManager().getEngineByName("hashorn");
        logger.info("engine = {}", engine.getClass());
    }

    @Override
    public @NotNull String next() {
        return "";
    }
}
