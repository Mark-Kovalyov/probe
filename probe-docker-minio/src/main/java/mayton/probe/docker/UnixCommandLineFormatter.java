package mayton.probe.docker;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;

public class UnixCommandLineFormatter implements DockerCommandLineFormatter {

    static Logger logger = LogManager.getLogger(UnixCommandLineFormatter.class);

    @Override
    public List<String> format(DockerParams dockerParams) {
        List<String> commandLineWords = new ArrayList<>();

        commandLineWords.add("docker");

        commandLineWords.add("run");

        if (!dockerParams.getInstanceName().isEmpty()) {
            commandLineWords.add("--name");
            commandLineWords.add(dockerParams.getInstanceName().get());
        }

        if (!dockerParams.getCpuAffinity().isEmpty()) {
            commandLineWords.add("--cpuset-cpus=" +
                        commandLineWords.add(dockerParams.getCpuAffinity()
                        .stream()
                        .map(String::valueOf)
                        .collect(Collectors.joining(","))));
        }

        if (!dockerParams.getNetwork().isEmpty()) {
            commandLineWords.add("--network=" + dockerParams.getNetwork().get());
        }

        dockerParams.getPortMapping()
                .entrySet()
                .forEach(entry -> {
                    commandLineWords.add("-p");
                    commandLineWords.add(entry.getKey() + ":" + entry.getValue());
                });

        if (dockerParams.isDetach()) {
            commandLineWords.add("--detach");
        }

        dockerParams.getEnvironment()
                .entrySet()
                .forEach(entry -> {
                    commandLineWords.add("-e");
                    commandLineWords.add(entry.getKey() + "=" + entry.getValue());
        });

        dockerParams.getVolumeMapping()
                .entrySet()
                .forEach(entry -> {
            commandLineWords.add("-v");
            commandLineWords.add(entry.getKey() + ":" + entry.getValue());
        });

        commandLineWords.add(dockerParams.getImageName());

        dockerParams.getCommandLineArguments().forEach(element -> commandLineWords.add(element));

        logger.trace("commandLine words = {}", commandLineWords.toString());

        logger.trace("concatenated commandLine words = {}", commandLineWords.stream().collect(joining(" ")));

        return commandLineWords;
    }

}
