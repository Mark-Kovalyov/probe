# resources

```
Name: Apache Maven Resources Plugin
Description: The Resources Plugin handles the copying of project resources to
  the output directory. There are two different kinds of resources: main
  resources and test resources. The difference is that the main resources are
  the resources associated to the main source code while the test resources are
  associated to the test source code. Thus, this allows the separation of
  resources for the main source code and its unit tests.
Group Id: org.apache.maven.plugins
Artifact Id: maven-resources-plugin
Version: 3.1.0
Goal Prefix: resources

This plugin has 4 goals:

resources:copy-resources
  Description: Copy resources of the configured plugin attribute resources

resources:help
  Description: Display help information on maven-resources-plugin.
    Call mvn resources:help -Ddetail=true -Dgoal=<goal-name> to display
    parameter details.

resources:resources
  Description: Copy resources for the main source code to the main output
    directory. Always uses the project.build.resources element to specify the
    resources to copy.

resources:testResources
  Description: Copy resources for the test source code to the test output
    directory. Always uses the project.build.testResources element to specify
    the resources to copy.

For more information, run 'mvn help:describe [...] -Ddetail'
```

## Detailed Info

```
Name: Apache Maven Resources Plugin
Description: The Resources Plugin handles the copying of project resources to
  the output directory. There are two different kinds of resources: main
  resources and test resources. The difference is that the main resources are
  the resources associated to the main source code while the test resources are
  associated to the test source code. Thus, this allows the separation of
  resources for the main source code and its unit tests.
Group Id: org.apache.maven.plugins
Artifact Id: maven-resources-plugin
Version: 3.1.0
Goal Prefix: resources

This plugin has 4 goals:

resources:copy-resources
  Description: Copy resources of the configured plugin attribute resources
  Implementation: org.apache.maven.plugins.resources.CopyResourcesMojo
  Language: java

  Available parameters:

    addDefaultExcludes (Default: true)
      By default files like .gitignore, .cvsignore etc. are excluded which
      means they will not being copied. If you need them for a particular
      reason you can do that by settings this to false. This means all files
      like the following will be copied.
      - Misc: **/*~, **/#*#, **/.#*, **/%*%, **/._*
      - CVS: **/CVS, **/CVS/**, **/.cvsignore
      - RCS: **/RCS, **/RCS/**
      - SCCS: **/SCCS, **/SCCS/**
      - VSSercer: **/vssver.scc
      - MKS: **/project.pj
      - SVN: **/.svn, **/.svn/**
      - GNU: **/.arch-ids, **/.arch-ids/**
      - Bazaar: **/.bzr, **/.bzr/**
      - SurroundSCM: **/.MySCMServerInfo
      - Mac: **/.DS_Store
      - Serena Dimension: **/.metadata, **/.metadata/**
      - Mercurial: **/.hg, **/.hg/**
      - GIT: **/.git, **/.gitignore, **/.gitattributes, **/.git/**
      - Bitkeeper: **/BitKeeper, **/BitKeeper/**, **/ChangeSet, **/ChangeSet/**
      - Darcs: **/_darcs, **/_darcs/**, **/.darcsrepo,
        **/.darcsrepo/****/-darcs-backup*, **/.darcs-temp-mail

    delimiters
      Set of delimiters for expressions to filter within the resources. These
      delimiters are specified in the form beginToken*endToken. If no * is
      given, the delimiter is assumed to be the same for start and end.
      
      So, the default filtering delimiters might be specified as:
      
      <delimiters>
       <delimiter>${*}</delimiter>
       <delimiter>@</delimiter>
      </delimiters>
      
      Since the @ delimiter is the same on both ends, we don't need to specify
      @*@ (though we can).

    encoding (Default: ${project.build.sourceEncoding})
      The character encoding scheme to be applied when filtering resources.

    escapeString
      Expressions preceded with this string won't be interpolated. Anything
      else preceded with this string will be passed through unchanged. For
      example \${foo} will be replaced with ${foo} but \\${foo} will be
      replaced with \\value of foo, if this parameter has been set to the
      backslash.

    escapeWindowsPaths (Default: true)
      Whether to escape backslashes and colons in windows-style paths.

    fileNameFiltering (Default: false)
      Support filtering of filenames folders etc.

    filters
      The list of extra filter properties files to be used along with System
      properties, project properties, and filter properties files specified in
      the POM build/filters section, which should be used for the filtering
      during the current mojo execution.
      
      Normally, these will be configured from a plugin's execution section, to
      provide a different set of filters for a particular execution. For
      instance, starting in Maven 2.2.0, you have the option of configuring
      executions with the id's default-resources and default-testResources to
      supply different configurations for the two different types of resources.
      By supplying extraFilters configurations, you can separate which filters
      are used for which type of resource.

    includeEmptyDirs (Default: false)
      Copy any empty directories included in the Resources.

    mavenFilteringHints
      List of plexus components hint which implements
      MavenResourcesFiltering.filterResources(). They will be executed after
      the resources copying/filtering.

    nonFilteredFileExtensions
      Additional file extensions to not apply filtering (already defined are :
      jpg, jpeg, gif, bmp, png)

    outputDirectory
      Required: true
      The output directory into which to copy the resources.

    overwrite (Default: false)
      Overwrite existing files even if the destination files are newer.

    resources
      Required: true
      The list of resources we want to transfer. See the Maven Model for a
      description of how to code the resources element.

    skip (Default: false)
      User property: maven.resources.skip
      You can skip the execution of the plugin if you need to. Its use is NOT
      RECOMMENDED, but quite convenient on occasion.

    supportMultiLineFiltering (Default: false)
      stop searching endToken at the end of line

    useBuildFilters (Default: true)
      If false, don't use the filters specified in the build/filters section of
      the POM when processing resources in this mojo execution.

    useDefaultDelimiters (Default: true)
      Use default delimiters in addition to custom delimiters, if any.

resources:help
  Description: Display help information on maven-resources-plugin.
    Call mvn resources:help -Ddetail=true -Dgoal=<goal-name> to display
    parameter details.
  Implementation: org.apache.maven.plugins.resources.HelpMojo
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

resources:resources
  Description: Copy resources for the main source code to the main output
    directory. Always uses the project.build.resources element to specify the
    resources to copy.
  Implementation: org.apache.maven.plugins.resources.ResourcesMojo
  Language: java
  Bound to phase: process-resources

  Available parameters:

    addDefaultExcludes (Default: true)
      By default files like .gitignore, .cvsignore etc. are excluded which
      means they will not being copied. If you need them for a particular
      reason you can do that by settings this to false. This means all files
      like the following will be copied.
      - Misc: **/*~, **/#*#, **/.#*, **/%*%, **/._*
      - CVS: **/CVS, **/CVS/**, **/.cvsignore
      - RCS: **/RCS, **/RCS/**
      - SCCS: **/SCCS, **/SCCS/**
      - VSSercer: **/vssver.scc
      - MKS: **/project.pj
      - SVN: **/.svn, **/.svn/**
      - GNU: **/.arch-ids, **/.arch-ids/**
      - Bazaar: **/.bzr, **/.bzr/**
      - SurroundSCM: **/.MySCMServerInfo
      - Mac: **/.DS_Store
      - Serena Dimension: **/.metadata, **/.metadata/**
      - Mercurial: **/.hg, **/.hg/**
      - GIT: **/.git, **/.gitignore, **/.gitattributes, **/.git/**
      - Bitkeeper: **/BitKeeper, **/BitKeeper/**, **/ChangeSet, **/ChangeSet/**
      - Darcs: **/_darcs, **/_darcs/**, **/.darcsrepo,
        **/.darcsrepo/****/-darcs-backup*, **/.darcs-temp-mail

    delimiters
      Set of delimiters for expressions to filter within the resources. These
      delimiters are specified in the form beginToken*endToken. If no * is
      given, the delimiter is assumed to be the same for start and end.
      
      So, the default filtering delimiters might be specified as:
      
      <delimiters>
       <delimiter>${*}</delimiter>
       <delimiter>@</delimiter>
      </delimiters>
      
      Since the @ delimiter is the same on both ends, we don't need to specify
      @*@ (though we can).

    encoding (Default: ${project.build.sourceEncoding})
      The character encoding scheme to be applied when filtering resources.

    escapeString
      Expressions preceded with this string won't be interpolated. Anything
      else preceded with this string will be passed through unchanged. For
      example \${foo} will be replaced with ${foo} but \\${foo} will be
      replaced with \\value of foo, if this parameter has been set to the
      backslash.

    escapeWindowsPaths (Default: true)
      Whether to escape backslashes and colons in windows-style paths.

    fileNameFiltering (Default: false)
      Support filtering of filenames folders etc.

    filters
      The list of extra filter properties files to be used along with System
      properties, project properties, and filter properties files specified in
      the POM build/filters section, which should be used for the filtering
      during the current mojo execution.
      
      Normally, these will be configured from a plugin's execution section, to
      provide a different set of filters for a particular execution. For
      instance, starting in Maven 2.2.0, you have the option of configuring
      executions with the id's default-resources and default-testResources to
      supply different configurations for the two different types of resources.
      By supplying extraFilters configurations, you can separate which filters
      are used for which type of resource.

    includeEmptyDirs (Default: false)
      Copy any empty directories included in the Resources.

    mavenFilteringHints
      List of plexus components hint which implements
      MavenResourcesFiltering.filterResources(). They will be executed after
      the resources copying/filtering.

    nonFilteredFileExtensions
      Additional file extensions to not apply filtering (already defined are :
      jpg, jpeg, gif, bmp, png)

    outputDirectory (Default: ${project.build.outputDirectory})
      Required: true
      The output directory into which to copy the resources.

    overwrite (Default: false)
      Overwrite existing files even if the destination files are newer.

    skip (Default: false)
      User property: maven.resources.skip
      You can skip the execution of the plugin if you need to. Its use is NOT
      RECOMMENDED, but quite convenient on occasion.

    supportMultiLineFiltering (Default: false)
      stop searching endToken at the end of line

    useBuildFilters (Default: true)
      If false, don't use the filters specified in the build/filters section of
      the POM when processing resources in this mojo execution.

    useDefaultDelimiters (Default: true)
      Use default delimiters in addition to custom delimiters, if any.

resources:testResources
  Description: Copy resources for the test source code to the test output
    directory. Always uses the project.build.testResources element to specify
    the resources to copy.
  Implementation: org.apache.maven.plugins.resources.TestResourcesMojo
  Language: java
  Bound to phase: process-test-resources

  Available parameters:

    addDefaultExcludes (Default: true)
      By default files like .gitignore, .cvsignore etc. are excluded which
      means they will not being copied. If you need them for a particular
      reason you can do that by settings this to false. This means all files
      like the following will be copied.
      - Misc: **/*~, **/#*#, **/.#*, **/%*%, **/._*
      - CVS: **/CVS, **/CVS/**, **/.cvsignore
      - RCS: **/RCS, **/RCS/**
      - SCCS: **/SCCS, **/SCCS/**
      - VSSercer: **/vssver.scc
      - MKS: **/project.pj
      - SVN: **/.svn, **/.svn/**
      - GNU: **/.arch-ids, **/.arch-ids/**
      - Bazaar: **/.bzr, **/.bzr/**
      - SurroundSCM: **/.MySCMServerInfo
      - Mac: **/.DS_Store
      - Serena Dimension: **/.metadata, **/.metadata/**
      - Mercurial: **/.hg, **/.hg/**
      - GIT: **/.git, **/.gitignore, **/.gitattributes, **/.git/**
      - Bitkeeper: **/BitKeeper, **/BitKeeper/**, **/ChangeSet, **/ChangeSet/**
      - Darcs: **/_darcs, **/_darcs/**, **/.darcsrepo,
        **/.darcsrepo/****/-darcs-backup*, **/.darcs-temp-mail

    delimiters
      Set of delimiters for expressions to filter within the resources. These
      delimiters are specified in the form beginToken*endToken. If no * is
      given, the delimiter is assumed to be the same for start and end.
      
      So, the default filtering delimiters might be specified as:
      
      <delimiters>
       <delimiter>${*}</delimiter>
       <delimiter>@</delimiter>
      </delimiters>
      
      Since the @ delimiter is the same on both ends, we don't need to specify
      @*@ (though we can).

    encoding (Default: ${project.build.sourceEncoding})
      The character encoding scheme to be applied when filtering resources.

    escapeString
      Expressions preceded with this string won't be interpolated. Anything
      else preceded with this string will be passed through unchanged. For
      example \${foo} will be replaced with ${foo} but \\${foo} will be
      replaced with \\value of foo, if this parameter has been set to the
      backslash.

    escapeWindowsPaths (Default: true)
      Whether to escape backslashes and colons in windows-style paths.

    fileNameFiltering (Default: false)
      Support filtering of filenames folders etc.

    filters
      The list of extra filter properties files to be used along with System
      properties, project properties, and filter properties files specified in
      the POM build/filters section, which should be used for the filtering
      during the current mojo execution.
      
      Normally, these will be configured from a plugin's execution section, to
      provide a different set of filters for a particular execution. For
      instance, starting in Maven 2.2.0, you have the option of configuring
      executions with the id's default-resources and default-testResources to
      supply different configurations for the two different types of resources.
      By supplying extraFilters configurations, you can separate which filters
      are used for which type of resource.

    includeEmptyDirs (Default: false)
      Copy any empty directories included in the Resources.

    mavenFilteringHints
      List of plexus components hint which implements
      MavenResourcesFiltering.filterResources(). They will be executed after
      the resources copying/filtering.

    nonFilteredFileExtensions
      Additional file extensions to not apply filtering (already defined are :
      jpg, jpeg, gif, bmp, png)

    outputDirectory (Default:
    ${project.build.testOutputDirectory})
      Required: true
      The output directory into which to copy the resources.

    overwrite (Default: false)
      Overwrite existing files even if the destination files are newer.

    resources (Default: ${project.testResources})
      Required: true
      The list of resources we want to transfer.

    skip (Default: false)
      User property: maven.test.skip
      Set this to 'true' to bypass copying of test resources. Its use is NOT
      RECOMMENDED, but quite convenient on occasion.

    supportMultiLineFiltering (Default: false)
      stop searching endToken at the end of line

    useBuildFilters (Default: true)
      If false, don't use the filters specified in the build/filters section of
      the POM when processing resources in this mojo execution.

    useDefaultDelimiters (Default: true)
      Use default delimiters in addition to custom delimiters, if any.
```
