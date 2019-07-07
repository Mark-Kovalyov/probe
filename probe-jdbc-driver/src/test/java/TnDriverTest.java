import org.junit.Ignore;
import org.junit.Test;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static mayton.jdbc.TnDriver.TN_DRIVER_URL_PATTERN;
import static org.junit.Assert.*;

public class TnDriverTest {

    static Logger logger = Logger.getLogger(TnDriverTest.class.getName());

    @Test
    public void testUrlHost() {
        Pattern pattern = Pattern.compile(TN_DRIVER_URL_PATTERN, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher("jdbc:tn://www.tn.com:8080");
        assertTrue(matcher.matches());

        assertNotNull(matcher.group("host"));
        assertNotNull(matcher.group("port"));

        assertEquals("www.tn.com", matcher.group("host"));
        assertEquals("8080", matcher.group("port"));
    }

    @Test
    public void testUrlIp() {

        Pattern pattern = Pattern.compile(TN_DRIVER_URL_PATTERN, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher("jdbc:tn://127.0.0.1:11211");
        assertTrue(matcher.matches());

        assertNotNull(matcher.group("host"));
        assertNotNull(matcher.group("port"));

        assertEquals("127.0.0.1", matcher.group("host"));
        assertEquals("11211", matcher.group("port"));
    }

}
