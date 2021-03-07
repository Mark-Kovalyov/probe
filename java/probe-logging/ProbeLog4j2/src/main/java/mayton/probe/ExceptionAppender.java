package mayton.probe;

import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.config.Property;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;

@Plugin(name = "ExceptionAppender", category = "Core", elementType = "appender", printObject = true)
public class ExceptionAppender extends AbstractAppender {

    @PluginFactory
    public static ExceptionAppender createAppender(
            @PluginAttribute(value = "name", defaultString = "null") final String name) {
        return new ExceptionAppender(name);
    }

    private ExceptionAppender(final String name) {
        super(name, null, null, true, Property.EMPTY_ARRAY);
    }

    @Override
    public void append(final LogEvent event) {
        throw new RuntimeException("Habba-habba...");
    }

}
