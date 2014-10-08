---
author: admin
comments: true
date: 2013-05-11 19:21:15+00:00
layout: post
excerpt: JUnit is a powerful and easy to use tool for writing and running Unit tests that every Java programmer should know.  In this tutorial, we'll get you up and running with JUnit and IntellijJ idea.
slug: a-simple-junit-tutorial-using-maven-and-intellij-idea
title: A Simple JUnit Tutorial using Maven and IntelliJ Idea
wordpress_id: 1422
categories:
- Testing, JUnit, TestNG Tutorials
- Tutorials
tags:
- IntelliJ Idea
- Java
- Maven
- Testing, JUnit, TestNG Tutorials
- Tutorials
---

<div id="table_of_contents">
<h1>Table of Contents</h1>
<ul >
<li><a href="#WhatIsJUnit">What is JUnit?</a></li>
<li><a href="#GetTheTools">Get the Tools</a></li>
<li><a href="#SettingUpTheMavenProject">Set up the Maven Project</a></li>
<li><a href="#RunInMaven">Run In Maven</a></li>
<li><a href="#ImportingTheProject">Importing the Maven Project Into Idea</a></li>
<li><a href="#YourFirstTest">Writing Your First Test</a></li>
<li><a href="#BuildingAndRunningTests">Building and Running the Tests</a></li>
<li><a href="#FixingABug">Fixing a Bug</a></li>
</ul>
</div>

<h2><a id="WhatIsJUnit">What is JUnit?</a></h2>

JUnit is a powerful and easy to use tool that every Java programmer should know.  It's a unit test framework  That is to say, it's a framework for testing out small pieces of code, such as individual classes and methods, as the code is being developed.

For those newcomers to Java who aren't familiar with JUnit, Here is a really simple, "Hello World" style tutorial about how to use JUnit with Maven and IntelliJ Idea Community Edition -- both free tools.  By the way, this tutorial also works fine with the professional edition.

<h2><a id="GetTheTools">Get The Tools</a></h2>

If you need to set up those tools, here are the  [IntelliJ Idea download page](http://www.jetbrains.com/idea/download/index.html) and the [Maven Download / Installation page](http://maven.apache.org/download.cgi#Installation).  Be sure to read the installation page carefully in the case of Maven especially

<h2><a id="SettingUpTheMavenProject">Set up the Maven Project</a></h2>

Though it may seem counter-intuitive, I find that the easiest way to start with Maven and Idea projects is not to start in Idea, but to simply to save a maven project file ("pom.xml") in a text editor.  Here is that file:

{% prism http %}
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.codesolid</groupId>
    <artifactId>HelloJUnit</artifactId>
    <version>1.0</version>

    <dependencies>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.11</version>
            <scope>test</scope>
        </dependency>
    </dependencies>

</project>
{% endprism %}


<h2><a id="RunInMaven">Run In Maven</a></h2>

Save the file above using a plain text editor in a new directory that will be the root directory of you project, then open a command prompt at that location.  Enter the command  "mvn -q install"

If you're new to Maven, the cool thing about it is that it will install any dependencies your project needs, so if the universe is behaving as it should you should see output that looks something like:

<pre>

[debug] execute contextualize
[debug] execute contextualize

--------------------------------------------------
 T E S T S
--------------------------------------------------

Results :

Tests run: 0, Failures: 0, Errors: 0, Skipped: 0
</pre>

You'll notice no tests were run -- that's as expected, since we haven't written them yet!  Before we do, while still at the command line there's one more manual step we have to do, and that's to set up our directory structure in a way that Maven expects it.  On Windows the command is:

<pre>
md src\main\java
md src\main\resources
md src\test\java
md src\test\resources
</pre>

On Unix or a Mac the command would be:
<pre>
mkdir -p src/main/java
mkdir -p src/main/resources
mkdir -p src/test/java
mkdir -p src/test/resources
</pre>


<h2><a id="ImportingTheProject">Importing the Project Into Idea</a></h2>

At this point you're ready to open the Idea IDE.  Now we're going to create an Idea project by importing the maven file we just created.  Click on Import Project, and then navigate to where you have that pom file stored.  It should look something like:

![Idea Import Project](/images/simple_junit/CaptureJUnit.jpg)

On the first dialog that comes up, I usually like to check the box that says "Import Maven projects automatically", as shown below.

![Maven Import](/images/simple_junit/MavenImport.jpg)

You'll then click through a few more dialog boxes, selecting the default for each.  On the one that asks you to select a JVM, make sure it looks like a valid one is selected.

Once you've imported the project, locate the project view on the left, and navigate to the src\test\java directory, as shown below:

![JUnit Project View](/images/simple_junit/JUnitProjectView.jpg)


<h2><a id="YourFirstTest">Your First Test</a></h2>

Right click on the src\test\java directory, and select New / File.  Give it the name "TestHello.java", and paste the following code into it:

{% prism java %}

import org.junit.Test;
import static org.junit.Assert.*;

public class TestHello {

    @Test
    public void testPasses() {
        String expected = "Hello, JUnit!";
        String hello = "Hello, JUnit!";
        assertEquals(hello, expected);
    }

    @Test
    public void testFails() {
        // The worlds most obvious bug:
        assertTrue(false);
    }

    @Test
    public void testArray() {
        int [] array1 = new int[] {1, 2, 3};
        int [] array2 = new int[] {1, 2, 3};
        assertArrayEquals(array1, array2);
    }
}

{% endprism %}


<h2><a id="BuildingAndRunningTests">Building and Running the Tests</a></h2>


To build the test, from the Idea menu select "Build / Make Project".  Next, from the Run menu select Debug.  If all goes well you should see a configuration named "Test Hello".  Click that.

<h2><a id="FixingABug">Fixing a Bug</a></h2>


You will next see the output of the tests in a window that looks like this:

![JUnit Debugging](/images/simple_junit/JUnitDebug.jpg)

You may have noticed when you pasted the code that the testFails method contains "The world's most obvious bug".  Because of this, that test will fail. You'll notice that -- very conveniently -- there will be a link to TestHello.java:16 -- clicking that link will bring you right to the failing line of code, where you should be able to fix it easily. Running tests in the debugger like this is a very easy way to fix the failing code.

Of course, these tests are very simple and do not test any "real" code.  In a real project you'd be moving back between fixing the bugs in the code, which the test code was exercising, while writing new tests to exercise new code.  But if you put your Java classes in src\main\java, you can easily test them from tests you add to src\test\java.  

Enjoy!
 
