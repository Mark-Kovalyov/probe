package mayton.probe.docker;

import java.util.List;

public interface DockerCommandLineFormatter {

    List<String> format(DockerParams dockerParams);

}
