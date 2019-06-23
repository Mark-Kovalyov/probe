package mayton.probe;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;

public class UnixCommandLineFormatter implements DockerCommandLineFormatter {

    static Logger logger = LogManager.getLogger(UnixCommandLineFormatter.class);

    private static String wrapQuotes(String arg) {
        return StringUtils.replace(arg, "\"", "\\\"");
    }

    @Override
    public String format(DockerParams dockerParams) {
        String commandLine =
                "docker run"
                        + " " + (dockerParams.getInstanceName().isEmpty() ? "" : " --name " + dockerParams.getInstanceName().get())
                        + " " + (dockerParams.getCpuAffinity().isEmpty() ? "" :
                                "--cpuset-cpus=" + dockerParams.getCpuAffinity()
                                        .stream()
                                        .map(String::valueOf)
                                        .collect(Collectors.joining(",")))
                        + (dockerParams.getNetwork().isEmpty() ? " " : " --network=" + dockerParams.getNetwork().get())
                        + " " + dockerParams.getPortMapping()
                                .entrySet()
                                .stream()
                                .map((entry) -> " -p " + entry.getKey() + ":" + entry.getValue())
                                .collect(joining(" "))
                        + (dockerParams.isDetach() ? " --detach " : "")
                        + " " + dockerParams.getEnvironment()
                                .entrySet()
                                .stream()
                                .map((entry) -> " -e " + "\"" + wrapQuotes(entry.getKey()) + "=" + wrapQuotes(entry.getValue()) + "\"")
                                .collect(joining(" "))
                        + " " + dockerParams.getVolumeMapping()
                                .entrySet()
                                .stream()
                                .map((entry) -> " -v " + entry.getKey() + ":" + entry.getValue())
                                .collect(joining(" "))
                        + " " + dockerParams.getImageName()
                        + " " + String.join(" ", dockerParams.getCommandLineArguments());

        logger.trace("commandLine = {}", commandLine);

        return commandLine;
    }

}
