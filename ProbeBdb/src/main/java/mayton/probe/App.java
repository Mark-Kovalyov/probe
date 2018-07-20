package mayton.probe;

import com.sleepycat.je.DatabaseException;
import com.sleepycat.je.Environment;
import com.sleepycat.je.EnvironmentConfig;
import com.sleepycat.persist.PrimaryIndex;

import java.io.File;
import java.time.LocalDateTime;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws DatabaseException {

        Environment env = null;

        EnvironmentConfig econf = new EnvironmentConfig();
        econf.setAllowCreate(true);
        econf.setTransactional(false);

        env = new Environment(new File("./db"),econf);

        PrimaryIndex<LocalDateTime,String > primaryIndex;



        env.close();

    }
}
