package mayton.html;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Hello world!
 */
public class App {

    static Logger logger = LogManager.getLogger(App.class);

    public static void main(String[] args) throws IOException {

        Injector injector = Guice.createInjector(new GuiceInjectorBasicModule());

        WalkerService walkerService = injector.getInstance(WalkerService.class);
        walkerService.walk("https://www.sql.ru");
        //ExecutorService threadPoolExecutor = Executors.newFixedThreadPool(20);
    }

}
