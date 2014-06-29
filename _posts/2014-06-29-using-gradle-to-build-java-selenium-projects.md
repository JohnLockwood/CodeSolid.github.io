---
author: John Lockwood
layout: post
title: "Using Gradle to Build Java Selenium Projects" 
description:  Ant lets you script anything, and Maven handles all your dependencies neatly.  If only there were a build tool that let you do both.  Well, there is -- it's Gradle, and in this article we show you how to use it with IntelliJ and Selenium.

excerpt:  Ant lets you script anything, and Maven handles all your dependencies neatly.  If only there were a build tool that let you do both.  Well, there is -- it's Gradle, and in this article we show you how to use it with IntelliJ and Selenium.
categories:
- Miscellaneous
- Gradle
- Selenium
- Testing
- Integration Testing
---

In a recent article, we showed you how to start working with [Selenium in IntelliJ Idea](http://localhost:4000/selenium-and-intellij-idea-part-two/).  As we've often done in the past, we started our Idea project with a Maven build script (a pom file).  Well, all that worked well enough, but as Jenniffer was beginning to make some
really good progress on her [Goalboost Testing](https://github.com/JennifferLockwood/GoalboostTesting) project, I wanted to help her with a tool that would enable
custom scripting of tasks such as generating HTML from Markdown for the test plan, while still allowing us to build and run Selenium tests in Java in a straightforward way as Maven does.  

In recent years there's been a lot of buzz around Gradle, the tool that lets you enjoy the easy dependency management that made Maven so popular while still having the flexibility to script whatever custom tasks you need to create.  So being the language and tools junkie that I am, of course I had to try it out.  My first task was to 
allow Jenniffer to write the Goalboost test plan in Markdown and generate the HTML as needed so she could preview it without pushing to Github.  Using Andres Almiray's [markdown-gradle-plugin](https://github.com/aalmiray/markdown-gradle-plugin), this turned out to be pretty easy to do:

{% prism groovy %}
buildscript {
    repositories {
        jcenter()
         maven {
     	 url  'http://dl.bintray.com/content/aalmiray/kordamp'
    	}        
    }
    dependencies {
        classpath 'org.kordamp:markdown-gradle-plugin:0.1'
    }
}
apply plugin: 'markdown'

markdownToHtml.sourceDir = file("doc/md")
markdownToHtml.outputDir = file("doc/html")
{% endprism %}

Now that we had a way to "build" the test plan as it was being written, we also wanted to use the same tool to build and run the Selenium tests that she'd be developing against the plan.  So what was needed was a Gradle build script that build a Java Selenium project the same way my earlier [Maven build script](https://github.com/CodeSolid/tutorials/blob/master/SeleniumStarter/pom.xml) did.  Taking as a starting point Juan Cavallotti's fine article on [converting a Maven project to Gradle](
http://jcavallotti.blogspot.com/2013/12/migration-of-maven-based-project-to.html), it turned out this was really to do.  The first version was done in about twenty minutes, but here's a version with a bit more polish:

{% prism groovy %}
/*
	build.gradle file for building and running Selenium tests
	Example uses:
	gradle clean test 		- Rebuild and run tests
	gradle test 			- Run tests only
	gradle cleanTest test  	- Force tests to be run even if up to date
	gradle viewResults		- Displays the report of test results (Windows only)
*/

// Support for building and testing
apply plugin: 'java'

// (Optional) configure name and version for jar
jar {
	version  '1.0'
	baseName 'SeleniumStarter'
	extension '.jar'
}

// Cf. Maven properties
ext.junitVersion = '4.11'
ext.seleniumVersion = '2.41.0'


// Cf. Maven <dependencies>
dependencies {
	compile group: 'junit', name: 'junit', version: junitVersion
	compile group: 'org.seleniumhq.selenium', name: 'selenium-firefox-driver', version:seleniumVersion
    compile group: 'org.seleniumhq.selenium', name: 'selenium-chrome-driver', version:seleniumVersion
	compile group: 'org.seleniumhq.selenium', name: 'selenium-api', version:seleniumVersion
	compile group: 'org.seleniumhq.selenium', name: 'selenium-java', version:seleniumVersion
}

// A custom task to show report on tests that have run
task viewResults(dependsOn: ['test'] , type:Exec) {
		workingDir './build/reports/tests'		
		commandLine 'cmd', '/c', 'start index.html'	
}
 
// Resolve Maven dependencies as Maven does
repositories {
	mavenCentral()
	mavenLocal() 
}

{% endprism %}

Once you have Gradle installed (if you don't, [download it here](http://www.gradle.org/downloads)), importing the 
Gradle project into IntelliJ Idea works pretty much the same way as importing a Maven project.  That is to say, in Idea you 
use File / Import Project, select the directory where the build.gradle file lives, then click "Import project from external model" 
and select "Gradle" as the model type.

Unfortunately, Idea's support for Gradle is not yet as polished as it is for Maven, but it's still possible to use Gradle to
import the dependencies you need to build from within Idea as well.  To do this, go to Idea / View / Tools / Gradle to open the Gradle
tool window, as shown below:

!["Gradle Tools Menu"](/images/gradle/GradleToolsMenu.png)

This will bring up the Gradle Tool Window, shown below.  In that window, the button with the circular arrows right under "Gradle Tasks"
is the "Refresh all Gradle projects" button.  Clicking this will allow you to build inside of IntelliJ Idea as you normally would,
or you can also select Gradle tasks to run from within this window as well.  I've run the test task and the jar task already, so this
shows up in the Gradle Tool Window under "Recent tasks".

!["Gradle Tool Window"](/images/gradle/GradleToolWindow.png)

The [SeleniumStarterGradle](https://github.com/CodeSolid/tutorials/tree/master/SeleniumStarterGradle) directory of the CodeSolid tutorials
contains a complete Idea project with a Java Selenium test to get you started.  You may need to go into the project settings
to point your gradle configuration to your local Gradle install (File / Settings / Gradle).


