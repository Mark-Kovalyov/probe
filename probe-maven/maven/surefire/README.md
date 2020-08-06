# surefire

```
Name: Maven Surefire Plugin
Description: Maven Surefire MOJO in maven-surefire-plugin.
Group Id: org.apache.maven.plugins
Artifact Id: maven-surefire-plugin
Version: 3.0.0-M5
Goal Prefix: surefire

This plugin has 2 goals:

surefire:help
  Description: Display help information on maven-surefire-plugin.
    Call mvn surefire:help -Ddetail=true -Dgoal=<goal-name> to display
    parameter details.

surefire:test
  Description: Run tests using Surefire.

For more information, run 'mvn help:describe [...] -Ddetail'
```

## Detailed Info

```
Name: Maven Surefire Plugin
Description: Maven Surefire MOJO in maven-surefire-plugin.
Group Id: org.apache.maven.plugins
Artifact Id: maven-surefire-plugin
Version: 3.0.0-M5
Goal Prefix: surefire

This plugin has 2 goals:

surefire:help
  Description: Display help information on maven-surefire-plugin.
    Call mvn surefire:help -Ddetail=true -Dgoal=<goal-name> to display
    parameter details.
  Implementation: org.apache.maven.plugin.surefire.HelpMojo
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

surefire:test
  Description: Run tests using Surefire.
  Implementation: org.apache.maven.plugin.surefire.SurefirePlugin
  Language: java
  Bound to phase: test

  Available parameters:

    additionalClasspathElements
      User property: maven.test.additionalClasspath
      Additional elements to be appended to the classpath.

    argLine
      User property: argLine
      Arbitrary JVM options to set on the command line.
      
      Since the Version 2.17 using an alternate syntax for argLine, @{...}
      allows late replacement of properties when the plugin is executed, so
      properties that have been modified by other plugins will be picked up
      correctly. See the Frequently Asked Questions page with more details:
      http://maven.apache.org/surefire/maven-surefire-plugin/faq.html
      http://maven.apache.org/surefire/maven-failsafe-plugin/faq.html

    childDelegation (Default: false)
      User property: childDelegation
      When false it makes tests run using the standard classloader delegation
      instead of the default Maven isolated classloader. Only used when forking
      (forkMode is not none).
      Setting it to false helps with some problems caused by conflicts between
      xml parsers in the classpath and the Java 5 provider parser.

    classesDirectory (Default: ${project.build.outputDirectory})
      The directory containing generated classes of the project being tested.
      This will be included after the test classes in the test classpath.

    classpathDependencyExcludes
      User property: maven.test.dependency.excludes
      List of dependencies to exclude from the test classpath. Each dependency
      string must follow the format groupId:artifactId. For example:
      org.acme:project-a

    classpathDependencyScopeExclude
      A dependency scope to exclude from the test classpath. The scope should
      be one of the scopes defined by org.apache.maven.artifact.Artifact. This
      includes the following:
      
      - compile - system, provided, compile
      - runtime - compile, runtime
      - compile+runtime - system, provided, compile, runtime
      - runtime+system - system, compile, runtime
      - test - system, provided, compile, runtime, test

    consoleOutputReporter
      (no description available)

    debugForkedProcess
      User property: maven.surefire.debug
      Attach a debugger to the forked JVM. If set to 'true', the process will
      suspend and wait for a debugger to attach on port 5005. If set to some
      other string, that string will be appended to the argLine, allowing you
      to configure arbitrary debuggability options (without overwriting the
      other options specified through the argLine parameter).

    dependenciesToScan
      User property: dependenciesToScan
      List of dependencies to scan for test classes to include in the test run.
      The child elements of this element must be <dependency> elements, and the
      contents of each of these elements must be a string which follows the
      general form:
      groupId[:artifactId[:type[:classifier][:version]]]
      
      The wildcard character * can be used within the sub parts of those
      composite identifiers to do glob-like pattern matching. The classifier
      may be omitted when matching dependencies without a classifier.
      
      Examples:
      
      - group or, equivalently, group:*
      - g*p:*rtifac*
      - group:*:jar
      - group:artifact:*:1.0.0 (no classifier)
      - group:*:test-jar:tests
      - *:artifact:*:*:1.0.0
      
      Since version 2.22.0 you can scan for test classes from a project
      dependency of your multi-module project.
      
      In versions before 3.0.0-M4, only groupId:artifactId is supported.

    disableXmlReport (Default: false)
      User property: disableXmlReport
      Flag to disable the generation of report files in xml format. Deprecated
      since 3.0.0-M4. Instead use disable within statelessTestsetReporter since
      of 3.0.0-M6.

    enableAssertions (Default: true)
      User property: enableAssertions
      By default, Surefire enables JVM assertions for the execution of your
      test cases. To disable the assertions, set this flag to 'false'.

    enableProcessChecker
      User property: surefire.enableProcessChecker
      Since 3.0.0-M4 the process checkers are disabled. You can enable them
      namely by setting ping and native or all in this parameter.
      The checker is useful in situations when you kill the build on a CI
      system and you want the Surefire forked JVM to kill the tests asap and
      free all handlers on the file system been previously used by the JVM and
      by the tests.
      The ping should be safely used together with ZGC or Shenandoah Garbage
      Collector. Due to the ping relies on timing of the PING (triggered every
      30 seconds), slow GCs may pause the timers and pretend that the parent
      process of the forked JVM does not exist.
      The native is very fast checker. It is useful mechanism on Unix based
      systems, Linux distributions and Alpine/BusyBox Linux. See the JIRA
      SUREFIRE-1631 for Windows issues.
      Another useful configuration parameter is forkedProcessTimeoutInSeconds.
      See the Frequently Asked Questions page with more details:
      http://maven.apache.org/surefire/maven-surefire-plugin/faq.html#kill-jvm
      http://maven.apache.org/surefire/maven-failsafe-plugin/faq.html#kill-jvm
      Example of use:
      mvn test -Dsurefire.enableProcessChecker=all

    encoding (Default: ${project.reporting.outputEncoding})
      User property: surefire.encoding
      The character encoding scheme to be applied while generating test report
      files (see target/surefire-reports/yourTestName.txt). The report output
      files (*-out.txt) are encoded in UTF-8 if not set otherwise.

    environmentVariables
      Additional environment variables to set on the command line.

    excludedEnvironmentVariables
      User property: surefire.excludedEnvironmentVariables
      You can selectively exclude individual environment variables by
      enumerating their keys.
      The environment is a system-dependent mapping from keys to values which
      is inherited from the Maven process to the forked Surefire processes. The
      keys must literally (case sensitive) match in order to exclude their
      environment variable.
      Example to exclude three environment variables:
      mvn test -Dsurefire.excludedEnvironmentVariables=ACME1,ACME2,ACME3

    excludedGroups
      User property: excludedGroups
      (TestNG/JUnit47 provider with JUnit4.8+ only and JUnit5+ provider since
      2.22.0) Excluded groups/categories/tags. Any methods/classes/etc with one
      of the groups/categories/tags specified in this list will specifically
      not be run.
      For JUnit4, this parameter forces the use of the 4.7 provider. For
      JUnit5, this parameter forces the use of the JUnit platform provider.
      This parameter is ignored if the suiteXmlFiles parameter is specified.
      Since version 2.18.1 and JUnit 4.12, the @Category annotation type is
      automatically inherited from superclasses, see
      @java.lang.annotation.Inherited. Make sure that test class inheritance
      still makes sense together with @Category annotation of the JUnit 4.12 or
      higher appeared in superclass.

    excludes
      A list of <exclude> elements specifying the tests (by pattern) that
      should be excluded in testing. When not specified and when the test
      parameter is not specified, the default excludes will be
      
      <excludes>
       <exclude>**/*$*</exclude>
      </excludes>
      
      (which excludes all inner classes).
      This parameter is ignored if the TestNG suiteXmlFiles parameter is
      specified.
      Each exclude item may also contain a comma-separated sub-list of items,
      which will be treated as multiple  <exclude> entries.
      Since 2.19 a complex syntax is supported in one parameter (JUnit 4, JUnit
      4.7+, TestNG):
      <exclude>%regex[pkg.*Slow.*.class], Unstable*</exclude>
      
      
      Notice that these values are relative to the directory containing
      generated test classes of the project being tested. This directory is
      declared by the parameter testClassesDirectory which defaults to the POM
      property ${project.build.testOutputDirectory}, typically src/test/java
      unless overridden.

    excludesFile
      User property: surefire.excludesFile
      A file containing exclude patterns. Blank lines, or lines starting with #
      are ignored. If excludes are also specified, these patterns are appended.
      Example with path, simple and regex excludes:
      
      */test/*
      **/DontRunTest.*
      %regex[.*Test.*|.*Not.*]

    failIfNoSpecifiedTests
      User property: surefire.failIfNoSpecifiedTests
      Set this to 'true' to cause a failure if none of the tests specified in
      -Dtest=... are run. Defaults to 'true'.

    failIfNoTests
      User property: failIfNoTests
      Set this to 'true' to cause a failure if there are no tests to run.
      Defaults to 'false'.

    forkCount (Default: 1)
      User property: forkCount
      Option to specify the number of VMs to fork in parallel in order to
      execute the tests. When terminated with 'C', the number part is
      multiplied with the number of CPU cores. Floating point value are only
      accepted together with 'C'. If set to '0', no VM is forked and all tests
      are executed within the main process.
      
      Example values: '1.5C', '4'
      
      The system properties and the argLine of the forked processes may contain
      the place holder string ${surefire.forkNumber}, which is replaced with a
      fixed number for each of the parallel forks, ranging from 1 to the
      effective value of forkCount times the maximum number of parallel
      Surefire executions in maven parallel builds, i.e. the effective value of
      the -T command line argument of maven core.

    forkedProcessExitTimeoutInSeconds (Default: 30)
      User property: surefire.exitTimeout
      Forked process is normally terminated without any significant delay after
      given tests have completed. If the particular tests started non-daemon
      Thread(s), the process hangs instead of been properly terminated by
      System.exit(). Use this parameter in order to determine the timeout of
      terminating the process. see the documentation:
      http://maven.apache.org/surefire/maven-surefire-plugin/examples/shutdown.html
      Turns to default fallback value of 30 seconds if negative integer.

    forkedProcessTimeoutInSeconds
      User property: surefire.timeout
      Kill the forked test process after a certain number of seconds. If set to
      0, wait forever for the process, never timing out.

    forkMode (Default: once)
      User property: forkMode
      DEPRECATED since version 2.14. Use forkCount and reuseForks instead.
      
      Option to specify the forking mode. Can be never, once, always,
      perthread.
      The none and pertest are also accepted for backwards compatibility.
      The always forks for each test-class.
      The perthread creates the number of parallel forks specified by
      threadCount, where each forked JVM is executing one test-class. See also
      the parameter reuseForks for the lifetime of JVM.

    forkNode
      User property: surefire.forkNode
      This parameter configures the forked node. Currently, you can select the
      communication protocol, i.e. process pipes or TCP/IP sockets. The plugin
      uses process pipes by default which will be turned to TCP/IP in the
      version 3.0.0. Alternatively, you can implement your own factory and SPI.
      See the documentation for more details:
      https://maven.apache.org/plugins/maven-surefire-plugin/examples/process-communication.html

    groups
      User property: groups
      (TestNG/JUnit47 provider with JUnit4.8+ only and JUnit5+ provider since
      2.22.0) Groups/categories/tags for this test. Only classes/methods/etc
      decorated with one of the groups/categories/tags specified here will be
      included in test run, if specified.
      For JUnit4 tests, this parameter forces the use of the 4.7 provider. For
      JUnit5 tests, this parameter forces the use of the JUnit platform
      provider.
      This parameter is ignored if the suiteXmlFiles parameter is specified.
      Since version 2.18.1 and JUnit 4.12, the @Category annotation type is
      automatically inherited from superclasses, see
      @java.lang.annotation.Inherited. Make sure that test class inheritance
      still makes sense together with @Category annotation of the JUnit 4.12 or
      higher appeared in superclass.

    includes
      A list of <include> elements specifying the tests (by pattern) that
      should be included in testing. When not specified and when the test
      parameter is not specified, the default includes will be
      <includes>
       <include>**/Test*.java</include>
       <include>**/*Test.java</include>
       <include>**/*Tests.java</include>
       <include>**/*TestCase.java</include>
      </includes>
      
      Each include item may also contain a comma-separated sub-list of items,
      which will be treated as multiple  <include> entries.
      Since 2.19 a complex syntax is supported in one parameter (JUnit 4, JUnit
      4.7+, TestNG):
      <include>%regex[.*[Cat|Dog].*], Basic????, !Unstable*</include>
      <include>%regex[.*[Cat|Dog].*], !%regex[pkg.*Slow.*.class],
      pkg/**/*Fast*.java</include>
      
      
      This parameter is ignored if the TestNG suiteXmlFiles parameter is
      specified.
      
      Notice that these values are relative to the directory containing
      generated test classes of the project being tested. This directory is
      declared by the parameter testClassesDirectory which defaults to the POM
      property ${project.build.testOutputDirectory}, typically src/test/java
      unless overridden.

    includesFile
      User property: surefire.includesFile
      A file containing include patterns. Blank lines, or lines starting with #
      are ignored. If includes are also specified, these patterns are appended.
      Example with path, simple and regex includes:
      */test/*
      **/NotIncludedByDefault.java
      %regex[.*Test.*|.*Not.*]

    jdkToolchain
      Allow for configuration of the test jvm via maven toolchains. This
      permits a configuration where the project is built with one jvm and
      tested with another. This is similar to jvm, but avoids hardcoding paths.
      The two parameters are mutually exclusive (jvm wins)
      
      Examples:
      (see Guide to Toolchains for more info)
      <configuration>
       ...
       <jdkToolchain>
       <version>1.11</version>
       </jdkToolchain>
       </configuration>
      
       <configuration>
       ...
       <jdkToolchain>
       <version>1.8</version>
       <vendor>zulu</vendor>
       </jdkToolchain>
       </configuration>
      

    junitArtifactName (Default: junit:junit)
      User property: junitArtifactName
      Allows you to specify the name of the JUnit artifact. If not set,
      junit:junit will be used.

    jvm
      User property: jvm
      Option to specify the jvm (or path to the java executable) to use with
      the forking options. For the default, the jvm will be a new instance of
      the same VM as the one used to run Maven. JVM settings are not inherited
      from MAVEN_OPTS.

    objectFactory
      User property: objectFactory
      (TestNG only) Define the factory class used to create all test instances.

    parallel
      User property: parallel
      (TestNG provider) When you use the parameter parallel, TestNG will try to
      run all your test methods in separate threads, except for methods that
      depend on each other, which will be run in the same thread in order to
      respect their order of execution.
      (JUnit 4.7 provider) Supports values classes, methods, both to run in
      separate threads been controlled by threadCount.
      
      Since version 2.16 (JUnit 4.7 provider), the value both is DEPRECATED.
      Use classesAndMethods instead.
      
      Since version 2.16 (JUnit 4.7 provider), additional vales are available:
      suites, suitesAndClasses, suitesAndMethods, classesAndMethods, all.
      By default, Surefire does not execute tests in parallel. You can set the
      parameter parallel to none to explicitly disable parallel execution (e.g.
      when disabling parallel execution in special Maven profiles when
      executing coverage analysis).

    parallelOptimized (Default: true)
      User property: parallelOptimized
      (JUnit 4.7 / provider only) The thread counts do not exceed the number of
      parallel suite, class runners and average number of methods per class if
      set to true.
      True by default.

    parallelTestsTimeoutForcedInSeconds
      User property: surefire.parallel.forcedTimeout
      Stop executing queued parallel JUnit tests and interrupt currently
      running tests after a certain number of seconds.
      Example values: '3.5', '4'
      
      If set to 0, wait forever, never timing out. Makes sense with specified
      parallel different from 'none'.

    parallelTestsTimeoutInSeconds
      User property: surefire.parallel.timeout
      Stop executing queued parallel JUnit tests after a certain number of
      seconds.
      Example values: '3.5', '4'
      
      If set to 0, wait forever, never timing out. Makes sense with specified
      parallel different from 'none'.

    perCoreThreadCount (Default: true)
      User property: perCoreThreadCount
      (JUnit 4.7 provider) Indicates that threadCount, threadCountSuites,
      threadCountClasses, threadCountMethods are per cpu core.

    printSummary (Default: true)
      User property: surefire.printSummary
      Option to print summary of test suites or just print the test cases that
      have errors.

    properties
      List of properties for configuring all TestNG related configurations.
      This is the new preferred method of configuring TestNG.

    redirectTestOutputToFile (Default: false)
      User property: maven.test.redirectTestOutputToFile
      Set this to 'true' to redirect the unit test standard output to a file
      (found in reportsDirectory/testName-output.txt).

    reportFormat (Default: brief)
      User property: surefire.reportFormat
      Selects the formatting for the test report to be generated. Can be set as
      'brief' or 'plain'. Only applies to the output format of the output files
      (target/surefire-reports/testName.txt)

    reportNameSuffix
      User property: surefire.reportNameSuffix
      Add custom text into report filename:
      TEST-testClassName-reportNameSuffix.xml,
      testClassName-reportNameSuffix.txt and
      testClassName-reportNameSuffix-output.txt. File
      TEST-testClassName-reportNameSuffix.xml has changed attributes
      'testsuite'--'name' and 'testcase'--'classname' - reportNameSuffix is
      added to the attribute value.

    reportsDirectory (Default:
    ${project.build.directory}/surefire-reports)
      Base directory where all reports are written to.

    rerunFailingTestsCount (Default: 0)
      User property: surefire.rerunFailingTestsCount
      (JUnit 4+ providers and JUnit 5+ providers since 3.0.0-M4) The number of
      times each failing test will be rerun. If set larger than 0, rerun
      failing tests immediately after they fail. If a failing test passes in
      any of those reruns, it will be marked as pass and reported as a 'flake'.
      However, all the failing attempts will be recorded.

    reuseForks (Default: true)
      User property: reuseForks
      Indicates if forked VMs can be reused. If set to 'false', a new VM is
      forked for each test class to be executed. If set to 'true', up to
      forkCount VMs will be forked and then reused to execute all tests.

    runOrder (Default: filesystem)
      User property: surefire.runOrder
      Defines the order the tests will be run in. Supported values are
      alphabetical, reversealphabetical, random, hourly (alphabetical on even
      hours, reverse alphabetical on odd hours), failedfirst, balanced and
      filesystem.
      
      Odd/Even for hourly is determined at the time the of scanning the
      classpath, meaning it could change during a multi-module build.
      
      Failed first will run tests that failed on previous run first, as well as
      new tests for this run.
      
      Balanced is only relevant with parallel=classes, and will try to optimize
      the run-order of the tests reducing the overall execution time. Initially
      a statistics file is created and every next test run will reorder
      classes.
      
      Note that the statistics are stored in a file named .surefire-XXXXXXXXX
      beside pom.xml and should not be checked into version control. The
      'XXXXX' is the SHA1 checksum of the entire surefire configuration, so
      different configurations will have different statistics files, meaning if
      you change any configuration settings you will re-run once before new
      statistics data can be established.

    shutdown (Default: exit)
      User property: surefire.shutdown
      After the plugin process is shutdown by sending SIGTERM signal (CTRL+C),
      SHUTDOWN command is received by every forked JVM.
      The value is set to (shutdown=exit) by default (changed in version
      3.0.0-M4).
      The parameter can be configured with other two values testset and kill.
      With(shutdown=testset) the test set may still continue to run in forked
      JVM.
      Using exit forked JVM executes System.exit(1) after the plugin process
      has received SIGTERM signal.
      Using kill the JVM executes Runtime.halt(1) and kills itself.

    skip (Default: false)
      User property: maven.test.skip
      Set this to 'true' to bypass unit tests entirely. Its use is NOT
      RECOMMENDED, especially if you enable it using the 'maven.test.skip'
      property, because maven.test.skip disables both running the tests and
      compiling the tests. Consider using the skipTests parameter instead.

    skipAfterFailureCount (Default: 0)
      User property: surefire.skipAfterFailureCount
      Set to error/failure count in order to skip remaining tests. Due to race
      conditions in parallel/forked execution this may not be fully guaranteed.
      Enable with system property -Dsurefire.skipAfterFailureCount=1 or any
      number greater than zero. Defaults to '0'.
      See the prerequisites and limitations in documentation:
      http://maven.apache.org/plugins/maven-surefire-plugin/examples/skip-after-failure.html

    skipExec
      User property: maven.test.skip.exec
      This old parameter is just like skipTests, but bound to the old property
      'maven.test.skip.exec'.
      Deprecated. Use skipTests instead.

    skipTests (Default: false)
      User property: skipTests
      Set this to 'true' to skip running tests, but still compile them. Its use
      is NOT RECOMMENDED, but quite convenient on occasion.
      Failsafe plugin deprecated the parameter skipTests and the parameter will
      be removed in Failsafe 3.0.0 as it is a source of conflicts between
      Failsafe and Surefire plugin.

    statelessTestsetInfoReporter
      (no description available)

    statelessTestsetReporter
      Note: use the legacy system property disableXmlReport set to true to
      disable the report.

    suiteXmlFiles
      User property: surefire.suiteXmlFiles
      (TestNG) List of <suiteXmlFile> elements specifying TestNG suite xml file
      locations. Note that suiteXmlFiles is incompatible with several other
      parameters of this plugin, like includes and excludes.
      This parameter is ignored if the test parameter is specified (allowing
      you to run a single test instead of an entire suite).

    systemProperties
      List of System properties to pass to the JUnit tests.
      Deprecated. Use systemPropertyVariables instead.

    systemPropertiesFile
      User property: surefire.systemPropertiesFile
      (no description available)

    systemPropertyVariables
      List of System properties to pass to the JUnit tests.

    tempDir (Default: surefire)
      User property: tempDir
      Relative path to temporary-surefire-boot directory containing internal
      Surefire temporary files.
      The temporary-surefire-boot directory is project.build.directory on most
      platforms or system default temporary-directory specified by the system
      property java.io.tmpdir on Windows (see SUREFIRE-1400).
      It is deleted after the test set has completed.

    test
      User property: test
      Specify this parameter to run individual tests by file name, overriding
      the parameter includes and excludes. Each pattern you specify here will
      be used to create an include pattern formatted like **/${test}.java, so
      you can just type -Dtest=MyTest to run a single test called
      'foo/MyTest.java'. The test patterns prefixed with a ! will be excluded.
      This parameter overrides the parameter includes, excludes, and the TestNG
      parameter suiteXmlFiles.
      Since 2.7.3, you can execute a limited number of methods in the test by
      adding #myMethod or #my*ethod. For example, -Dtest=MyTest#myMethod. This
      is supported for junit 4.x and TestNg.
      
      Since 2.19 a complex syntax is supported in one parameter (JUnit 4, JUnit
      4.7+, TestNG):
      '-Dtest=???Test, !Unstable*, pkg/**/Ci*leTest.java,
      *Test#test*One+testTwo?????, #fast*+slowTest'
      
      or e.g.
      '-Dtest=Basic*, !%regex[.*.Unstable.*],
      !%regex[.*.MyTest.class#one.*|two.*], %regex[#fast.*|slow.*]'
      
      
      The Parameterized JUnit runner describes test methods using an index in
      brackets, so the non-regex method pattern would become: #testMethod[*].
      If using @Parameters(name='{index}: fib({0})={1}') and selecting the
      index e.g. 5 in pattern, the non-regex method pattern would become
      #testMethod[5:*].

    testClassesDirectory (Default:
    ${project.build.testOutputDirectory})
      The directory containing generated test classes of the project being
      tested. This will be included at the beginning of the test classpath. *

    testFailureIgnore (Default: false)
      User property: maven.test.failure.ignore
      Set this to 'true' to ignore a failure during testing. Its use is NOT
      RECOMMENDED, but quite convenient on occasion.

    testNGArtifactName (Default: org.testng:testng)
      User property: testNGArtifactName
      Allows you to specify the name of the TestNG artifact. If not set,
      org.testng:testng will be used.

    testSourceDirectory (Default:
    ${project.build.testSourceDirectory})
      Required: true
      The test source directory containing test class sources.

    threadCount
      User property: threadCount
      (TestNG/JUnit 4.7 provider) The attribute thread-count allows you to
      specify how many threads should be allocated for this execution. Only
      makes sense to use in conjunction with the parallel parameter.

    threadCountClasses (Default: 0)
      User property: threadCountClasses
      (JUnit 4.7 provider) This attribute allows you to specify the concurrency
      in test classes, i.e.:
      - number of concurrent classes if threadCount is 0 or unspecified
      - limited classes concurrency if useUnlimitedThreads is set to true
      - if threadCount and certain thread-count parameters are > 0 for
        parallel, the concurrency is computed from ratio. For instance
        parallel=all and the ratio between
        threadCountSuites:threadCountClasses:threadCountMethods is 2:3:5, there
        is 30% of threadCount in concurrent classes.
      - as in the previous case but without this leaf thread-count. Example:
        parallel=suitesAndClasses, threadCount=16, threadCountSuites=5,
        threadCountClasses is unspecified leaf, the number of concurrent
        classes is varying from >= 11 to 14 or 15. The threadCountSuites become
        given number of threads.
      Only makes sense to use in conjunction with the parallel parameter. The
      default value 0 behaves same as unspecified one.

    threadCountMethods (Default: 0)
      User property: threadCountMethods
      (JUnit 4.7 provider) This attribute allows you to specify the concurrency
      in test methods, i.e.:
      - number of concurrent methods if threadCount is 0 or unspecified
      - limited concurrency of methods if useUnlimitedThreads is set to true
      - if threadCount and certain thread-count parameters are > 0 for
        parallel, the concurrency is computed from ratio. For instance
        parallel=all and the ratio between
        threadCountSuites:threadCountClasses:threadCountMethods is 2:3:5, there
        is 50% of threadCount which appears in concurrent methods.
      - as in the previous case but without this leaf thread-count. Example:
        parallel=all, threadCount=16, threadCountSuites=2,
        threadCountClasses=3, but threadCountMethods is unspecified leaf, the
        number of concurrent methods is varying from >= 11 to 14 or 15. The
        threadCountSuites and threadCountClasses become given number of
        threads.
      Only makes sense to use in conjunction with the parallel parameter. The
      default value 0 behaves same as unspecified one.

    threadCountSuites (Default: 0)
      User property: threadCountSuites
      (JUnit 4.7 provider) This attribute allows you to specify the concurrency
      in test suites, i.e.:
      - number of concurrent suites if threadCount is 0 or unspecified
      - limited suites concurrency if useUnlimitedThreads is set to true
      - if threadCount and certain thread-count parameters are > 0 for
        parallel, the concurrency is computed from ratio. For instance
        parallel=all and the ratio between
        threadCountSuites:threadCountClasses:threadCountMethods is 2:3:5, there
        is 20% of threadCount which appeared in concurrent suites.
      Only makes sense to use in conjunction with the parallel parameter. The
      default value 0 behaves same as unspecified one.

    trimStackTrace (Default: true)
      User property: trimStackTrace
      Whether to trim the stack trace in the reports to just the lines within
      the test, or show the full trace.

    useFile (Default: true)
      User property: surefire.useFile
      Option to generate a file test report or just output the test report to
      the console.

    useManifestOnlyJar (Default: true)
      User property: surefire.useManifestOnlyJar
      By default, Surefire forks your tests using a manifest-only JAR; set this
      parameter to 'false' to force it to launch your tests with a plain old
      Java classpath. (See the
      http://maven.apache.org/plugins/maven-surefire-plugin/examples/class-loading.html
      for a more detailed explanation of manifest-only JARs and their
      benefits.)
      Beware, setting this to 'false' may cause your tests to fail on Windows
      if your classpath is too long.

    useModulePath (Default: true)
      User property: surefire.useModulePath
      Disables modular path (aka Jigsaw project since of Java 9) even if
      module-info.java is used in project.
      Enabled by default. If enabled, module-info.java exists and executes with
      JDK 9+, modular path is used.

    useSystemClassLoader (Default: true)
      User property: surefire.useSystemClassLoader
      Option to pass dependencies to the system's classloader instead of using
      an isolated class loader when forking. Prevents problems with JDKs which
      implement the service provider lookup mechanism by using the system's
      ClassLoader.

    useUnlimitedThreads (Default: false)
      User property: useUnlimitedThreads
      (JUnit 4.7 provider) Indicates that the thread pool will be unlimited.
      The parallel parameter and the actual number of classes/methods will
      decide. Setting this to 'true' effectively disables perCoreThreadCount
      and threadCount. Defaults to 'false'.

    workingDirectory
      User property: basedir
      Command line working directory.
```
