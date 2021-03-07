# site

```
Name: Maven Site Plugin 3
Description: The Maven Site Plugin is a plugin that generates a site for the
  current project.
Group Id: org.apache.maven.plugins
Artifact Id: maven-site-plugin
Version: 3.3
Goal Prefix: site

This plugin has 9 goals:

site:attach-descriptor
  Description: Adds the site descriptor (site.xml) to the list of files to be
    installed/deployed.
    For Maven-2.x this is enabled by default only when the project has pom
    packaging since it will be used by modules inheriting, but this can be
    enabled for other projects packaging if needed.
    This default execution has been removed from the built-in lifecycle of
    Maven 3.x for pom-projects. Users that actually use those projects to
    provide a common site descriptor for sub modules will need to explicitly
    define this goal execution to restore the intended behavior.

site:deploy
  Description: Deploys the generated site using wagon supported protocols to
    the site URL specified in the <distributionManagement> section of the POM.
    For scp protocol, the website files are packaged by wagon into zip archive,
    then the archive is transfered to the remote host, next it is un-archived
    which is much faster than making a file by file copy.

site:effective-site
  Description: Displays the effective site descriptor as an XML for this
    build, after inheritance and interpolation of site.xml.

site:help
  Description: Display help information on maven-site-plugin.
    Call mvn site:help -Ddetail=true -Dgoal=<goal-name> to display parameter
    details.

site:jar
  Description: Bundles the site output into a JAR so that it can be deployed
    to a repository.

site:run
  Description: Starts the site up, rendering documents as requested for
    faster editing. It uses Jetty as the web server.

site:site
  Description: Generates the site for a single project.
    Note that links between module sites in a multi module build will not work,
    since local build directory structure doesn't match deployed site.

site:stage
  Description: Deploys the generated site to a local staging or mock
    directory based on the site URL specified in the <distributionManagement>
    section of the POM.
    It can be used to test that links between module sites in a multi-module
    build work.

site:stage-deploy
  Description: Deploys the generated site to a staging or mock URL to the
    site URL specified in the <distributionManagement> section of the POM,
    using wagon supported protocols

For more information, run 'mvn help:describe [...] -Ddetail'
```

## Detailed Info

```
Name: Maven Site Plugin 3
Description: The Maven Site Plugin is a plugin that generates a site for the
  current project.
Group Id: org.apache.maven.plugins
Artifact Id: maven-site-plugin
Version: 3.3
Goal Prefix: site

This plugin has 9 goals:

site:attach-descriptor
  Description: Adds the site descriptor (site.xml) to the list of files to be
    installed/deployed.
    For Maven-2.x this is enabled by default only when the project has pom
    packaging since it will be used by modules inheriting, but this can be
    enabled for other projects packaging if needed.
    This default execution has been removed from the built-in lifecycle of
    Maven 3.x for pom-projects. Users that actually use those projects to
    provide a common site descriptor for sub modules will need to explicitly
    define this goal execution to restore the intended behavior.
  Implementation: org.apache.maven.plugins.site.SiteDescriptorAttachMojo
  Language: java
  Bound to phase: package

  Available parameters:

    inputEncoding (Default: ${project.build.sourceEncoding})
      User property: encoding
      Specifies the input encoding.

    locales
      User property: locales
      A comma separated list of locales supported by Maven. The first valid
      token will be the default Locale for this instance of the Java Virtual
      Machine.

    outputEncoding (Default: ${project.reporting.outputEncoding})
      User property: outputEncoding
      Specifies the output encoding.

    pomPackagingOnly (Default: true)
      (no description available)

    siteDirectory (Default: ${basedir}/src/site)
      Directory containing the site.xml file and the source for apt, fml and
      xdoc docs.

site:deploy
  Description: Deploys the generated site using wagon supported protocols to
    the site URL specified in the <distributionManagement> section of the POM.
    For scp protocol, the website files are packaged by wagon into zip archive,
    then the archive is transfered to the remote host, next it is un-archived
    which is much faster than making a file by file copy.
  Implementation: org.apache.maven.plugins.site.SiteDeployMojo
  Language: java

  Available parameters:

    chmod (Default: true)
      User property: maven.site.chmod
      Whether to run the 'chmod' command on the remote site after the deploy.
      Defaults to 'true'.

    chmodMode (Default: g+w,a+rX)
      User property: maven.site.chmod.mode
      The mode used by the 'chmod' command. Only used if chmod = true. Defaults
      to 'g+w,a+rX'.

    chmodOptions (Default: -Rf)
      User property: maven.site.chmod.options
      The options used by the 'chmod' command. Only used if chmod = true.
      Defaults to '-Rf'.

    inputDirectory (Default:
    ${project.reporting.outputDirectory})
      Alias: outputDirectory
      Required: true
      Directory containing the generated project sites and report
      distributions.

    inputEncoding (Default: ${project.build.sourceEncoding})
      User property: encoding
      Specifies the input encoding.

    locales
      User property: locales
      A comma separated list of locales supported by Maven. The first valid
      token will be the default Locale for this instance of the Java Virtual
      Machine.

    outputEncoding (Default: ${project.reporting.outputEncoding})
      User property: outputEncoding
      Specifies the output encoding.

    siteDirectory (Default: ${basedir}/src/site)
      Directory containing the site.xml file and the source for apt, fml and
      xdoc docs.

    skipDeploy (Default: false)
      User property: maven.site.deploy.skip
      Set this to 'true' to skip site deployment.

site:effective-site
  Description: Displays the effective site descriptor as an XML for this
    build, after inheritance and interpolation of site.xml.
  Implementation: org.apache.maven.plugins.site.EffectiveSiteMojo
  Language: java

  Available parameters:

    attributes
      Additional template properties for rendering the site. See Doxia Site
      Renderer.

    generatedSiteDirectory (Default:
    ${project.build.directory}/generated-site)
      Alias: workingDirectory
      Directory containing generated documentation. This is used to pick up
      other source docs that might have been generated at build time.

    generateProjectInfo (Default: true)
      User property: generateProjectInfo
      Whether to generate the summary page for project reports:
      project-info.html.

    inputEncoding (Default: ${project.build.sourceEncoding})
      User property: encoding
      Specifies the input encoding.

    locales
      User property: locales
      A comma separated list of locales supported by Maven. The first valid
      token will be the default Locale for this instance of the Java Virtual
      Machine.

    moduleExcludes
      Module type exclusion mappings ex: fml -> **/*-m1.fml (excludes fml files
      ending in '-m1.fml' recursively) The configuration looks like this:
       <moduleExcludes>
       <moduleType>filename1.ext,**/*sample.ext</moduleType>
       <!-- moduleType can be one of 'apt', 'fml' or 'xdoc'. -->
       <!-- The value is a comma separated list of -->
       <!-- filenames or fileset patterns. -->
       <!-- Here's an example: -->
       <xdoc>changes.xml,navigation.xml</xdoc>
       </moduleExcludes>

    output
      User property: output
      Optional parameter to write the output of this help in a given file,
      instead of writing to the console.
      Note: Could be a relative path.

    outputEncoding (Default: ${project.reporting.outputEncoding})
      User property: outputEncoding
      Specifies the output encoding.

    relativizeDecorationLinks (Default: true)
      User property: relativizeDecorationLinks
      Make links in the site descriptor relative to the project URL. By
      default, any absolute links that appear in the site descriptor, e.g.
      banner hrefs, breadcrumbs, menu links, etc., will be made relative to
      project.url. Links will not be changed if this is set to false, or if the
      project has no URL defined.

    siteDirectory (Default: ${basedir}/src/site)
      Directory containing the site.xml file and the source for apt, fml and
      xdoc docs.

    template
      User property: template
      Default template page.
      Deprecated. use templateFile or skinning instead

    templateDirectory (Default: src/site)
      User property: templateDirectory
      Directory containing the template page.
      Deprecated. use templateFile or skinning instead

    templateFile
      User property: templateFile
      The location of a Velocity template file to use. When used, skins and the
      default templates, CSS and images are disabled. It is highly recommended
      that you package this as a skin instead.

    xdocDirectory (Default: ${basedir}/xdocs)
      Alternative directory for xdoc source, useful for m1 to m2 migration
      Deprecated. use the standard m2 directory layout

site:help
  Description: Display help information on maven-site-plugin.
    Call mvn site:help -Ddetail=true -Dgoal=<goal-name> to display parameter
    details.
  Implementation: org.apache.maven.plugins.site.HelpMojo
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

site:jar
  Description: Bundles the site output into a JAR so that it can be deployed
    to a repository.
  Implementation: org.apache.maven.plugins.site.SiteJarMojo
  Language: java
  Bound to phase: package

  Available parameters:

    archive
      The archive configuration to use. See Maven Archiver Reference.

    archiveExcludes
      List of files to exclude. Specified as file set patterns which are
      relative to the input directory whose contents is being packaged into the
      JAR.

    archiveIncludes
      List of files to include. Specified as file set patterns which are
      relative to the input directory whose contents is being packaged into the
      JAR.

    attach (Default: true)
      User property: site.attach
      Specifies whether to attach the generated artifact to the project.

    attributes
      Additional template properties for rendering the site. See Doxia Site
      Renderer.

    finalName
      Required: true
      User property: project.build.finalName
      Specifies the filename that will be used for the generated jar file.
      Please note that '-site' will be appended to the file name.

    generatedSiteDirectory (Default:
    ${project.build.directory}/generated-site)
      Alias: workingDirectory
      Directory containing generated documentation. This is used to pick up
      other source docs that might have been generated at build time.

    generateProjectInfo (Default: true)
      User property: generateProjectInfo
      Whether to generate the summary page for project reports:
      project-info.html.

    generateReports (Default: true)
      User property: generateReports
      Convenience parameter that allows you to disable report generation.

    generateSitemap (Default: false)
      User property: generateSitemap
      Generate a sitemap. The result will be a 'sitemap.html' file at the site
      root.

    inputEncoding (Default: ${project.build.sourceEncoding})
      User property: encoding
      Specifies the input encoding.

    jarOutputDirectory
      Required: true
      User property: project.build.directory
      Specifies the directory where the generated jar file will be put.

    locales
      User property: locales
      A comma separated list of locales supported by Maven. The first valid
      token will be the default Locale for this instance of the Java Virtual
      Machine.

    moduleExcludes
      Module type exclusion mappings ex: fml -> **/*-m1.fml (excludes fml files
      ending in '-m1.fml' recursively) The configuration looks like this:
       <moduleExcludes>
       <moduleType>filename1.ext,**/*sample.ext</moduleType>
       <!-- moduleType can be one of 'apt', 'fml' or 'xdoc'. -->
       <!-- The value is a comma separated list of -->
       <!-- filenames or fileset patterns. -->
       <!-- Here's an example: -->
       <xdoc>changes.xml,navigation.xml</xdoc>
       </moduleExcludes>

    outputDirectory (Default:
    ${project.reporting.outputDirectory})
      User property: siteOutputDirectory
      Directory where the project sites and report distributions will be
      generated.

    outputEncoding (Default: ${project.reporting.outputEncoding})
      User property: outputEncoding
      Specifies the output encoding.

    relativizeDecorationLinks (Default: true)
      User property: relativizeDecorationLinks
      Make links in the site descriptor relative to the project URL. By
      default, any absolute links that appear in the site descriptor, e.g.
      banner hrefs, breadcrumbs, menu links, etc., will be made relative to
      project.url. Links will not be changed if this is set to false, or if the
      project has no URL defined.

    siteDirectory (Default: ${basedir}/src/site)
      Directory containing the site.xml file and the source for apt, fml and
      xdoc docs.

    skip (Default: false)
      User property: maven.site.skip
      Set this to 'true' to skip site generation and staging.

    template
      User property: template
      Default template page.
      Deprecated. use templateFile or skinning instead

    templateDirectory (Default: src/site)
      User property: templateDirectory
      Directory containing the template page.
      Deprecated. use templateFile or skinning instead

    templateFile
      User property: templateFile
      The location of a Velocity template file to use. When used, skins and the
      default templates, CSS and images are disabled. It is highly recommended
      that you package this as a skin instead.

    validate (Default: false)
      User property: validate
      Whether to validate xml input documents. If set to true, all input
      documents in xml format (in particular xdoc and fml) will be validated
      and any error will lead to a build failure.

    xdocDirectory (Default: ${basedir}/xdocs)
      Alternative directory for xdoc source, useful for m1 to m2 migration
      Deprecated. use the standard m2 directory layout

site:run
  Description: Starts the site up, rendering documents as requested for
    faster editing. It uses Jetty as the web server.
  Implementation: org.apache.maven.plugins.site.SiteRunMojo
  Language: java

  Available parameters:

    attributes
      Additional template properties for rendering the site. See Doxia Site
      Renderer.

    generatedSiteDirectory (Default:
    ${project.build.directory}/generated-site)
      Alias: workingDirectory
      Directory containing generated documentation. This is used to pick up
      other source docs that might have been generated at build time.

    generateProjectInfo (Default: true)
      User property: generateProjectInfo
      Whether to generate the summary page for project reports:
      project-info.html.

    inputEncoding (Default: ${project.build.sourceEncoding})
      User property: encoding
      Specifies the input encoding.

    locales
      User property: locales
      A comma separated list of locales supported by Maven. The first valid
      token will be the default Locale for this instance of the Java Virtual
      Machine.

    moduleExcludes
      Module type exclusion mappings ex: fml -> **/*-m1.fml (excludes fml files
      ending in '-m1.fml' recursively) The configuration looks like this:
       <moduleExcludes>
       <moduleType>filename1.ext,**/*sample.ext</moduleType>
       <!-- moduleType can be one of 'apt', 'fml' or 'xdoc'. -->
       <!-- The value is a comma separated list of -->
       <!-- filenames or fileset patterns. -->
       <!-- Here's an example: -->
       <xdoc>changes.xml,navigation.xml</xdoc>
       </moduleExcludes>

    outputEncoding (Default: ${project.reporting.outputEncoding})
      User property: outputEncoding
      Specifies the output encoding.

    port (Default: 8080)
      User property: port
      The port to execute the HTTP server on.

    relativizeDecorationLinks (Default: true)
      User property: relativizeDecorationLinks
      Make links in the site descriptor relative to the project URL. By
      default, any absolute links that appear in the site descriptor, e.g.
      banner hrefs, breadcrumbs, menu links, etc., will be made relative to
      project.url. Links will not be changed if this is set to false, or if the
      project has no URL defined.

    siteDirectory (Default: ${basedir}/src/site)
      Directory containing the site.xml file and the source for apt, fml and
      xdoc docs.

    template
      User property: template
      Default template page.
      Deprecated. use templateFile or skinning instead

    templateDirectory (Default: src/site)
      User property: templateDirectory
      Directory containing the template page.
      Deprecated. use templateFile or skinning instead

    templateFile
      User property: templateFile
      The location of a Velocity template file to use. When used, skins and the
      default templates, CSS and images are disabled. It is highly recommended
      that you package this as a skin instead.

    tempWebappDirectory (Default:
    ${project.build.directory}/site-webapp)
      Where to create the dummy web application.

    xdocDirectory (Default: ${basedir}/xdocs)
      Alternative directory for xdoc source, useful for m1 to m2 migration
      Deprecated. use the standard m2 directory layout

site:site
  Description: Generates the site for a single project.
    Note that links between module sites in a multi module build will not work,
    since local build directory structure doesn't match deployed site.
  Implementation: org.apache.maven.plugins.site.SiteMojo
  Language: java

  Available parameters:

    attributes
      Additional template properties for rendering the site. See Doxia Site
      Renderer.

    generatedSiteDirectory (Default:
    ${project.build.directory}/generated-site)
      Alias: workingDirectory
      Directory containing generated documentation. This is used to pick up
      other source docs that might have been generated at build time.

    generateProjectInfo (Default: true)
      User property: generateProjectInfo
      Whether to generate the summary page for project reports:
      project-info.html.

    generateReports (Default: true)
      User property: generateReports
      Convenience parameter that allows you to disable report generation.

    generateSitemap (Default: false)
      User property: generateSitemap
      Generate a sitemap. The result will be a 'sitemap.html' file at the site
      root.

    inputEncoding (Default: ${project.build.sourceEncoding})
      User property: encoding
      Specifies the input encoding.

    locales
      User property: locales
      A comma separated list of locales supported by Maven. The first valid
      token will be the default Locale for this instance of the Java Virtual
      Machine.

    moduleExcludes
      Module type exclusion mappings ex: fml -> **/*-m1.fml (excludes fml files
      ending in '-m1.fml' recursively) The configuration looks like this:
       <moduleExcludes>
       <moduleType>filename1.ext,**/*sample.ext</moduleType>
       <!-- moduleType can be one of 'apt', 'fml' or 'xdoc'. -->
       <!-- The value is a comma separated list of -->
       <!-- filenames or fileset patterns. -->
       <!-- Here's an example: -->
       <xdoc>changes.xml,navigation.xml</xdoc>
       </moduleExcludes>

    outputDirectory (Default:
    ${project.reporting.outputDirectory})
      User property: siteOutputDirectory
      Directory where the project sites and report distributions will be
      generated.

    outputEncoding (Default: ${project.reporting.outputEncoding})
      User property: outputEncoding
      Specifies the output encoding.

    relativizeDecorationLinks (Default: true)
      User property: relativizeDecorationLinks
      Make links in the site descriptor relative to the project URL. By
      default, any absolute links that appear in the site descriptor, e.g.
      banner hrefs, breadcrumbs, menu links, etc., will be made relative to
      project.url. Links will not be changed if this is set to false, or if the
      project has no URL defined.

    siteDirectory (Default: ${basedir}/src/site)
      Directory containing the site.xml file and the source for apt, fml and
      xdoc docs.

    skip (Default: false)
      User property: maven.site.skip
      Set this to 'true' to skip site generation and staging.

    template
      User property: template
      Default template page.
      Deprecated. use templateFile or skinning instead

    templateDirectory (Default: src/site)
      User property: templateDirectory
      Directory containing the template page.
      Deprecated. use templateFile or skinning instead

    templateFile
      User property: templateFile
      The location of a Velocity template file to use. When used, skins and the
      default templates, CSS and images are disabled. It is highly recommended
      that you package this as a skin instead.

    validate (Default: false)
      User property: validate
      Whether to validate xml input documents. If set to true, all input
      documents in xml format (in particular xdoc and fml) will be validated
      and any error will lead to a build failure.

    xdocDirectory (Default: ${basedir}/xdocs)
      Alternative directory for xdoc source, useful for m1 to m2 migration
      Deprecated. use the standard m2 directory layout

site:stage
  Description: Deploys the generated site to a local staging or mock
    directory based on the site URL specified in the <distributionManagement>
    section of the POM.
    It can be used to test that links between module sites in a multi-module
    build work.
  Implementation: org.apache.maven.plugins.site.SiteStageMojo
  Language: java

  Available parameters:

    chmod (Default: true)
      User property: maven.site.chmod
      Whether to run the 'chmod' command on the remote site after the deploy.
      Defaults to 'true'.

    chmodMode (Default: g+w,a+rX)
      User property: maven.site.chmod.mode
      The mode used by the 'chmod' command. Only used if chmod = true. Defaults
      to 'g+w,a+rX'.

    chmodOptions (Default: -Rf)
      User property: maven.site.chmod.options
      The options used by the 'chmod' command. Only used if chmod = true.
      Defaults to '-Rf'.

    inputDirectory (Default:
    ${project.reporting.outputDirectory})
      Alias: outputDirectory
      Required: true
      Directory containing the generated project sites and report
      distributions.

    inputEncoding (Default: ${project.build.sourceEncoding})
      User property: encoding
      Specifies the input encoding.

    locales
      User property: locales
      A comma separated list of locales supported by Maven. The first valid
      token will be the default Locale for this instance of the Java Virtual
      Machine.

    outputEncoding (Default: ${project.reporting.outputEncoding})
      User property: outputEncoding
      Specifies the output encoding.

    siteDirectory (Default: ${basedir}/src/site)
      Directory containing the site.xml file and the source for apt, fml and
      xdoc docs.

    skip (Default: false)
      User property: maven.site.skip
      Set this to 'true' to skip site generation and staging.

    skipDeploy (Default: false)
      User property: maven.site.deploy.skip
      Set this to 'true' to skip site deployment.

    stagingDirectory
      User property: stagingDirectory
      Staging directory location. This needs to be an absolute path, like
      C:\stagingArea\myProject\ on Windows or /stagingArea/myProject/ on Unix.
      If this is not specified, the site will be staged in
      ${project.build.directory}/staging.

    topSiteURL
      User property: topSiteURL
      Top distribution management site url, for manual configuration when
      auto-calculated value doesn't match expectations. Relative module
      directory will be calculated from this url.

site:stage-deploy
  Description: Deploys the generated site to a staging or mock URL to the
    site URL specified in the <distributionManagement> section of the POM,
    using wagon supported protocols
  Implementation: org.apache.maven.plugins.site.SiteStageDeployMojo
  Language: java

  Available parameters:

    chmod (Default: true)
      User property: maven.site.chmod
      Whether to run the 'chmod' command on the remote site after the deploy.
      Defaults to 'true'.

    chmodMode (Default: g+w,a+rX)
      User property: maven.site.chmod.mode
      The mode used by the 'chmod' command. Only used if chmod = true. Defaults
      to 'g+w,a+rX'.

    chmodOptions (Default: -Rf)
      User property: maven.site.chmod.options
      The options used by the 'chmod' command. Only used if chmod = true.
      Defaults to '-Rf'.

    inputDirectory (Default:
    ${project.reporting.outputDirectory})
      Alias: outputDirectory
      Required: true
      Directory containing the generated project sites and report
      distributions.

    inputEncoding (Default: ${project.build.sourceEncoding})
      User property: encoding
      Specifies the input encoding.

    locales
      User property: locales
      A comma separated list of locales supported by Maven. The first valid
      token will be the default Locale for this instance of the Java Virtual
      Machine.

    outputEncoding (Default: ${project.reporting.outputEncoding})
      User property: outputEncoding
      Specifies the output encoding.

    siteDirectory (Default: ${basedir}/src/site)
      Directory containing the site.xml file and the source for apt, fml and
      xdoc docs.

    skipDeploy (Default: false)
      User property: maven.site.deploy.skip
      Set this to 'true' to skip site deployment.

    stagingRepositoryId
      User property: stagingRepositoryId
      The identifier of the repository where the staging site will be deployed.
      This id will be used to lookup a corresponding <server> entry from the
      settings.xml. If a matching <server> entry is found, its configured
      credentials will be used for authentication. If this is not specified,
      then the corresponding value of distributionManagement.site.id will be
      taken as default, unless this is not defined either then the String
      'stagingSite' is used. (Note: until v. 2.3 and 3.0-beta-3 the String
      'stagingSite' is always used.)

    stagingSiteURL
      User property: stagingSiteURL
      The staged site will be deployed to this URL. If you don't specify this,
      the default-value will be
      '${project.distributionManagement.site.url}/staging', where 'project' is
      either the current project or, in a reactor build, the top level project
      in the reactor.
      Note that even if you specify this plugin parameter, you still need to
      indicate ${project.distributionManagement.site.url} at least in your top
      level project in order for relative links between modules to be resolved
      correctly.

    topSiteURL
      User property: topSiteURL
      Top distribution management site url, for manual configuration when
      auto-calculated value doesn't match expectations. Relative module
      directory will be calculated from this url.
```
