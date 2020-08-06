# assembly


```
Name: Maven Assembly Plugin
Description: A Maven 2 plugin to create archives of your project's sources,
  classes, dependencies etc. from flexible assembly descriptors.
Group Id: org.apache.maven.plugins
Artifact Id: maven-assembly-plugin
Version: 2.2-beta-5
Goal Prefix: assembly

This plugin has 8 goals:

assembly:assembly
  Description: Assemble an application bundle or distribution using an
    assembly descriptor from the command line. This goal will force Maven to
    build all included POMs up to the package phase BEFORE the assembly is
    processed.
    NOTE: This goal should ONLY be run from the command line, and if building a
    multimodule project it should be used from the root POM. Use the
    assembly:single goal for binding your assembly to the lifecycle.

assembly:attached
  Description: Assemble an application bundle or distribution from an
    assembly descriptor, WITHOUT first forcing Maven to build all POMs to the
    package phase (as is required by the assembly:assembly goal).
    NOTE: This goal should ONLY be run from the command line, and if building a
    multimodule project it should be used from the root POM. Use the
    assembly:single goal for binding your assembly to the lifecycle.
  Deprecated. Use goal: 'assembly' (from the command line) or 'single'
  (from a lifecycle binding) instead.

assembly:directory
  Description: Like the assembly:attached goal, assemble an application
    bundle or distribution using an assembly descriptor from the command line.
    This goal will force Maven to build all included POMs up to the package
    phase BEFORE the assembly is processed. This goal differs from
    assembly:assembly in that it ignores the <formats/> section of the assembly
    descriptor, and forces the assembly to be created as a directory in the
    project's build-output directory (usually ./target).
    This goal is also functionally equivalent to using the assembly:assembly
    goal in conjunction with the dir assembly format.
    NOTE: This goal should ONLY be run from the command line, and if building a
    multimodule project it should be used from the root POM. Use the
    assembly:directory-single goal for binding your assembly to the lifecycle.

assembly:directory-inline
  Description: Like the assembly:attached goal, assemble an application
    bundle or distribution from an assembly descriptor, WITHOUT first forcing
    Maven to build all POMs to the package phase (as is required by the
    assembly:assembly goal). This goal differs from assembly:attached in that
    it ignores the <formats/> section of the assembly descriptor, and forces
    the assembly to be created as a directory in the project's build-output
    directory (usually ./target).
    This goal is also functionally equivalent to using the assembly:attached
    goal in conjunction with the dir assembly format.
    NOTE: This goal should ONLY be run from the command line, and if building a
    multimodule project it should be used from the root POM. Use the
    assembly:directory-single goal for binding your assembly to the lifecycle.
  Deprecated. Use goal: 'directory' (from the command line) or
  'directory-single' (from a lifecycle binding) instead.

assembly:directory-single
  Description: Like the assembly:attached goal, assemble an application
    bundle or distribution from an assembly descriptor. This goal is suitable
    either for binding to the lifecycle or calling directly from the command
    line (provided all required files are available before the build starts, or
    are produced by another goal specified before this one on the command
    line).
    This goal differs from assembly:single in that it ignores the <formats/>
    section of the assembly descriptor, and forces the assembly to be created
    as a directory in the project's build-output directory (usually ./target).

assembly:help
  Description: Display help information on maven-assembly-plugin.
    Call
     mvn assembly:help -Ddetail=true -Dgoal=<goal-name>
    to display parameter details.

assembly:single
  Description: Assemble an application bundle or distribution from an
    assembly descriptor. This goal is suitable either for binding to the
    lifecycle or calling directly from the command line (provided all required
    files are available before the build starts, or are produced by another
    goal specified before this one on the command line).

assembly:unpack
  Description: Unpack project dependencies. Currently supports dependencies
    of type jar and zip.
  Deprecated. Use org.apache.maven.plugins:maven-dependency-plugin goal:
  unpack or unpack-dependencies instead.

For more information, run 'mvn help:describe [...] -Ddetail'
```
## Detailed Info

```
Name: Maven Assembly Plugin
Description: A Maven 2 plugin to create archives of your project's sources,
  classes, dependencies etc. from flexible assembly descriptors.
Group Id: org.apache.maven.plugins
Artifact Id: maven-assembly-plugin
Version: 2.2-beta-5
Goal Prefix: assembly

This plugin has 8 goals:

assembly:assembly
  Description: Assemble an application bundle or distribution using an
    assembly descriptor from the command line. This goal will force Maven to
    build all included POMs up to the package phase BEFORE the assembly is
    processed.
    NOTE: This goal should ONLY be run from the command line, and if building a
    multimodule project it should be used from the root POM. Use the
    assembly:single goal for binding your assembly to the lifecycle.
  Implementation: org.apache.maven.plugin.assembly.mojos.AssemblyMojo
  Language: java
  Before this goal executes, it will call:
    Phase: 'package'

  Available parameters:

    appendAssemblyId (Default: true)
      User property: appendAssemblyId
      Set to false to exclude the assembly id from the assembly final name.

    archive
      This is a set of instructions to the archive builder, especially for
      building .jar files. It enables you to specify a Manifest file for the
      jar, in addition to other options.

    archiveBaseDirectory
      This is the base directory from which archive files are created. This
      base directory pre-pended to any <directory> specifications in the
      assembly descriptor. This is an optional parameter.

    archiverConfig
      Allows additional configuration options that are specific to a particular
      type of archive format. This is intended to capture an XML configuration
      that will be used to reflectively setup the options on the archiver
      instance.
      For instance, to direct an assembly with the 'ear' format to use a
      particular deployment descriptor, you should specify the following for
      the archiverConfig value in your plugin configuration:
      
      <appxml>${project.basedir}/somepath/app.xml</appxml>

    attach (Default: true)
      User property: attach
      Controls whether the assembly plugin tries to attach the resulting
      assembly to the project.

    classifier
      User property: classifier
      This is the artifact classifier to be used for the resultant assembly
      artifact. Normally, you would use the assembly-id instead of specifying
      this here.
      Deprecated. Please use the Assembly's id for classifier instead

    descriptor
      User property: descriptor
      Assembly XML Descriptor file. This must be the path to your customized
      descriptor file.
      Deprecated. Please use descriptors instead

    descriptorId
      User property: descriptorId
      Predefined Assembly Descriptor Id's. You can select bin,
      jar-with-dependencies, or src.
      Deprecated. Please use descriptorRefs instead

    descriptorRefs
      A list of built-in descriptor references to generate from. You can select
      from bin, jar-with-dependencies, or src.

    descriptors
      A list of descriptor files to generate from.

    descriptorSourceDirectory
      Directory to scan for descriptor files in.

    dryRun (Default: false)
      User property: assembly.dryRun
      If this flag is set, everything up to the call to
      Archiver.createArchive() will be executed.

    executedProject
      User property: executedProject
      Get the executed project from the forked lifecycle.

    filters
      (no description available)

    finalName (Default: ${project.build.finalName})
      Required: true
      The filename of the assembled distribution file.

    ignoreDirFormatExtensions (Default: true)
      If this flag is set, the '.dir' suffix will be suppressed in the output
      directory name when using assembly/format == 'dir' and other formats that
      begin with 'dir'.
      NOTE: Since 2.2-beta-3, the default-value for this is true, NOT false as
      it used to be.

    ignoreMissingDescriptor (Default: false)
      User property: ignoreMissingDescriptor
      Set to true in order to not fail when a descriptor is missing.

    includeSite (Default: false)
      User property: includeSite
      Set to true to include the site generated by site:site goal.
      Deprecated. Please set this variable in the assembly descriptor
      instead

    outputDirectory (Default: ${project.build.directory})
      Required: true
      The output directory of the assembled distribution file.

    runOnlyAtExecutionRoot (Default: false)
      User property: runOnlyAtExecutionRoot
      This will cause the assembly to run only at the top of a given module
      tree. That is, run in the project contained in the same folder where the
      mvn execution was launched.

    skipAssembly (Default: false)
      User property: skipAssembly
      Flag allowing one or more executions of the assembly plugin to be
      configured as skipped for a particular build. This makes the assembly
      plugin more controllable from profiles.

    tarLongFileMode (Default: warn)
      User property: tarLongFileMode
      Sets the TarArchiver behavior on file paths with more than 100 characters
      length. Valid values are: 'warn' (default), 'fail', 'truncate', 'gnu', or
      'omit'.

    workDirectory (Default:
    ${project.build.directory}/assembly/work)
      Required: true
      Directory to unpack JARs into if needed

assembly:attached
  Description: Assemble an application bundle or distribution from an
    assembly descriptor, WITHOUT first forcing Maven to build all POMs to the
    package phase (as is required by the assembly:assembly goal).
    NOTE: This goal should ONLY be run from the command line, and if building a
    multimodule project it should be used from the root POM. Use the
    assembly:single goal for binding your assembly to the lifecycle.
  Deprecated. Use goal: 'assembly' (from the command line) or 'single'
  (from a lifecycle binding) instead.
  Implementation: org.apache.maven.plugin.assembly.mojos.AttachedAssemblyMojo
  Language: java

  Available parameters:

    appendAssemblyId (Default: true)
      User property: appendAssemblyId
      Set to false to exclude the assembly id from the assembly final name.

    archive
      This is a set of instructions to the archive builder, especially for
      building .jar files. It enables you to specify a Manifest file for the
      jar, in addition to other options.

    archiveBaseDirectory
      This is the base directory from which archive files are created. This
      base directory pre-pended to any <directory> specifications in the
      assembly descriptor. This is an optional parameter.

    archiverConfig
      Allows additional configuration options that are specific to a particular
      type of archive format. This is intended to capture an XML configuration
      that will be used to reflectively setup the options on the archiver
      instance.
      For instance, to direct an assembly with the 'ear' format to use a
      particular deployment descriptor, you should specify the following for
      the archiverConfig value in your plugin configuration:
      
      <appxml>${project.basedir}/somepath/app.xml</appxml>

    attach (Default: true)
      User property: attach
      Controls whether the assembly plugin tries to attach the resulting
      assembly to the project.

    classifier
      User property: classifier
      This is the artifact classifier to be used for the resultant assembly
      artifact. Normally, you would use the assembly-id instead of specifying
      this here.
      Deprecated. Please use the Assembly's id for classifier instead

    descriptor
      User property: descriptor
      Assembly XML Descriptor file. This must be the path to your customized
      descriptor file.
      Deprecated. Please use descriptors instead

    descriptorId
      User property: descriptorId
      Predefined Assembly Descriptor Id's. You can select bin,
      jar-with-dependencies, or src.
      Deprecated. Please use descriptorRefs instead

    descriptorRefs
      A list of built-in descriptor references to generate from. You can select
      from bin, jar-with-dependencies, or src.

    descriptors
      A list of descriptor files to generate from.

    descriptorSourceDirectory
      Directory to scan for descriptor files in.

    dryRun (Default: false)
      User property: assembly.dryRun
      If this flag is set, everything up to the call to
      Archiver.createArchive() will be executed.

    filters
      (no description available)

    finalName (Default: ${project.build.finalName})
      Required: true
      The filename of the assembled distribution file.

    ignoreDirFormatExtensions (Default: true)
      If this flag is set, the '.dir' suffix will be suppressed in the output
      directory name when using assembly/format == 'dir' and other formats that
      begin with 'dir'.
      NOTE: Since 2.2-beta-3, the default-value for this is true, NOT false as
      it used to be.

    ignoreMissingDescriptor (Default: false)
      User property: ignoreMissingDescriptor
      Set to true in order to not fail when a descriptor is missing.

    includeSite (Default: false)
      User property: includeSite
      Set to true to include the site generated by site:site goal.
      Deprecated. Please set this variable in the assembly descriptor
      instead

    outputDirectory (Default: ${project.build.directory})
      Required: true
      The output directory of the assembled distribution file.

    runOnlyAtExecutionRoot (Default: false)
      User property: runOnlyAtExecutionRoot
      This will cause the assembly to run only at the top of a given module
      tree. That is, run in the project contained in the same folder where the
      mvn execution was launched.

    skipAssembly (Default: false)
      User property: skipAssembly
      Flag allowing one or more executions of the assembly plugin to be
      configured as skipped for a particular build. This makes the assembly
      plugin more controllable from profiles.

    tarLongFileMode (Default: warn)
      User property: tarLongFileMode
      Sets the TarArchiver behavior on file paths with more than 100 characters
      length. Valid values are: 'warn' (default), 'fail', 'truncate', 'gnu', or
      'omit'.

    workDirectory (Default:
    ${project.build.directory}/assembly/work)
      Required: true
      Directory to unpack JARs into if needed

assembly:directory
  Description: Like the assembly:attached goal, assemble an application
    bundle or distribution using an assembly descriptor from the command line.
    This goal will force Maven to build all included POMs up to the package
    phase BEFORE the assembly is processed. This goal differs from
    assembly:assembly in that it ignores the <formats/> section of the assembly
    descriptor, and forces the assembly to be created as a directory in the
    project's build-output directory (usually ./target).
    This goal is also functionally equivalent to using the assembly:assembly
    goal in conjunction with the dir assembly format.
    NOTE: This goal should ONLY be run from the command line, and if building a
    multimodule project it should be used from the root POM. Use the
    assembly:directory-single goal for binding your assembly to the lifecycle.
  Implementation: org.apache.maven.plugin.assembly.mojos.DirectoryMojo
  Language: java
  Before this goal executes, it will call:
    Phase: 'package'

  Available parameters:

    appendAssemblyId (Default: true)
      User property: appendAssemblyId
      Set to false to exclude the assembly id from the assembly final name.

    archive
      This is a set of instructions to the archive builder, especially for
      building .jar files. It enables you to specify a Manifest file for the
      jar, in addition to other options.

    archiveBaseDirectory
      This is the base directory from which archive files are created. This
      base directory pre-pended to any <directory> specifications in the
      assembly descriptor. This is an optional parameter.

    archiverConfig
      Allows additional configuration options that are specific to a particular
      type of archive format. This is intended to capture an XML configuration
      that will be used to reflectively setup the options on the archiver
      instance.
      For instance, to direct an assembly with the 'ear' format to use a
      particular deployment descriptor, you should specify the following for
      the archiverConfig value in your plugin configuration:
      
      <appxml>${project.basedir}/somepath/app.xml</appxml>

    attach (Default: true)
      User property: attach
      Controls whether the assembly plugin tries to attach the resulting
      assembly to the project.

    classifier
      User property: classifier
      This is the artifact classifier to be used for the resultant assembly
      artifact. Normally, you would use the assembly-id instead of specifying
      this here.
      Deprecated. Please use the Assembly's id for classifier instead

    descriptor
      User property: descriptor
      Assembly XML Descriptor file. This must be the path to your customized
      descriptor file.
      Deprecated. Please use descriptors instead

    descriptorId
      User property: descriptorId
      Predefined Assembly Descriptor Id's. You can select bin,
      jar-with-dependencies, or src.
      Deprecated. Please use descriptorRefs instead

    descriptorRefs
      A list of built-in descriptor references to generate from. You can select
      from bin, jar-with-dependencies, or src.

    descriptors
      A list of descriptor files to generate from.

    descriptorSourceDirectory
      Directory to scan for descriptor files in.

    dryRun (Default: false)
      User property: assembly.dryRun
      If this flag is set, everything up to the call to
      Archiver.createArchive() will be executed.

    executedProject
      User property: executedProject
      Get the executed project from the forked lifecycle.

    filters
      (no description available)

    finalName (Default: ${project.build.finalName})
      Required: true
      The filename of the assembled distribution file.

    ignoreDirFormatExtensions (Default: true)
      If this flag is set, the '.dir' suffix will be suppressed in the output
      directory name when using assembly/format == 'dir' and other formats that
      begin with 'dir'.
      NOTE: Since 2.2-beta-3, the default-value for this is true, NOT false as
      it used to be.

    ignoreMissingDescriptor (Default: false)
      User property: ignoreMissingDescriptor
      Set to true in order to not fail when a descriptor is missing.

    includeSite (Default: false)
      User property: includeSite
      Set to true to include the site generated by site:site goal.
      Deprecated. Please set this variable in the assembly descriptor
      instead

    outputDirectory (Default: ${project.build.directory})
      Required: true
      The output directory of the assembled distribution file.

    runOnlyAtExecutionRoot (Default: false)
      User property: runOnlyAtExecutionRoot
      This will cause the assembly to run only at the top of a given module
      tree. That is, run in the project contained in the same folder where the
      mvn execution was launched.

    skipAssembly (Default: false)
      User property: skipAssembly
      Flag allowing one or more executions of the assembly plugin to be
      configured as skipped for a particular build. This makes the assembly
      plugin more controllable from profiles.

    tarLongFileMode (Default: warn)
      User property: tarLongFileMode
      Sets the TarArchiver behavior on file paths with more than 100 characters
      length. Valid values are: 'warn' (default), 'fail', 'truncate', 'gnu', or
      'omit'.

    workDirectory (Default:
    ${project.build.directory}/assembly/work)
      Required: true
      Directory to unpack JARs into if needed

assembly:directory-inline
  Description: Like the assembly:attached goal, assemble an application
    bundle or distribution from an assembly descriptor, WITHOUT first forcing
    Maven to build all POMs to the package phase (as is required by the
    assembly:assembly goal). This goal differs from assembly:attached in that
    it ignores the <formats/> section of the assembly descriptor, and forces
    the assembly to be created as a directory in the project's build-output
    directory (usually ./target).
    This goal is also functionally equivalent to using the assembly:attached
    goal in conjunction with the dir assembly format.
    NOTE: This goal should ONLY be run from the command line, and if building a
    multimodule project it should be used from the root POM. Use the
    assembly:directory-single goal for binding your assembly to the lifecycle.
  Deprecated. Use goal: 'directory' (from the command line) or
  'directory-single' (from a lifecycle binding) instead.
  Implementation: org.apache.maven.plugin.assembly.mojos.DirectoryInlineMojo
  Language: java

  Available parameters:

    appendAssemblyId (Default: true)
      User property: appendAssemblyId
      Set to false to exclude the assembly id from the assembly final name.

    archive
      This is a set of instructions to the archive builder, especially for
      building .jar files. It enables you to specify a Manifest file for the
      jar, in addition to other options.

    archiveBaseDirectory
      This is the base directory from which archive files are created. This
      base directory pre-pended to any <directory> specifications in the
      assembly descriptor. This is an optional parameter.

    archiverConfig
      Allows additional configuration options that are specific to a particular
      type of archive format. This is intended to capture an XML configuration
      that will be used to reflectively setup the options on the archiver
      instance.
      For instance, to direct an assembly with the 'ear' format to use a
      particular deployment descriptor, you should specify the following for
      the archiverConfig value in your plugin configuration:
      
      <appxml>${project.basedir}/somepath/app.xml</appxml>

    attach (Default: true)
      User property: attach
      Controls whether the assembly plugin tries to attach the resulting
      assembly to the project.

    classifier
      User property: classifier
      This is the artifact classifier to be used for the resultant assembly
      artifact. Normally, you would use the assembly-id instead of specifying
      this here.
      Deprecated. Please use the Assembly's id for classifier instead

    descriptor
      User property: descriptor
      Assembly XML Descriptor file. This must be the path to your customized
      descriptor file.
      Deprecated. Please use descriptors instead

    descriptorId
      User property: descriptorId
      Predefined Assembly Descriptor Id's. You can select bin,
      jar-with-dependencies, or src.
      Deprecated. Please use descriptorRefs instead

    descriptorRefs
      A list of built-in descriptor references to generate from. You can select
      from bin, jar-with-dependencies, or src.

    descriptors
      A list of descriptor files to generate from.

    descriptorSourceDirectory
      Directory to scan for descriptor files in.

    dryRun (Default: false)
      User property: assembly.dryRun
      If this flag is set, everything up to the call to
      Archiver.createArchive() will be executed.

    filters
      (no description available)

    finalName (Default: ${project.build.finalName})
      Required: true
      The filename of the assembled distribution file.

    ignoreDirFormatExtensions (Default: true)
      If this flag is set, the '.dir' suffix will be suppressed in the output
      directory name when using assembly/format == 'dir' and other formats that
      begin with 'dir'.
      NOTE: Since 2.2-beta-3, the default-value for this is true, NOT false as
      it used to be.

    ignoreMissingDescriptor (Default: false)
      User property: ignoreMissingDescriptor
      Set to true in order to not fail when a descriptor is missing.

    includeSite (Default: false)
      User property: includeSite
      Set to true to include the site generated by site:site goal.
      Deprecated. Please set this variable in the assembly descriptor
      instead

    outputDirectory (Default: ${project.build.directory})
      Required: true
      The output directory of the assembled distribution file.

    runOnlyAtExecutionRoot (Default: false)
      User property: runOnlyAtExecutionRoot
      This will cause the assembly to run only at the top of a given module
      tree. That is, run in the project contained in the same folder where the
      mvn execution was launched.

    skipAssembly (Default: false)
      User property: skipAssembly
      Flag allowing one or more executions of the assembly plugin to be
      configured as skipped for a particular build. This makes the assembly
      plugin more controllable from profiles.

    tarLongFileMode (Default: warn)
      User property: tarLongFileMode
      Sets the TarArchiver behavior on file paths with more than 100 characters
      length. Valid values are: 'warn' (default), 'fail', 'truncate', 'gnu', or
      'omit'.

    workDirectory (Default:
    ${project.build.directory}/assembly/work)
      Required: true
      Directory to unpack JARs into if needed

assembly:directory-single
  Description: Like the assembly:attached goal, assemble an application
    bundle or distribution from an assembly descriptor. This goal is suitable
    either for binding to the lifecycle or calling directly from the command
    line (provided all required files are available before the build starts, or
    are produced by another goal specified before this one on the command
    line).
    This goal differs from assembly:single in that it ignores the <formats/>
    section of the assembly descriptor, and forces the assembly to be created
    as a directory in the project's build-output directory (usually ./target).
  Implementation: org.apache.maven.plugin.assembly.mojos.DirectorySingleMojo
  Language: java

  Available parameters:

    appendAssemblyId (Default: true)
      User property: appendAssemblyId
      Set to false to exclude the assembly id from the assembly final name.

    archive
      This is a set of instructions to the archive builder, especially for
      building .jar files. It enables you to specify a Manifest file for the
      jar, in addition to other options.

    archiveBaseDirectory
      This is the base directory from which archive files are created. This
      base directory pre-pended to any <directory> specifications in the
      assembly descriptor. This is an optional parameter.

    archiverConfig
      Allows additional configuration options that are specific to a particular
      type of archive format. This is intended to capture an XML configuration
      that will be used to reflectively setup the options on the archiver
      instance.
      For instance, to direct an assembly with the 'ear' format to use a
      particular deployment descriptor, you should specify the following for
      the archiverConfig value in your plugin configuration:
      
      <appxml>${project.basedir}/somepath/app.xml</appxml>

    attach (Default: true)
      User property: attach
      Controls whether the assembly plugin tries to attach the resulting
      assembly to the project.

    classifier
      User property: classifier
      This is the artifact classifier to be used for the resultant assembly
      artifact. Normally, you would use the assembly-id instead of specifying
      this here.
      Deprecated. Please use the Assembly's id for classifier instead

    descriptor
      User property: descriptor
      Assembly XML Descriptor file. This must be the path to your customized
      descriptor file.
      Deprecated. Please use descriptors instead

    descriptorId
      User property: descriptorId
      Predefined Assembly Descriptor Id's. You can select bin,
      jar-with-dependencies, or src.
      Deprecated. Please use descriptorRefs instead

    descriptorRefs
      A list of built-in descriptor references to generate from. You can select
      from bin, jar-with-dependencies, or src.

    descriptors
      A list of descriptor files to generate from.

    descriptorSourceDirectory
      Directory to scan for descriptor files in.

    dryRun (Default: false)
      User property: assembly.dryRun
      If this flag is set, everything up to the call to
      Archiver.createArchive() will be executed.

    filters
      (no description available)

    finalName (Default: ${project.build.finalName})
      Required: true
      The filename of the assembled distribution file.

    ignoreDirFormatExtensions (Default: true)
      If this flag is set, the '.dir' suffix will be suppressed in the output
      directory name when using assembly/format == 'dir' and other formats that
      begin with 'dir'.
      NOTE: Since 2.2-beta-3, the default-value for this is true, NOT false as
      it used to be.

    ignoreMissingDescriptor (Default: false)
      User property: ignoreMissingDescriptor
      Set to true in order to not fail when a descriptor is missing.

    includeSite (Default: false)
      User property: includeSite
      Set to true to include the site generated by site:site goal.
      Deprecated. Please set this variable in the assembly descriptor
      instead

    outputDirectory (Default: ${project.build.directory})
      Required: true
      The output directory of the assembled distribution file.

    runOnlyAtExecutionRoot (Default: false)
      User property: runOnlyAtExecutionRoot
      This will cause the assembly to run only at the top of a given module
      tree. That is, run in the project contained in the same folder where the
      mvn execution was launched.

    skipAssembly (Default: false)
      User property: skipAssembly
      Flag allowing one or more executions of the assembly plugin to be
      configured as skipped for a particular build. This makes the assembly
      plugin more controllable from profiles.

    tarLongFileMode (Default: warn)
      User property: tarLongFileMode
      Sets the TarArchiver behavior on file paths with more than 100 characters
      length. Valid values are: 'warn' (default), 'fail', 'truncate', 'gnu', or
      'omit'.

    workDirectory (Default:
    ${project.build.directory}/assembly/work)
      Required: true
      Directory to unpack JARs into if needed

assembly:help
  Description: Display help information on maven-assembly-plugin.
    Call
     mvn assembly:help -Ddetail=true -Dgoal=<goal-name>
    to display parameter details.
  Implementation: org.apache.maven.plugin.assembly.mojos.HelpMojo
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

assembly:single
  Description: Assemble an application bundle or distribution from an
    assembly descriptor. This goal is suitable either for binding to the
    lifecycle or calling directly from the command line (provided all required
    files are available before the build starts, or are produced by another
    goal specified before this one on the command line).
  Implementation: org.apache.maven.plugin.assembly.mojos.SingleAssemblyMojo
  Language: java

  Available parameters:

    appendAssemblyId (Default: true)
      User property: appendAssemblyId
      Set to false to exclude the assembly id from the assembly final name.

    archive
      This is a set of instructions to the archive builder, especially for
      building .jar files. It enables you to specify a Manifest file for the
      jar, in addition to other options.

    archiveBaseDirectory
      This is the base directory from which archive files are created. This
      base directory pre-pended to any <directory> specifications in the
      assembly descriptor. This is an optional parameter.

    archiverConfig
      Allows additional configuration options that are specific to a particular
      type of archive format. This is intended to capture an XML configuration
      that will be used to reflectively setup the options on the archiver
      instance.
      For instance, to direct an assembly with the 'ear' format to use a
      particular deployment descriptor, you should specify the following for
      the archiverConfig value in your plugin configuration:
      
      <appxml>${project.basedir}/somepath/app.xml</appxml>

    attach (Default: true)
      User property: attach
      Controls whether the assembly plugin tries to attach the resulting
      assembly to the project.

    classifier
      User property: classifier
      This is the artifact classifier to be used for the resultant assembly
      artifact. Normally, you would use the assembly-id instead of specifying
      this here.
      Deprecated. Please use the Assembly's id for classifier instead

    descriptor
      User property: descriptor
      Assembly XML Descriptor file. This must be the path to your customized
      descriptor file.
      Deprecated. Please use descriptors instead

    descriptorId
      User property: descriptorId
      Predefined Assembly Descriptor Id's. You can select bin,
      jar-with-dependencies, or src.
      Deprecated. Please use descriptorRefs instead

    descriptorRefs
      A list of built-in descriptor references to generate from. You can select
      from bin, jar-with-dependencies, or src.

    descriptors
      A list of descriptor files to generate from.

    descriptorSourceDirectory
      Directory to scan for descriptor files in.

    dryRun (Default: false)
      User property: assembly.dryRun
      If this flag is set, everything up to the call to
      Archiver.createArchive() will be executed.

    filters
      (no description available)

    finalName (Default: ${project.build.finalName})
      Required: true
      The filename of the assembled distribution file.

    ignoreDirFormatExtensions (Default: true)
      If this flag is set, the '.dir' suffix will be suppressed in the output
      directory name when using assembly/format == 'dir' and other formats that
      begin with 'dir'.
      NOTE: Since 2.2-beta-3, the default-value for this is true, NOT false as
      it used to be.

    ignoreMissingDescriptor (Default: false)
      User property: ignoreMissingDescriptor
      Set to true in order to not fail when a descriptor is missing.

    includeSite (Default: false)
      User property: includeSite
      Set to true to include the site generated by site:site goal.
      Deprecated. Please set this variable in the assembly descriptor
      instead

    outputDirectory (Default: ${project.build.directory})
      Required: true
      The output directory of the assembled distribution file.

    runOnlyAtExecutionRoot (Default: false)
      User property: runOnlyAtExecutionRoot
      This will cause the assembly to run only at the top of a given module
      tree. That is, run in the project contained in the same folder where the
      mvn execution was launched.

    skipAssembly (Default: false)
      User property: skipAssembly
      Flag allowing one or more executions of the assembly plugin to be
      configured as skipped for a particular build. This makes the assembly
      plugin more controllable from profiles.

    tarLongFileMode (Default: warn)
      User property: tarLongFileMode
      Sets the TarArchiver behavior on file paths with more than 100 characters
      length. Valid values are: 'warn' (default), 'fail', 'truncate', 'gnu', or
      'omit'.

    workDirectory (Default:
    ${project.build.directory}/assembly/work)
      Required: true
      Directory to unpack JARs into if needed

assembly:unpack
  Description: Unpack project dependencies. Currently supports dependencies
    of type jar and zip.
  Deprecated. Use org.apache.maven.plugins:maven-dependency-plugin goal:
  unpack or unpack-dependencies instead.
  Implementation: org.apache.maven.plugin.assembly.mojos.UnpackMojo
  Language: java

  Available parameters:

    workDirectory
      Required: true
      Expression: ${project.build.directory}/assembly/work
      Directory to unpack JARs into if needed
```
