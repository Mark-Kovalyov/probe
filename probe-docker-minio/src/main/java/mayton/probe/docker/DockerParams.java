package mayton.probe.docker;

import javax.annotation.Nonnull;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Collections.*;

// $ docker run [OPTIONS] IMAGE[:TAG|@DIGEST] [COMMAND] [ARG...]

// Linux/MacOS

//docker run -p 9000:9000 --name minio1 \
//        -e "MINIO_ACCESS_KEY=AKIAIOSFODNN7EXAMPLE" \
//        -e "MINIO_SECRET_KEY=wJalrXUtnFEMI/K7MDENG/bPxRfiCYEXAMPLEKEY" \
//        -v /mnt/data:/data \
//        -v /mnt/config:/root/.minio \
//        minio/minio server /data

// Windows

// docker run -p 9000:9000 --name minio1 \
//  -e "MINIO_ACCESS_KEY=**************************" \
//  -e "MINIO_SECRET_KEY=@@@@@@@@@@@@@@@@@@@@@" \
//  -v D:\data:/data \
//  -v D:\minio\config:/root/.minio \
//  minio/minio server /data

// docker run  --name minio-instance
// --detach
// -e "aws_access_key_id=**************************"
// -e "aws_secret_access_key=@@@@@@@@@@@@@@@@@@@@@"
// -p 9000:9000  -v /home/mayton/git/probe/probe-docker-minio/docker/data /data
// -v /home/mayton/git/probe/probe-docker-minio/docker/config /root/.minio
// minio/minio

// Docker process affinity

// # docker run --cpuset 0,1 /bin/bash mycontainer

// $ docker run -d --name elasticsearch --net somenetwork -p 9200:9200 -p 9300:9300 -e "discovery.type=single-node" elasticsearch:tag

// --volume

public final class DockerParams {

    enum DockerRestart {

        NO("no"), // defaule
        ALWAYS("always") ,
        UNLESS_STOPPED("unless-stopped"); // on-failure[:max-retries]

        public final String value;
        DockerRestart(String value) {
            this.value = value;
        }
    }

    private String imageName;

    private Optional<String> instanceName = Optional.empty();

    private Optional<String> network = Optional.empty();

    private DockerRestart dockerRestart = DockerRestart.NO;

    private LinkedHashMap<String,String>   environment = new LinkedHashMap<>();

    private LinkedHashMap<Integer,Integer> portMapping = new LinkedHashMap<>();

    private LinkedHashMap<String,String>   volumeMapping = new LinkedHashMap<>();

    private List<String> commandLineArguments = new ArrayList<>();

    private boolean isDetach;

    private Set<Integer> cpuAffinity = new LinkedHashSet<>();

    public DockerParams(@Nonnull String imageName) {
        this.imageName = imageName;
    }

    public boolean isDetach() {
        return isDetach;
    }

    public Map<Integer, Integer> getPortMapping() {
        return unmodifiableMap(portMapping);
    }

    public Map<String, String> getVolumeMapping() {
        return unmodifiableMap(volumeMapping);
    }

    public Map<String, String> getEnvironment() {
        return unmodifiableMap(environment);
    }

    public List<String> getCommandLineArguments() {
        return unmodifiableList(commandLineArguments);
    }

    public DockerParams withDetach() {
        isDetach = true;
        return this;
    }

    public DockerParams withInstanceName(@Nonnull String instanceName) {
        this.instanceName = Optional.of(instanceName);
        return this;
    }

    public DockerParams addEnvironment(@Nonnull String name,@Nonnull String value) {
        environment.put(name,value);
        return this;
    }

    public DockerParams addVolumeMapping(@Nonnull String volumeFrom, @Nonnull String volumeTo) {
        volumeMapping.put(volumeFrom, volumeTo);
        return this;
    }

    public DockerParams addPortMapping(int from, int to) {
        portMapping.put(from, to);
        return this;
    }

    public DockerParams addCommandLineArgument(String argument) {
        commandLineArguments.add(argument);
        return this;
    }

    public DockerParams withCpuAffinity(Integer ...args) {
        cpuAffinity = Arrays.stream(args).collect(Collectors.toSet());
        return this;
    }

    public DockerParams withRestartOption(@Nonnull DockerRestart dockerRestart) {
        this.dockerRestart = dockerRestart;
        return this;
    }

    public String getImageName() {
        return imageName;
    }

    public DockerRestart getDockerRestart() {
        return dockerRestart;
    }

    public Set<Integer> getCpuAffinity() {
        return unmodifiableSet(cpuAffinity);
    }

    public Optional<String> getInstanceName() {
        return instanceName;
    }

    public Optional<String> getNetwork() {
        return network;
    }

    public DockerParams withNetwork(@Nonnull String network) {
        if (network != null) {
            this.network = Optional.of(network);
        }
        return this;
    }

}
