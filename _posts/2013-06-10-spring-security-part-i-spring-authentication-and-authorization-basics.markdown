---
author: admin
comments: true
date: 2013-06-10 02:37:37+00:00
excerpt: In the first of a multi-part series on Spring Security, we'll examine how
  to set up a really basic MVC app with Spring Security by relying on IntelliJ idea
  project generation.   We'll examine the code and discuss how it can be improved,
  and how you can use Spring security features both in the controller layer and the
  view layer.
layout: post
slug: spring-security-part-i-spring-authentication-and-authorization-basics
title: 'Spring Security - Part I:  Spring Authentication and Authorization Basics'
wordpress_id: 1682
categories:
- Spring
- Tutorials
tags:
- Java
- Spring
- Spring Security
- Testing, JUnit, TestNG Tutorials
---

<div id="table_of_contents">
<h1>Table of Contents</h1>
<ul >
<li><a href="#Introduction">Introduction -- My Scandalous Confession</a></li>
<li><a href="#GeneratingAProject">Generating a Spring Security Project</a></li>
<li><a href="#SecurityConfiguration">The Security Configuration Files</a></li>
<li><a href="#StandardPasswordEncoder">Spring's StandardPasswordEncoder</a></li>
<li><a href="#SecurityInTheViewLayer">Using Security in the View Layer -- The Spring Security Tag Libraries</a></li>
</ul>
</div>

<h2><a id="Introduction" name="Introduction">Introduction -- My Scandalous Confession</a></h2>

Before we begin with the tutorial, I am going to lay bare my soul to you.  I know, most tutorials are dry and boring, and don't include such titillating and scandalous gossip as you are about to hear. You're welcome.

Whenever I write an article that has Part 1 in the title, I generally am doing so without even a remote business plan for a clue about how many parts I'm going to end up with.  There, I've made my confession.  Eat your heart out, St. Augustine.

In this tutorial we're going to start digging into Spring Security in the easiest way I know how.  First, spend $199 on a [great Java IDE](http://www.jetbrains.com/idea/download/), or download it free for thirty days so you can get through however many parts this article ends up having.  We're using IntelliJ 12 Ultimate Edition to generate a starter project for this article in the next section.  The cool thing is, though, once you have the [starter project](https://github.com/CodeSolid/tutorials/tree/master/SpringSecurity1), you can likely follow along with the rest of the article using either the IntelliJ Idea 11 or 12 Community Edition, which is free. I've had good luck doing that. You can even [import the POM file into Eclipse](/how-to-run-the-codesolid-tutorials-in-eclipse/) and run the project that way.

<h2><a id="GeneratingAProject" name="GeneratingAProject">Generating a Spring Security Project</a></h2>

With Idea Ultimate Edition Version 12 and later, to create a Spring Security Project you simply select create a new project from the Welcome Screen:

![IntelliJCreateNewProject](/images/spring_security/IntelliJCreateNewProject.jpg)

Next, select Spring Security and give your project a name:

![SpringSecurityCreateProject](/images/spring_security/SpringSecurityCreateProject1.jpg)

<h2><a id="SecurityConfiguration" name="SecurityConfiguration">The Security Configuration Files</a></h2>


In the Spring security starter project, IntelliJ adds the security-related configuration to the two required places, web.xml, and the main Spring configuration file, both of which are in src\webapp\WEB-INF\.

In web.xml, we first set up a security filter name, then use that name to defining a mapping to ensure that all application URLs go through the security filter first:

{% prism http %}
    
 <filter>
        <filter-name>springSecurityFilterChain</filter-name>
        <filter-class>org.springframework.web.filter.DelegatingFilterProxy</filter-class>
    </filter>

    <filter-mapping>
        <filter-name>springSecurityFilterChain</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>

{% endprism %}

Next we turn to the main Spring configuration file, spring-config.xml, which is shown in abbreviated form below:

{% prism http %}
<security:global-method-security jsr250-annotations="enabled" pre-post-annotations="enabled" secured-annotations="enabled" />

<security:http use-expressions="true">
    <security:intercept-url pattern="/**" access="permitAll" />
    <security:form-login />
    <security:logout logout-success-url="/" delete-cookies="JSESSIONID"/>
    <security:remember-me />
    <security:session-management invalid-session-url="/">
        <security:concurrency-control max-sessions="1" error-if-maximum-exceeded="true" />
    </security:session-management>
</security:http>

<bean id="encoder" class="org.springframework.security.crypto.password.StandardPasswordEncoder"/>

<security:authentication-manager>
    <security:authentication-provider>
        <security:password-encoder ref="encoder"/>
        <security:user-service>
            <!-- Password: "koala" for both -->
            <security:user name="user"
                  password="4efe081594ce25ee4efd9f7067f7f678a347bccf2de201f3adf2a3eb544850b465b4e51cdc3fcdde"
                  authorities="user"/>
            <security:user name="admin"
                  password="4efe081594ce25ee4efd9f7067f7f678a347bccf2de201f3adf2a3eb544850b465b4e51cdc3fcdde"
                  authorities="admin"/>
        </security:user-service>
    </security:authentication-provider>
</security:authentication-manager>

{% endprism %}

First, we see a line enabling JSR-250 annotations, such as @RolesAllowed, @PermitAll, @DenyAll, which allows fine-grained, method level access control.  Furthermore, the 'use-expressions="true"' clause allows us to use Spring Expression Language style security expressions in our view layer. We'll be looking at what that allows us to do a bit further on when we discuss the view layer. 

Next, the attributes 'pre-post-annotations="enabled"' and 'secured-annotations="enabled"' will be used by our controllers.  These configuration elements enable a feature that is new as of Spring 3.0.  Spring 3.0 introduced several new, method-level annotations beginning with @Pre and @Post.  We see one of these, @PreAuthorize, used in both the UserService class and the ApplicationController classes:

{% prism java %}
    // In UserService.java:
    @PreAuthorize("hasRole('admin')")
    public Collection getAuthorities(UserDetails userDetails) {
        return userDetails.getAuthorities();
    }

    // In ApplicationController.java:
    @PreAuthorize("hasRole('admin')")
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String admin(ModelMap map) {
        UserDetails userDetails =
                (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Collection securedMessage = userService.getAuthorities(userDetails);
        map.addAttribute("userDetails", userDetails);
        map.addAttribute("userAuthorities", securedMessage);
        return "admin";
    }
{% endprism %}

In the next section of the configuration, we see a setting that is probably worth giving some thought to before you move an application into production:

{% prism http %}
  <security:http use-expressions="true">
        <security:intercept-url pattern="/**" access="permitAll" />
        ...
   </security:http>
{% endprism %}

The demo app takes the unusual approach of allowing access by default, and allowing it for certain roles.  Generally it's considered a best practice for secure applications to deny access by default, then turn it on selectively -- that way when you forget a setting somewhere, you err on the side of more security, not less.


<h2><a id="StandardPasswordEncoder" name="StandardPasswordEncoder">Spring's StandardPasswordEncoder</a></h2>


In the next two sections of spring-config.xml, we set the password encoder our application will use to the StandardPasswordEncoder, then we configure two users with passwords generated by that encoder:

{% prism http %}
...
<security:user-service>
    <!-- Password: "koala" for both -->
    <security:user name="user"
         password="4efe081594ce25ee4efd9f7067f7f678a347bccf2de201f3adf2a3eb544850b465b4e51cdc3fcdde"
         authorities="user"/>
    <security:user name="admin"
         password="4efe081594ce25ee4efd9f7067f7f678a347bccf2de201f3adf2a3eb544850b465b4e51cdc3fcdde"
         authorities="admin"/>
</security:user-service>
...    
{% endprism %}

The StandardPasswordEncoder is a small and quite nicely designed class that bundles everything you want a password encoder to do into a very small interface.  It uses an SHA-256 hasing together with an eight byte random salt to generate a password hash that is unique each time.  Although the starter application does not show this, for additional security you can add an extra secret value for your application, in which case the hash will be based on the password, the random salt, and the secret value.  This is configured using basic constructor injection:

{% prism http %}

<bean id="encoder" class="org.springframework.security.crypto.password.StandardPasswordEncoder">
  <constructor-arg value="x9ajDR$#Qkr91">
</bean>  

{% endprism %}

Getting back to the application configuration, we see that the application is configured with two users, "user" and "admin", both with the password "koala", which is hashed to: 4efe081594ce25ee4efd9f7067f7f678a347bccf2de201f3adf2a3eb544850b465b4e51cdc3fcdde.

To show you a little more about how the StandardPasswordController works, I've added a unit test to the code that IntelliJ Idea provides.  As you can guess from the code, each time the tests are run the output for each encoded string will be different, even though we are encoding the same password over and over again.  Moreover, though we can verify the encoded string against the password, the password is virtually impossible to reverse engineer.

{% prism java %}
import static org.junit.Assert.*;
import org.junit.Test;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

public class StandardPasswordEncoderDemo {
    @Test
    public void testCannotReproduceHashFromConfigFile() {
        StandardPasswordEncoder encoder = new StandardPasswordEncoder();
        String password = "koala";
        String encoded = encoder.encode(password);
        String encodedFromXML = "4efe081594ce25ee4efd9f7067f7f678a347bccf2de201f3adf2a3eb544850b465b4e51cdc3fcdde";

        // What we generated is not what's in the XML
        assertTrue(encodedFromXML != encoded);

        // But both what's in the XML and what we generated match the password.
        assertTrue(encoder.matches(password, encoded));
        assertTrue(encoder.matches(password, encodedFromXML));

        System.out.println(encoded);
   }

    @Test
    public void testAnotherRunWillAlsoYieldDifferentHashes() {
        StandardPasswordEncoder encoder = new StandardPasswordEncoder();
        String password = "koala";
        String encoded = encoder.encode(password);
        String encoded2 = encoder.encode(password);
        assertTrue(encoded2 != encoded);

        System.out.println(encoded);
        System.out.println(encoded2);
    }
}
{% endprism %}



### Using Security in the View Layer - The Spring Security Tag Libraries


If we look in ApplicationController.java, we find the following method mapping:
{% prism java %}
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(ModelMap map) {
        // Additional code...
        return "index";
    }
{% endprism %}

No surprises here, the root mapping is to the traditional index page.  Examining the source in src/webapp/WEB-INF/pages/index.jsp we find a good demonstration of the use of the Spring Security Tag Library.  First, we import the tag library:

{% prism java %}
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
{% endprism %}

We now have access to the [Spring Security Expressions](http://static.springsource.org/spring-security/site/docs/3.0.x/reference/el-access.html#el-access-web) that we saw enabled earlier when we discussed the configuration. For example, we first check to see if the User has logged in or not, and if not, we prompt for a sign-in:

{% prism http %}
    
<sec:authorize access="isAnonymous()">
    <p>
        <a href="/spring_security_login">Sign In</a>
    </p>
</sec:authorize>

{% endprism %}

Using the inverse of the isAnonymousCheck, if the user is authenticated we display a welcome message and a sign-out link:

{% prism http %}
<sec:authorize access="isAuthenticated()">
    <p>Hello, ${userDetails.username}! <a href="/j_spring_security_logout">Sign Out</a></p>
</sec:authorize>

{% endprism %}

Note that for production use, there's a bit of a bug in this code, since there's a the user could be a user who asked to be remembered ("Remember Me"), in which case isAuthenticated would still return true even if the user were not logged in.  To eliminate this possibility, use "isFullyAuthenticated" instead.

Finally, we do a check for the admin role and conditionally display a link if the signed in user has that role assigned.

{% prism http %}

<sec:authorize access="hasRole('admin')">
    <p>
        <a href="/admin">Admin page</a>
    </p>
</sec:authorize>

{% endprism %}

With that, we've seen a basic Spring Security starter project that illustrates how to set up a very basic authentication based on pre-configured user names and passwords.  In Spring Security Part II, we'll dig into how to handle the much more common use case of storing user authentication information in a database.  We'll also dig into customizing the log-in forms.  This will serve as a starting point for a much more full featured authorization system suitable for a business to consumer or business to business web site.  Later on in another tutorial we're planning to tie this database-based authentication system with Spring OAuth, allowing users with (for example) a Google account to sign in using that account.
