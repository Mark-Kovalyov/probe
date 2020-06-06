package org.example;

import org.jetbrains.annotations.NotNull;

public interface Config {

    String getProperty(@NotNull String name);

    int getPropertyInteger(@NotNull String name);

    int getPropertyBoolean(@NotNull String name);

    double getPropertyDouble(@NotNull String name);

}
