# ANTLR 4 Maven plugin 4.9.2

Help
```
ANTLR 4 Maven plugin 4.9.2
  Maven plugin for ANTLR 4 grammars

This plugin has 2 goals:

antlr4:antlr4
  Parses ANTLR 4 grammar files *.g4 and transforms them into Java source files.

antlr4:help
  Display help information on antlr4-maven-plugin.
  Call mvn antlr4:help -Ddetail=true -Dgoal=<goal-name> to display parameter
  details.

```

Detailed help
```
This plugin has 2 goals:

antlr4:antlr4
  Parses ANTLR 4 grammar files *.g4 and transforms them into Java source files.

  Available parameters:

    arguments
      A list of additional command line arguments to pass to the ANTLR tool.

    atn (Default: false)
      If set to true then the ANTLR tool will generate a description of the ATN
      for each rule in Dot format.
      User property: antlr4.atn

    excludes
      A set of Ant-like exclusion patterns used to prevent certain files from
      being processed. By default, this set is empty such that no files are
      excluded.

    forceATN (Default: false)
      Use the ATN simulator for all predictions.
      User property: antlr4.forceATN

    generateTestSources (Default: false)
      Specifies whether sources are added to the compile or test scope.
      User property: antlr4.generateTestSources

    includes
      Provides an explicit list of all the grammars that should be included in
      the generate phase of the plugin. Note that the plugin is smart enough to
      realize that imported grammars should be included but not acted upon
      directly by the ANTLR Tool.
      A set of Ant-like inclusion patterns used to select files from the source
      directory for processing. By default, the pattern **/*.g4 is used to
      select grammar files.

    inputEncoding
      specify grammar file encoding; e.g., euc-jp
      User property: project.build.sourceEncoding

    libDirectory (Default: ${basedir}/src/main/antlr4/imports)
      Specify location of imported grammars and tokens files.

    listener (Default: true)
      Generate parse tree listener interface and base class.
      User property: antlr4.listener

    options
      A list of grammar options to explicitly specify to the tool. These options
      are passed to the tool using the -D<option>=<value> syntax.

    outputDirectory (Default:
    ${project.build.directory}/generated-sources/antlr4)
      Specify output directory where the Java files are generated.

    outputEncoding
      specify output file encoding; defaults to source encoding
      User property: project.build.sourceEncoding

    sourceDirectory (Default: ${basedir}/src/main/antlr4)
      The directory where the ANTLR grammar files (*.g4) are located.

    treatWarningsAsErrors (Default: false)
      Treat warnings as errors.
      User property: antlr4.treatWarningsAsErrors

    visitor (Default: false)
      Generate parse tree visitor interface and base class.
      User property: antlr4.visitor

antlr4:help
  Display help information on antlr4-maven-plugin.
  Call mvn antlr4:help -Ddetail=true -Dgoal=<goal-name> to display parameter
  details.

  Available parameters:

    detail (Default: false)
      If true, display all settable properties for each goal.
      User property: detail

    goal
      The name of the goal for which to show help. If unspecified, all goals
      will be displayed.
      User property: goal

    indentSize (Default: 2)
      The number of spaces per indentation level, should be positive.
      User property: indentSize

    lineLength (Default: 80)
      The maximum length of a display line, should be positive.
      User property: lineLength

```