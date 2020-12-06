package mayton.html;

import java.util.LinkedHashMap;

// TODO: Move all configs into Spring-application.yaml file
@Deprecated
public interface Config {

    LinkedHashMap<String, Object> getRoot();

}
