# project-info-reports

```
Name: Apache Maven Project Info Reports Plugin
Description: The Maven Project Info Reports Plugin is a plugin that generates
  standard reports for the specified project.
Group Id: org.apache.maven.plugins
Artifact Id: maven-project-info-reports-plugin
Version: 3.1.0
Goal Prefix: project-info-reports

This plugin has 17 goals:

project-info-reports:ci-management
  Description: Generates the Project Continuous Integration Management
    report.
  Note: This goal should be used as a Maven report.

project-info-reports:dependencies
  Description: Generates the Project Dependencies report.
  Note: This goal should be used as a Maven report.

project-info-reports:dependency-convergence
  Description: Generates the Project Dependency Convergence report for
    (reactor) builds.
  Note: This goal should be used as a Maven report.

project-info-reports:dependency-info
  Description: Generates code snippets to be added to build tools.
  Note: This goal should be used as a Maven report.

project-info-reports:dependency-management
  Description: Generates the Project Dependency Management report.
  Note: This goal should be used as a Maven report.

project-info-reports:distribution-management
  Description: Generates the Project Distribution Management report.
  Note: This goal should be used as a Maven report.

project-info-reports:help
  Description: Display help information on maven-project-info-reports-plugin.
    Call mvn project-info-reports:help -Ddetail=true -Dgoal=<goal-name> to
    display parameter details.

project-info-reports:index
  Description: Generates the Project Index report.
  Note: This goal should be used as a Maven report.

project-info-reports:issue-management
  Description: Generates the Project Issue Management report.
  Note: This goal should be used as a Maven report.

project-info-reports:licenses
  Description: Generates the Project Licenses report.
  Note: This goal should be used as a Maven report.

project-info-reports:mailing-lists
  Description: Generates the Mailing Lists report.
  Note: This goal should be used as a Maven report.

project-info-reports:modules
  Description: Generates the Project Modules report.
  Note: This goal should be used as a Maven report.

project-info-reports:plugin-management
  Description: Generates the Project Plugin Management report.
  Note: This goal should be used as a Maven report.

project-info-reports:plugins
  Description: Generates the Project Plugins report.
  Note: This goal should be used as a Maven report.

project-info-reports:scm
  Description: Generates the Project Source Code Management (SCM) report.
  Note: This goal should be used as a Maven report.

project-info-reports:summary
  Description: Generates the Project Summary report.
  Note: This goal should be used as a Maven report.

project-info-reports:team
  Description: Generates the Project Team report.
  Note: This goal should be used as a Maven report.

For more information, run 'mvn help:describe [...] -Ddetail'

```

## Detailed Info

```
Name: Apache Maven Project Info Reports Plugin
Description: The Maven Project Info Reports Plugin is a plugin that generates
  standard reports for the specified project.
Group Id: org.apache.maven.plugins
Artifact Id: maven-project-info-reports-plugin
Version: 3.1.0
Goal Prefix: project-info-reports

This plugin has 17 goals:

project-info-reports:ci-management
  Description: Generates the Project Continuous Integration Management
    report.
  Note: This goal should be used as a Maven report.
  Implementation: org.apache.maven.report.projectinfo.CiManagementReport
  Language: java

  Available parameters:

    customBundle (Default:
    ${project.basedir}/src/site/custom/project-info-reports.properties)
      Path for a custom bundle instead of using the default one.
      Using this field, you could change the texts in the generated reports.

    pluginRepositories
      User property: project.pluginArtifactRepositories
      Plugin repositories used for the project.

    remoteRepositories
      User property: project.remoteArtifactRepositories
      Remote repositories used for the project.

    skip (Default: false)
      User property: mpir.skip
      Skip report.

    skipEmptyReport (Default: true)
      Skip the project info report generation if a report-specific section of
      the POM is empty. Defaults to true.

project-info-reports:dependencies
  Description: Generates the Project Dependencies report.
  Note: This goal should be used as a Maven report.
  Implementation: org.apache.maven.report.projectinfo.DependenciesReport
  Language: java

  Available parameters:

    customBundle (Default:
    ${project.basedir}/src/site/custom/project-info-reports.properties)
      Path for a custom bundle instead of using the default one.
      Using this field, you could change the texts in the generated reports.

    dependencyDetailsEnabled (Default: true)
      User property: dependency.details.enabled
      Display file details for each dependency, such as: file size, number of
      classes, number of packages etc.

    pluginRepositories
      User property: project.pluginArtifactRepositories
      Plugin repositories used for the project.

    remoteRepositories
      User property: project.remoteArtifactRepositories
      Remote repositories used for the project.

    skip (Default: false)
      User property: mpir.skip
      Skip report.

    skipEmptyReport (Default: true)
      Skip the project info report generation if a report-specific section of
      the POM is empty. Defaults to true.

project-info-reports:dependency-convergence
  Description: Generates the Project Dependency Convergence report for
    (reactor) builds.
  Note: This goal should be used as a Maven report.
  Implementation:
  org.apache.maven.report.projectinfo.DependencyConvergenceReport
  Language: java

  Available parameters:

    customBundle (Default:
    ${project.basedir}/src/site/custom/project-info-reports.properties)
      Path for a custom bundle instead of using the default one.
      Using this field, you could change the texts in the generated reports.

    pluginRepositories
      User property: project.pluginArtifactRepositories
      Plugin repositories used for the project.

    remoteRepositories
      User property: project.remoteArtifactRepositories
      Remote repositories used for the project.

    skip (Default: false)
      User property: mpir.skip
      Skip report.

    skipEmptyReport (Default: true)
      Skip the project info report generation if a report-specific section of
      the POM is empty. Defaults to true.

project-info-reports:dependency-info
  Description: Generates code snippets to be added to build tools.
  Note: This goal should be used as a Maven report.
  Implementation:
  org.apache.maven.report.projectinfo.DependencyInformationReport
  Language: java

  Available parameters:

    artifactId (Default: ${project.artifactId})
      Required: true
      (no description available)

    customBundle (Default:
    ${project.basedir}/src/site/custom/project-info-reports.properties)
      Path for a custom bundle instead of using the default one.
      Using this field, you could change the texts in the generated reports.

    groupId (Default: ${project.groupId})
      Required: true
      (no description available)

    packaging (Default: ${project.packaging})
      Required: true
      (no description available)

    pluginRepositories
      User property: project.pluginArtifactRepositories
      Plugin repositories used for the project.

    remoteRepositories
      User property: project.remoteArtifactRepositories
      Remote repositories used for the project.

    skip (Default: false)
      User property: mpir.skip
      Skip report.

    skipEmptyReport (Default: true)
      Skip the project info report generation if a report-specific section of
      the POM is empty. Defaults to true.

    version (Default: ${project.version})
      Required: true
      (no description available)

project-info-reports:dependency-management
  Description: Generates the Project Dependency Management report.
  Note: This goal should be used as a Maven report.
  Implementation:
  org.apache.maven.report.projectinfo.DependencyManagementReport
  Language: java

  Available parameters:

    customBundle (Default:
    ${project.basedir}/src/site/custom/project-info-reports.properties)
      Path for a custom bundle instead of using the default one.
      Using this field, you could change the texts in the generated reports.

    pluginRepositories
      User property: project.pluginArtifactRepositories
      Plugin repositories used for the project.

    remoteRepositories
      User property: project.remoteArtifactRepositories
      Remote repositories used for the project.

    skip (Default: false)
      User property: mpir.skip
      Skip report.

    skipEmptyReport (Default: true)
      Skip the project info report generation if a report-specific section of
      the POM is empty. Defaults to true.

project-info-reports:distribution-management
  Description: Generates the Project Distribution Management report.
  Note: This goal should be used as a Maven report.
  Implementation:
  org.apache.maven.report.projectinfo.DistributionManagementReport
  Language: java

  Available parameters:

    customBundle (Default:
    ${project.basedir}/src/site/custom/project-info-reports.properties)
      Path for a custom bundle instead of using the default one.
      Using this field, you could change the texts in the generated reports.

    pluginRepositories
      User property: project.pluginArtifactRepositories
      Plugin repositories used for the project.

    remoteRepositories
      User property: project.remoteArtifactRepositories
      Remote repositories used for the project.

    skip (Default: false)
      User property: mpir.skip
      Skip report.

    skipEmptyReport (Default: true)
      Skip the project info report generation if a report-specific section of
      the POM is empty. Defaults to true.

project-info-reports:help
  Description: Display help information on maven-project-info-reports-plugin.
    Call mvn project-info-reports:help -Ddetail=true -Dgoal=<goal-name> to
    display parameter details.
  Implementation: org.apache.maven.report.projectinfo.HelpMojo
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

project-info-reports:index
  Description: Generates the Project Index report.
  Note: This goal should be used as a Maven report.
  Implementation: org.apache.maven.report.projectinfo.IndexReport
  Language: java

  Available parameters:

    customBundle (Default:
    ${project.basedir}/src/site/custom/project-info-reports.properties)
      Path for a custom bundle instead of using the default one.
      Using this field, you could change the texts in the generated reports.

    pluginRepositories
      User property: project.pluginArtifactRepositories
      Plugin repositories used for the project.

    remoteRepositories
      User property: project.remoteArtifactRepositories
      Remote repositories used for the project.

    skip (Default: false)
      User property: mpir.skip
      Skip report.

    skipEmptyReport (Default: true)
      Skip the project info report generation if a report-specific section of
      the POM is empty. Defaults to true.

project-info-reports:issue-management
  Description: Generates the Project Issue Management report.
  Note: This goal should be used as a Maven report.
  Implementation: org.apache.maven.report.projectinfo.IssueManagementReport
  Language: java

  Available parameters:

    customBundle (Default:
    ${project.basedir}/src/site/custom/project-info-reports.properties)
      Path for a custom bundle instead of using the default one.
      Using this field, you could change the texts in the generated reports.

    pluginRepositories
      User property: project.pluginArtifactRepositories
      Plugin repositories used for the project.

    remoteRepositories
      User property: project.remoteArtifactRepositories
      Remote repositories used for the project.

    skip (Default: false)
      User property: mpir.skip
      Skip report.

    skipEmptyReport (Default: true)
      Skip the project info report generation if a report-specific section of
      the POM is empty. Defaults to true.

project-info-reports:licenses
  Description: Generates the Project Licenses report.
  Note: This goal should be used as a Maven report.
  Implementation: org.apache.maven.report.projectinfo.LicensesReport
  Language: java

  Available parameters:

    customBundle (Default:
    ${project.basedir}/src/site/custom/project-info-reports.properties)
      Path for a custom bundle instead of using the default one.
      Using this field, you could change the texts in the generated reports.

    licenseFileEncoding
      Specifies the input encoding of the project's license file(s).

    linkOnly (Default: false)
      Whether the only render links to the license documents instead of
      inlining them.
      If the system is in offline mode, the linkOnly parameter will be always
      true.

    offline
      User property: settings.offline
      Whether the system is currently offline.

    pluginRepositories
      User property: project.pluginArtifactRepositories
      Plugin repositories used for the project.

    remoteRepositories
      User property: project.remoteArtifactRepositories
      Remote repositories used for the project.

    skip (Default: false)
      User property: mpir.skip
      Skip report.

    skipEmptyReport (Default: true)
      Skip the project info report generation if a report-specific section of
      the POM is empty. Defaults to true.

project-info-reports:mailing-lists
  Description: Generates the Mailing Lists report.
  Note: This goal should be used as a Maven report.
  Implementation: org.apache.maven.report.projectinfo.MailingListsReport
  Language: java

  Available parameters:

    customBundle (Default:
    ${project.basedir}/src/site/custom/project-info-reports.properties)
      Path for a custom bundle instead of using the default one.
      Using this field, you could change the texts in the generated reports.

    pluginRepositories
      User property: project.pluginArtifactRepositories
      Plugin repositories used for the project.

    remoteRepositories
      User property: project.remoteArtifactRepositories
      Remote repositories used for the project.

    skip (Default: false)
      User property: mpir.skip
      Skip report.

    skipEmptyReport (Default: true)
      Skip the project info report generation if a report-specific section of
      the POM is empty. Defaults to true.

project-info-reports:modules
  Description: Generates the Project Modules report.
  Note: This goal should be used as a Maven report.
  Implementation: org.apache.maven.report.projectinfo.ModulesReport
  Language: java

  Available parameters:

    customBundle (Default:
    ${project.basedir}/src/site/custom/project-info-reports.properties)
      Path for a custom bundle instead of using the default one.
      Using this field, you could change the texts in the generated reports.

    pluginRepositories
      User property: project.pluginArtifactRepositories
      Plugin repositories used for the project.

    remoteRepositories
      User property: project.remoteArtifactRepositories
      Remote repositories used for the project.

    skip (Default: false)
      User property: mpir.skip
      Skip report.

    skipEmptyReport (Default: true)
      Skip the project info report generation if a report-specific section of
      the POM is empty. Defaults to true.

project-info-reports:plugin-management
  Description: Generates the Project Plugin Management report.
  Note: This goal should be used as a Maven report.
  Implementation: org.apache.maven.report.projectinfo.PluginManagementReport
  Language: java

  Available parameters:

    customBundle (Default:
    ${project.basedir}/src/site/custom/project-info-reports.properties)
      Path for a custom bundle instead of using the default one.
      Using this field, you could change the texts in the generated reports.

    pluginManagementExcludes
      Specify the excluded plugins. This can be a list of artifacts in the
      format groupId[:artifactId[:type[:version]]].
      Plugins matching any exclude will not be present in the report.

    pluginRepositories
      User property: project.pluginArtifactRepositories
      Plugin repositories used for the project.

    remoteRepositories
      User property: project.remoteArtifactRepositories
      Remote repositories used for the project.

    skip (Default: false)
      User property: mpir.skip
      Skip report.

    skipEmptyReport (Default: true)
      Skip the project info report generation if a report-specific section of
      the POM is empty. Defaults to true.

project-info-reports:plugins
  Description: Generates the Project Plugins report.
  Note: This goal should be used as a Maven report.
  Implementation: org.apache.maven.report.projectinfo.PluginsReport
  Language: java

  Available parameters:

    customBundle (Default:
    ${project.basedir}/src/site/custom/project-info-reports.properties)
      Path for a custom bundle instead of using the default one.
      Using this field, you could change the texts in the generated reports.

    pluginRepositories
      User property: project.pluginArtifactRepositories
      Plugin repositories used for the project.

    remoteRepositories
      User property: project.remoteArtifactRepositories
      Remote repositories used for the project.

    skip (Default: false)
      User property: mpir.skip
      Skip report.

    skipEmptyReport (Default: true)
      Skip the project info report generation if a report-specific section of
      the POM is empty. Defaults to true.

project-info-reports:scm
  Description: Generates the Project Source Code Management (SCM) report.
  Note: This goal should be used as a Maven report.
  Implementation: org.apache.maven.report.projectinfo.ScmReport
  Language: java

  Available parameters:

    anonymousConnection (Default: ${project.scm.connection})
      The SCM anonymous connection url respecting the SCM URL Format.

    checkoutDirectoryName (Default: ${project.artifactId})
      The directory name to checkout right after the SCM URL.

    customBundle (Default:
    ${project.basedir}/src/site/custom/project-info-reports.properties)
      Path for a custom bundle instead of using the default one.
      Using this field, you could change the texts in the generated reports.

    developerConnection (Default:
    ${project.scm.developerConnection})
      The SCM developer connection url respecting the SCM URL Format.

    pluginRepositories
      User property: project.pluginArtifactRepositories
      Plugin repositories used for the project.

    remoteRepositories
      User property: project.remoteArtifactRepositories
      Remote repositories used for the project.

    scmTag (Default: ${project.scm.tag})
      The SCM tag.

    skip (Default: false)
      User property: mpir.skip
      Skip report.

    skipEmptyReport (Default: true)
      Skip the project info report generation if a report-specific section of
      the POM is empty. Defaults to true.

    webAccessUrl (Default: ${project.scm.url})
      The SCM web access url.

project-info-reports:summary
  Description: Generates the Project Summary report.
  Note: This goal should be used as a Maven report.
  Implementation: org.apache.maven.report.projectinfo.SummaryReport
  Language: java

  Available parameters:

    customBundle (Default:
    ${project.basedir}/src/site/custom/project-info-reports.properties)
      Path for a custom bundle instead of using the default one.
      Using this field, you could change the texts in the generated reports.

    pluginRepositories
      User property: project.pluginArtifactRepositories
      Plugin repositories used for the project.

    remoteRepositories
      User property: project.remoteArtifactRepositories
      Remote repositories used for the project.

    skip (Default: false)
      User property: mpir.skip
      Skip report.

    skipEmptyReport (Default: true)
      Skip the project info report generation if a report-specific section of
      the POM is empty. Defaults to true.

project-info-reports:team
  Description: Generates the Project Team report.
  Note: This goal should be used as a Maven report.
  Implementation: org.apache.maven.report.projectinfo.TeamReport
  Language: java

  Available parameters:

    customBundle (Default:
    ${project.basedir}/src/site/custom/project-info-reports.properties)
      Path for a custom bundle instead of using the default one.
      Using this field, you could change the texts in the generated reports.

    pluginRepositories
      User property: project.pluginArtifactRepositories
      Plugin repositories used for the project.

    remoteRepositories
      User property: project.remoteArtifactRepositories
      Remote repositories used for the project.

    showAvatarImages (Default: true)
      User property: teamlist.showAvatarImages
      Shows avatar images for team members that have a) properties/picUrl set
      b) An avatar at gravatar.com for their email address Future versions of
      this plugin may choose to implement different strategies for resolving
      avatar images, possibly using different providers.
      Note: This property will be renamed to tteam.showAvatarImages in 3.0.

    skip (Default: false)
      User property: mpir.skip
      Skip report.

    skipEmptyReport (Default: true)
      Skip the project info report generation if a report-specific section of
      the POM is empty. Defaults to true.
```
