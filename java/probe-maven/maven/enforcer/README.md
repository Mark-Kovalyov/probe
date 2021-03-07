# enforcer

```
Name: Apache Maven Enforcer Plugin
Description: The Loving Iron Fist of Maven
Group Id: org.apache.maven.plugins
Artifact Id: maven-enforcer-plugin
Version: 3.0.0-M3
Goal Prefix: enforcer

This plugin has 3 goals:

enforcer:display-info
  Description: This goal displays the current platform information.

enforcer:enforce
  Description: This goal executes the defined enforcer-rules once per module.

enforcer:help
  Description: Display help information on maven-enforcer-plugin.
    Call mvn enforcer:help -Ddetail=true -Dgoal=<goal-name> to display
    parameter details.

For more information, run 'mvn help:describe [...] -Ddetail'

```
## Detailed Info

```
Name: Apache Maven Enforcer Plugin
Description: The Loving Iron Fist of Maven
Group Id: org.apache.maven.plugins
Artifact Id: maven-enforcer-plugin
Version: 3.0.0-M3
Goal Prefix: enforcer

This plugin has 3 goals:

enforcer:display-info
  Description: This goal displays the current platform information.
  Implementation: org.apache.maven.plugins.enforcer.DisplayInfoMojo
  Language: java

  Available parameters:

enforcer:enforce
  Description: This goal executes the defined enforcer-rules once per module.
  Implementation: org.apache.maven.plugins.enforcer.EnforceMojo
  Language: java
  Bound to phase: validate

  Available parameters:

    commandLineRules
      User property: rules
      Array of Strings that matches the EnforcerRules to execute.

    fail (Default: true)
      User property: enforcer.fail
      Flag to fail the build if a version check fails.

    failFast (Default: false)
      User property: enforcer.failFast
      Fail on the first rule that doesn't pass

    ignoreCache (Default: false)
      User property: enforcer.ignoreCache
      Use this flag to disable rule result caching. This will cause all rules
      to execute on each project even if the rule indicates it can safely be
      cached.

    rules
      Array of objects that implement the EnforcerRule interface to execute.

    skip (Default: false)
      User property: enforcer.skip
      Flag to easily skip all checks

enforcer:help
  Description: Display help information on maven-enforcer-plugin.
    Call mvn enforcer:help -Ddetail=true -Dgoal=<goal-name> to display
    parameter details.
  Implementation: org.apache.maven.plugins.enforcer.HelpMojo
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
