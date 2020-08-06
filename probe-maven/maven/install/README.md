# install

```
Name: Maven Install Plugin
Description: Copies the project artifacts to the user's local repository.
Group Id: org.apache.maven.plugins
Artifact Id: maven-install-plugin
Version: 2.4
Goal Prefix: install

This plugin has 3 goals:

install:help
  Description: Display help information on maven-install-plugin.
    Call mvn install:help -Ddetail=true -Dgoal=<goal-name> to display parameter
    details.

install:install
  Description: Installs the project's main artifact, and any other artifacts
    attached by other plugins in the lifecycle, to the local repository.

install:install-file
  Description: Installs a file in the local repository.

For more information, run 'mvn help:describe [...] -Ddetail'

```

## Detailed Info

```
Name: Maven Install Plugin
Description: Copies the project artifacts to the user's local repository.
Group Id: org.apache.maven.plugins
Artifact Id: maven-install-plugin
Version: 2.4
Goal Prefix: install

This plugin has 3 goals:

install:help
  Description: Display help information on maven-install-plugin.
    Call mvn install:help -Ddetail=true -Dgoal=<goal-name> to display parameter
    details.
  Implementation: org.apache.maven.plugin.install.HelpMojo
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

install:install
  Description: Installs the project's main artifact, and any other artifacts
    attached by other plugins in the lifecycle, to the local repository.
  Implementation: org.apache.maven.plugin.install.InstallMojo
  Language: java
  Bound to phase: install

  Available parameters:

    createChecksum (Default: false)
      User property: createChecksum
      Flag whether to create checksums (MD5, SHA-1) or not.

    skip (Default: false)
      Required: true
      User property: maven.install.skip
      Set this to true to bypass artifact installation. Use this for artifacts
      that does not need to be installed in the local repository.

    updateReleaseInfo (Default: false)
      User property: updateReleaseInfo
      Whether to update the metadata to make the artifact a release version.

install:install-file
  Description: Installs a file in the local repository.
  Implementation: org.apache.maven.plugin.install.InstallFileMojo
  Language: java

  Available parameters:

    artifactId
      User property: artifactId
      ArtifactId of the artifact to be installed. Retrieved from POM file if
      one is specified.

    classifier
      User property: classifier
      Classifier type of the artifact to be installed. For example, 'sources'
      or 'javadoc'. Defaults to none which means this is the project's main
      artifact.

    createChecksum (Default: false)
      User property: createChecksum
      Flag whether to create checksums (MD5, SHA-1) or not.

    file
      Required: true
      User property: file
      The file to be installed in the local repository.

    generatePom
      User property: generatePom
      Generate a minimal POM for the artifact if none is supplied via the
      parameter pomFile. Defaults to true if there is no existing POM in the
      local repository yet.

    groupId
      User property: groupId
      GroupId of the artifact to be installed. Retrieved from POM file if one
      is specified.

    javadoc
      User property: javadoc
      The bundled API docs for the artifact.

    localRepositoryPath
      User property: localRepositoryPath
      The path for a specific local repository directory. If not specified the
      local repository path configured in the Maven settings will be used.

    packaging
      User property: packaging
      Packaging type of the artifact to be installed. Retrieved from POM file
      if one is specified.

    pomFile
      User property: pomFile
      Location of an existing POM file to be installed alongside the main
      artifact, given by the file parameter.

    repositoryLayout (Default: default)
      Required: true
      User property: repositoryLayout
      The type of remote repository layout to install to. Try legacy for a
      Maven 1.x-style repository layout.

    sources
      User property: sources
      The bundled sources for the artifact.

    updateReleaseInfo (Default: false)
      User property: updateReleaseInfo
      Whether to update the metadata to make the artifact a release version.

    version
      User property: version
      Version of the artifact to be installed. Retrieved from POM file if one
      is specified.
```
