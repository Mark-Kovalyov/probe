# jar

```
Name: Apache Maven JAR Plugin
Description: Builds a Java Archive (JAR) file from the compiled project
  classes and resources.
Group Id: org.apache.maven.plugins
Artifact Id: maven-jar-plugin
Version: 3.2.0
Goal Prefix: jar

This plugin has 3 goals:

jar:help
  Description: Display help information on maven-jar-plugin.
    Call mvn jar:help -Ddetail=true -Dgoal=<goal-name> to display parameter
    details.

jar:jar
  Description: Build a JAR from the current project.

jar:test-jar
  Description: Build a JAR of the test classes for the current project.

For more information, run 'mvn help:describe [...] -Ddetail'
```

## Detailed Info

```
Name: Apache Maven JAR Plugin
Description: Builds a Java Archive (JAR) file from the compiled project
  classes and resources.
Group Id: org.apache.maven.plugins
Artifact Id: maven-jar-plugin
Version: 3.2.0
Goal Prefix: jar

This plugin has 3 goals:

jar:help
  Description: Display help information on maven-jar-plugin.
    Call mvn jar:help -Ddetail=true -Dgoal=<goal-name> to display parameter
    details.
  Implementation: org.apache.maven.plugins.jar.HelpMojo
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

jar:jar
  Description: Build a JAR from the current project.
  Implementation: org.apache.maven.plugins.jar.JarMojo
  Language: java
  Bound to phase: package

  Available parameters:

    archive
      The archive configuration to use. See Maven Archiver Reference.

    classesDirectory (Default: ${project.build.outputDirectory})
      Required: true
      Directory containing the classes and resource files that should be
      packaged into the JAR.

    classifier
      Classifier to add to the artifact generated. If given, the artifact will
      be attached as a supplemental artifact. If not given this will create the
      main artifact which is the default behavior. If you try to do that a
      second time without using a classifier the build will fail.

    excludes
      List of files to exclude. Specified as fileset patterns which are
      relative to the input directory whose contents is being packaged into the
      JAR.

    forceCreation (Default: false)
      User property: maven.jar.forceCreation
      Require the jar plugin to build a new JAR even if none of the contents
      appear to have changed. By default, this plugin looks to see if the
      output jar exists and inputs have not changed. If these conditions are
      true, the plugin skips creation of the jar. This does not work when other
      plugins, like the maven-shade-plugin, are configured to post-process the
      jar. This plugin can not detect the post-processing, and so leaves the
      post-processed jar in place. This can lead to failures when those plugins
      do not expect to find their own output as an input. Set this parameter to
      true to avoid these problems by forcing this plugin to recreate the jar
      every time.
      Starting with 3.0.0 the property has been renamed from jar.forceCreation
      to maven.jar.forceCreation.

    includes
      List of files to include. Specified as fileset patterns which are
      relative to the input directory whose contents is being packaged into the
      JAR.

    outputDirectory (Default: ${project.build.directory})
      Required: true
      Directory containing the generated JAR.

    outputTimestamp (Default: ${project.build.outputTimestamp})
      Timestamp for reproducible output archive entries, either formatted as
      ISO 8601 yyyy-MM-dd'T'HH:mm:ssXXX or as an int representing seconds since
      the epoch (like SOURCE_DATE_EPOCH).

    skipIfEmpty (Default: false)
      Skip creating empty archives.

    useDefaultManifestFile (Default: false)
      User property: jar.useDefaultManifestFile
      Using this property will fail your build cause it has been removed from
      the plugin configuration. See the Major Version Upgrade to version 3.0.0
      for the plugin.
      Deprecated. For version 3.0.0 this parameter is only defined here
      to break the build if you use it!

jar:test-jar
  Description: Build a JAR of the test classes for the current project.
  Implementation: org.apache.maven.plugins.jar.TestJarMojo
  Language: java
  Bound to phase: package

  Available parameters:

    archive
      The archive configuration to use. See Maven Archiver Reference.

    classifier (Default: tests)
      Classifier to used for test-jar.

    excludes
      List of files to exclude. Specified as fileset patterns which are
      relative to the input directory whose contents is being packaged into the
      JAR.

    forceCreation (Default: false)
      User property: maven.jar.forceCreation
      Require the jar plugin to build a new JAR even if none of the contents
      appear to have changed. By default, this plugin looks to see if the
      output jar exists and inputs have not changed. If these conditions are
      true, the plugin skips creation of the jar. This does not work when other
      plugins, like the maven-shade-plugin, are configured to post-process the
      jar. This plugin can not detect the post-processing, and so leaves the
      post-processed jar in place. This can lead to failures when those plugins
      do not expect to find their own output as an input. Set this parameter to
      true to avoid these problems by forcing this plugin to recreate the jar
      every time.
      Starting with 3.0.0 the property has been renamed from jar.forceCreation
      to maven.jar.forceCreation.

    includes
      List of files to include. Specified as fileset patterns which are
      relative to the input directory whose contents is being packaged into the
      JAR.

    outputDirectory (Default: ${project.build.directory})
      Required: true
      Directory containing the generated JAR.

    outputTimestamp (Default: ${project.build.outputTimestamp})
      Timestamp for reproducible output archive entries, either formatted as
      ISO 8601 yyyy-MM-dd'T'HH:mm:ssXXX or as an int representing seconds since
      the epoch (like SOURCE_DATE_EPOCH).

    skip
      User property: maven.test.skip
      Set this to true to bypass test-jar generation. Its use is NOT
      RECOMMENDED, but quite convenient on occasion.

    skipIfEmpty (Default: false)
      Skip creating empty archives.

    testClassesDirectory (Default:
    ${project.build.testOutputDirectory})
      Required: true
      Directory containing the test classes and resource files that should be
      packaged into the JAR.

    useDefaultManifestFile (Default: false)
      User property: jar.useDefaultManifestFile
      Using this property will fail your build cause it has been removed from
      the plugin configuration. See the Major Version Upgrade to version 3.0.0
      for the plugin.
      Deprecated. For version 3.0.0 this parameter is only defined here
      to break the build if you use it!
```
