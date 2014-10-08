---
author: John Lockwood
layout: post
title: "Java REST tutorial" 
categories:
- Java, Jersey, REST
excerpt: Tutorial shows you how to easily create a RESTful API using Java, Jersey, Maven, and IntelliJ Idea.
description:  Tutorial shows you how to easily create a RESTful API using Java, Jersey, Maven, and IntelliJ Idea.
---
<p>Create a RESTful API Using Java and Jersey in IntelliJ Idea.</p>
<div>
<h1>Table of Contents</h1>
<ul class="list-group">
<li class="list-group-item"><a href="#RESTinFiveMinutes">Take Five - REST in Five Minutes</a></li>
<li class="list-group-item"><a href="#DumbNoSQL">Keeping It Simple - No Database Connection Needed</a>
<li class="list-group-item"><a href="#ProjectSetup">Setting up the Jersey Project in Maven and IntelliJ Idea</a></li>
<li class="list-group-item"><a href="#TheRestfulAPI">Server Side:  the RESTful API</a></li>
<li class="list-group-item"><a href="#ClientSideTesting">Testing the Server:  A Jersey REST client</a></li>
<li class="list-group-item"><a href="#Conclusion">Putting it All Together</a></li>


</li>
</ul>
<a href="/downloads/RestfulWebService.zip">Download the Project (ZIP)</a>
</div>
<div style="margin-top:1em;margin-bottom:-.5em">
<iframe src="http://ghbtns.com/github-btn.html?user=codesolid&repo=tutorials&type=fork&size=large"" allowtransparency="true" frameborder="0" scrolling="0" width="83" height="40" size="large"></iframe>
<iframe src="http://ghbtns.com/github-btn.html?user=codesolid&repo=tutorials&type=follow&size=large"" allowtransparency="true" frameborder="0" scrolling="0" width="200" height="40" size="large"></iframe>
<iframe src="http://ghbtns.com/github-btn.html?user=codesolid&repo=tutorials&type=watch&size=large" allowtransparency="true" frameborder="0" scrolling="0" width="110" height="40""></iframe>
</div>
<h2><a name="RESTinFiveMinutes" id="RESTinFiveMinutes">Take Five -- REST in Five Minutes</a></h2>

Let's face it -- now that web Services written in crufty old XML are yesterday's news, we all want to be hanging out with the cool kids writing RESTful APIs that use JSON.  Best of all, because Java's [JAX-RS specification](https://jax-rs-spec.java.net/) makes RESTful APIs so easy to create and test, hanging out with the cool kids who are writing RESTful APIs turns out to be a good deal easier than hanging out with the cool kids ever was in high school.  

This tutorial is an easy and 100%-wedgie-free way to create a RESTful API using [Jersey](https://jersey.java.net/), the reference implementation for the JAX-RS specification.  

REST is an architectural style which favors APIs that exposes resources through standard HTTP verbs.  REST APIs are stateless, i.e., every request should carry sufficient information to execute the request without resorting to a session on the server side, including authentication information. (Note that in the spirit of a "Hello world" tutorial, we won't get into RESTful authentication in this article.  We may take this up in a later article, but for now we refer you to the [Jersey Security](https://jersey.java.net/documentation/latest/security.html) page.)

Though not strictly speaking a requirement, combinining JSON with REST is a popular choice since it makes RESTful APIs Javascript and mobile-app friendly.

A resource can be anything, a person, a todo list item, a contact in an address book.  The simplicity of REST is that resources can be read or manipulated using only four HTTP verbs.  RESTful clients submit the following verbs:

* ___POST___ -- to create a new resource.
* ___GET___ -- to read (return) a single resource or a list of resources.
* ___PUT___ -- to update an existing resource.
* ___DELETE___ -- to delete a resource.

<h2><a name="DumbNoSQL" id="DumbNoSQL">Keeping It Simple - No Database Connection Needed</a></h2>

Let's start with some example resource, a task.  A task has an id, a description, and the email address of the person to whom it is assigned.

{% prism java %}
package com.codesolid.tutorials.restfulwebservice;

public class Task {
    private String  description;
    private String  assigned;
    private Integer id;

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getAssigned() {
        return assigned;
    }
    public void setAssigned(String assigned) {
        this.assigned = assigned;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
}
{% endprism %}

Our API will allow us to perform CRUD (Create, Read, Update, and Delete) operations using our four HTTP verbs, but to keep things simple our entire "model layer" consists of the world's dumbest in-memory, NoSQL database -- a simple map of keys to values.  We even start out with a test object pre-populated, so we're not standing on ceremony here.  The important point is to create a class that can act like a database class might act -- returning a list of all the objects, returning a single object by a primary key, etc.

{% prism java %}

package com.codesolid.tutorials.restfulwebservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TaskModel {
    HashMap<Integer, Task> taskMap = new HashMap<Integer, Task>();

    public TaskModel() {
        // Make sure we can always see at least one object by setting up a test
        // object
        createTestObject();
    }

    private synchronized void createTestObject(){
        Task td = new Task();
        td.setAssigned("unluckyintern@example.com");
        td.setDescription("Get coffee for developers");
        td.setId(1);
        insert(td);
    }

    // Store a new task.
    public synchronized Integer insert(Task task) {
        Integer id = taskMap.size() + 1;

        task.setId(id);
        taskMap.put(id, task);
        return id;
    }

    // Delete a task
    public synchronized void delete(Integer taskId) {
        if (taskMap.containsKey(taskId)) {
            taskMap.remove(taskId);
        }
    }

    // Update an existing task
    public synchronized void update(Task task) {
        taskMap.put(task.getId(), task);
    }

    // Get a single task
    public synchronized Task get(int taskId) {
        return taskMap.get(taskId);
    }

    // Get a list of all the tasks in the map
    public synchronized List<Task> getAll() {
        List<Task> tasks = new ArrayList<Task>();
        for(Task t : taskMap.values()) {
            tasks.add(t);
        }
        return tasks;
    }
}
{% endprism %}


<h2><a id="ProjectSetup" name="ProjectSetup">Setting up the Jersey Project in Maven and IntelliJ Idea</a></h2>

We'll be using Maven and IntelliJ idea to build and run our project.  Here's the Maven pom.xml file:

{% prism http %}
<project xmlns="http://maven.apache.org/POM/4.0.0" 
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 
	http://maven.apache.org/maven-v4_0_0.xsd">

    <modelVersion>4.0.0</modelVersion>

    <groupId>com.codesolid.tutorials</groupId>
    <artifactId>RestfulWebService</artifactId>
    <packaging>war</packaging>
    <version>1.0-SNAPSHOT</version>
    <name>RestfulWebService</name>

    <properties>
        <jersey.version>2.4.1</jersey.version>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>

    <build>
        <finalName>RestfulWebService</finalName>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>2.5.1</version>
                <inherited>true</inherited>
                <configuration>
                    <source>1.6</source>
                    <target>1.6</target>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>2.16</version>
                <configuration>
                    <excludes>
                        <exclude>**/TaskServiceTest.java</exclude>
                    </excludes>
                </configuration>
            </plugin>
        </plugins>
    </build>

    <dependencies>
        <dependency>
            <groupId>org.glassfish.jersey.core</groupId>
            <artifactId>jersey-client</artifactId>
            <version>${jersey.version}</version>
        </dependency>
        <dependency>
            <groupId>org.glassfish.jersey.core</groupId>
            <artifactId>jersey-client</artifactId>
            <version>${jersey.version}</version>
        </dependency>
        <dependency>
            <groupId>org.glassfish.jersey.containers</groupId>
            <artifactId>jersey-container-servlet</artifactId>
            <version>${jersey.version}</version>
        </dependency>
        <dependency>
            <groupId>org.glassfish.jersey.media</groupId>
            <artifactId>jersey-media-moxy</artifactId>
            <version>${jersey.version}</version>
        </dependency>

        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-core</artifactId>
            <version>2.3.0</version>
        </dependency>
        <dependency>
            <groupId>org.codehaus.jackson</groupId>
            <artifactId>jackson-mapper-asl</artifactId>
            <version>1.9.13</version>
        </dependency>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.8.1</version>
            <scope>test</scope>
        </dependency>
    </dependencies>
</project>

{% endprism %}

As you can see we've taken the strange step of excluding our only unit test from the Maven build.  The reason for this is that our "unit test" is really more of an integraion test because it is a RESTful client that relies on the Tomcat server running the application.

The procedure for importing the POM file into an Idea project is documented [here](http://codesolid.com/a-simple-junit-tutorial-using-maven-and-intellij-idea/#ImportingTheProject), but since you'll also be needing configuration to run the web application in Tomcat and to run the client side test, the easiest thing to do at this point is [download the project](/downloads/RestfulWebService.zip) and open it in IntelliJ Idea.

<h2><a name="TheRestfulAPI" id="TheRestfulAPI">The Restful API</a></h2>

To make the Jersey magic happen, we set up a base URL on our web server "/webapi/*".  Any requests underneath that base pattern will be handled by Jersey's ServletContainer class, which will know how to handle the RESTful API we'll develop.  To do this, we the web.xml shown below into src/main/webapp/WEB-INF:

{% prism http %}
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xmlns="http://java.sun.com/xml/ns/javaee" xmlns:web="http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd"
         xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd"
         id="WebApp_ID" version="2.5">

    <servlet>
        <servlet-name>Jersey Web Application</servlet-name>
        <servlet-class>org.glassfish.jersey.servlet.ServletContainer</servlet-class>
        <init-param>
            <param-name>jersey.config.server.provider.packages</param-name>
            <param-value>com.codesolid.tutorials.restfulwebservice</param-value>
        </init-param>
        <load-on-startup>1</load-on-startup>
    </servlet>

    <servlet-mapping>
        <servlet-name>Jersey Web Application</servlet-name>
        <url-pattern>/webapi/*</url-pattern>
    </servlet-mapping>
</web-app>

{% endprism %}

The Jersey ServletContainer works in conjunction with annotations 
we'll import from the package, javax.ws.rs.  It will scan the package we've specified, com.codesolid.tutorials.restfulwebservice, for Jersey provider files.For example, our API is defined in the file TaskRestfulService.java, shown below:

{% prism java %}
package com.codesolid.tutorials.restfulwebservice;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;

import java.net.URI;
import java.util.List;

import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

/**
 * This is a Jersey resource.  The RESOURCE_URL we define here, "tasks",
 * is added on to our base Jersey url_pattern of "/webapi/" (see web.xml).
 *
 * So the starting point for all URLs for this resource will look something
 * like http://localhost:8080/webapi/tasks on a test server, for example,
 * or http://localhost/webapi/tasks in production.
 */
@Path(TaskRestfulService.RESOURCE_URL)
public class TaskRestfulService {

    public static final String RESOURCE_URL = "tasks";

    static TaskModel model = new TaskModel();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Task> getTasks() {
        List<Task> tasks = model.getAll();
        return tasks;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Task getTask(@PathParam("id") Integer id) {
        Task task = model.get(id);
        return task;
    }

    /* Handle a POST request, i.e., a request to create a new Task object.
    */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response doPost(Task task, @Context UriInfo uriInfo) {
        return saveNewTask(task, uriInfo);
    }

    // Used by POST and PUT when entity does not yet exist
    private Response saveNewTask(Task task, UriInfo uriInfo) {
        Integer id = model.insert(task);
        URI createdURI = URI.create(uriInfo.getBaseUri().toString() + RESOURCE_URL + "/" + id);
        task.setId(id);
        return Response
                .status(Status.CREATED)
                .contentLocation(createdURI)
                .entity(task)
                .build();
    }

    /* A more verbose version of the same POST handler.  Don't write your code
       this way, it's provided for illustration purposes so you can see what's going
       on behind the scenes.

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response doPost(String json, @Context UriInfo uriInfo) {

        ObjectMapper mapper = new ObjectMapper();
        Task task = null;
        try {
            task = mapper.readValue(json, Task.class);
        }
        catch (Exception e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }
        return saveNewTask(task, uriInfo);
    }
    */

    /* PUT to overwrites (updates) a pre-existing task.  Optionally,
       it can store a new task as well.
    */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response doPut(@PathParam("id") Integer id, Task task, @Context UriInfo uriInfo) {

        if (model.isTaskSaved(id)) {
            // In case of a disagreement, the pathParam overrides
            // any id that may be set in the POST body
            if (id > 0) {
                task.setId(id);
            }
            return updateExistingTask(task);
        }
        else {
            return saveNewTask(task, uriInfo);
        }
    }

    private Response updateExistingTask(Task task) {
        model.update(task);
        return Response
                .status(200)
                .entity(task)
                .build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteTask(@PathParam("id") Integer id) {
        model.delete(id);
        return Response.accepted().build();
    }
}
{% endprism %}

The @Path("tasks") annotation marks the class as a resource that Jersey's ServletContainer will serve for us.  Because we earlier specified /webapi as our root all our resources, the path string here, "tasks" this will get tacked on to the end of that, so the relative URLs for our task resource will look like with "/webapi/tasks".  

Inside the class we declare a model object for our "database", and we see our first RESTful method (below).  There's no additional path annotation here, just an annotation @GET, so the result is that a simple GET on /webapi/tasks/ returns a list of all the tasks object we have in memory.  

Because our file imports org.codehaus.jackson.map.ObjectMapper, and because we've used the annotation "@Produces(MediaType.APPLICATION_JSON)", even though we're returning a list of Task objects from the method, Jersey's going to be kind enough to use Jackson to [automagically convert](https://jersey.java.net/documentation/latest/media.html) your objects to JSON strings.

Don't you love it when things just go your way?

{% prism java %}
@GET
@Produces(MediaType.APPLICATION_JSON)
public List<Task> getTasks() {
    List<Task> tasks = model.getAll();
    return tasks;
}
{% endprism %}

Returning a single object from our API via a GET request is also straightforward because it's almost identical to our first method.  All we need to add is a way to specify an id for the object we want.   We use @Path("{id}") to specify a path variable parameter, so our GET URL becomes /webapi/tasks/{id}, where {id} is some primary key for the object.  We then use the expression "@PathParam("id") Integer id" to have Jersey read the id into an Integer variable we can pass to the model.

{% prism java %}
@GET
@Produces(MediaType.APPLICATION_JSON)
@Path("{id}")
public Task getTask(@PathParam("id") Integer id) {
    Task task = model.get(id);
    return task;
}
{% endprism %}

Once again, conversion to JSON will be automatic in this case.

In our handler to POST a new Task object, to give you an idea of what's going on behind the scenes, we make our code considerably more verbose by handling the JSON conversion to a Java object ourselves.  

{% prism java %}

/*
   Handle a POST request, i.e., a request to create a new Task object.
   We're going to Unpack our JSON String manually for illustration purposes.
   A more concise way to do this will be shown in the PUT handler, below
 */
@POST
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public Response doPost(String json, @Context UriInfo uriInfo) {
    ObjectMapper mapper = new ObjectMapper();
    Task task = null;
    try {
        task = mapper.readValue(json, Task.class);
    }
    catch (Exception e) {
        return Response.status(Status.INTERNAL_SERVER_ERROR).build();
    }
    return saveNewDTO(task, uriInfo);
}

// Used by POST and PUT when entity does not yet exist
private Response saveNewDTO(Task task, UriInfo uriInfo) {
    Integer id = model.insert(task);
    URI createdURI = URI.create(uriInfo.getBaseUri().toString() + RESOURCE_URL + "/" + id);
    task.setId(id);
    return Response
            .status(Status.CREATED)
            .contentLocation(createdURI)
            .entity(task)
            .build();
}

{% endprism %}

Our verbose POST method is equivalent to the much more eye-friendly:

{% prism java %}
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response doPost(Task task, @Context UriInfo uriInfo) {
        return saveNewTask(task, uriInfo);
    }
{% endprism %}

Strictly speaking, PUT is used to update a new task.  We read the id
from the URL and ensure that that's the id of the task we're updating.
We don't return the new task. According to the [HTTP Protocol Standard](http://www.w3.org/Protocols/rfc2616/rfc2616.html), however, PUT can also be used
to store a new resource.

{% prism java %}
	/* PUT to overwrites (updates) a pre-existing task.  Optionally,
       it can store a new task as well. */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response doPut(@PathParam("id") Integer id, Task task, @Context UriInfo uriInfo) {

        if (model.isTaskSaved(id)) {
            // In case of a disagreement, the pathParam overrides
            // any id that may be set in the POST body
            if (id > 0) {
                task.setId(id);
            }
            return updateExistingTask(task);
        }
        else {
            return saveNewTask(task, uriInfo);
        }
    }

    private Response updateExistingTask(Task task) {
        model.update(task);
        return Response
                .status(200)
                .entity(task)
                .build();
    }

{% endprism %}

And of course, we need a way to bid our objects a fond (or not) farewell.

{% prism java %}

@DELETE
@Path("{id}")
public Response deleteTask(@PathParam("id") Integer id) {
    model.delete(id);
    return Response.accepted().build();
}

{% endprism %}

<h2><a name="ClientSideTesting" id="ClientSideTesting">Testing the Server:  A Jersey REST client</a></h2>

In addition to being a fine API for easily setting up a RESTful API on the Server side, Jersey provides excellent services that you can use to create a RESTful client.  In the next listing, we show how to construct an integration
test to exercise our client.  Each of our methods tests a specific part of the API, with the HTTP verb in caps, for example "canPOSTaNewTask" or "canGETaTaskList".  We use the Jackson ObjectMapper to convert our entities 
to and from the JSON our REST API consumes.  

{% prism java %}
package com.codesolid.tutorials.restfulwebservice;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.junit.Assert.*;

/** 
 * Note that this is an Integration Test.  It relies on having the Restful Web Service running
 * in Tomcat or another container.
 * @see TaskRestfulService  
 */
public class TaskServiceTest {

    public static Client client = null;

    @BeforeClass
    public static void Before() {
        TaskServiceTest.client = ClientBuilder.newClient();
    }

    @AfterClass
    public static void After() {
        client.close();
    }

    @Test
    public void canPOSTaNewTask() throws Exception {

        WebTarget target = client.target("http://localhost:8080/webapi").path("tasks");
        Task task = new Task();
        task.setAssigned("john@particlewave.com");
        task.setDescription("Fix post test");

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(task);
        Response rs = target.request().post(Entity.entity(json, MediaType.APPLICATION_JSON));
        Task taskResponse = rs.readEntity(Task.class);
        assertNotNull(taskResponse);
        assertNotNull(taskResponse.getId());
        assertEquals(taskResponse.getAssigned(), task.getAssigned());
    }

    // In order not to rely on the fact that our toy model
    // creates a test object for us, we create one here and then update it.
    @Test
    public void canPUTanUpdatedTask() throws Exception {

        String original = "Update me -- don't leave me in this awful, just-created state!";
        String updated = "Freshly updated and open for business.  Thank you so much!";

        // Create the original object
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080/webapi").path("tasks");
        Task task = new Task();
        task.setAssigned("bogus@example.com");
        task.setDescription(original);
        Response rs = target.request().post(Entity.entity(task, MediaType.APPLICATION_JSON));
        Task taskResponse = rs.readEntity(Task.class);
        assertNotNull(taskResponse);

        // Now update it, and PUT the updated version
        taskResponse.setDescription(updated);
        target = client.target("http://localhost:8080/webapi").path("tasks/" + taskResponse.getId());
        rs = target.request().put(Entity.entity(taskResponse, MediaType.APPLICATION_JSON));
        Task taskResponseUpdated = rs.readEntity(Task.class);

        // And test...
        assertNotNull(taskResponseUpdated);
        assertEquals(taskResponseUpdated.getDescription(), updated);
    }

    @Test
    public void canGETaTaskList() throws Exception {

        WebTarget target = client.target("http://localhost:8080/webapi");
        WebTarget resource = target.path("/tasks");
        Invocation.Builder invocationBuilder =
                resource.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.get();
        String tasks = response.readEntity(String.class);
        assertNotNull(tasks);
        ObjectMapper mapper = new ObjectMapper();
        Task[] taskArray = mapper.readValue(tasks, Task[].class);
        assertNotNull(taskArray);
        assertTrue(taskArray.length >= 1);
    }

    @Test
    public void canGETaSingleTask() {

        WebTarget target = client.target("http://localhost:8080/webapi");
        WebTarget resource = target.path("/tasks/1");
        Invocation.Builder invocationBuilder =
                resource.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.get();
        Task task = response.readEntity(Task.class);
        assertNotNull(task);
        assertNotNull(task.getId());
        assertTrue(task.getId() >= 1);
    }

    @Test
    public void canDELETEaTask() throws Exception {

        // Create a task to have one we can safely delete
        WebTarget target = client.target("http://localhost:8080/webapi").path("tasks");
        Task task = new Task();
        task.setAssigned("example@test.com");
        task.setDescription("I can't go on like this.  Please delete me.");
        Response rs = target.request().post(Entity.entity(task, MediaType.APPLICATION_JSON));
        Task taskResponse = rs.readEntity(Task.class);
        assertNotNull(taskResponse);
        assertNotNull(taskResponse.getId());
        assertTrue(taskResponse.getId() >= 1);

        // Set up a URL based on our returned object
        target = client.target("http://localhost:8080/webapi").path("tasks/" + taskResponse.getId());

        // Test that it's saved
        taskResponse = null;
        rs = target.request().get();
        taskResponse = rs.readEntity(Task.class);
        assertNotNull(taskResponse);

        // Goodbye!
        target.request().delete();

        // Test that it's really gone.
        taskResponse = null;
        rs = target.request().get();
        taskResponse = rs.readEntity(Task.class);
        assertNull(taskResponse);
    }
}

{% endprism %}

<h2><a name="Conclusion" id="Conclusion">Putting it All Together</a></h2>

We've bundled it all up for you in a <a href="/downloads/RestfulWebService.zip">ZIP file download</a> that has everything you need to get started.  (If you prefer, you can [browse the code](https://github.com/CodeSolid/tutorials/tree/master/RestfulWebService) on Github.)  

In the IntelliJ project, you'll need to re-point your Tomcat instance to a Tomcat instance running on your local machine (Run / Edit Configurations / Tomcat Server / Tomcat7 then press the "Configure button" to set up your Tomcat instance).  In the Run dropdown, select Tomcat first and run that, and once
Tomcat runs up your integration tests should all be green.  

If you enjoyed this tutorial, we'll happily accept Github love via the links below. (Shameless, I know, but thanks!)

<div style="margin-top:1em;margin-bottom:-.5em">
<iframe src="http://ghbtns.com/github-btn.html?user=codesolid&repo=tutorials&type=fork&size=large"" allowtransparency="true" frameborder="0" scrolling="0" width="83" height="40" size="large"></iframe>
<iframe src="http://ghbtns.com/github-btn.html?user=codesolid&repo=tutorials&type=follow&size=large"" allowtransparency="true" frameborder="0" scrolling="0" width="200" height="40" size="large"></iframe>
<iframe src="http://ghbtns.com/github-btn.html?user=codesolid&repo=tutorials&type=watch&size=large" allowtransparency="true" frameborder="0" scrolling="0" width="110" height="40""></iframe>
</div>
