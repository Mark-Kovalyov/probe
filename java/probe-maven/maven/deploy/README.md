# deploy

```
Name: Maven Deploy Plugin
Description: Uploads the project artifacts to the internal remote repository.
Group Id: org.apache.maven.plugins
Artifact Id: maven-deploy-plugin
Version: 2.7
Goal Prefix: deploy

This plugin has 3 goals:

deploy:deploy
  Description: Deploys an artifact to remote repository.

deploy:deploy-file
  Description: Installs the artifact in the remote repository.

deploy:help
  Description: Display help information on maven-deploy-plugin.
    Call
     mvn deploy:help -Ddetail=true -Dgoal=<goal-name>
    to display parameter details.

For more information, run 'mvn help:describe [...] -Ddetail'

```
## Detailed Info

```
Name: Maven Deploy Plugin
Description: Uploads the project artifacts to the internal remote repository.
Group Id: org.apache.maven.plugins
Artifact Id: maven-deploy-plugin
Version: 2.7
Goal Prefix: deploy

This plugin has 3 goals:

deploy:deploy
  Description: Deploys an artifact to remote repository.
  Implementation: org.apache.maven.plugin.deploy.DeployMojo
  Language: java
  Bound to phase: deploy

  Available parameters:

    altDeploymentRepository
      User property: altDeploymentRepository
      Specifies an alternative repository to which the project artifacts should
      be deployed ( other than those specified in <distributionManagement> ).
      Format: id::layout::url

    retryFailedDeploymentCount (Default: 1)
      User property: retryFailedDeploymentCount
      Parameter used to control how many times a failed deployment will be
      retried before giving up and failing. If a value outside the range 1-10
      is specified it will be pulled to the nearest value within the range
      1-10.

    skip (Default: false)
      User property: maven.deploy.skip
      Set this to 'true' to bypass artifact deploy

    updateReleaseInfo (Default: false)
      User property: updateReleaseInfo
      Parameter used to update the metadata to make the artifact as release.

deploy:deploy-file
  Description: Installs the artifact in the remote repository.
  Implementation: org.apache.maven.plugin.deploy.DeployFileMojo
  Language: java

  Available parameters:

    artifactId
      User property: artifactId
      ArtifactId of the artifact to be deployed. Retrieved from POM file if
      specified.

    classifier
      User property: classifier
      Add classifier to the artifact

    classifiers
      User property: classifiers
      A comma separated list of classifiers for each of the extra side
      artifacts to deploy. If there is a mis-match in the number of entries in
      files or types, then an error will be raised.

    description
      User property: generatePom.description
      Description passed to a generated POM file (in case of generatePom=true)

    file
      Required: true
      User property: file
      File to be deployed.

    files
      User property: files
      A comma separated list of files for each of the extra side artifacts to
      deploy. If there is a mis-match in the number of entries in types or
      classifiers, then an error will be raised.

    generatePom (Default: true)
      User property: generatePom
      Upload a POM for this artifact. Will generate a default POM if none is
      supplied with the pomFile argument.

    groupId
      User property: groupId
      GroupId of the artifact to be deployed. Retrieved from POM file if
      specified.

    javadoc
      User property: javadoc
      The bundled API docs for the artifact.

    packaging
      User property: packaging
      Type of the artifact to be deployed. Retrieved from the <packaging>
      element of the POM file if a POM file specified. Defaults to the file
      extension if it is not specified via command line or POM.
      Maven uses two terms to refer to this datum: the <packaging> element for
      the entire POM, and the <type> element in a dependency specification.

    pomFile
      User property: pomFile
      Location of an existing POM file to be deployed alongside the main
      artifact, given by the ${file} parameter.

    repositoryId (Default: remote-repository)
      Required: true
      User property: repositoryId
      Server Id to map on the <id> under <server> section of settings.xml In
      most cases, this parameter will be required for authentication.

    repositoryLayout (Default: default)
      User property: repositoryLayout
      The type of remote repository layout to deploy to. Try legacy for a Maven
      1.x-style repository layout.

    retryFailedDeploymentCount (Default: 1)
      User property: retryFailedDeploymentCount
      Parameter used to control how many times a failed deployment will be
      retried before giving up and failing. If a value outside the range 1-10
      is specified it will be pulled to the nearest value within the range
      1-10.

    sources
      User property: sources
      The bundled sources for the artifact.

    types
      User property: types
      A comma separated list of types for each of the extra side artifacts to
      deploy. If there is a mis-match in the number of entries in files or
      classifiers, then an error will be raised.

    uniqueVersion (Default: true)
      User property: uniqueVersion
      Whether to deploy snapshots with a unique version or not.

    updateReleaseInfo (Default: false)
      User property: updateReleaseInfo
      Parameter used to update the metadata to make the artifact as release.

    url
      Required: true
      User property: url
      URL where the artifact will be deployed.
      ie ( file:///C:/m2-repo or scp://host.com/path/to/repo )

    version
      User property: version
      Version of the artifact to be deployed. Retrieved from POM file if
      specified.

deploy:help
  Description: Display help information on maven-deploy-plugin.
    Call
     mvn deploy:help -Ddetail=true -Dgoal=<goal-name>
    to display parameter details.
  Implementation: org.apache.maven.plugin.deploy.HelpMojo
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
