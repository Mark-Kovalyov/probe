# clean

```
Name: Maven Clean Plugin
Description: The Maven Clean Plugin is a plugin that removes files generated
  at build-time in a project's directory.
Group Id: org.apache.maven.plugins
Artifact Id: maven-clean-plugin
Version: 2.5
Goal Prefix: clean

This plugin has 2 goals:

clean:clean
  Description: Goal which cleans the build.
    This attempts to clean a project's working directory of the files that were
    generated at build-time. By default, it discovers and deletes the
    directories configured in project.build.directory,
    project.build.outputDirectory, project.build.testOutputDirectory, and
    project.reporting.outputDirectory.
    
    Files outside the default may also be included in the deletion by
    configuring the filesets tag.

clean:help
  Description: Display help information on maven-clean-plugin.
    Call
     mvn clean:help -Ddetail=true -Dgoal=<goal-name>
    to display parameter details.

For more information, run 'mvn help:describe [...] -Ddetail'
```

## Detailed Info

```
Name: Maven Clean Plugin
Description: The Maven Clean Plugin is a plugin that removes files generated
  at build-time in a project's directory.
Group Id: org.apache.maven.plugins
Artifact Id: maven-clean-plugin
Version: 2.5
Goal Prefix: clean

This plugin has 2 goals:

clean:clean
  Description: Goal which cleans the build.
    This attempts to clean a project's working directory of the files that were
    generated at build-time. By default, it discovers and deletes the
    directories configured in project.build.directory,
    project.build.outputDirectory, project.build.testOutputDirectory, and
    project.reporting.outputDirectory.
    
    Files outside the default may also be included in the deletion by
    configuring the filesets tag.
  Implementation: org.apache.maven.plugin.clean.CleanMojo
  Language: java

  Available parameters:

    excludeDefaultDirectories (Default: false)
      User property: clean.excludeDefaultDirectories
      Disables the deletion of the default output directories configured for a
      project. If set to true, only the files/directories selected via the
      parameter filesets will be deleted.

    failOnError (Default: true)
      User property: maven.clean.failOnError
      Indicates whether the build will continue even if there are clean errors.

    filesets
      The list of file sets to delete, in addition to the default directories.
      For example:
      <filesets>
       <fileset>
       <directory>src/main/generated</directory>
       <followSymlinks>false</followSymlinks>
       <useDefaultExcludes>true</useDefaultExcludes>
       <includes>
       <include>*.java</include>
       </includes>
       <excludes>
       <exclude>Template*</exclude>
       </excludes>
       </fileset>
      </filesets>

    followSymLinks (Default: false)
      User property: clean.followSymLinks
      Sets whether the plugin should follow symbolic links while deleting files
      from the default output directories of the project. Not following
      symlinks requires more IO operations and heap memory, regardless whether
      symlinks are actually present. So projects with a huge output directory
      that knowingly does not contain symlinks can improve performance by
      setting this parameter to true.

    retryOnError (Default: true)
      User property: maven.clean.retryOnError
      Indicates whether the plugin should undertake additional attempts (after
      a short delay) to delete a file if the first attempt failed. This is
      meant to help deleting files that are temporarily locked by third-party
      tools like virus scanners or search indexing.

    skip (Default: false)
      User property: clean.skip
      Disables the plugin execution.

    verbose
      User property: clean.verbose
      Sets whether the plugin runs in verbose mode. As of plugin version 2.3,
      the default value is derived from Maven's global debug flag (compare
      command line switch -X).

clean:help
  Description: Display help information on maven-clean-plugin.
    Call
     mvn clean:help -Ddetail=true -Dgoal=<goal-name>
    to display parameter details.
  Implementation: org.apache.maven.plugin.clean.HelpMojo
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
```
