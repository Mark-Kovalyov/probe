# dependency

```
Name: Maven Dependency Plugin
Description: Provides utility goals to work with dependencies like copying,
  unpacking, analyzing, resolving and many more.
Group Id: org.apache.maven.plugins
Artifact Id: maven-dependency-plugin
Version: 2.8
Goal Prefix: dependency

This plugin has 21 goals:

dependency:analyze
  Description: Analyzes the dependencies of this project and determines which
    are: used and declared; used and undeclared; unused and declared. This goal
    is intended to be used standalone, thus it always executes the test-compile
    phase - use the dependency:analyze-only goal instead when participating in
    the build lifecycle.
    By default, maven-dependency-analyzer is used to perform the analysis, with
    limitations due to the fact that it works at bytecode level, but any
    analyzer can be plugged in through analyzer parameter.

dependency:analyze-dep-mgt
  Description: This mojo looks at the dependencies after final resolution and
    looks for mismatches in your dependencyManagement section. In versions of
    maven prior to 2.0.6, it was possible to inherit versions that didn't match
    your dependencyManagement. See MNG-1577 for more info. This mojo is also
    useful for just detecting projects that override the dependencyManagement
    directly. Set ignoreDirect to false to detect these otherwise normal
    conditions.

dependency:analyze-duplicate
  Description: Analyzes the <dependencies/> and <dependencyManagement/> tags
    in the pom.xml and determines the duplicate declared dependencies.

dependency:analyze-only
  Description: Analyzes the dependencies of this project and determines which
    are: used and declared; used and undeclared; unused and declared. This goal
    is intended to be used in the build lifecycle, thus it assumes that the
    test-compile phase has been executed - use the dependency:analyze goal
    instead when running standalone.
    By default, maven-dependency-analyzer is used to perform the analysis, with
    limitations due to the fact that it works at bytecode level, but any
    analyzer can be plugged in through analyzer parameter.

dependency:analyze-report
  Description: Analyzes the dependencies of this project and produces a
    report that summarizes which are: used and declared; used and undeclared;
    unused and declared.
  Note: This goal should be used as a Maven report.

dependency:build-classpath
  Description: This goal will output a classpath string of dependencies from
    the local repository to a file or log.

dependency:copy
  Description: Goal that copies a list of artifacts from the repository to
    defined locations.

dependency:copy-dependencies
  Description: Goal that copies the project dependencies from the repository
    to a defined location.

dependency:get
  Description: Resolves a single artifact, eventually transitively, from the
    specified remote repositories. Caveat: will always check the central
    repository defined in the super pom. You could use a mirror entry in your
    settings.xml

dependency:go-offline
  Description: Goal that resolves all project dependencies, including plugins
    and reports and their dependencies.

dependency:help
  Description: Display help information on maven-dependency-plugin.
    Call mvn dependency:help -Ddetail=true -Dgoal=<goal-name> to display
    parameter details.

dependency:list
  Description: Displays the list of dependencies for this project.

dependency:list-repositories
  Description: Goal that resolves all project dependencies and then lists the
    repositories used by the build and by the transitive dependencies

dependency:properties
  Description: Goal that sets a property pointing to the artifact file for
    each project dependency. For each dependency (direct and transitive) a
    project property will be set which follows the
    groupId:artifactId:type:[classifier] form and contains the path to the
    resolved artifact.

dependency:purge-local-repository
  Description: Remove the project dependencies from the local repository, and
    optionally re-resolve them.

dependency:resolve
  Description: Goal that resolves the project dependencies from the
    repository.

dependency:resolve-plugins
  Description: Goal that resolves all project plugins and reports and their
    dependencies.

dependency:sources
  Description: Goal that resolves the project source dependencies from the
    repository.

dependency:tree
  Description: Displays the dependency tree for this project.

dependency:unpack
  Description: Goal that retrieves a list of artifacts from the repository
    and unpacks them in a defined location.

dependency:unpack-dependencies
  Description: Goal that unpacks the project dependencies from the repository
    to a defined location.

For more information, run 'mvn help:describe [...] -Ddetail'
```

## Detailed Info

```
Name: Maven Dependency Plugin
Description: Provides utility goals to work with dependencies like copying,
  unpacking, analyzing, resolving and many more.
Group Id: org.apache.maven.plugins
Artifact Id: maven-dependency-plugin
Version: 2.8
Goal Prefix: dependency

This plugin has 21 goals:

dependency:analyze
  Description: Analyzes the dependencies of this project and determines which
    are: used and declared; used and undeclared; unused and declared. This goal
    is intended to be used standalone, thus it always executes the test-compile
    phase - use the dependency:analyze-only goal instead when participating in
    the build lifecycle.
    By default, maven-dependency-analyzer is used to perform the analysis, with
    limitations due to the fact that it works at bytecode level, but any
    analyzer can be plugged in through analyzer parameter.
  Implementation: org.apache.maven.plugin.dependency.analyze.AnalyzeMojo
  Language: java
  Before this goal executes, it will call:
    Phase: 'test-compile'

  Available parameters:

    analyzer (Default: default)
      User property: analyzer
      Specify the project dependency analyzer to use (plexus component
      role-hint). By default, maven-dependency-analyzer is used. To use this,
      you must declare a dependency for this plugin that contains the code for
      the analyzer. The analyzer must have a declared Plexus role name, and you
      specify the role name here.

    failOnWarning (Default: false)
      User property: failOnWarning
      Whether to fail the build if a dependency warning is found.

    ignoreNonCompile (Default: false)
      User property: ignoreNonCompile
      Ignore Runtime/Provided/Test/System scopes for unused dependency
      analysis.

    outputXML (Default: false)
      User property: outputXML
      Output the xml for the missing dependencies (used but not declared).

    scriptableFlag (Default: $$$%%%)
      User property: scriptableFlag
      Flag to use for scriptable output.

    scriptableOutput (Default: false)
      User property: scriptableOutput
      Output scriptable values for the missing dependencies (used but not
      declared).

    skip (Default: false)
      User property: mdep.analyze.skip
      Skip plugin execution completely.

    usedDependencies
      Force dependencies as used, to override incomplete result caused by
      bytecode-level analysis. Dependency format is groupId:artifactId.

    verbose (Default: false)
      User property: verbose
      Output used dependencies.

dependency:analyze-dep-mgt
  Description: This mojo looks at the dependencies after final resolution and
    looks for mismatches in your dependencyManagement section. In versions of
    maven prior to 2.0.6, it was possible to inherit versions that didn't match
    your dependencyManagement. See MNG-1577 for more info. This mojo is also
    useful for just detecting projects that override the dependencyManagement
    directly. Set ignoreDirect to false to detect these otherwise normal
    conditions.
  Implementation: org.apache.maven.plugin.dependency.analyze.AnalyzeDepMgt
  Language: java

  Available parameters:

    failBuild (Default: false)
      User property: mdep.analyze.failBuild
      Fail the build if a problem is detected.

    ignoreDirect (Default: true)
      User property: mdep.analyze.ignore.direct
      Ignore Direct Dependency Overrides of dependencyManagement section.

    skip (Default: false)
      User property: mdep.analyze.skip
      Skip plugin execution completely.

dependency:analyze-duplicate
  Description: Analyzes the <dependencies/> and <dependencyManagement/> tags
    in the pom.xml and determines the duplicate declared dependencies.
  Implementation:
  org.apache.maven.plugin.dependency.analyze.AnalyzeDuplicateMojo
  Language: java

  Available parameters:

    skip (Default: false)
      User property: mdep.analyze.skip
      Skip plugin execution completely.

dependency:analyze-only
  Description: Analyzes the dependencies of this project and determines which
    are: used and declared; used and undeclared; unused and declared. This goal
    is intended to be used in the build lifecycle, thus it assumes that the
    test-compile phase has been executed - use the dependency:analyze goal
    instead when running standalone.
    By default, maven-dependency-analyzer is used to perform the analysis, with
    limitations due to the fact that it works at bytecode level, but any
    analyzer can be plugged in through analyzer parameter.
  Implementation: org.apache.maven.plugin.dependency.analyze.AnalyzeOnlyMojo
  Language: java
  Bound to phase: verify

  Available parameters:

    analyzer (Default: default)
      User property: analyzer
      Specify the project dependency analyzer to use (plexus component
      role-hint). By default, maven-dependency-analyzer is used. To use this,
      you must declare a dependency for this plugin that contains the code for
      the analyzer. The analyzer must have a declared Plexus role name, and you
      specify the role name here.

    failOnWarning (Default: false)
      User property: failOnWarning
      Whether to fail the build if a dependency warning is found.

    ignoreNonCompile (Default: false)
      User property: ignoreNonCompile
      Ignore Runtime/Provided/Test/System scopes for unused dependency
      analysis.

    outputXML (Default: false)
      User property: outputXML
      Output the xml for the missing dependencies (used but not declared).

    scriptableFlag (Default: $$$%%%)
      User property: scriptableFlag
      Flag to use for scriptable output.

    scriptableOutput (Default: false)
      User property: scriptableOutput
      Output scriptable values for the missing dependencies (used but not
      declared).

    skip (Default: false)
      User property: mdep.analyze.skip
      Skip plugin execution completely.

    usedDependencies
      Force dependencies as used, to override incomplete result caused by
      bytecode-level analysis. Dependency format is groupId:artifactId.

    verbose (Default: false)
      User property: verbose
      Output used dependencies.

dependency:analyze-report
  Description: Analyzes the dependencies of this project and produces a
    report that summarizes which are: used and declared; used and undeclared;
    unused and declared.
  Note: This goal should be used as a Maven report.
  Implementation: org.apache.maven.plugin.dependency.analyze.AnalyzeReportMojo
  Language: java
  Before this goal executes, it will call:
    Phase: 'test-compile'

  Available parameters:

    ignoreNonCompile (Default: false)
      User property: ignoreNonCompile
      Ignore Runtime/Provided/Test/System scopes for unused dependency analysis

    skip (Default: false)
      User property: mdep.analyze.skip
      Skip plugin execution completely.

    usedDependencies
      Force dependencies as used, to override incomplete result caused by
      bytecode-level analysis. Dependency format is groupId:artifactId.

dependency:build-classpath
  Description: This goal will output a classpath string of dependencies from
    the local repository to a file or log.
  Implementation:
  org.apache.maven.plugin.dependency.fromDependencies.BuildClasspathMojo
  Language: java
  Bound to phase: generate-sources

  Available parameters:

    attach (Default: false)
      Attach the classpath file to the main artifact so it can be installed and
      deployed.

    classifier
      User property: classifier
      Specify classifier to look for. Example: sources

    cpFile
      User property: mdep.cpFile
      The file to write the classpath string. If undefined, it just prints the
      classpath as [INFO]. This parameter is deprecated. Use outputFile
      instead.
      Deprecated. use outputFile instead

    excludeArtifactIds
      User property: excludeArtifactIds
      Comma separated list of Artifact names to exclude.

    excludeClassifiers
      User property: excludeClassifiers
      Comma Separated list of Classifiers to exclude. Empty String indicates
      don't exclude anything (default).

    excludeGroupIds
      User property: excludeGroupIds
      Comma separated list of GroupId Names to exclude.

    excludeScope
      User property: excludeScope
      Scope to exclude. An Empty string indicates no scopes (default).

    excludeTransitive (Default: false)
      User property: excludeTransitive
      If we should exclude transitive dependencies

    excludeTypes
      User property: excludeTypes
      Comma Separated list of Types to exclude. Empty String indicates don't
      exclude anything (default).

    fileSeparator
      User property: mdep.fileSeparator
      Override the char used between the paths. This field is initialized to
      contain the first character of the value of the system property
      file.separator. On UNIX systems the value of this field is '/'; on
      Microsoft Windows systems it is '\'. The default is File.separator

    ignorePermissions
      not used in this goal

    includeArtifactIds
      User property: includeArtifactIds
      Comma separated list of Artifact names to include.

    includeClassifiers
      User property: includeClassifiers
      Comma Separated list of Classifiers to include. Empty String indicates
      include everything (default).

    includeGroupIds
      User property: includeGroupIds
      Comma separated list of GroupIds to include.

    includeScope
      User property: includeScope
      Scope to include. An Empty string indicates all scopes (default). The
      scopes being interpreted are the scopes as Maven sees them, not as
      specified in the pom. In summary:
      - runtime scope gives runtime and compile dependencies,
      - compile scope gives compile, provided, and system dependencies,
      - test (default) scope gives all dependencies,
      - provided scope just gives provided dependencies,
      - system scope just gives system dependencies.

    includeTypes
      User property: includeTypes
      Comma Separated list of Types to include. Empty String indicates include
      everything (default).

    localRepoProperty
      User property: mdep.localRepoProperty
      Replace the absolute path to the local repo with this property. This
      field is ignored it prefix is declared. The value will be forced to
      '${M2_REPO}' if no value is provided AND the attach flag is true.

    markersDirectory (Default:
    ${project.build.directory}/dependency-maven-plugin-markers)
      User property: markersDirectory
      Directory to store flag files

    outputAbsoluteArtifactFilename (Default: false)
      User property: outputAbsoluteArtifactFilename
      Output absolute filename for resolved artifacts

    outputFile
      User property: mdep.outputFile
      The file to write the classpath string. If undefined, it just prints the
      classpath as [INFO].

    outputFilterFile (Default: false)
      User property: mdep.outputFilterFile
      Write out the classpath in a format compatible with filtering
      (classpath=xxxxx)

    outputProperty
      User property: mdep.outputProperty
      A property to set to the content of the classpath string.

    overWriteIfNewer (Default: true)
      User property: overWriteIfNewer
      Overwrite artifacts that don't exist or are older than the source.

    overWriteReleases (Default: false)
      User property: overWriteReleases
      Overwrite release artifacts

    overWriteSnapshots (Default: false)
      User property: overWriteSnapshots
      Overwrite snapshot artifacts

    pathSeparator
      User property: mdep.pathSeparator
      Override the char used between path folders. The system-dependent
      path-separator character. This field is initialized to contain the first
      character of the value of the system property path.separator. This
      character is used to separate filenames in a sequence of files given as a
      path list. On UNIX systems, this character is ':'; on Microsoft Windows
      systems it is ';'.

    prefix
      User property: mdep.prefix
      The prefix to prepend on each dependent artifact. If undefined, the paths
      refer to the actual files store in the local repository (the stripVersion
      parameter does nothing then).

    prependGroupId (Default: false)
      User property: mdep.prependGroupId
      Prepend the groupId during copy.

    regenerateFile (Default: false)
      User property: mdep.regenerateFile
      If 'true', it skips the up-to-date-check, and always regenerates the
      classpath file.

    silent (Default: false)
      User property: silent
      If the plugin should be silent.

    skip (Default: false)
      User property: mdep.skip
      Skip plugin execution completely.

    stripClassifier (Default: false)
      User property: mdep.stripClassifier
      Strip artifact classifier during copy (only works if prefix is set)

    stripVersion (Default: false)
      User property: mdep.stripVersion
      Strip artifact version during copy (only works if prefix is set)

    type
      User property: type
      Specify type to look for when constructing artifact based on classifier.
      Example: java-source,jar,war

    useBaseVersion (Default: true)
      User property: mdep.useBaseVersion
      Either append the artifact's baseVersion or uniqueVersion to the
      filename. Will only be used if isStripVersion() is false.

    useJvmChmod
      not used in this goal

dependency:copy
  Description: Goal that copies a list of artifacts from the repository to
    defined locations.
  Implementation: org.apache.maven.plugin.dependency.fromConfiguration.CopyMojo
  Language: java
  Bound to phase: process-sources

  Available parameters:

    artifact
      User property: artifact
      The artifact to copy from commandLine. Use artifactItems within the
      pom-configuration.

    artifactItems
      Collection of ArtifactItems to work on. (ArtifactItem contains groupId,
      artifactId, version, type, classifier, outputDirectory, destFileName and
      overWrite.) See Usage for details.

    ignorePermissions
      not used in this goal

    localRepositoryDirectory
      Path to override default local repository during plugin's execution. To
      remove all downloaded artifacts as part of the build, set this value to a
      location under your project's target directory

    outputAbsoluteArtifactFilename (Default: false)
      User property: outputAbsoluteArtifactFilename
      Output absolute filename for resolved artifacts

    outputDirectory (Default:
    ${project.build.directory}/dependency)
      User property: outputDirectory
      Default output location used for mojo, unless overridden in ArtifactItem.

    overWriteIfNewer (Default: true)
      User property: mdep.overIfNewer
      Overwrite if newer

    overWriteReleases (Default: false)
      User property: mdep.overWriteReleases
      Overwrite release artifacts

    overWriteSnapshots (Default: false)
      User property: mdep.overWriteSnapshots
      Overwrite snapshot artifacts

    prependGroupId (Default: false)
      User property: mdep.prependGroupId
      Prepend artifact groupId during copy

    silent (Default: false)
      User property: silent
      If the plugin should be silent.

    skip (Default: false)
      User property: mdep.skip
      Skip plugin execution completely.

    stripClassifier (Default: false)
      User property: mdep.stripClassifier
      Strip artifact classifier during copy

    stripVersion (Default: false)
      User property: mdep.stripVersion
      Strip artifact version during copy

    useBaseVersion (Default: false)
      User property: mdep.useBaseVersion
      Use artifact baseVersion during copy

    useJvmChmod
      not used in this goal

dependency:copy-dependencies
  Description: Goal that copies the project dependencies from the repository
    to a defined location.
  Implementation:
  org.apache.maven.plugin.dependency.fromDependencies.CopyDependenciesMojo
  Language: java
  Bound to phase: process-sources

  Available parameters:

    addParentPoms (Default: false)
      Add parent poms to the list of copied dependencies (both current project
      pom parents and dependencies parents).

    classifier
      User property: classifier
      Specify classifier to look for. Example: sources

    copyPom (Default: false)
      User property: mdep.copyPom
      Also copy the pom of each artifact.

    excludeArtifactIds
      User property: excludeArtifactIds
      Comma separated list of Artifact names to exclude.

    excludeClassifiers
      User property: excludeClassifiers
      Comma Separated list of Classifiers to exclude. Empty String indicates
      don't exclude anything (default).

    excludeGroupIds
      User property: excludeGroupIds
      Comma separated list of GroupId Names to exclude.

    excludeScope
      User property: excludeScope
      Scope to exclude. An Empty string indicates no scopes (default).

    excludeTransitive (Default: false)
      User property: excludeTransitive
      If we should exclude transitive dependencies

    excludeTypes
      User property: excludeTypes
      Comma Separated list of Types to exclude. Empty String indicates don't
      exclude anything (default).

    failOnMissingClassifierArtifact (Default: false)
      User property: mdep.failOnMissingClassifierArtifact
      This only applies if the classifier parameter is used.

    ignorePermissions
      not used in this goal

    includeArtifactIds
      User property: includeArtifactIds
      Comma separated list of Artifact names to include.

    includeClassifiers
      User property: includeClassifiers
      Comma Separated list of Classifiers to include. Empty String indicates
      include everything (default).

    includeGroupIds
      User property: includeGroupIds
      Comma separated list of GroupIds to include.

    includeScope
      User property: includeScope
      Scope to include. An Empty string indicates all scopes (default). The
      scopes being interpreted are the scopes as Maven sees them, not as
      specified in the pom. In summary:
      - runtime scope gives runtime and compile dependencies,
      - compile scope gives compile, provided, and system dependencies,
      - test (default) scope gives all dependencies,
      - provided scope just gives provided dependencies,
      - system scope just gives system dependencies.

    includeTypes
      User property: includeTypes
      Comma Separated list of Types to include. Empty String indicates include
      everything (default).

    markersDirectory (Default:
    ${project.build.directory}/dependency-maven-plugin-markers)
      User property: markersDirectory
      Directory to store flag files

    outputAbsoluteArtifactFilename (Default: false)
      User property: outputAbsoluteArtifactFilename
      Output absolute filename for resolved artifacts

    outputDirectory (Default:
    ${project.build.directory}/dependency)
      User property: outputDirectory
      Output location.

    overWriteIfNewer (Default: true)
      User property: overWriteIfNewer
      Overwrite artifacts that don't exist or are older than the source.

    overWriteReleases (Default: false)
      User property: overWriteReleases
      Overwrite release artifacts

    overWriteSnapshots (Default: false)
      User property: overWriteSnapshots
      Overwrite snapshot artifacts

    prependGroupId (Default: false)
      User property: mdep.prependGroupId
      Prepend the groupId during copy.

    silent (Default: false)
      User property: silent
      If the plugin should be silent.

    skip (Default: false)
      User property: mdep.skip
      Skip plugin execution completely.

    stripClassifier (Default: false)
      User property: mdep.stripClassifier
      Strip artifact classifier during copy

    stripVersion (Default: false)
      User property: mdep.stripVersion
      Strip artifact version during copy

    type
      User property: type
      Specify type to look for when constructing artifact based on classifier.
      Example: java-source,jar,war

    useBaseVersion (Default: true)
      User property: mdep.useBaseVersion
      Either append the artifact's baseVersion or uniqueVersion to the
      filename. Will only be used if isStripVersion() is false.

    useJvmChmod
      not used in this goal

    useRepositoryLayout (Default: false)
      User property: mdep.useRepositoryLayout
      Place each artifact in the same directory layout as a default repository.
      example: /outputDirectory/junit/junit/3.8.1/junit-3.8.1.jar

    useSubDirectoryPerArtifact (Default: false)
      User property: mdep.useSubDirectoryPerArtifact
      Place each file in a separate subdirectory. (example
      /outputDirectory/junit-3.8.1-jar)

    useSubDirectoryPerScope (Default: false)
      User property: mdep.useSubDirectoryPerScope
      Place each type of file in a separate subdirectory. (example
      /outputDirectory/runtime /outputDirectory/provided etc)

    useSubDirectoryPerType (Default: false)
      User property: mdep.useSubDirectoryPerType
      Place each type of file in a separate subdirectory. (example
      /outputDirectory/jars /outputDirectory/wars etc)

dependency:get
  Description: Resolves a single artifact, eventually transitively, from the
    specified remote repositories. Caveat: will always check the central
    repository defined in the super pom. You could use a mirror entry in your
    settings.xml
  Implementation: org.apache.maven.plugin.dependency.GetMojo
  Language: java

  Available parameters:

    artifact
      User property: artifact
      A string of the form groupId:artifactId:version[:packaging][:classifier].

    artifactId
      User property: artifactId
      The artifactId of the artifact to download. Ignored if artifact is used.

    classifier
      User property: classifier
      The classifier of the artifact to download. Ignored if artifact is used.

    destination
      User property: dest
      The destination file or directory to copy the artifact to, if other than
      the local repository
      Deprecated. if you need to copy the resolved artifact, use
      dependency:copy

    groupId
      User property: groupId
      The groupId of the artifact to download. Ignored if artifact is used.

    packaging (Default: jar)
      User property: packaging
      The packaging of the artifact to download. Ignored if artifact is used.

    remoteRepositories
      User property: remoteRepositories
      Repositories in the format id::[layout]::url or just url, separated by
      comma. ie.
      central::default::http://repo1.maven.apache.org/maven2,myrepo::::http://repo.acme.com,http://repo.acme2.com

    repositoryId (Default: temp)
      User property: repoId
      The id of the repository from which we'll download the artifact
      Deprecated. Use remoteRepositories

    repositoryUrl
      User property: repoUrl
      The url of the repository from which we'll download the artifact.
      DEPRECATED Use remoteRepositories
      Deprecated. Use remoteRepositories

    skip (Default: false)
      User property: mdep.skip
      Skip plugin execution completely.

    transitive (Default: true)
      User property: transitive
      Download transitively, retrieving the specified artifact and all of its
      dependencies.

    version
      User property: version
      The version of the artifact to download. Ignored if artifact is used.

dependency:go-offline
  Description: Goal that resolves all project dependencies, including plugins
    and reports and their dependencies.
  Implementation: org.apache.maven.plugin.dependency.resolvers.GoOfflineMojo
  Language: java
  Before this goal executes, it will call:
    Single goal: 'resolve-plugins'

  Available parameters:

    appendOutput (Default: false)
      User property: appendOutput
      Whether to append outputs into the output file or overwrite it.

    classifier
      User property: classifier
      Specify classifier to look for. Example: sources

    excludeArtifactIds
      User property: excludeArtifactIds
      Comma separated list of Artifact names to exclude.

    excludeClassifiers
      User property: excludeClassifiers
      Comma Separated list of Classifiers to exclude. Empty String indicates
      don't exclude anything (default).

    excludeGroupIds
      User property: excludeGroupIds
      Comma separated list of GroupId Names to exclude.

    excludeReactor (Default: true)
      User property: excludeReactor
      Don't resolve plugins that are in the current reactor. Only works for
      plugins at the moment.

    excludeScope
      User property: excludeScope
      Scope to exclude. An Empty string indicates no scopes (default).

    excludeTransitive (Default: false)
      User property: excludeTransitive
      If we should exclude transitive dependencies

    excludeTypes
      User property: excludeTypes
      Comma Separated list of Types to exclude. Empty String indicates don't
      exclude anything (default).

    ignorePermissions
      not used in this goal

    includeArtifactIds
      User property: includeArtifactIds
      Comma separated list of Artifact names to include.

    includeClassifiers
      User property: includeClassifiers
      Comma Separated list of Classifiers to include. Empty String indicates
      include everything (default).

    includeGroupIds
      User property: includeGroupIds
      Comma separated list of GroupIds to include.

    includeScope
      User property: includeScope
      Scope to include. An Empty string indicates all scopes (default). The
      scopes being interpreted are the scopes as Maven sees them, not as
      specified in the pom. In summary:
      - runtime scope gives runtime and compile dependencies,
      - compile scope gives compile, provided, and system dependencies,
      - test (default) scope gives all dependencies,
      - provided scope just gives provided dependencies,
      - system scope just gives system dependencies.

    includeTypes
      User property: includeTypes
      Comma Separated list of Types to include. Empty String indicates include
      everything (default).

    markersDirectory (Default:
    ${project.build.directory}/dependency-maven-plugin-markers)
      User property: markersDirectory
      Directory to store flag files

    outputAbsoluteArtifactFilename (Default: false)
      User property: outputAbsoluteArtifactFilename
      Output absolute filename for resolved artifacts

    outputFile
      User property: outputFile
      If specified, this parameter will cause the dependencies to be written to
      the path specified, instead of writing to the console.

    overWriteIfNewer (Default: true)
      User property: overWriteIfNewer
      Overwrite artifacts that don't exist or are older than the source.

    overWriteReleases (Default: false)
      User property: overWriteReleases
      Overwrite release artifacts

    overWriteSnapshots (Default: false)
      User property: overWriteSnapshots
      Overwrite snapshot artifacts

    prependGroupId (Default: false)
      User property: mdep.prependGroupId
      Prepend the groupId during copy.

    silent (Default: false)
      User property: silent
      If the plugin should be silent.

    skip (Default: false)
      User property: mdep.skip
      Skip plugin execution completely.

    type
      User property: type
      Specify type to look for when constructing artifact based on classifier.
      Example: java-source,jar,war

    useJvmChmod
      not used in this goal

dependency:help
  Description: Display help information on maven-dependency-plugin.
    Call mvn dependency:help -Ddetail=true -Dgoal=<goal-name> to display
    parameter details.
  Implementation: org.apache.maven.plugin.dependency.resolvers.HelpMojo
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

dependency:list
  Description: Displays the list of dependencies for this project.
  Implementation: org.apache.maven.plugin.dependency.resolvers.ListMojo
  Language: java

  Available parameters:

    appendOutput (Default: false)
      User property: appendOutput
      Whether to append outputs into the output file or overwrite it.

    classifier
      User property: classifier
      Specify classifier to look for. Example: sources

    excludeArtifactIds
      User property: excludeArtifactIds
      Comma separated list of Artifact names to exclude.

    excludeClassifiers
      User property: excludeClassifiers
      Comma Separated list of Classifiers to exclude. Empty String indicates
      don't exclude anything (default).

    excludeGroupIds
      User property: excludeGroupIds
      Comma separated list of GroupId Names to exclude.

    excludeReactor (Default: true)
      User property: excludeReactor
      Don't resolve plugins that are in the current reactor. Only works for
      plugins at the moment.

    excludeScope
      User property: excludeScope
      Scope to exclude. An Empty string indicates no scopes (default).

    excludeTransitive (Default: false)
      User property: excludeTransitive
      If we should exclude transitive dependencies

    excludeTypes
      User property: excludeTypes
      Comma Separated list of Types to exclude. Empty String indicates don't
      exclude anything (default).

    ignorePermissions
      not used in this goal

    includeArtifactIds
      User property: includeArtifactIds
      Comma separated list of Artifact names to include.

    includeClassifiers
      User property: includeClassifiers
      Comma Separated list of Classifiers to include. Empty String indicates
      include everything (default).

    includeGroupIds
      User property: includeGroupIds
      Comma separated list of GroupIds to include.

    includeParents (Default: false)
      User property: includeParents
      Include parent poms in the dependency resolution list.

    includeScope
      User property: includeScope
      Scope to include. An Empty string indicates all scopes (default). The
      scopes being interpreted are the scopes as Maven sees them, not as
      specified in the pom. In summary:
      - runtime scope gives runtime and compile dependencies,
      - compile scope gives compile, provided, and system dependencies,
      - test (default) scope gives all dependencies,
      - provided scope just gives provided dependencies,
      - system scope just gives system dependencies.

    includeTypes
      User property: includeTypes
      Comma Separated list of Types to include. Empty String indicates include
      everything (default).

    markersDirectory (Default:
    ${project.build.directory}/dependency-maven-plugin-markers)
      User property: markersDirectory
      Directory to store flag files

    outputAbsoluteArtifactFilename (Default: false)
      User property: outputAbsoluteArtifactFilename
      Output absolute filename for resolved artifacts

    outputFile
      User property: outputFile
      If specified, this parameter will cause the dependencies to be written to
      the path specified, instead of writing to the console.

    outputScope (Default: true)
      User property: mdep.outputScope
      If we should display the scope when resolving

    overWriteIfNewer (Default: true)
      User property: overWriteIfNewer
      Overwrite artifacts that don't exist or are older than the source.

    overWriteReleases (Default: false)
      User property: overWriteReleases
      Overwrite release artifacts

    overWriteSnapshots (Default: false)
      User property: overWriteSnapshots
      Overwrite snapshot artifacts

    prependGroupId (Default: false)
      User property: mdep.prependGroupId
      Prepend the groupId during copy.

    silent (Default: false)
      User property: silent
      If the plugin should be silent.

    skip (Default: false)
      User property: mdep.skip
      Skip plugin execution completely.

    sort (Default: false)
      User property: sort
      Sort the output list of resolved artifacts alphabetically. The default
      ordering matches the classpath order.

    type
      User property: type
      Specify type to look for when constructing artifact based on classifier.
      Example: java-source,jar,war

    useJvmChmod
      not used in this goal

dependency:list-repositories
  Description: Goal that resolves all project dependencies and then lists the
    repositories used by the build and by the transitive dependencies
  Implementation:
  org.apache.maven.plugin.dependency.resolvers.ListRepositoriesMojo
  Language: java

  Available parameters:

    ignorePermissions (Default: false)
      User property: dependency.ignorePermissions
      ignore to set file permissions when unpacking a dependency

    outputAbsoluteArtifactFilename (Default: false)
      User property: outputAbsoluteArtifactFilename
      Output absolute filename for resolved artifacts

    silent (Default: false)
      User property: silent
      If the plugin should be silent.

    skip (Default: false)
      User property: mdep.skip
      Skip plugin execution completely.

    useJvmChmod (Default: true)
      User property: dependency.useJvmChmod
      will use the jvm chmod, this is available for user and all level group
      level will be ignored
      since 2.6 is on by default

dependency:properties
  Description: Goal that sets a property pointing to the artifact file for
    each project dependency. For each dependency (direct and transitive) a
    project property will be set which follows the
    groupId:artifactId:type:[classifier] form and contains the path to the
    resolved artifact.
  Implementation: org.apache.maven.plugin.dependency.PropertiesMojo
  Language: java
  Bound to phase: initialize

  Available parameters:

    skip (Default: false)
      User property: mdep.skip
      Skip plugin execution completely.

dependency:purge-local-repository
  Description: Remove the project dependencies from the local repository, and
    optionally re-resolve them.
  Implementation: org.apache.maven.plugin.dependency.PurgeLocalRepositoryMojo
  Language: java

  Available parameters:

    actTransitively (Default: true)
      User property: actTransitively
      Whether this mojo should act on all transitive dependencies. Default
      value is true.

    exclude
      User property: exclude
      Comma-separated list of groupId:artifactId entries, which should be used
      to exclude artifacts from deletion/refresh. This is a command-line
      alternative to the excludes parameter, since List parameters are not
      currently compatible with CLI specification.

    excludes
      The list of dependencies in the form of groupId:artifactId which should
      NOT be deleted/refreshed.

    include
      User property: include
      Comma-separated list of groupId:artifactId entries, which should be used
      to include artifacts for deletion/refresh. This is a command-line
      alternative to the includes parameter, since List parameters are not
      currently compatible with CLI specification.

    includes
      The list of dependencies in the form of groupId:artifactId which should
      BE deleted/refreshed.

    manualInclude
      User property: manualInclude
      Comma-separated list of groupId:artifactId entries, which should be used
      to manually include artifacts for deletion. This is a command-line
      alternative to the manualIncludes parameter, since List parameters are
      not currently compatible with CLI specification.

    manualIncludes
      The list of dependencies in the form of groupId:artifactId which should
      BE deleted/purged from the local repository. Note that using this
      parameter will deactivate the normal process for purging the current
      project dependency tree. If this parameter is used, only the included
      artifacts will be purged. The manualIncludes parameter should not be used
      in combination with the includes/excludes parameters.

    reResolve (Default: true)
      User property: reResolve
      Whether to re-resolve the artifacts once they have been deleted from the
      local repository. If you are running this mojo from the command-line, you
      may want to disable this. By default, artifacts will be re-resolved.

    resolutionFuzziness (Default: version)
      User property: resolutionFuzziness
      Determines how liberally the plugin will delete an artifact from the
      local repository. Values are:
      
      - file - Eliminate only the artifact's file.
      - version (default) - Eliminate all files associated with the version of
        the artifact.
      - artifactId - Eliminate all files associated with the artifact's
        artifactId.
      - groupId - Eliminate all files associated with the artifact's groupId.

    skip (Default: false)
      User property: skip
      Skip plugin execution completely.

    snapshotsOnly (Default: false)
      User property: snapshotsOnly
      Whether to purge only snapshot artifacts.

    verbose (Default: false)
      User property: verbose
      Whether this plugin should output verbose messages. Default is false.

dependency:resolve
  Description: Goal that resolves the project dependencies from the
    repository.
  Implementation:
  org.apache.maven.plugin.dependency.resolvers.ResolveDependenciesMojo
  Language: java
  Bound to phase: generate-sources

  Available parameters:

    appendOutput (Default: false)
      User property: appendOutput
      Whether to append outputs into the output file or overwrite it.

    classifier
      User property: classifier
      Specify classifier to look for. Example: sources

    excludeArtifactIds
      User property: excludeArtifactIds
      Comma separated list of Artifact names to exclude.

    excludeClassifiers
      User property: excludeClassifiers
      Comma Separated list of Classifiers to exclude. Empty String indicates
      don't exclude anything (default).

    excludeGroupIds
      User property: excludeGroupIds
      Comma separated list of GroupId Names to exclude.

    excludeReactor (Default: true)
      User property: excludeReactor
      Don't resolve plugins that are in the current reactor. Only works for
      plugins at the moment.

    excludeScope
      User property: excludeScope
      Scope to exclude. An Empty string indicates no scopes (default).

    excludeTransitive (Default: false)
      User property: excludeTransitive
      If we should exclude transitive dependencies

    excludeTypes
      User property: excludeTypes
      Comma Separated list of Types to exclude. Empty String indicates don't
      exclude anything (default).

    ignorePermissions
      not used in this goal

    includeArtifactIds
      User property: includeArtifactIds
      Comma separated list of Artifact names to include.

    includeClassifiers
      User property: includeClassifiers
      Comma Separated list of Classifiers to include. Empty String indicates
      include everything (default).

    includeGroupIds
      User property: includeGroupIds
      Comma separated list of GroupIds to include.

    includeParents (Default: false)
      User property: includeParents
      Include parent poms in the dependency resolution list.

    includeScope
      User property: includeScope
      Scope to include. An Empty string indicates all scopes (default). The
      scopes being interpreted are the scopes as Maven sees them, not as
      specified in the pom. In summary:
      - runtime scope gives runtime and compile dependencies,
      - compile scope gives compile, provided, and system dependencies,
      - test (default) scope gives all dependencies,
      - provided scope just gives provided dependencies,
      - system scope just gives system dependencies.

    includeTypes
      User property: includeTypes
      Comma Separated list of Types to include. Empty String indicates include
      everything (default).

    markersDirectory (Default:
    ${project.build.directory}/dependency-maven-plugin-markers)
      User property: markersDirectory
      Directory to store flag files

    outputAbsoluteArtifactFilename (Default: false)
      User property: outputAbsoluteArtifactFilename
      Output absolute filename for resolved artifacts

    outputFile
      User property: outputFile
      If specified, this parameter will cause the dependencies to be written to
      the path specified, instead of writing to the console.

    outputScope (Default: true)
      User property: mdep.outputScope
      If we should display the scope when resolving

    overWriteIfNewer (Default: true)
      User property: overWriteIfNewer
      Overwrite artifacts that don't exist or are older than the source.

    overWriteReleases (Default: false)
      User property: overWriteReleases
      Overwrite release artifacts

    overWriteSnapshots (Default: false)
      User property: overWriteSnapshots
      Overwrite snapshot artifacts

    prependGroupId (Default: false)
      User property: mdep.prependGroupId
      Prepend the groupId during copy.

    silent (Default: false)
      User property: silent
      If the plugin should be silent.

    skip (Default: false)
      User property: mdep.skip
      Skip plugin execution completely.

    sort (Default: false)
      User property: sort
      Sort the output list of resolved artifacts alphabetically. The default
      ordering matches the classpath order.

    type
      User property: type
      Specify type to look for when constructing artifact based on classifier.
      Example: java-source,jar,war

    useJvmChmod
      not used in this goal

dependency:resolve-plugins
  Description: Goal that resolves all project plugins and reports and their
    dependencies.
  Implementation:
  org.apache.maven.plugin.dependency.resolvers.ResolvePluginsMojo
  Language: java
  Bound to phase: generate-sources

  Available parameters:

    appendOutput (Default: false)
      User property: appendOutput
      Whether to append outputs into the output file or overwrite it.

    classifier
      User property: classifier
      Specify classifier to look for. Example: sources

    excludeArtifactIds
      User property: excludeArtifactIds
      Comma separated list of Artifact names to exclude.

    excludeClassifiers
      User property: excludeClassifiers
      Comma Separated list of Classifiers to exclude. Empty String indicates
      don't exclude anything (default).

    excludeGroupIds
      User property: excludeGroupIds
      Comma separated list of GroupId Names to exclude.

    excludeReactor (Default: true)
      User property: excludeReactor
      Don't resolve plugins that are in the current reactor. Only works for
      plugins at the moment.

    excludeScope
      User property: excludeScope
      Scope to exclude. An Empty string indicates no scopes (default).

    excludeTransitive (Default: false)
      User property: excludeTransitive
      If we should exclude transitive dependencies

    excludeTypes
      User property: excludeTypes
      Comma Separated list of Types to exclude. Empty String indicates don't
      exclude anything (default).

    ignorePermissions
      not used in this goal

    includeArtifactIds
      User property: includeArtifactIds
      Comma separated list of Artifact names to include.

    includeClassifiers
      User property: includeClassifiers
      Comma Separated list of Classifiers to include. Empty String indicates
      include everything (default).

    includeGroupIds
      User property: includeGroupIds
      Comma separated list of GroupIds to include.

    includeScope
      User property: includeScope
      Scope to include. An Empty string indicates all scopes (default). The
      scopes being interpreted are the scopes as Maven sees them, not as
      specified in the pom. In summary:
      - runtime scope gives runtime and compile dependencies,
      - compile scope gives compile, provided, and system dependencies,
      - test (default) scope gives all dependencies,
      - provided scope just gives provided dependencies,
      - system scope just gives system dependencies.

    includeTypes
      User property: includeTypes
      Comma Separated list of Types to include. Empty String indicates include
      everything (default).

    markersDirectory (Default:
    ${project.build.directory}/dependency-maven-plugin-markers)
      User property: markersDirectory
      Directory to store flag files

    outputAbsoluteArtifactFilename (Default: false)
      User property: outputAbsoluteArtifactFilename
      Output absolute filename for resolved artifacts

    outputFile
      User property: outputFile
      If specified, this parameter will cause the dependencies to be written to
      the path specified, instead of writing to the console.

    overWriteIfNewer (Default: true)
      User property: overWriteIfNewer
      Overwrite artifacts that don't exist or are older than the source.

    overWriteReleases (Default: false)
      User property: overWriteReleases
      Overwrite release artifacts

    overWriteSnapshots (Default: false)
      User property: overWriteSnapshots
      Overwrite snapshot artifacts

    prependGroupId (Default: false)
      User property: mdep.prependGroupId
      Prepend the groupId during copy.

    silent (Default: false)
      User property: silent
      If the plugin should be silent.

    skip (Default: false)
      User property: mdep.skip
      Skip plugin execution completely.

    type
      User property: type
      Specify type to look for when constructing artifact based on classifier.
      Example: java-source,jar,war

    useJvmChmod
      not used in this goal

dependency:sources
  Description: Goal that resolves the project source dependencies from the
    repository.
  Implementation:
  org.apache.maven.plugin.dependency.resolvers.ResolveDependencySourcesMojo
  Language: java
  Bound to phase: generate-sources

  Available parameters:

    appendOutput (Default: false)
      User property: appendOutput
      Whether to append outputs into the output file or overwrite it.

    classifier
      User property: classifier
      Specify classifier to look for. Example: sources

    excludeArtifactIds
      User property: excludeArtifactIds
      Comma separated list of Artifact names to exclude.

    excludeClassifiers
      User property: excludeClassifiers
      Comma Separated list of Classifiers to exclude. Empty String indicates
      don't exclude anything (default).

    excludeGroupIds
      User property: excludeGroupIds
      Comma separated list of GroupId Names to exclude.

    excludeReactor (Default: true)
      User property: excludeReactor
      Don't resolve plugins that are in the current reactor. Only works for
      plugins at the moment.

    excludeScope
      User property: excludeScope
      Scope to exclude. An Empty string indicates no scopes (default).

    excludeTransitive (Default: false)
      User property: excludeTransitive
      If we should exclude transitive dependencies

    excludeTypes
      User property: excludeTypes
      Comma Separated list of Types to exclude. Empty String indicates don't
      exclude anything (default).

    ignorePermissions
      not used in this goal

    includeArtifactIds
      User property: includeArtifactIds
      Comma separated list of Artifact names to include.

    includeClassifiers
      User property: includeClassifiers
      Comma Separated list of Classifiers to include. Empty String indicates
      include everything (default).

    includeGroupIds
      User property: includeGroupIds
      Comma separated list of GroupIds to include.

    includeParents (Default: false)
      User property: includeParents
      Include parent poms in the dependency resolution list.

    includeScope
      User property: includeScope
      Scope to include. An Empty string indicates all scopes (default). The
      scopes being interpreted are the scopes as Maven sees them, not as
      specified in the pom. In summary:
      - runtime scope gives runtime and compile dependencies,
      - compile scope gives compile, provided, and system dependencies,
      - test (default) scope gives all dependencies,
      - provided scope just gives provided dependencies,
      - system scope just gives system dependencies.

    includeTypes
      User property: includeTypes
      Comma Separated list of Types to include. Empty String indicates include
      everything (default).

    markersDirectory (Default:
    ${project.build.directory}/dependency-maven-plugin-markers)
      User property: markersDirectory
      Directory to store flag files

    outputAbsoluteArtifactFilename (Default: false)
      User property: outputAbsoluteArtifactFilename
      Output absolute filename for resolved artifacts

    outputFile
      User property: outputFile
      If specified, this parameter will cause the dependencies to be written to
      the path specified, instead of writing to the console.

    outputScope (Default: true)
      User property: mdep.outputScope
      If we should display the scope when resolving

    overWriteIfNewer (Default: true)
      User property: overWriteIfNewer
      Overwrite artifacts that don't exist or are older than the source.

    overWriteReleases (Default: false)
      User property: overWriteReleases
      Overwrite release artifacts

    overWriteSnapshots (Default: false)
      User property: overWriteSnapshots
      Overwrite snapshot artifacts

    prependGroupId (Default: false)
      User property: mdep.prependGroupId
      Prepend the groupId during copy.

    silent (Default: false)
      User property: silent
      If the plugin should be silent.

    skip (Default: false)
      User property: mdep.skip
      Skip plugin execution completely.

    sort (Default: false)
      User property: sort
      Sort the output list of resolved artifacts alphabetically. The default
      ordering matches the classpath order.

    type
      User property: type
      Specify type to look for when constructing artifact based on classifier.
      Example: java-source,jar,war

    useJvmChmod
      not used in this goal

dependency:tree
  Description: Displays the dependency tree for this project.
  Implementation: org.apache.maven.plugin.dependency.tree.TreeMojo
  Language: java

  Available parameters:

    appendOutput (Default: false)
      User property: appendOutput
      Whether to append outputs into the output file or overwrite it.

    excludes
      User property: excludes
      A comma-separated list of artifacts to filter from the serialized
      dependency tree, or null not to filter any artifacts from the dependency
      tree. The filter syntax is:
      [groupId]:[artifactId]:[type]:[version]
      where each pattern segment is optional and supports full and partial *
      wildcards. An empty pattern segment is treated as an implicit wildcard.
      For example, org.apache.* will match all artifacts whose group id starts
      with org.apache., and :::*-SNAPSHOT will match all snapshot artifacts.

    includes
      User property: includes
      A comma-separated list of artifacts to filter the serialized dependency
      tree by, or null not to filter the dependency tree. The filter syntax is:
      [groupId]:[artifactId]:[type]:[version]
      where each pattern segment is optional and supports full and partial *
      wildcards. An empty pattern segment is treated as an implicit wildcard.
      For example, org.apache.* will match all artifacts whose group id starts
      with org.apache., and :::*-SNAPSHOT will match all snapshot artifacts.

    output
      User property: output
      If specified, this parameter will cause the dependency tree to be written
      to the path specified, instead of writing to the console.
      Deprecated. use outputFile instead.

    outputFile
      User property: outputFile
      If specified, this parameter will cause the dependency tree to be written
      to the path specified, instead of writing to the console.

    outputType (Default: text)
      User property: outputType
      If specified, this parameter will cause the dependency tree to be written
      using the specified format. Currently supported format are: text, dot,
      graphml and tgf. These formats can be plotted to image files. An example
      of how to plot a dot file using pygraphviz can be found here.

    scope
      User property: scope
      The scope to filter by when resolving the dependency tree, or null to
      include dependencies from all scopes. Note that this feature does not
      currently work due to MNG-3236.

    skip (Default: false)
      User property: skip
      Skip plugin execution completely.

    tokens (Default: standard)
      User property: tokens
      The token set name to use when outputting the dependency tree. Possible
      values are whitespace, standard or extended, which use whitespace,
      standard (ie ASCII) or extended character sets respectively.

    verbose (Default: false)
      User property: verbose
      Whether to include omitted nodes in the serialized dependency tree.

dependency:unpack
  Description: Goal that retrieves a list of artifacts from the repository
    and unpacks them in a defined location.
  Implementation:
  org.apache.maven.plugin.dependency.fromConfiguration.UnpackMojo
  Language: java
  Bound to phase: process-sources

  Available parameters:

    artifact
      User property: artifact
      The artifact to unpack from commandLine. Use artifactItems within the
      pom-configuration.

    artifactItems
      Collection of ArtifactItems to work on. (ArtifactItem contains groupId,
      artifactId, version, type, classifier, outputDirectory, destFileName and
      overWrite.) See Usage for details.

    excludes
      User property: mdep.unpack.excludes
      A comma separated list of file patterns to exclude when unpacking the
      artifact. i.e. **\/*.xml,**\/*.properties NOTE: Excludes patterns
      override the includes. (component code = return isIncluded( name ) AND
      !isExcluded( name );)

    ignorePermissions (Default: false)
      User property: dependency.ignorePermissions
      ignore to set file permissions when unpacking a dependency

    includes
      User property: mdep.unpack.includes
      A comma separated list of file patterns to include when unpacking the
      artifact. i.e. **\/*.xml,**\/*.properties NOTE: Excludes patterns
      override the includes. (component code = return isIncluded( name ) AND
      !isExcluded( name );)

    localRepositoryDirectory
      Path to override default local repository during plugin's execution. To
      remove all downloaded artifacts as part of the build, set this value to a
      location under your project's target directory

    markersDirectory (Default:
    ${project.build.directory}/dependency-maven-plugin-markers)
      Directory to store flag files after unpack

    outputAbsoluteArtifactFilename (Default: false)
      User property: outputAbsoluteArtifactFilename
      Output absolute filename for resolved artifacts

    outputDirectory (Default:
    ${project.build.directory}/dependency)
      User property: outputDirectory
      Default output location used for mojo, unless overridden in ArtifactItem.

    overWriteIfNewer (Default: true)
      User property: mdep.overIfNewer
      Overwrite if newer

    overWriteReleases (Default: false)
      User property: mdep.overWriteReleases
      Overwrite release artifacts

    overWriteSnapshots (Default: false)
      User property: mdep.overWriteSnapshots
      Overwrite snapshot artifacts

    silent (Default: false)
      User property: silent
      If the plugin should be silent.

    skip (Default: false)
      User property: mdep.skip
      Skip plugin execution completely.

    useJvmChmod (Default: true)
      User property: dependency.useJvmChmod
      will use the jvm chmod, this is available for user and all level group
      level will be ignored
      since 2.6 is on by default

dependency:unpack-dependencies
  Description: Goal that unpacks the project dependencies from the repository
    to a defined location.
  Implementation:
  org.apache.maven.plugin.dependency.fromDependencies.UnpackDependenciesMojo
  Language: java
  Bound to phase: process-sources

  Available parameters:

    classifier
      User property: classifier
      Specify classifier to look for. Example: sources

    excludeArtifactIds
      User property: excludeArtifactIds
      Comma separated list of Artifact names to exclude.

    excludeClassifiers
      User property: excludeClassifiers
      Comma Separated list of Classifiers to exclude. Empty String indicates
      don't exclude anything (default).

    excludeGroupIds
      User property: excludeGroupIds
      Comma separated list of GroupId Names to exclude.

    excludes
      User property: mdep.unpack.excludes
      A comma separated list of file patterns to exclude when unpacking the
      artifact. i.e. **\/*.xml,**\/*.properties NOTE: Excludes patterns
      override the includes. (component code = return isIncluded( name ) AND
      !isExcluded( name );)

    excludeScope
      User property: excludeScope
      Scope to exclude. An Empty string indicates no scopes (default).

    excludeTransitive (Default: false)
      User property: excludeTransitive
      If we should exclude transitive dependencies

    excludeTypes
      User property: excludeTypes
      Comma Separated list of Types to exclude. Empty String indicates don't
      exclude anything (default).

    failOnMissingClassifierArtifact (Default: false)
      User property: mdep.failOnMissingClassifierArtifact
      This only applies if the classifier parameter is used.

    ignorePermissions (Default: false)
      User property: dependency.ignorePermissions
      ignore to set file permissions when unpacking a dependency

    includeArtifactIds
      User property: includeArtifactIds
      Comma separated list of Artifact names to include.

    includeClassifiers
      User property: includeClassifiers
      Comma Separated list of Classifiers to include. Empty String indicates
      include everything (default).

    includeGroupIds
      User property: includeGroupIds
      Comma separated list of GroupIds to include.

    includes
      User property: mdep.unpack.includes
      A comma separated list of file patterns to include when unpacking the
      artifact. i.e. **\/*.xml,**\/*.properties NOTE: Excludes patterns
      override the includes. (component code = return isIncluded( name ) AND
      !isExcluded( name );)

    includeScope
      User property: includeScope
      Scope to include. An Empty string indicates all scopes (default). The
      scopes being interpreted are the scopes as Maven sees them, not as
      specified in the pom. In summary:
      - runtime scope gives runtime and compile dependencies,
      - compile scope gives compile, provided, and system dependencies,
      - test (default) scope gives all dependencies,
      - provided scope just gives provided dependencies,
      - system scope just gives system dependencies.

    includeTypes
      User property: includeTypes
      Comma Separated list of Types to include. Empty String indicates include
      everything (default).

    markersDirectory (Default:
    ${project.build.directory}/dependency-maven-plugin-markers)
      User property: markersDirectory
      Directory to store flag files

    outputAbsoluteArtifactFilename (Default: false)
      User property: outputAbsoluteArtifactFilename
      Output absolute filename for resolved artifacts

    outputDirectory (Default:
    ${project.build.directory}/dependency)
      User property: outputDirectory
      Output location.

    overWriteIfNewer (Default: true)
      User property: overWriteIfNewer
      Overwrite artifacts that don't exist or are older than the source.

    overWriteReleases (Default: false)
      User property: overWriteReleases
      Overwrite release artifacts

    overWriteSnapshots (Default: false)
      User property: overWriteSnapshots
      Overwrite snapshot artifacts

    prependGroupId (Default: false)
      User property: mdep.prependGroupId
      Prepend the groupId during copy.

    silent (Default: false)
      User property: silent
      If the plugin should be silent.

    skip (Default: false)
      User property: mdep.skip
      Skip plugin execution completely.

    stripClassifier (Default: false)
      User property: mdep.stripClassifier
      Strip artifact classifier during copy

    stripVersion (Default: false)
      User property: mdep.stripVersion
      Strip artifact version during copy

    type
      User property: type
      Specify type to look for when constructing artifact based on classifier.
      Example: java-source,jar,war

    useJvmChmod (Default: true)
      User property: dependency.useJvmChmod
      will use the jvm chmod, this is available for user and all level group
      level will be ignored
      since 2.6 is on by default

    useRepositoryLayout (Default: false)
      User property: mdep.useRepositoryLayout
      Place each artifact in the same directory layout as a default repository.
      example: /outputDirectory/junit/junit/3.8.1/junit-3.8.1.jar

    useSubDirectoryPerArtifact (Default: false)
      User property: mdep.useSubDirectoryPerArtifact
      Place each file in a separate subdirectory. (example
      /outputDirectory/junit-3.8.1-jar)

    useSubDirectoryPerScope (Default: false)
      User property: mdep.useSubDirectoryPerScope
      Place each type of file in a separate subdirectory. (example
      /outputDirectory/runtime /outputDirectory/provided etc)

    useSubDirectoryPerType (Default: false)
      User property: mdep.useSubDirectoryPerType
      Place each type of file in a separate subdirectory. (example
      /outputDirectory/jars /outputDirectory/wars etc)
```
