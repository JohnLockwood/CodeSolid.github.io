---
author: admin
comments: true
date: 2013-05-23 01:00:40+00:00
excerpt: In this tutorial we'll set up some simple Hibernate entities in a non-Spring,
  POJO (Plain Old Java) environment.  We'll explore how to configure Hibernate to
  work with MySQL, and how to write unit tests for Hibernate entities.
layout: post
slug: a-simple-hibernate-mysql-and-junit-tutorial
title: A Simple Hibernate, MySQL, and JUnit Tutorial
wordpress_id: 1564
categories:
- Testing, JUnit, TestNG Tutorials
- Tutorials
tags:
- Hibernate
- Java
- Log4J
- MySQL
- Test First Developent
- Testing, JUnit, TestNG Tutorials
---

<div id="table_of_contents">
<h1>Table of Contents</h1>
<ul >
<li><a href="#GettingStarted">Getting Started with Hibernate and MySQL</a></li>
<li><a href="#ConfiguringMYSQL">Configuring MySQL</a></li>
<li><a href="#ConfiguringHibernate">Configuring Hibernate</a></li>
<li><a href="#MinimizingSetupTime">Minimizing Setup Time</a></li>
<li><a href="#SimpleEntity">A Simple Hibernate Entity</a></li>
<li><a href="#GenericEntity">A Hibernate Generic Entity Storage Class</a></li>
<li><a href="#JUnit">The JUnit Test</a></li>

</ul>
</div>

<h2><a id="GettingStarted">Getting Started With Hibernate and MySQL</a></h2>

In this tutorial, we're once again going to use JUnit as a learning tool for exploring various Java technologies -- in this case Hibernate.  We'll be configuring Hibernate to run with MySQL in a way that works both in a standalone application or in a Spring or other web application.  Along the way we'll also be setting up Log4J logging for hibernate.  We'll work on a small generics class that -- although it doesn't really do much more than wrap a few Hibernate functions -- will allow us to demonstrate and test out the basic CRUD operations -- Create, Read, Update, and Delete.

As usual, you can download the complete project files for IntelliJ Idea from our [Tutorials Repository on Github](https://github.com/CodeSolid/tutorials). This tutorial is located in the HibernateGeneric directory.  If you're using Eclipse, check out our [Eclipse instructions](/how-to-run-the-codesolid-tutorials-in-eclipse/).

<h2><a id="ConfiguringMYSQL">Configuring MySQL</a></h2>

This tutorial assumes you have mysql installed and can log in as root.  If you don't have MySQL on your system, you should [start here](http://dev.mysql.com/doc/refman/5.1/en/windows-installation.html) and work through the instructions appropriate to your environment.  Go into the mysql monitor (i.e., the mysql command line client).  To do this, run:
<pre>
&lt;PathToMySQLBin&gt;mysql.exe --user=root --password=YourRootPassword
</pre>
(If you haven't set a mysql root password, you should -- if you haven't, you can simply use the command:
<pre>
&lt;PathToMySQLBin&gt;\mysql.exe --user=root
</pre>
Once you're connected in the mysql client, use the source command to run the dbsetup.sql script located in the database directory of the tutorial, for example:

<pre>source database\dbsetup.sql</pre>

{% prism sql %}
CREATE DATABASE tutorials;
USE tutorials;
GRANT ALL ON tutorials.* to 'myuser'@'localhost' identified by 'mypassword';
commit;
flush privileges;
{% endprism %}

Of course, the user name and password given in this script are pretty brain-dead and certainly not secure so probably want to change them.  If you do, that's fine, you'll just need to make one other change, in the hibernate configuration file.  Let's look at that next:



<h2><a id="ConfiguringHibernate">Configuring Hibernate</a></h2>


The hibernate configuration file is the file hibernate.cfg.xml in the root of the resources directory (i.e., at src/main/resources).  As you can see, we've configured the JDBC driver directly in this file.  If you changed the user name or password in the dbsetup.sql script, you need to make the corresponding chagnes to the "connection.username" and / or "connection.password" properties below.

You might also experiment with setting the show_sql and use_sql_comments to true, especially if you run into problems.  For now they are turned off to keep the test run output a little bit cleaner.


{% prism http %}
  <?xml version='1.0' encoding='utf-8'?>
<!DOCTYPE hibernate-configuration PUBLIC
        "-//Hibernate/Hibernate Configuration DTD 3.0//EN"
        "http://hibernate.sourceforge.net/hibernate-configuration-3.0.dtd">

<hibernate-configuration>
    <session-factory>

        <!-- Database connection settings -->
        <property name="connection.driver_class">com.mysql.jdbc.Driver</property>
        <property name="connection.url">jdbc:mysql://localhost/tutorials</property>
        <property name="connection.username">myuser</property>
        <property name="connection.password">mypassword</property>

        <!-- JDBC connection pool (use the built-in connection pool) -->
        <property name="connection.pool_size">1</property>

        <!--
            SQL dialect
            There are other mysql dialects avaialable IF you run INTO trouble WITH this one.
            The FULL list IS:
                MySQL5Dialect
                MySQL5InnoDBDialect
                MySQLDialect
                MySQLInnoDBDialect
                MySQLMyISAMDialect
            See http://docs.jboss.org/hibernate/orm/3.5/api/org/hibernate/dialect/package-summary.html
        -->
        <property name="dialect">org.hibernate.dialect.MySQL5InnoDBDialect</property>

        <!-- Enable Hibernate's automatic session context management -->
        <property name="current_session_context_class">thread</property>

        <!-- Disable the second-level cache  -->
        <property name="cache.provider_class">org.hibernate.cache.NoCacheProvider</property>

        <!-- Change these values to true to echo all executed SQL to stdout -->
        <property name="show_sql">false</property>
        <property name="use_sql_comments">false</property>

        <!-- Drop and re-create the database schema on startup -->
        <property name="hbm2ddl.auto">update</property>

        <!-- This is the entity class that we'll be testing. -->
        <mapping class="com.codesolid.tutorials.model.entities.Actor"/>

    </session-factory>
</hibernate-configuration>
{% endprism %}

I won't post the log4j configuration here.  Hibernate issues a warning if logging is not enabled, so I have configured Hibernate to use the log file, "log4j.log" in the root of the project directory.  The configuration file for log4j is at src/main/resources/log4j.xml.



<h2><a id="MinimizingSetupTime">Minimizing Setup Time</a></h2>


As with any process that initially connects to a database, initializing Hibernate sessions takes a fair amount of time, and is not something you want to be doing over and over again in your tests.  In the case of Hibernate, creating the SessionFactory object takes even more time, adding up to about 1.4 seconds on a laptop, or about .8 seconds on a faster machine.  We don't want to be running that for every test class.  (To be sure, in the kind of one-class suite we're running in this tutorial, it wouldn't make a difference, but one of the purposes of these tutorials is to give you steal-able code that you can use in larger projects.  What we want is to initialize the SessionFactory only once.

The [Hibernate tutorial](http://docs.jboss.org/hibernate/orm/3.3/reference/en-US/html/tutorial.html) that comes with the Hibernate docs gives us a part of the solution, the HibernateUtil class, which exists for the sole purpose of ensuring a one time initialization of a SessionFactory, which it then caches as a static object.  I've used this class pretty much verbatim in the current tutorial, changing only the package name.

{% prism java %}
// This file is adapted with only minor changes from the original in the hibernate
// tutorial, http://docs.jboss.org/hibernate/orm/3.3/reference/en-US/html/tutorial.html.
package com.codesolid.tutorials.model.dal;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            return new Configuration().configure().buildSessionFactory();
        }
        catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}
{% endprism %}

OK, so we have a class where we can cache our SessionFactory, but the question remains -- how should we wire that up to our JUnit tests?  Since JUnit 4.9, we can use the @ClassRule annotation to set up a test suite with before and after methods that will run before and after all the classes in the suite have run.  This allows for the initialization of expensive resoures once before all the tests in the suite are run.

The following listing shows the RuleSuite test suite, which builds with the rest of the tests in the src\test\java\code\codesolid\tests directory:

{% prism java %}

package com.codesolid.tests;

import com.codesolid.tutorials.model.dal.HibernateUtil;
import org.apache.log4j.Level;
import org.apache.log4j.xml.Log4jEntityResolver;
import org.junit.ClassRule;
import org.junit.rules.ExternalResource;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.apache.log4j.Logger;

@RunWith( Suite.class )
@Suite.SuiteClasses( {
        TestStorage.class
        /* , Add more test classes here separated by commas*/
} )
public class RuleSuite{

    static HibernateUtil util;

    // This is a static field.  Per the ClassRule documentation,
    // to use a ClassRule we need a field that is "public, static,
    // and a subtype of of TestRule."
    // See http://junit.czweb.org/apidocs/org/junit/ClassRule.html
    @ClassRule
    public static ExternalResource testRule = new ExternalResource(){
        @Override
        protected void before() throws Throwable{
            Logger.getLogger("com.codesolid.tests").log(Level.DEBUG, "Inside RuleSuite::ExternalResource::before.");
            util = new HibernateUtil();
        };

        @Override
        protected void after(){
            // Nothing to do here in this case.
            Logger.getLogger("com.codesolid.tests").log(Level.DEBUG, "Inside RuleSuite::ExternalResource::after.");
        };
    };
}
{% endprism %}

I've added some logging methods so you can see that the before and after methods are run only once at the start and end of the suite.


<h2><a id="SimpleEntity">A Simple Hibernate Entity</a></h2>

In order to have an entity to save, I created an "Actor" entity.  This represents a user in the system, but I've named the class "Actor" to avoid any potential conflict with the SQL keyword by the same name.  I am using Hibernate annotations to define the entity.

@Table allows us to name the table.
@Entity marks the class as representing a Hibernate Entity, that is to say, an object that Hibernate can save to and load from the database.

For the Id primary key field, we use the following Hibernate annotations to mark the field as the primary key and to auto-increment the field for each new record:

{% prism java %}
@Id
@GeneratedValue(generator="increment")
@GenericGenerator(name="increment", strategy = "increment")
{% endprism %}
Finally, we used the column annotation to explicitly set the field name for the Role property to "user_role":

@Column(name="user_role")

{% prism java %}

/**
 * Class Actor
 * Description:  A user of the system, the subject of a user story.  We use
 *               actor instead of user as table name to avoid any conflict
 *               with the database concept of user
 */
package com.codesolid.tutorials.model.entities;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;

@Table( name = "ACTORS" )
@Entity
public class Actor {
    public static final String DEFAULT_ROLE = "Standard User";

    public Actor() {
        setRole(Actor.DEFAULT_ROLE);
    }

    private Long id;
    private String role;

    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Column(name="user_role")
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
{% endprism %}

The final step to making the Entity work is one that's easy to forget, but as we saw when we discussed the hibernate.cfg.xml file, make sure you include a mapping to the entity class, as we show again below:

{% prism http %}
<!-- This is the entity class that we'll be testing. -->
<mapping class="com.codesolid.tutorials.model.entities.Actor"/>
{% endprism %}

<h2><a id="GenericEntity">A Hibernate Generic Entity Storage Class</a></h2>


While working with Hibernate enitities,  it struck me that many of the basic operations one might do with an entity could be captured well in a generic class, which is what we'll be using to test here. As you can see below, each of the methods was pretty much a wrapper around a single Hibernate session call -- with the exception of BeginTransaction, which combines getting the session from the session factory and starting the transaction.  

{% prism java %}
package com.codesolid.tutorials.model.dal;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Storage  {

    public static SessionFactory getSessionFactory() {
        return HibernateUtil.getSessionFactory();
    }

    private T entity;
    private Session session;

    public void beginTransaction() {
        session = Storage.getSessionFactory().getCurrentSession();
        session.beginTransaction();
    }

    public void commit () {
        session.getTransaction().commit();
    }

    public Storage(T entity) {
        this.entity = entity;
    }

    public void update(T entity) {
        session.update(entity);
    }

    public Long insert(T entity) {
        return (Long) session.save(entity);
    }

    public void delete(T entity) {
        session.delete(entity);
    }

    // This call will issue a warning about the unchecked cast,
    // but we know the value returned will be of the right type because
    // we specify the entity (T) class in the call.
    //
    // Note that "get" will return a null if no value with this id fails
    @SuppressWarnings (value="unchecked")
    public T getById(Long id) {
        return (T) session.get(entity.getClass(), id);        
    }
}
{% endprism %}

Taken together, this class and the unit tests I wrote for it were a good way to discover a couple of interesting Hibernate gotchas.  The first Hibernate mine that I steped on was that objects are no longer usable outside the context of a transaction.  Before my testing uncovered this, my original implementation of getById was called loadById, and the implementation and test looked something like this:

{% prism java %}
   // Implementation  
    public T getById(Long id) {
        storage.beginTransaction();
        T obj = (T) session.load(entity.getClass(), id);
        session.getTransaction().commit();
        return obj;
    }

   // Test code:
   // ...
   Actor actor  = storage.getById(id);
   assertEquals(actor.getRole(), "Some Role");
{% endprism %}

This seemed like a safe approach (albeit not an efficient one since each call is wrapped in a transaction).  As it turns out, because Hibernate uses lazy initialization, the object returned from getById needed to still have the transaction active.  As a result, I got the following exception:

org.hibernate.LazyInitializationException: could not initialize proxy - no Session

This caused me to push the transaction management out to the client, so I exposed beginTransaction and commit methods on my storage class.

At that point, the code and test looked like:

{% prism java %}
// Implmentation
public T getById(Long id) {
    return (T) session.load(entity.getClass(), id);
}

// Test code
storage.beginTransaction();
Actor actor  = storage.getById(id);
assertEquals(actor.getRole(), "Some Role");
storage.commit();
{% endprism %}

The second interesting Hibernate quirk that I ran into was in trying to test my delete method.  Originally I wrote the test by deleting an object and then checking for the ObjectNotFoundException when I called loadById in an effort to reload the deleted object.  The first time I ran it, that test passed, but a few minutes later it failed.  It turns out that loadById may or may not return an ObjectNotFoundException, or it may instead return a "proxy object", though I admit I have a bit of a hard time following the architectural genius behind the decision to return a proxy to an object that doesn't exist yet -- or in this case, doesn't exist any more.  At that time I researched it and found that getById has the more reasonable behavior of reliably returning a for objects that don't exist, so the test and the code evolved accordingly.



<h2><a id="JUnit">The JUnit Test</a></h2>


With that, the final form of the JUnit test is below.  Even though I'm showing it last, in reality the process was more test-first, going back and forth between the tests, the configuration, and the classes under development.

{% prism java %}

package com.codesolid.tests;

import com.codesolid.tutorials.model.entities.*;
import com.codesolid.tutorials.model.dal.Storage;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestStorage {

    @Test
    public void testWrite() {
        // Just a write, verify id set
        Actor user = new Actor();
        Storage storage = new Storage(user);
        storage.beginTransaction();
        user.setRole("SuperUser");
        assertNull(user.getId());
        storage.insert(user);
        assertNotNull(user.getId());
        storage.commit();
    }

    @Test
    public void testWriteAndRead() {

        // This time write and read back
        String overRideRole = "SuperUser";

        // Write
        Actor actor = new Actor();
        Storage storage = new Storage(actor);
        storage.beginTransaction();
        actor.setRole(overRideRole);
        assertNull(actor.getId());
        storage.insert(actor);
        assertNotNull(actor.getId());
        Long id = actor.getId();
        storage.commit();

        // Read and verify
        Actor actor2  = new Actor();
        assertEquals(actor2.getRole(), Actor.DEFAULT_ROLE);
        storage = new Storage(actor2);
        storage.beginTransaction();
        actor2 = storage.getById(id);
        assertEquals(actor2.getRole(), overRideRole);
        storage.commit();
    }


    @Test
    public void testUpdate() {

        String overRideRole = "SuperUser";
        String newOverrideRole = "GUEST";

        // Insert an actor
        Actor actor = new Actor();
        Storage storage = new Storage(actor);
        storage.beginTransaction();
        actor.setRole(overRideRole);
        assertNull(actor.getId());
        storage.insert(actor);
        assertNotNull(actor.getId());
        Long id = actor.getId();
        storage.commit();

        // Read it back
        Actor actor2  = new Actor();
        assertEquals(actor2.getRole(), Actor.DEFAULT_ROLE);
        storage.beginTransaction();
        actor2 = storage.getById(id);
        assertEquals(actor2.getRole(), overRideRole);

        // Update it
        actor2.setRole(newOverrideRole);
        storage.update(actor2);
        storage.commit();

        // Read it again and verify update
        storage.beginTransaction();
        Actor actor3  = storage.getById(id);
        assertEquals(actor3.getRole(), newOverrideRole);
        storage.commit();
    }

    @Test
    public void testDelete() {

        Actor actor = new Actor();
        Storage storage = new Storage(actor);

        // Write
        storage.beginTransaction();
        storage.insert(actor);
        Long id = actor.getId();
        storage.commit();

        // Delete it now
        assert(actor.getId() > 0);
        storage.beginTransaction();
        storage.delete(actor);
        storage.commit();

        // Now we can't read it back, as expected
        storage.beginTransaction();
        Actor actor2 = storage.getById(id);
        assertNull(actor2);
        storage.commit();
    }
}
{% endprism %}