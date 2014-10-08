---
author: John Lockwood
layout: post
title: "Getting Started in Selenium and Java in IntelliJ Idea, Part 2" 
excerpt:  "In this article we use a simple IntelliJ Idea project with a corresponding Maven POM file to serve as a home for our Selenium tests, and export a simple Selenium Script from Selenium IDE to Java."
categories:
- Testing
- Selenium
- IntelliJ Idea
---

Read [part one](/getting-started-with-selenium-in-intellij-idea/).
[Get the source](https://github.com/CodeSolid/tutorials/tree/master/SeleniumStarter)

<div id="table_of_contents">
<h1>Table of Contents</h1>
<ul>
<li><a href="#Introduction">Introduction</a></li>
<li><a href="#SimpleSeleniumTestcase">Set up a Selenium Test Project Using Maven and IntelliJ Idea</a></li>
<li><a href="#ExportingSeleniumToJava">Exporting Selenium IDE Tests to Java</a></li>
</ul>
</div>

<h2><a id="Introduction">Introduction</a></h2>
In [part one](/getting-started-with-selenium-in-intellij-idea/) of this article, we looked at how to use the Selenium IDE to record browser actions.
We then used Firebug to locate a page element, and added a simple assertion to verify that a page element had the text we expected.

In part two of the series, we want to look at how to begin to work with out Selenium Scripts using Java.  We'll explore how easy it is to do a simple
export to a JUnit test, and we'll get you the Maven and IntelliJ project files you'll need to get started.

<h2><a id="SimpleSeleniumTestcase">Set up a Selenium Test Project Using Maven and IntelliJ Idea</a></h2>

When setting up new projects in IntelliJ Idea, I almost always start with a Maven POM file first and then import it, since I find that pasting Maven
dependencies is the simplest way to get the jars I need. If you prefer to skip past this section, all the source including POM file and the finished project for this article are in the [CodeSolid Tutorials repository](https://github.com/CodeSolid/tutorials) on Github, in the [Selenium Starter](https://github.com/CodeSolid/tutorials/tree/master/SeleniumStarter) project.  However, the steps are pretty simple:

* Copy the pom file (see below) into a directory.

{% prism http %}
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <groupId>com.codesolid.tutorials</groupId>
    <artifactId>SeleniumStarter</artifactId>
    <version>1.0</version>

    <properties>
        <junit.version>4.11</junit.version>
        <selenium.version>2.41.0</selenium.version>
    </properties>

    <dependencies>
        <!-- JUnit -->         
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>${junit.version}</version>
            <scope>test</scope>
        </dependency>

        <!-- Selenium -->

        <dependency>
            <groupId>org.seleniumhq.selenium</groupId>
            <artifactId>selenium-firefox-driver</artifactId>
            <version>${selenium.version}</version>
        </dependency>

        <dependency>
            <groupId>org.seleniumhq.selenium</groupId>
            <artifactId>selenium-support</artifactId>
            <version>${selenium.version}</version>
        </dependency>

        <dependency>
            <groupId>org.seleniumhq.selenium</groupId>
            <artifactId>selenium-java</artifactId>
            <version>${selenium.version}</version>
        </dependency>

    </dependencies>

</project>
{% endprism %}


* Set up a place for files to go.  In the same directory where you copied your pom file, run the following at the command prompt: _mkdir src\test\java\com\codesolid\tutorials\seleniumstarter_" The "src\test\java" portion is a maven standard directory layout for test files and should
be kept as is.  In contrast, the "com\codesolid\tutorials\seleniumstarter" portion of that path will be our package structure, and you can modify 
it to fit your project's package structure as needed.
* In IntelliJ Idea, "File / Import Project", then point to your directory.  When prompted in the Import Project dialog, make sure "Import project from external
model" and "Maven" are selected.
* In the next Import Project dialog (below), I generally leave the defaults with the exception of "Import Maven Projects Automatically".  This helps to make sure that any changes you make to the POM are also reflected in your IntelliJ idea.

!["Import Maven Project"](/images/20140606/ImportMaven.png)

The rest of the dialogs are pretty straightforward.  I generally skip importing any modules if prompted for one, choose my favorite JDK when prompted,
and provide a name for the project that matches the name I'm prompted for in the POM.

If you need more details on working with Idea and Maven projects, check out the IntelliJ documentation on [working with Maven](http://wiki.jetbrains.net/intellij/Creating_and_importing_Maven_projects).

<h2><a id="ExportingSeleniumToJava">Exporting Selenium to Java</a></h2>

Once you have a project set up to house your tests, exporting Selenium tests to a JUnit test case is generally quite straightforward. To export a single test to JUnit, simply select "Export Test Case As", and select the type of test case you want to create.  Let's select Java / JUnit 4 / WebDriver, as shown here:

!["Export Java JUnit4 WebDriver Test"](/images/20140606/ExportJUnitTestcase.png)

What you'll want to do is export it to the package directory you created above, in other words, the directory where you copied the pom file plus  src\test\java\com\codesolid\tutorials\seleniumstarter and give it a name, such as FirstSeleniumTest.java.  The final step before running it in Idea is to make sure
your package name is set up correctly -- chances are Selenium exported something like "package com.example".  Find the package directive at the top of the file
and change it to read:  

{% prism  java %}
package com.codesolid.tutorials.seleniumstarter;
{% endprism %}

At this point you should be able to right-click on the file in IntelliJ Idea and click on "Debug FirstSeleniumTest".  You should see Firefox launch and your test should pass.  If you run into trouble, try grabbing the project from [Github](https://github.com/CodeSolid/tutorials/tree/master/SeleniumStarter).  Enjoy!