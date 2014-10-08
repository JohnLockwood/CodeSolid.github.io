---
author: admin
comments: true
date: 2013-07-04 19:14:37+00:00
excerpt: Although there aren't too many examples to be found about it, using Spring's
  AbstractTestNGSpringContextTests turns out to be really easy! We walk you through
  it in this brief tutorial
layout: post
slug: using-testng-with-spring
title: Using TestNG with Spring
wordpress_id: 1836
categories:
- Testing, JUnit, TestNG Tutorials
tags:
- Java
- Spring
- Testing
- TestNG
- Unit Testing
---

Testing Spring in JUnit is fairly simple given the test runner support.  Using TestNG is a little bit more complex, but this tutorial will quickly get you up to speed.
<div id="table_of_contents">
<h1>Table of Contents</h1>
<ul >
<li><a href="#MovingToTestNGfromJUnit">Moving to TestNG from JUnit</a></li>
<li><a href="#SettingUpMaven">Setting Up The Maven Dependency for TestNG</a></li>
<li><a href="#SimpleTests">Some Simple AbstractTestNGSpringContextTests Examples</a></li>
</ul>
</div>

<h2><a name="MovingToTestNGfromJUnit" id="MovingToTestNGfromJUnit">Moving to TestNG from JUnit</a></h2>

A few weeks ago I published a [Simple Introduction to Spring Unit testing using JUnit](http://codesolid.com/spring-unit-testing-using-junit/).  We've been using JUnit quite a bit in our examples so far, but as some of you may already know, JUnit is not the only unit test framework for Java.  Another framework, TestNG, offers a lot of features that JUnit lacks, such as the ability to run parameterized tests in which a given test is run against an array of different values, and the ability to break tests down into categories coupled with fine-grained configuration control over what tests to run.  Indeed, the author of TestNG ("Next Generation") very much had the limitations of JUnit in mind in writing the new framework.  You can read about some of the problems with JUnit that TestNG was designed to solve on it the [TestNG web site](http://testng.org/).

Of course, the choice of which technology to use is often a matter not of what's best, but what's most popular (especially if you're early in your career and need to get certain key technologies added to your resume).  So for my wife, who's just beginning her technical career as a tester, I'd be inclined to recommend JUnit, whereas for myself, old fart programmer that I am, I have the luxury of picking the technology I consider better in many cases.  For example, compared to IntelliJ Idea, for me Eclipse is just too painful -- free or not, big market share or not.  So I use IntelliJ.  So if you're playing "Dice Keyword Bingo", stick with JUnit, otherwise do yourself a favor and take a look at TestNG.  </My two cents>


<h2><a name="SettingUpMaven" id="SettingUpMaven">Setting Up The Maven Dependency for TestNG</a></h2>

### Setting up the Maven Dependency for TestNG


Rather than repeat all the sample bean code from our [original JUnit article](=), let's focus here on the tests. The original sample code is, after all, not very interesting -- it only gave us something to test.  To migrate the JUnit sample, the first thing I needed to do was change the dependency section in our POM file slightly.  Remove the JUnit dependency:

{% prism java %}
  junit
  junit
  4.8.2
  test
{% endprism %}

Now add the dependency for TestNG:

{% prism java %}
  org.testng
  testng
  6.1.1
  test
{% endprism %}

For the benefit of those of you starting from scratch, the here is [the full POM file](https://github.com/CodeSolid/tutorials/blob/master/SpringTestNG/pom.xml).


<h2><a name="SimpleTests" id="SimpleTests">Some Simple AbstractTestNGSpringContextTests Examples</a></h2>

### Simple AbstractTestNGSpringContextTests Test Examples


Next, let's see what we need to do to use Spring's basic TestNG testing support class, AbstractTestNGSpringContextTests.  Don't let the "Abstract" in the name scare you off here, there's really nothing much to implement. All you need to do is add a ContextConfiguration annotation to your class and it just works.  Here's the Hello-World-simple example, which only uses the ContextConfiguration annotation to get it to compile and run.

{% prism java %}
package com.codesolid.tutorials.tests;

import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

@ContextConfiguration("file:src/main/resources/spring-config.xml")
public class SimpleTest extends AbstractTestNGSpringContextTests {

    @Test
    public void SomeTest() {
        assertTrue(true);
    }
}
{% endprism %}

Modifying our JUnit tests to work with TestNG was just as easy.  The @RunWith annotation was no longer needed, being replaced by the fact that we're extending AbstractTestNGSpringContextTexts.  So  pretty much the only migration we did involved changing JUnit imports to TestNG ones.  We removed:

{% prism java %}

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
{% endprism %}

We replaced it with:

{% prism java %}
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;
import static org.testng.Assert.*;
{% endprism %}

Here for example is a little bit longer example, that exercises some of the test beans we've wired up in our application context:

{% prism java %}
package com.codesolid.tutorials.tests;

// Generally needed:
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

// Application specific
import com.codesolid.tutorials.UserStory;
import com.codesolid.tutorials.User;

@ContextConfiguration("file:src/main/resources/spring-config.xml")
public class ContextTests extends AbstractTestNGSpringContextTests {

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
    // role with "SuperGenius User".  (This presumably
    // is Wile E. Coyote.)
    @Test
    public void testUserRoleIsAsExpected() {
        UserStory story = (UserStory) ac.getBean("userStory");
        assertEquals(story.getUser().getRole(), "SuperGenius User");
    }
}
{% endprism %}
Getting started using the Spring TestNG support turned out to be really easy.  Check our our IntelliJ project and the full source on [GitHub](https://github.com/CodeSolid/tutorials/tree/master/SpringTestNG).
