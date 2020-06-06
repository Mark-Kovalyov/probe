package org.example;

import org.jetbrains.annotations.NotNull;
import org.yaml.snakeyaml.Yaml;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedHashMap;

// TODO: Consider for @Threadsafe
public class ConfigImpl implements Config{

    private LinkedHashMap<String, Object> root;

    public ConfigImpl() {
        Yaml yaml = new Yaml();
        try {
            root = yaml.load(new FileReader("config.yaml"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Unable to load property config.yaml from user.dir = " + System.getProperty("user.dir"));
        }
    }

    @Override
    public String getProperty(@NotNull String name) {
        return (String) root.get("name");
    }

    @Override
    public int getPropertyInteger(@NotNull String name) {
        return 0;
    }

    @Override
    public int getPropertyBoolean(@NotNull String name) {
        return 0;
    }

    @Override
    public double getPropertyDouble(@NotNull String name) {
        return 0;
    }
}
