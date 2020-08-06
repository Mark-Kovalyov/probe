# spring-boot-maven-plugin

```
Name: Spring Boot Maven Plugin
Description: (no description available)
Group Id: org.springframework.boot
Artifact Id: spring-boot-maven-plugin
Version: 2.3.2.RELEASE
Goal Prefix: spring-boot

This plugin has 7 goals:

spring-boot:build-image
  Description: Package an application into a OCI image using a buildpack.

spring-boot:build-info
  Description: Generate a build-info.properties file based the content of the
    current MavenProject.

spring-boot:help
  Description: Display help information on spring-boot-maven-plugin.
    Call mvn spring-boot:help -Ddetail=true -Dgoal=<goal-name> to display
    parameter details.

spring-boot:repackage
  Description: Repackage existing JAR and WAR archives so that they can be
    executed from the command line using java -jar. With layout=NONE can also
    be used simply to package a JAR with nested dependencies (and no main
    class, so not executable).

spring-boot:run
  Description: Run an application in place.

spring-boot:start
  Description: Start a spring application. Contrary to the run goal, this
    does not block and allows other goals to operate on the application. This
    goal is typically used in integration test scenario where the application
    is started before a test suite and stopped after.

spring-boot:stop
  Description: Stop an application that has been started by the 'start' goal.
    Typically invoked once a test suite has completed.

For more information, run 'mvn help:describe [...] -Ddetail'
```

## Detailed Info

```
Name: Spring Boot Maven Plugin
Description: (no description available)
Group Id: org.springframework.boot
Artifact Id: spring-boot-maven-plugin
Version: 2.3.2.RELEASE
Goal Prefix: spring-boot

This plugin has 7 goals:

spring-boot:build-image
  Description: Package an application into a OCI image using a buildpack.
  Implementation: org.springframework.boot.maven.BuildImageMojo
  Language: java
  Bound to phase: package
  Before this goal executes, it will call:
    Phase: 'package'

  Available parameters:

    classifier
      Classifier used when finding the source jar.

    excludeDevtools (Default: true)
      User property: spring-boot.repackage.excludeDevtools
      Exclude Spring Boot devtools from the repackaged archive.

    excludeGroupIds
      User property: spring-boot.excludeGroupIds
      Comma separated list of groupId names to exclude (exact match).

    excludes
      User property: spring-boot.excludes
      Collection of artifact definitions to exclude. The Exclude element
      defines a groupId and artifactId mandatory properties and an optional
      classifier property.

    image
      Image configuration, with `builder`, `runImage`, `name`, `env`,
      `cleanCache` and `verboseLogging` options.

    includes
      User property: spring-boot.includes
      Collection of artifact definitions to include. The Include element
      defines a groupId and artifactId mandatory properties and an optional
      classifier property.

    includeSystemScope (Default: false)
      Include system scoped dependencies.

    layers
      Layer configuration with the option to exclude layer tools jar.

    layout
      User property: spring-boot.repackage.layout
      The type of archive (which corresponds to how the dependencies are laid
      out inside it). Possible values are JAR, WAR, ZIP, DIR, NONE. Defaults to
      a guess based on the archive type.

    layoutFactory
      The layout factory that will be used to create the executable archive if
      no explicit layout is set. Alternative layouts implementations can be
      provided by 3rd parties.

    mainClass
      The name of the main class. If not specified the first compiled class
      found that contains a 'main' method will be used.

    skip (Default: false)
      User property: spring-boot.build-image.skip
      Skip the execution.

    sourceDirectory (Default: ${project.build.directory})
      Required: true
      Directory containing the JAR.

spring-boot:build-info
  Description: Generate a build-info.properties file based the content of the
    current MavenProject.
  Implementation: org.springframework.boot.maven.BuildInfoMojo
  Language: java
  Bound to phase: generate-resources

  Available parameters:

    additionalProperties
      Additional properties to store in the build-info.properties. Each entry
      is prefixed by build. in the generated build-info.properties.

    outputFile (Default:
    ${project.build.outputDirectory}/META-INF/build-info.properties)
      The location of the generated build-info.properties.

    time
      The value used for the build.time property in a form suitable for
      Instant.parse(). Defaults to session.request.startTime. To disable the
      build.time property entirely, use 'off'.

spring-boot:help
  Description: Display help information on spring-boot-maven-plugin.
    Call mvn spring-boot:help -Ddetail=true -Dgoal=<goal-name> to display
    parameter details.
  Implementation: org.springframework.boot.maven.HelpMojo
  Language: java

  Available parameters:

    detail (Default: false)
      User property: detail
      If true, display all settable properties for each goal.

    goal
      User property: goal
      The name of the goal for which to show help. If unspecified, all goals
      will be displayed.

    indentSize (Default: 2)
      User property: indentSize
      The number of spaces per indentation level, should be positive.

    lineLength (Default: 80)
      User property: lineLength
      The maximum length of a display line, should be positive.

spring-boot:repackage
  Description: Repackage existing JAR and WAR archives so that they can be
    executed from the command line using java -jar. With layout=NONE can also
    be used simply to package a JAR with nested dependencies (and no main
    class, so not executable).
  Implementation: org.springframework.boot.maven.RepackageMojo
  Language: java
  Bound to phase: package

  Available parameters:

    attach (Default: true)
      Attach the repackaged archive to be installed into your local Maven
      repository or deployed to a remote repository. If no classifier has been
      configured, it will replace the normal jar. If a classifier has been
      configured such that the normal jar and the repackaged jar are different,
      it will be attached alongside the normal jar. When the property is set to
      false, the repackaged archive will not be installed or deployed.

    classifier
      Classifier to add to the repackaged archive. If not given, the main
      artifact will be replaced by the repackaged archive. If given, the
      classifier will also be used to determine the source archive to
      repackage: if an artifact with that classifier already exists, it will be
      used as source and replaced. If no such artifact exists, the main
      artifact will be used as source and the repackaged archive will be
      attached as a supplemental artifact with that classifier. Attaching the
      artifact allows to deploy it alongside to the original one, see the Maven
      documentation for more details.

    embeddedLaunchScript
      The embedded launch script to prepend to the front of the jar if it is
      fully executable. If not specified the 'Spring Boot' default script will
      be used.

    embeddedLaunchScriptProperties
      Properties that should be expanded in the embedded launch script.

    excludeDevtools (Default: true)
      User property: spring-boot.repackage.excludeDevtools
      Exclude Spring Boot devtools from the repackaged archive.

    excludeGroupIds
      User property: spring-boot.excludeGroupIds
      Comma separated list of groupId names to exclude (exact match).

    excludes
      User property: spring-boot.excludes
      Collection of artifact definitions to exclude. The Exclude element
      defines a groupId and artifactId mandatory properties and an optional
      classifier property.

    executable (Default: false)
      Make a fully executable jar for *nix machines by prepending a launch
      script to the jar.
      Currently, some tools do not accept this format so you may not always be
      able to use this technique. For example, jar -xf may silently fail to
      extract a jar or war that has been made fully-executable. It is
      recommended that you only enable this option if you intend to execute it
      directly, rather than running it with java -jar or deploying it to a
      servlet container.

    includes
      User property: spring-boot.includes
      Collection of artifact definitions to include. The Include element
      defines a groupId and artifactId mandatory properties and an optional
      classifier property.

    includeSystemScope (Default: false)
      Include system scoped dependencies.

    layers
      Layer configuration with the option to exclude layer tools jar.

    layout
      User property: spring-boot.repackage.layout
      The type of archive (which corresponds to how the dependencies are laid
      out inside it). Possible values are JAR, WAR, ZIP, DIR, NONE. Defaults to
      a guess based on the archive type.

    layoutFactory
      The layout factory that will be used to create the executable archive if
      no explicit layout is set. Alternative layouts implementations can be
      provided by 3rd parties.

    mainClass
      The name of the main class. If not specified the first compiled class
      found that contains a 'main' method will be used.

    outputDirectory (Default: ${project.build.directory})
      Required: true
      Directory containing the generated archive.

    outputTimestamp (Default: ${project.build.outputTimestamp})
      Timestamp for reproducible output archive entries, either formatted as
      ISO 8601 (yyyy-MM-dd'T'HH:mm:ssXXX) or an int representing seconds since
      the epoch. Not supported with war packaging.

    requiresUnpack
      A list of the libraries that must be unpacked from fat jars in order to
      run. Specify each library as a <dependency> with a <groupId> and a
      <artifactId> and they will be unpacked at runtime.

    skip (Default: false)
      User property: spring-boot.repackage.skip
      Skip the execution.

spring-boot:run
  Description: Run an application in place.
  Implementation: org.springframework.boot.maven.RunMojo
  Language: java
  Bound to phase: validate
  Before this goal executes, it will call:
    Phase: 'test-compile'

  Available parameters:

    addResources (Default: false)
      User property: spring-boot.run.addResources
      Add maven resources to the classpath directly, this allows live in-place
      editing of resources. Duplicate resources are removed from target/classes
      to prevent them to appear twice if ClassLoader.getResources() is called.
      Please consider adding spring-boot-devtools to your project instead as it
      provides this feature and many more.

    agents
      User property: spring-boot.run.agents
      Path to agent jars. NOTE: a forked process is required to use this
      feature.

    arguments
      Arguments that should be passed to the application.

    classesDirectory (Default: ${project.build.outputDirectory})
      Required: true
      Directory containing the classes and resource files that should be
      packaged into the archive.

    commandlineArguments
      User property: spring-boot.run.arguments
      Arguments from the command line that should be passed to the application.
      Use spaces to separate multiple arguments and make sure to wrap multiple
      values between quotes. When specified, takes precedence over arguments.

    directories
      User property: spring-boot.run.directories
      Additional directories besides the classes directory that should be added
      to the classpath.

    environmentVariables
      List of Environment variables that should be associated with the forked
      process used to run the application. NOTE: a forked process is required
      to use this feature.

    excludeGroupIds
      User property: spring-boot.excludeGroupIds
      Comma separated list of groupId names to exclude (exact match).

    excludes
      User property: spring-boot.excludes
      Collection of artifact definitions to exclude. The Exclude element
      defines a groupId and artifactId mandatory properties and an optional
      classifier property.

    folders
      User property: spring-boot.run.folders
      Additional directories besides the classes directory that should be added
      to the classpath.
      Deprecated. since 2.3.0 in favor of {@code directories}

    fork (Default: true)
      User property: spring-boot.run.fork
      Flag to indicate if the run processes should be forked. Disabling forking
      will disable some features such as an agent, custom JVM arguments,
      devtools or specifying the working directory to use.

    includes
      User property: spring-boot.includes
      Collection of artifact definitions to include. The Include element
      defines a groupId and artifactId mandatory properties and an optional
      classifier property.

    jvmArguments
      User property: spring-boot.run.jvmArguments
      JVM arguments that should be associated with the forked process used to
      run the application. On command line, make sure to wrap multiple values
      between quotes. NOTE: a forked process is required to use this feature.

    mainClass
      User property: spring-boot.run.main-class
      The name of the main class. If not specified the first compiled class
      found that contains a 'main' method will be used.

    noverify
      User property: spring-boot.run.noverify
      Flag to say that the agent requires -noverify.

    optimizedLaunch (Default: true)
      User property: spring-boot.run.optimizedLaunch
      Whether the JVM's launch should be optimized.

    profiles
      User property: spring-boot.run.profiles
      The spring profiles to activate. Convenience shortcut of specifying the
      'spring.profiles.active' argument. On command line use commas to separate
      multiple profiles.

    skip (Default: false)
      User property: spring-boot.run.skip
      Skip the execution.

    systemPropertyVariables
      List of JVM system properties to pass to the process. NOTE: a forked
      process is required to use this feature.

    useTestClasspath (Default: false)
      User property: spring-boot.run.useTestClasspath
      Flag to include the test classpath when running.

    workingDirectory
      User property: spring-boot.run.workingDirectory
      Current working directory to use for the application. If not specified,
      basedir will be used. NOTE: a forked process is required to use this
      feature.

spring-boot:start
  Description: Start a spring application. Contrary to the run goal, this
    does not block and allows other goals to operate on the application. This
    goal is typically used in integration test scenario where the application
    is started before a test suite and stopped after.
  Implementation: org.springframework.boot.maven.StartMojo
  Language: java
  Bound to phase: pre-integration-test

  Available parameters:

    addResources (Default: false)
      User property: spring-boot.run.addResources
      Add maven resources to the classpath directly, this allows live in-place
      editing of resources. Duplicate resources are removed from target/classes
      to prevent them to appear twice if ClassLoader.getResources() is called.
      Please consider adding spring-boot-devtools to your project instead as it
      provides this feature and many more.

    agents
      User property: spring-boot.run.agents
      Path to agent jars. NOTE: a forked process is required to use this
      feature.

    arguments
      Arguments that should be passed to the application.

    classesDirectory (Default: ${project.build.outputDirectory})
      Required: true
      Directory containing the classes and resource files that should be
      packaged into the archive.

    commandlineArguments
      User property: spring-boot.run.arguments
      Arguments from the command line that should be passed to the application.
      Use spaces to separate multiple arguments and make sure to wrap multiple
      values between quotes. When specified, takes precedence over arguments.

    directories
      User property: spring-boot.run.directories
      Additional directories besides the classes directory that should be added
      to the classpath.

    environmentVariables
      List of Environment variables that should be associated with the forked
      process used to run the application. NOTE: a forked process is required
      to use this feature.

    excludeGroupIds
      User property: spring-boot.excludeGroupIds
      Comma separated list of groupId names to exclude (exact match).

    excludes
      User property: spring-boot.excludes
      Collection of artifact definitions to exclude. The Exclude element
      defines a groupId and artifactId mandatory properties and an optional
      classifier property.

    folders
      User property: spring-boot.run.folders
      Additional directories besides the classes directory that should be added
      to the classpath.
      Deprecated. since 2.3.0 in favor of {@code directories}

    fork (Default: true)
      User property: spring-boot.run.fork
      Flag to indicate if the run processes should be forked. Disabling forking
      will disable some features such as an agent, custom JVM arguments,
      devtools or specifying the working directory to use.

    includes
      User property: spring-boot.includes
      Collection of artifact definitions to include. The Include element
      defines a groupId and artifactId mandatory properties and an optional
      classifier property.

    jmxName
      The JMX name of the automatically deployed MBean managing the lifecycle
      of the spring application.

    jmxPort
      The port to use to expose the platform MBeanServer if the application is
      forked.

    jvmArguments
      User property: spring-boot.run.jvmArguments
      JVM arguments that should be associated with the forked process used to
      run the application. On command line, make sure to wrap multiple values
      between quotes. NOTE: a forked process is required to use this feature.

    mainClass
      User property: spring-boot.run.main-class
      The name of the main class. If not specified the first compiled class
      found that contains a 'main' method will be used.

    maxAttempts
      The maximum number of attempts to check if the spring application is
      ready. Combined with the 'wait' argument, this gives a global timeout
      value (30 sec by default)

    noverify
      User property: spring-boot.run.noverify
      Flag to say that the agent requires -noverify.

    profiles
      User property: spring-boot.run.profiles
      The spring profiles to activate. Convenience shortcut of specifying the
      'spring.profiles.active' argument. On command line use commas to separate
      multiple profiles.

    skip (Default: false)
      User property: spring-boot.run.skip
      Skip the execution.

    systemPropertyVariables
      List of JVM system properties to pass to the process. NOTE: a forked
      process is required to use this feature.

    useTestClasspath (Default: false)
      User property: spring-boot.run.useTestClasspath
      Flag to include the test classpath when running.

    wait
      The number of milli-seconds to wait between each attempt to check if the
      spring application is ready.

    workingDirectory
      User property: spring-boot.run.workingDirectory
      Current working directory to use for the application. If not specified,
      basedir will be used. NOTE: a forked process is required to use this
      feature.

spring-boot:stop
  Description: Stop an application that has been started by the 'start' goal.
    Typically invoked once a test suite has completed.
  Implementation: org.springframework.boot.maven.StopMojo
  Language: java
  Bound to phase: post-integration-test

  Available parameters:

    fork
      User property: spring-boot.stop.fork
      Flag to indicate if process to stop was forked. By default, the value is
      inherited from the MavenProject. If it is set, it must match the value
      used to StartMojo start the process.

    jmxName
      The JMX name of the automatically deployed MBean managing the lifecycle
      of the application.

    jmxPort
      The port to use to lookup the platform MBeanServer if the application has
      been forked.

    skip (Default: false)
      User property: spring-boot.stop.skip
      Skip the execution.
```
