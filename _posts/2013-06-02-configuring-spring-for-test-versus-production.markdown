---
author: admin
comments: true
date: 2013-06-02 06:08:04+00:00
excerpt: In this tutorial, we'll discuss some different ways to configure Spring for
  different environments such as test, production, etc.,  You'll also learn how to
  configure spring properties at runtime using the PropertyPlaceholderConfigurer class,
  and how to test your Spring configuration using JUnit.
layout: post
slug: configuring-spring-for-test-versus-production
title: Configuring Spring for Test versus Production
wordpress_id: 1611
categories:
- Spring
- Tutorials
tags:
- Java
- PropertyPlaceholderConfigurer
- Spring
- Spring Configuration
- Testing, JUnit, TestNG Tutorials
---




### Contents






  * Spring Configuration Options


  * The META-INF Gospel


  * Configuring Spring Properties at Build Time Using Multiple Modules


  * Configuring Spring Properties at Run Time Using the PropertyPlaceholderConfigurer

  
  
    * Setting up  PropertyPlaceholderConfigurer in the ApplicationContext

  
    * Some Properties Files to Test

  
    * Injecting Properties

  
    * Testing with JUnit

  






### Spring Configuration Options


The Spring Framework is not opinionated software.  Instead, Spring has a rich set of configuration options about which you can -- indeed, about which you must -- make up your own mind.  In this tutorial we'll discuss some of the ways you can easily configure your application to be ready for production, while still making it easy to work with your code in a test environment.
[](META-INF)

### The META-INF Gospel


When you're first getting something working, the easiest path I've found is to locate your properties somewhere underneath what in the standard Maven directory structure is the src\main\resources\META-INF directory.  Then, to give just one example for now, you want to point to those resources as shown in the following annotation from a Unit test:

[cc lang="Java"]
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:META-INF/spring/applicationContext.xml")
// class YourTestClass { ...
[/cc]

This will work just fine, and although I've seen a few spec Nazis on Stackoverflow complain that the Java JAR specification contains the gospel truth about what files are allowed in the META-INF directory, I'll take the side of Roo and other Spring tools to preach a new gospel to you:

**Generally speaking, put your configuration files in META-INF, unless you like pain.**

That will get your software working, but the limitation of this approach is that once you've located all your configuration files in META-INF\<somewhere> to set up a single environment correctly, how do you set up _different_ environments like those you'll need for test, staging, and production.  Though I'm sure there are a lot more ways to skin this poor cat, let's discuss two of the more popular approaches to this problem.  The first option, which we'll discuss more briefly, is to resolve all your option file dependencies at compile time.  


### Configuring Spring Properties Using Multiple Modules


To use this approach, you set up a multi-module project.  This approach takes advantage of the fact that WAR files can pack all the jars they need inside themselves, ready to be deployed to Tomcat or another web server.  So we bundle  our code and any configuration files that are common to all our target environments in the WAR file, then build a jar file for each different environment containing only environment-specific files.

What we might end up with is a directory structure that looks something like this:


    
    
    root        
    root/app
    root/config_test
    root/config_production
    



The app directory contains the whole structure of the application's WAR file project.  In the root directory is a pom file that builds the targets in the other directories, as shown in the following snippet:

[cc lang="XML"]


   app
   config_test
   config_production

[/cc]

The config_test and config_production files contain a src\main\resource\WEB-INF directory, whatever configuration files you need, and a pom file in the root to bundle the configuration files into a JAR.  What's important is that although the contents of the files will contain different settings, the names of the files should be the same in each configuration file we build -- that way when they're referenced from the main files in the application's WAR files, all is well.

In turn, the WAR file in the main app depend on either the test configuration or the production configuration as desired, as shown in the snippet below.   In the snippet below, we're building for the test environment, so the production configuration is disabled.

[cc lang="XML"]



	com.codesolid
	config_test
	1.0-SNAPSHOT



[/cc]

I've used the multi-module approach and it works fine, but having to build three projects to configure one WAR file is a bit heavy handed.  It also spreads the configuration files around more than I like.  Fortunately, there's an easier way to get this done, and that is to resolve the configuration files at run time, not at build time.  


### Configuring Spring Properties at Run Time Using the PropertyPlaceholderConfigurer



One powerful mechanism that Spring provides to make configuration flexible across different environments is the PropertyPlaceholderConfigurer.  Since I think it's the better approach, let's take a look at a more detailed tutorial.  The code for this is available under the PropertyPlaceholderConfigurer directory of our [Github Tutorials Repository](https://github.com/CodeSolid/tutorials).

The structure of our PropertyPlaceholderConfigurer build is shown below:

[![PropertyPlaceholderConfigurer](http://www.particlewave.com/wordpress/wp-content/uploads/2013/06/PropertyPlaceholderConfigurer.jpg)](http://www.particlewave.com/wordpress/wp-content/uploads/2013/06/PropertyPlaceholderConfigurer.jpg)

We have a single bean under test (yes, you guessed it: TestBean), and our mission, if we choose to accept it, is to:




	
  1. Dynamically set up properties in our configuration files, so we can switch to new properties in production.

	
  2. Inject the values of those properties into the test bean.  In many cases our properties would be consumed by another third-party bean, but injecting properties directly allows us to take the next step...

        
  3. Set up a unit test to show that we correctly injected the properties we want for the appropriate environment





#### Setting up  PropertyPlaceholderConfigurer in the ApplicationContext



Our main "common configuration" area is in the META-INF\spring.  Our global properties file and main application context file go here.   Let's look at applicationContext.xml, where the detailed comment explains what's going on:


[cc lang="XML"]

 

    

    
        
            
                classpath:META-INF/spring/*.properties
                classpath:META-INF/${environment}/*.properties
            
        
    

    
    

[/cc]



#### Some Properties Files to Test


To have something to test, we've set up three simple properties files.  First, we have a global "common.properties" with our humble title: 

[cc lang="bash"]
application.name=The Greatest Sample Ever
[/cc]

Next come two db.properties files:

test/db.properties:

[cc lang="bash"]
# These are the values we'll use when testing.
database.driverClassName=com.mysql.jdbc.Driver
database.url=jdbc:mysql://localhost/mydb
database.username=myuser
database.password=mypassword
[/cc]

production/db.properties:

[cc lang="bash"]
# Different values for the production server
database.driverClassName=com.mysql.jdbc.Driver
database.url=jdbc:mysql://localhost/proddb
database.username=prod_user
database.password=DoubleSecretProbation
[/cc]



#### Injecting Properties



To keep things simple, lets inject only two of these properties.  If our setup is correct and we set the environment variable "environment" to "test" we should be able to verify that we get the right application name (a global property), and the test environment value for (for example) database.username. 

We inject these properties in TestBean.java:

[cc lang="Java"]
package com.codesolid.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service(value="testBean")
public class TestBean {
    private String applicationName;
    private String databaseUser;

    // This property is set in common.properties and will be the same
    // for all environments, test, production, etc.
    @Value("${application.name}")
    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    // In contrast, this property and other database related properties
    // are quite likely different in test and production.
    @Value("${database.username}")
    public void setDatabaseUser(String databaseUser) {
        this.databaseUser = databaseUser;
    }

    public String getDatabaseUser() {
        return databaseUser;
    }

    public String getApplicationName() {
        return applicationName;
    }
}
[/cc]



#### Testing with JUnit



As a final step, let's make sure we're running our test environment and our properties are injected correctly.  Our JUnit test is as follows:

[cc lang="Java"]
package com.codesolid.properties.tests;

import com.codesolid.properties.TestBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.context.ApplicationContext;


import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:META-INF/spring/applicationContext.xml")
public class ContextTests {

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private ApplicationContext ac;

    /* Verify that we've correctly injected a property from the Spring directory 
    (in this case, the common.properties file).  Property files in this
    directory define properties that can be used in all environments. */
    @Test
    public void testApplicationName() {
        String expected = "The Greatest Sample Ever";
        TestBean test = (TestBean) ac.getBean("testBean");
        String actual = test.getApplicationName();
        assertEquals(actual, expected);
    }

    /* Verify that we've correctly injected a property from the test directory's 
    db.properties. Property files in the test directory only apply to the test 
    environment and can be overridden in the production directory (for example). */
    @Test
    public void testDatabaseConfiguredCorrectly() {
        String expected = "myuser";
        TestBean test = (TestBean) ac.getBean("testBean");
        String actual = test.getDatabaseUser();
        assertEquals(actual, expected);
    }

[/cc]

Working with the PropertyPlaceholderConfigurer class lets you easily configure your WAR file for production, test, and any other environments you need -- and with that out of the way, you can now get busy writing your application.  Enjoy!
