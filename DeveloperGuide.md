**Table of Contents**


# 1.0 Prerequisites #

**Apache Ant 1.8.2** and **Java 1.6** must be installed before development can commence.  Any IDE will do, but if Eclipse is the IDE of your choice, the .project and .classpath files are also provided.

# 2.0 Installation #

## 2.1 Run Ant to Install Required Libraries ##

After checkout from SVN or downloading the distribution, invoke the following command in the top-level directory:

```
% ant
```

This will download Ivy, use Ivy to download other required packages, and compile the project.

If compilation fails, contact the [mailing list](http://groups.google.com/group/robocode-pmj-dacruzer-discuss) for help.

## 2.2 Verify Installation ##

After successful compilation, verify the project as follows:

```
% ant -f verify.build.xml
```

This command downloads the JUnit, Checkstyle, PMD, JavaDoc, and FindBugs tools using Ivy if they are not already present in the distribution.  Then it runs these tools and fails the build if these tools issue any warnings.

If verification fails, contact the [mailing list](http://groups.google.com/group/robocode-pmj-dacruzer-discuss) for help.

# 3.0 Development Guidelines #

After successful verification of robocode-hyh-beater, development can truly commence.  Please keep the following guidelines in mind:

  * This project was developed in Eclipse using [this format](http://ics-software-engineering.googlecode.com/svn/trunk/configfiles/eclipse.format.xml).
  * Add an Issue specifying any additional features you plan to develop.
  * Develop a JUnit test case anytime during development of a new feature to ensure its proper functionality.
  * Ensure that `ant -f verify.build.xml` runs successfully before committing changes to the repository.