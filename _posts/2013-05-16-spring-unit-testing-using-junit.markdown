---
author: admin
comments: true
date: 2013-05-16 19:32:44+00:00
excerpt: The Spring Framework provides special JUnit test runners to make acquiring
  and testing an ApplicationContext really easy to do.  We'll show you how to take
  advantage of these special test runners to do basic Spring Unit testing.  We'll
  also teach you how to acquire an application context without using these runners,
  and though we'll demonstrate this using JUnit as well, the same techniques can be
  used in Standalone Spring applications or in other test frameworks like TestNG.
layout: post
slug: spring-unit-testing-using-junit
title: Spring Unit Testing Using JUnit
wordpress_id: 1494
categories:
- Spring
- Testing, JUnit, TestNG Tutorials
- Tutorials
tags:
- Java
- Swing
- TDD
- Testing
- Testing, JUnit, TestNG Tutorials
- Unit Testing
---


<div id="table_of_contents">
<h1>Table of Contents</h1>
<ul >
<li><a href="#Introduction">The Simplest Case - Exploring the IDE Starter Project</a></li>
<li><a href="#TestingSpringBeans">Testing Some Basic Spring Beans</a></li>
<li><a href="#ApplicationContext">Pojo Style: Getting an Application Context in a junit.org Test Runner</a></li>
</ul>
</div>

<h2><a id="Introduction" name="Introduction">Exploring the IDE Starter Project</a></h2>

In this tutorial we'll set up some really basic Spring unit tests -- the sort of thing that is vanilla enough so you can steal it and get started on your own tests. I developed these tests in IntelliJ Idea Ultimate Edition, but when I check them in to the [Github repository](https://github.com/codesolid/tutorials) I'll try to make something available in Maven and Eclipse, so you can try it out that way if you like. (Update -- that's done, [here are the instructions](http://www.particlewave.com/2013/05/17/how-to-run-the-codesolid-tutorials-in-eclipse/)).  I originally developed these tests in a web app I was working on, but to keep things simple let's start instead by creating a new project in Idea as a "Spring Application" for the project type.

[![Spring Starter Project in IntelliJ](http://www.particlewave.com/wordpress/wp-content/uploads/2013/05/SwingJUnitScreenshot1.jpg)](http://www.particlewave.com/wordpress/wp-content/uploads/2013/05/SwingJUnitScreenshot1.jpg)

You can then make the project from the build menu, and you'll see if you look around in the test directory (actually src\test\java\foo.bar, to be precise), that Idea has created your first Spring Test for you as SpringAppTests.java:

{% prism java %}
package foo.bar;

import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-config.xml")
public class SpringAppTests {
@Autowired
private HelloService helloService;

@Test
public void testSayHello() {
Assert.assertEquals("Hello world!", helloService.sayHello());
}
}

{% endprism %}

This unit test is doing a few interesting things. First of all, it's annotated as "@RunWith(SpringJUnit4ClassRunner.class)". The Spring JUnit runner, which works in conjunction with the Spring [TestContextManager](http://static.springsource.org/spring/docs/3.0.x/api/org/springframework/test/context/TestContextManager.html), allows you to easily set up Spring ApplicationContexts for your tests, as you can see it does here with the annotation @ContextConfiguration("classpath:spring-config.xml"). Inside the SpringAppTests class, you can see that you can also use Dependency Injection to wire up your objects for you. Note that the object that's under the @Autowired annotation is used in the test without an explicit constructor call.

<h2><a id="TestingSpringBeans" name="TestingSpringBeans">Testing Some Basic Spring Beans</a></h2>

Now let's write our own test to exercise some basic beans to see how the Spring framework injects dependencies as needed. We'll want to compare that to what would happen outside of the framework so we'll set up our beans in such a way as to work differently depending on the context -- Spring versus non-Spring. (I was tempted to say Spring versus Fall in that last sentence, but Java suffers from enough puns already).

Let's say we want to model some classes to capture software User Stories. Of course, every story needs a character, so we'll want a class to model the person using the software. One very useful form of user story takes the basic form: As a <Type of User> I want to <Something the software can do> in order that <some benefit to the user>.

So let's write two beans to capture the relationship between a user and the UserStory. First, the UserStory class:

{% prism java %}
// UserStory.java

package com.codesolid.tutorials;

/**
* Class UserStory
* Description: An example bean that models a user story.
* Uses the standard story form:
* "As a ______" (user role), "I want to ______" (actionDesired)
* "so that _____" (outcomeDesired).
*/
public class UserStory {
private User user;
private String actionDesired = "";
private String outcomeDesired = "";
public UserStory() {
}

public User getUser() {
return user;
}

public void setUser(User user) {
this.user = user;
}

public String getActionDesired() {
return actionDesired;
}

public void setActionDesired(String actionDesired) {
this.actionDesired = actionDesired;
}

public String getOutcomeDesired() {
return outcomeDesired;
}

public void setOutcomeDesired(String outcomeDesired) {
this.outcomeDesired = outcomeDesired;
}
}
{% endprism %}
And the User class:
{% prism java %}
package com.codesolid.tutorials;

/**
* Class User
* Description: An example of a bean dependency. Story is dependent on Actor.
*/
public class User {
private String role;

public User() {
setRole("User");
}

public String getRole() {
return role;
}

public void setRole(String role) {
this.role = role;
}
}

{% endprism %}

As you can see, if you were to construct a UserStory object at this point, the user field it contains would be null. The trade-off on class dependencies is basically this: You can ensure that your objects are always fully constructed by putting all the dependencies in the constructor. However, this means that your object becomes tightly coupled with other objects, and harder to test. Having accessors to set the dependencies means you have the flexibility to test only that part of the object that relies on the dependencies, or you can set up all your dependencies up front and test a fully constructed object.

Spring can inject the dependencies either way, but many people prefer using the accessors using what's known as "Setter Based Dependency Injection".

Take a look at the file src\main\resources\spring-config.xml. Here we've configured the dependency using XML. (Spring also allows this to be done using annotations, but we won't cover that in this tutorial):

When we add our dependencies, the file looks like this:

{% prism XML %}

xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
xmlns:context="http://www.springframework.org/schema/context"
xsi:schemaLocation="http://www.springframework.org/schema/beans 
http://www.springframework.org/schema/beans/spring-beans.xsd 
http://www.springframework.org/schema/context 
http://www.springframework.org/schema/context/spring-context.xsd">

{% endprism %}

We're now ready to write some tests against this code. I'll show you the whole listing in one go, since I've commented it fairly heavily to show what we're demonstrating in each case. Bear with the imports -- there's some code underneath, I promise!

{% prism java %}
package com.codesolid.tutorials.tests;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.context.ApplicationContext;
import com.codesolid.tutorials.UserStory;
import com.codesolid.tutorials.User;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/resources/spring-config.xml")
public class ContextTests {

@SuppressWarnings("SpringJavaAutowiringInspection")
@Autowired
protected ApplicationContext ac;

// Using ApplicationContext
// The getBean method is the workhorse of the Spring ApplicationContext.
// The user field in the UserStory object was set up (dependency injected)
// by Spring:
@Test
public void testUserNotNull() {
UserStory story = (UserStory) ac.getBean("userStory");
assertNotNull(story.getUser());
}

// Not using ApplicationContext
// Regular non-Spring instantiation
// Instantiating our own UserStory, you can see that the user
// is null.
@Test
public void testUserStoryNotFromContext() {
UserStory story = new UserStory();
assertNull(story.getUser());
}

// Not using ApplicationContext
// Regular non-Spring instantiation
// The default role if you just call the constructor
// is "User".
@Test
public void testNonSpringUserDefaultRole()
{
User u = new User();
assertEquals(u.getRole(), "User");
}

// Using ApplicationContext
// Since Spring instantiated this according to the property
// from spring-config.xml, it overrode the default "user"
// role with "SuperGenius User". (This presumably
// is Wile E. Coyote.)
@Test
public void testUserRoleIsAsExpected() {
UserStory story = (UserStory) ac.getBean("userStory");
assertEquals(story.getUser().getRole(), "SuperGenius User");
}
}
{% endprism %}

As you can see, Spring allows for a great deal of flexibility in configuring the objects in the system. In a simple context like this one it may be hard to see the advantage of this, but in the context of a large application, having a configuration system like this in place means that the system is very flexible.

<h2><a id="ApplicationContext" name="ApplicationContext">Getting an Application Context in a junit.org Test Runner</a></h2>

You'll probably use the SpringJUnit4ClassRunner for most of your Spring testing, since it is derived from the "vanilla", junit.orgJ Unit4ClassRunner. However it's also possible to get an ApplicationContext outside of the context of the Spring test runner, and doing this in JUnit demonstrates how to achieve this programatically. The following sample shows some of the same tests we ran earlier set up in this way:

{% prism java %}
package com.codesolid.tutorials.tests;
import com.codesolid.tutorials.UserStory;
import org.junit.Test;
import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import static org.junit.Assert.*;

/* Here we run one of the same tests we run in ContextTests, but we separate it out here to demonstrate
* we can do it with a POJU (Plain Old JUnit :) test runner.
*/
public class JUnitContextTests {

ApplicationContext ac;

@Before
public void setUp()
{
ac = new FileSystemXmlApplicationContext("file:src/main/resources/spring-config.xml");
}

@Test
public void testUserCorrectFromPlainOldJUnitTest() {

UserStory story = (UserStory) ac.getBean("userStory");
// Spring is working fine using this app context
assertEquals(story.getUser().getRole(), "SuperGenius User");

// In this case our User is not wired up
UserStory story2 = new UserStory();
assertNull(story2.getUser());
}

@Test
public void testPrewiredUserCorrect() {
UserStory story = (UserStory) ac.getBean("userStory");
assertEquals(story.getUser().getRole(), "SuperGenius User");
}
}

{% endprism %}

I hope you enjoyed this tutorial on Spring Unit Testing -- feel free to leave a comment if you're finding it useful.
