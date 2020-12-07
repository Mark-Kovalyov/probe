# Mojo's AspectJ Maven Plugin

```
Name: Mojo's AspectJ Maven Plugin
Description: Handles AspectJ usage within Maven. Functionality provided is:
  weaving of aspects (or existing aspects from libraries) with the test and/or
  main classes, weaving of pre-existing jars and ajdoc reporting.
Group Id: org.codehaus.mojo
Artifact Id: aspectj-maven-plugin
Version: 1.11
Goal Prefix: aspectj

This plugin has 5 goals:

aspectj:aspectj-report
  Description: Creates an AspectJ HTML report using the ajdoc tool and
    format.
  Note: This goal should be used as a Maven report.

aspectj:compile
  Description: Weaves all main classes.

aspectj:EclipseAjcMojo
  Description: Create eclipse configuration of aspectJ

aspectj:help
  Description: Display help information on aspectj-maven-plugin.
    Call mvn aspectj:help -Ddetail=true -Dgoal=<goal-name> to display parameter
    details.

aspectj:test-compile
  Description: Weaves all test classes.
```

## Detailed

```
Name: Mojo's AspectJ Maven Plugin
Description: Handles AspectJ usage within Maven. Functionality provided is:
  weaving of aspects (or existing aspects from libraries) with the test and/or
  main classes, weaving of pre-existing jars and ajdoc reporting.
Group Id: org.codehaus.mojo
Artifact Id: aspectj-maven-plugin
Version: 1.11
Goal Prefix: aspectj

This plugin has 5 goals:

aspectj:aspectj-report
  Description: Creates an AspectJ HTML report using the ajdoc tool and
    format.
  Note: This goal should be used as a Maven report.
  Implementation: org.codehaus.mojo.aspectj.AjcReportMojo
  Language: java

  Available parameters:

    ajdtBuildDefFile
      Where to find the ajdt build definition file. If set this will override
      the use of project sourcedirs.

    aspectDirectory (Default: src/main/aspect)
      The source directory for the aspects

    complianceLevel (Default: ${mojo.java.target})
      Specify compiler compliance setting (1.3 to 1.8, default is 1.5)

    doctitle
      Specifies the title to be placed near the top of the overview summary
      file. The title will be placed as a centered, level-one heading directly
      beneath the upper navigation bar. The title may contain html tags and
      white space, though if it does, it must be enclosed in quotes. Any
      internal quotation marks within title may have to be escaped.

    outputDirectory (Default:
    ${project.reporting.outputDirectory}/aspectj-report)
      Required: true
      The output directory for the report.

    overview
      Specifies that javadoc should retrieve the text for the overview
      documentation from the 'source' file specified by path/filename and place
      it on the Overview page (overview-summary.html). The path/filename is
      relative to the ${basedir}. While you can use any name you want for
      filename and place it anywhere you want for path, a typical thing to do
      is to name it overview.html and place it in the source tree at the
      directory that contains the topmost package directories. In this
      location, no path is needed when documenting packages, since -sourcepath
      will point to this file. For example, if the source tree for the
      java.lang package is /src/classes/java/lang/, then you could place the
      overview file at /src/classes/overview.html. See Real World Example. For
      information about the file specified by path/filename, see overview
      comment file.Note that the overview page is created only if you pass into
      javadoc two or more package names. For further explanation, see HTML
      Frames.) The title on the overview page is set by -doctitle.

    packageScope
      Shows only package, protected, and public classes and members.

    privateScope
      Shows all classes and members.

    protectedScope
      Shows only protected and public classes and members. This is the default.

    publicScope
      Shows only public classes and members.

    testAspectDirectory (Default: src/test/aspect)
      The source directory for the test aspects

    verbose
      Provides more detailed messages while javadoc is running. Without the
      verbose option, messages appear for loading the source files, generating
      the documentation (one message per source file), and sorting. The verbose
      option causes the printing of additional messages specifying the number
      of milliseconds to parse each java source file.

aspectj:compile
  Description: Weaves all main classes.
  Implementation: org.codehaus.mojo.aspectj.AjcCompileMojo
  Language: java
  Bound to phase: compile

  Available parameters:

    ajdtBuildDefFile
      Where to find the ajdt build definition file. If set this will override
      the use of project sourcedirs.

    argumentFileName (Default: builddef.lst)
      The filename holding AJC build arguments. The file will be placed in the
      project build output directory, and will contain all the arguments passed
      to the AJC compiler in the last run, and also all the files included in
      the AJC build. Sample content shown below to illustrate typical content
      within the builddef.lst file:
       -1.6
      -encoding
      UTF-8
      -classpath
      /Users/lj/Development/Projects/Nazgul/nazgul_tools/validation/validation-api/target/nazgul-tools-validation-api-2.0.10-SNAPSHOT.jar:/Users/lj/.m2/repository/org/slf4j/slf4j-api/1.7.5/slf4j-api-1.7.5.jar:/Users/lj/.m2/repository/org/aspectj/aspectjrt/1.7.3/aspectjrt-1.7.3.jar:/Users/lj/.m2/repository/junit/junit/4.11/junit-4.11.jar:/Users/lj/.m2/repository/ch/qos/logback/logback-classic/1.0.13/logback-classic-1.0.13.jar:/Users/lj/.m2/repository/org/apache/commons/commons-lang3/3.1/commons-lang3-3.1.jar:/Users/lj/Development/Projects/Nazgul/nazgul_tools/validation/validation-aspect/target/classes
      -d
      /Users/lj/Development/Projects/Nazgul/nazgul_tools/validation/validation-aspect/target/classes
      /Users/lj/Development/Projects/Nazgul/nazgul_tools/validation/validation-aspect/src/main/java/se/jguru/nazgul/tools/validation/aspect/ValidationAspect.java
      

    aspectDirectory (Default: src/main/aspect)
      The source directory for the aspects.

    aspectLibraries
      Weave binary aspects from the jars. The aspects should have been output
      by the same version of the compiler. The modules must also be
      dependencies of the project. Corresponds to ajc -aspectpath option

    bootclasspath
      Override location of VM's bootclasspath for purposes of evaluating types
      when compiling. Path is a single argument containing a list of paths to
      zip files or directories, delimited by the platform-specific path
      delimiter.

    complianceLevel (Default: 1.4)
      Specify compiler compliance setting. Defaults to 1.4, with permitted
      values ('1.3', '1.4', '1.5', '1.6' and '1.7', '1.8').

    crossrefs
      generate .ajsym file into the output directory

    deprecation
      Toggle warning messages on deprecations

    emacssym
      Generate .ajesym symbol files for emacs support.

    encoding
      User property: project.build.sourceEncoding
      Specify default source encoding format.

    excludes
      List of ant-style patterns used to specify the aspects that should be
      excluded when compiling. When none specified all .java and .aj files in
      the project source directories, or directories specified by the
      ajdtDefFile property are included.

    forceAjcCompile (Default: false)
      Forces re-compilation, regardless of whether the compiler arguments or
      the sources have changed.

    includes
      List of ant-style patterns used to specify the aspects that should be
      included when compiling. When none specified all .java and .aj files in
      the project source directories, or directories specified by the
      ajdtDefFile property are included.

    noImportError
      Emit no errors for unresolved imports;

    outxml
      Generate aop.xml file for load-time weaving with default name
      (/META-INF/aop.xml).

    outxmlfile
      Generate aop.xml file for load-time weaving with custom name.

    parameters
      Set the compiler 'parameters' argument.

    preserveAllLocals
      Preserve all local variables during code generation (to facilitate
      debugging).

    proc
      Set the compiler 'proc' argument. Aspectj supports Annotation processing
      since 1.8.2, it can been disabled by proc:none.

    proceedOnError
      Keep compiling after error, dumping class files with problem methods

    referenceInfo
      Compute reference information.

    repeat
      Repeat compilation process N times (typically to do performance
      analysis).

    showWeaveInfo
      Emit messages about weaving

    skip (Default: false)
      User property: aspectj.skip
      Skip plugin execution.

    source (Default: ${mojo.java.target})
      Toggle assertions (1.3, 1.4, 1.5, 1.6, 1.7 or 1.8 - default is 1.4). When
      using -source 1.3, an assert() statement valid under Java 1.4 will result
      in a compiler error. When using -source 1.4, treat assert as a keyword
      and implement assertions according to the 1.4 language spec. When using
      -source 1.5 or higher, Java 5 language features are permitted. With
      --source 1.7 or higher Java 7 features are supported.

    sources
      Set the java source folders to use, specifying the includes and excludes.
      
      If you don't specify this parameter, all java sources of the current
      project fill be used. If you specify this parameter as an empty tag (i.e.
      <sources/>), all source folders will be ignored. Otherwise specify the
      source folder(s) to use.

    target (Default: ${project.build.java.target})
      Specify classfile target setting (1.1 to 1.8) default is 1.2

    testAspectDirectory (Default: src/test/aspect)
      The source directory for the test aspects.

    verbose
      Emit messages about accessed/processed compilation units

    warn
      Emit warnings for any instances of the comma-delimited list of
      questionable code. Supported values are shown in the list below, with
      their respective explanations - as copied directly from the AJC
      reference.
      constructorName
        method with constructor name
      packageDefaultMethod
        attempt to override package-default method
      deprecation
        usage of deprecated type or member
      maskedCatchBlocks
        hidden catch block
      unusedLocals
        local variable never read
      unusedArguments
        method argument never read
      unusedImports
        import statement not used by code in file
      none
        suppress all compiler warnings

    weaveDependencies
      List of of modules to weave (into target directory). Corresponds to ajc
      -inpath option (or -injars for pre-1.2 (which is not supported)).

    weaveDirectories
      List of of directories with .class files to weave (into target
      directory). Corresponds to ajc -inpath option.

    XaddSerialVersionUID
      Causes the compiler to calculate and add the SerialVersionUID field to
      any type implementing Serializable that is affected by an aspect. The
      field is calculated based on the class before weaving has taken place.

    Xajruntimetarget (Default: 1.5)
      (Experimental) Allows code to be generated that targets a 1.2 or a 1.5
      level AspectJ runtime (default 1.5)

    XhasMember
      Enables the compiler to support hasmethod(method_pattern) and
      hasfield(field_pattern) type patterns, but only within declare
      statements. It's experimental and undocumented because it may change, and
      because it doesn't yet take into account ITDs.

    Xjoinpoints
      supply a comma separated list of new joinpoints that can be identified by
      pointcuts. Values are: arrayconstruction, synchronization

    Xlint
      Set default level for messages about potential programming mistakes in
      crosscutting code. {level} may be ignore, warning, or error. This
      overrides entries in org/aspectj/weaver/XlintDefault.properties from
      aspectjtools.jar.

    Xlintfile
      Specify properties file to set levels for specific crosscutting messages.
      PropertyFile is a path to a Java .properties file that takes the same
      property names and values as org/aspectj/weaver/XlintDefault.properties
      from aspectjtools.jar, which it also overrides.

    xmlConfigured
      Parameter which indicates an XML file containing AspectJ weaving
      instructions. Assigning this plugin parameter adds the -xmlConfigured
      option to ajc.

    XnoInline
      (Experimental) do not inline around advice

    XnotReweavable
      (Experimental) Create class files that can't be subsequently rewoven by
      AspectJ.

    Xreweavable
      (Experimental) runs weaver in reweavable mode which causes it to create
      woven classes that can be rewoven, subject to the restriction that on
      attempting a reweave all the types that advised the woven type must be
      accessible.

    XserializableAspects
      (Experimental) Normally it is an error to declare aspects Serializable.
      This option removes that restriction.

    Xset
      Allows the caller to provide additional arguments in a Map format. For
      example:
      <configuration>
       <Xset>
       <overWeaving>true</overWeaving>
       <avoidFinal>false</avoidFinal>
       </Xset>
      </configuration>

    XterminateAfterCompilation
      Causes compiler to terminate before weaving

aspectj:EclipseAjcMojo
  Description: Create eclipse configuration of aspectJ
  Implementation: org.codehaus.mojo.aspectj.EclipseAjcMojo
  Language: java

  Available parameters:

    aspectLibraries
      Weave binary aspects from the jars. The aspects should have been output
      by the same version of the compiler. The modules must also be
      dependencies of the project. Corresponds to ajc -aspectpath option

    skip (Default: false)
      User property: aspectj.skip
      Skip plugin execution.

    weaveDependencies
      List of of modules to weave (into target directory). Corresponds to ajc
      -inpath option (or -injars for pre-1.2 (which is not supported)).

    weaveDirectories
      List of of directories with .class files to weave (into target
      directory). Corresponds to ajc -inpath option.

    xmlConfigured
      Parameter which indicates an XML file containing AspectJ weaving
      instructions. Assigning this plugin parameter adds the -xmlConfigured
      option to ajc.

aspectj:help
  Description: Display help information on aspectj-maven-plugin.
    Call mvn aspectj:help -Ddetail=true -Dgoal=<goal-name> to display parameter
    details.
  Implementation: org.codehaus.mojo.aspectj.HelpMojo
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

aspectj:test-compile
  Description: Weaves all test classes.
  Implementation: org.codehaus.mojo.aspectj.AjcTestCompileMojo
  Language: java
  Bound to phase: test-compile

  Available parameters:

    ajdtBuildDefFile
      Where to find the ajdt build definition file. If set this will override
      the use of project sourcedirs.

    argumentFileName (Default: builddef.lst)
      The filename holding AJC build arguments. The file will be placed in the
      project build output directory, and will contain all the arguments passed
      to the AJC compiler in the last run, and also all the files included in
      the AJC build. Sample content shown below to illustrate typical content
      within the builddef.lst file:
       -1.6
      -encoding
      UTF-8
      -classpath
      /Users/lj/Development/Projects/Nazgul/nazgul_tools/validation/validation-api/target/nazgul-tools-validation-api-2.0.10-SNAPSHOT.jar:/Users/lj/.m2/repository/org/slf4j/slf4j-api/1.7.5/slf4j-api-1.7.5.jar:/Users/lj/.m2/repository/org/aspectj/aspectjrt/1.7.3/aspectjrt-1.7.3.jar:/Users/lj/.m2/repository/junit/junit/4.11/junit-4.11.jar:/Users/lj/.m2/repository/ch/qos/logback/logback-classic/1.0.13/logback-classic-1.0.13.jar:/Users/lj/.m2/repository/org/apache/commons/commons-lang3/3.1/commons-lang3-3.1.jar:/Users/lj/Development/Projects/Nazgul/nazgul_tools/validation/validation-aspect/target/classes
      -d
      /Users/lj/Development/Projects/Nazgul/nazgul_tools/validation/validation-aspect/target/classes
      /Users/lj/Development/Projects/Nazgul/nazgul_tools/validation/validation-aspect/src/main/java/se/jguru/nazgul/tools/validation/aspect/ValidationAspect.java
      

    aspectDirectory (Default: src/main/aspect)
      The source directory for the aspects.

    aspectLibraries
      Weave binary aspects from the jars. The aspects should have been output
      by the same version of the compiler. The modules must also be
      dependencies of the project. Corresponds to ajc -aspectpath option

    bootclasspath
      Override location of VM's bootclasspath for purposes of evaluating types
      when compiling. Path is a single argument containing a list of paths to
      zip files or directories, delimited by the platform-specific path
      delimiter.

    complianceLevel (Default: 1.4)
      Specify compiler compliance setting. Defaults to 1.4, with permitted
      values ('1.3', '1.4', '1.5', '1.6' and '1.7', '1.8').

    crossrefs
      generate .ajsym file into the output directory

    deprecation
      Toggle warning messages on deprecations

    emacssym
      Generate .ajesym symbol files for emacs support.

    encoding
      User property: project.build.sourceEncoding
      Specify default source encoding format.

    excludes
      List of ant-style patterns used to specify the aspects that should be
      excluded when compiling. When none specified all .java and .aj files in
      the project source directories, or directories specified by the
      ajdtDefFile property are included.

    forceAjcCompile (Default: false)
      Forces re-compilation, regardless of whether the compiler arguments or
      the sources have changed.

    includes
      List of ant-style patterns used to specify the aspects that should be
      included when compiling. When none specified all .java and .aj files in
      the project source directories, or directories specified by the
      ajdtDefFile property are included.

    noImportError
      Emit no errors for unresolved imports;

    outxml
      Generate aop.xml file for load-time weaving with default name
      (/META-INF/aop.xml).

    outxmlfile
      Generate aop.xml file for load-time weaving with custom name.

    parameters
      Set the compiler 'parameters' argument.

    preserveAllLocals
      Preserve all local variables during code generation (to facilitate
      debugging).

    proc
      Set the compiler 'proc' argument. Aspectj supports Annotation processing
      since 1.8.2, it can been disabled by proc:none.

    proceedOnError
      Keep compiling after error, dumping class files with problem methods

    referenceInfo
      Compute reference information.

    repeat
      Repeat compilation process N times (typically to do performance
      analysis).

    showWeaveInfo
      Emit messages about weaving

    skip (Default: false)
      User property: aspectj.skip
      Skip plugin execution.

    source (Default: ${mojo.java.target})
      Toggle assertions (1.3, 1.4, 1.5, 1.6, 1.7 or 1.8 - default is 1.4). When
      using -source 1.3, an assert() statement valid under Java 1.4 will result
      in a compiler error. When using -source 1.4, treat assert as a keyword
      and implement assertions according to the 1.4 language spec. When using
      -source 1.5 or higher, Java 5 language features are permitted. With
      --source 1.7 or higher Java 7 features are supported.

    target (Default: ${project.build.java.target})
      Specify classfile target setting (1.1 to 1.8) default is 1.2

    testAspectDirectory (Default: src/test/aspect)
      The source directory for the test aspects.

    testSources
      Set the java test source folders to use, specifying the includes and
      excludes.
      
      If you don't specify this parameter, all java test sources of the current
      project fill be used. If you specify this parameter as an empty tag (i.e.
      <testSources/>), all test source folders will be ignored. Otherwise
      specify the test source folder(s) to use.

    verbose
      Emit messages about accessed/processed compilation units

    warn
      Emit warnings for any instances of the comma-delimited list of
      questionable code. Supported values are shown in the list below, with
      their respective explanations - as copied directly from the AJC
      reference.
      constructorName
        method with constructor name
      packageDefaultMethod
        attempt to override package-default method
      deprecation
        usage of deprecated type or member
      maskedCatchBlocks
        hidden catch block
      unusedLocals
        local variable never read
      unusedArguments
        method argument never read
      unusedImports
        import statement not used by code in file
      none
        suppress all compiler warnings

    weaveDependencies
      List of of modules to weave (into target directory). Corresponds to ajc
      -inpath option (or -injars for pre-1.2 (which is not supported)).

    weaveDirectories
      List of of directories with .class files to weave (into target
      directory). Corresponds to ajc -inpath option.

    weaveMainSourceFolder (Default: false)
      Flag to indicate if the main source dirs should be a part of the compile
      process. Note! This will make all classes in main source dir appear in
      the test output dir also, potentially overwriting test resources.

    weaveWithAspectsInMainSourceFolder (Default: true)
      Flag to indicate if aspects in the the main source dirs should be a part
      of the compile process

    XaddSerialVersionUID
      Causes the compiler to calculate and add the SerialVersionUID field to
      any type implementing Serializable that is affected by an aspect. The
      field is calculated based on the class before weaving has taken place.

    Xajruntimetarget (Default: 1.5)
      (Experimental) Allows code to be generated that targets a 1.2 or a 1.5
      level AspectJ runtime (default 1.5)

    XhasMember
      Enables the compiler to support hasmethod(method_pattern) and
      hasfield(field_pattern) type patterns, but only within declare
      statements. It's experimental and undocumented because it may change, and
      because it doesn't yet take into account ITDs.

    Xjoinpoints
      supply a comma separated list of new joinpoints that can be identified by
      pointcuts. Values are: arrayconstruction, synchronization

    Xlint
      Set default level for messages about potential programming mistakes in
      crosscutting code. {level} may be ignore, warning, or error. This
      overrides entries in org/aspectj/weaver/XlintDefault.properties from
      aspectjtools.jar.

    Xlintfile
      Specify properties file to set levels for specific crosscutting messages.
      PropertyFile is a path to a Java .properties file that takes the same
      property names and values as org/aspectj/weaver/XlintDefault.properties
      from aspectjtools.jar, which it also overrides.

    xmlConfigured
      Parameter which indicates an XML file containing AspectJ weaving
      instructions. Assigning this plugin parameter adds the -xmlConfigured
      option to ajc.

    XnoInline
      (Experimental) do not inline around advice

    XnotReweavable
      (Experimental) Create class files that can't be subsequently rewoven by
      AspectJ.

    Xreweavable
      (Experimental) runs weaver in reweavable mode which causes it to create
      woven classes that can be rewoven, subject to the restriction that on
      attempting a reweave all the types that advised the woven type must be
      accessible.

    XserializableAspects
      (Experimental) Normally it is an error to declare aspects Serializable.
      This option removes that restriction.

    Xset
      Allows the caller to provide additional arguments in a Map format. For
      example:
      <configuration>
       <Xset>
       <overWeaving>true</overWeaving>
       <avoidFinal>false</avoidFinal>
       </Xset>
      </configuration>

    XterminateAfterCompilation
      Causes compiler to terminate before weaving

```
