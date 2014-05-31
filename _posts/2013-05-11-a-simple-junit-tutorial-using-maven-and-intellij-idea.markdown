---
author: admin
comments: true
date: 2013-05-11 19:21:15+00:00
layout: post
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




### Contents






  * What Is JUnit?


  * Get the tools


  * Set Up the Maven Project


  * Run In Maven


  * Importing the Maven Project Into Idea


  * Writing Your First Test


  * Building and Running the Tests


  * Fixing a Bug







### What is JUnit?


JUnit is a powerful and easy to use tool that every Java programmer should know.  It's a unit test framework  That is to say, it's a framework for testing out small pieces of code, such as individual classes and methods, as the code is being developed.

For those newcomers to Java who aren't familiar with JUnit, Here is a really simple, "Hello World" style tutorial about how to use JUnit with Maven and IntelliJ Idea Community Edition -- both free tools.  By the way, this tutorial also works fine with the professional edition.


### Get The Tools


If you need to set up those tools, here are the  [IntelliJ Idea download page](http://www.jetbrains.com/idea/download/index.html) and the [Maven Download / Installation page](http://maven.apache.org/download.cgi#Installation).  Be sure to read the installation page carefully in the case of Maven especially


### Set up the Maven Project


Though it may seem counter-intuitive, I find that the easiest way to start with Maven and Idea projects is not to start in Idea, but to simply to save a maven project file ("pom.xml") in a text editor.  Here is that file:

[cc lang="Java"]


    4.0.0

    com.particlewave
    HelloJUnit
    1.0

    
        
            junit
            junit
            4.11
            test
        
    


[/cc]



### Run In Maven


Save the file above using a plain text editor in a new directory that will be the root directory of you project, then open a command prompt at that location.  Enter the command  "mvn -q install"

If you're new to Maven, the cool thing about it is that it will install any dependencies your project needs, so if the universe is behaving as it should you should see output that looks something like:

[cc lang="bash"]

[debug] execute contextualize
[debug] execute contextualize

--------------------------------------------------
 T E S T S
--------------------------------------------------

Results :

Tests run: 0, Failures: 0, Errors: 0, Skipped: 0
[/cc]

You'll notice no tests were run -- that's as expected, since we haven't written them yet!  Before we do, while still at the command line there's one more manual step we have to do, and that's to set up our directory structure in a way that Maven expects it.  On Windows the command is:

[cc lang="bash"]
md src\main\java
md src\main\resources
md src\test\java
md src\test\resources
[/cc]

On Unix or a Mac the command would be:
[cc lang="bash"]
mkdir -p src/main/java
mkdir -p src/main/resources
mkdir -p src/test/java
mkdir -p src/test/resources
[/cc]




### Importing the Project Into Idea



At this point you're ready to open the Idea IDE.  Now we're going to create an Idea project by importing the maven file we just created.  Click on Import Project, and then navigate to where you have that pom file stored.  It should look something like:

![](http://www.particlewave.com/wordpress/wp-content/uploads/2013/05/CaptureJUnit.jpg)

On the first dialog that comes up, I usually like to check the box that says "Import Maven projects automatically", as shown below.

![](http://www.particlewave.com/wordpress/wp-content/uploads/2013/05/MavenImport.jpg)

You'll then click through a few more dialog boxes, selecting the default for each.  On the one that asks you to select a JVM, make sure it looks like a valid one is selected.

Once you've imported the project, locate the project view on the left, and navigate to the src\test\java directory, as shown below:

![](http://www.particlewave.com/wordpress/wp-content/uploads/2013/05/JUnitProjectView.jpg)


### Your First Test



Right click on the src\test\java directory, and select New / File.  Give it the name "TestHello.java", and paste the following code into it:

[cc lang="Java"]
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

[/cc]


### Building and Running the Tests


To build the test, from the Idea menu select "Build / Make Project".  Next, from the Run menu select Debug.  If all goes well you should see a configuration named "Test Hello".  Click that.


### Fixing a Bug



You will next see the output of the tests in a window that looks like this:

![](http://www.particlewave.com/wordpress/wp-content/uploads/2013/05/JUnitDebug.jpg)

You may have noticed when you pasted the code that the testFails method contains "The world's most obvious bug".  Because of this, that test will fail. You'll notice that -- very conveniently -- there will be a link to TestHello.java:16 -- clicking that link will bring you right to the failing line of code, where you should be able to fix it easily. Running tests in the debugger like this is a very easy way to fix the failing code.

Of course, these tests are very simple and do not test any "real" code.  In a real project you'd be moving back between fixing the bugs in the code, which the test code was exercising, while writing new tests to exercise new code.  But if you put your Java classes in src\main\java, you can easily test them from tests you add to src\test\java.  

Enjoy!
 
