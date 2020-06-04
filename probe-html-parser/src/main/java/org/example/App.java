package org.example;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.name.Named;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * Hello world!
 */
public class App {

    static Logger logger = Logger.getLogger(App.class.getName());



    public static void main(String[] args) throws IOException {

        Injector injector = Guice.createInjector(new GuiceInjectorBasicModule());
        WalkerService walkerService = injector.getInstance(WalkerService.class);
        walkerService.walk("https://www.sql.ru");

    }

}
