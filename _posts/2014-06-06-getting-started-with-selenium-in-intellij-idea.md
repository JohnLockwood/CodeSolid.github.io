---
author: John Lockwood
layout: post
excerpt:  Learn how to set up Selenium IDE and start recording your first Selenium tests in part one of this article series on working with Selenium IDE and IntelliJ Idea.
title: "Getting Started with Selenium in IntelliJ Idea - Part I" 

categories:
- Testing
- Selenium
- IntelliJ Idea
---
<div id="table_of_contents">
<h1>Table of Contents</h1>
<ul >
<li><a href="#Introduction">Introduction</a></li>
<li><a href="#Setup">Fill Your Toolbox -- Setting Up Firebug and Selenium IDE</a></li>
<li><a href="#SimpleSeleniumTestcase">A Simple Selenium Test Case</a></li>
<li><a href="#TestingWebElements">Testing Web Elements</a></li>
</ul>
</div>

<h2><a id="Introduction" name="Introduction">Introduction</a></h2>

Selenium is a one of the most popular test automation tools.  Selenium lets you write automated tests to drive a variety of popular browsers including Firefox, Chrome, and Internet Explorer. Using Selenium IDE, you can record browser sessions and export the recorded tests to test cases written in a variety of languages, including Java, Python, Ruby, and .NET.  These tests can then be easily extended within that language to add assertions to verify that the test case passed.

In this tutorial, we'll walk you through all the basics of using Selenium, from recording your first browser session using Selenium IDE, to setting up and running your JUnit tests in IntelliJ Idea.

<h2><a id="Setup" name="Setup">Setting up Firebug and Selenium IDE</a></h2>

The Selenium IDE is an extension for Firefox that should serve as your starting point for your Selenium work.  So of course, you'll need Firefox installed.

Before getting Selenium, however, there's one more tool we'll need if you don't already have it, Firebug.  Among other things, Firebug makes it easy to examine page elements, something which we'll be doing a lot as we dig into Selenium.  [Download Firebug](https://getfirebug.com/) from this site and install it.  Once it's installed, you should see a little bug on the Firefox menu bar, click it to open Firebug.  We'll be using it in a few minutes.

Now we're ready to get the Selenium IDE from the [Selenium Download Page](http://docs.seleniumhq.org/download/).  Selenium IDE will appear as an option on the tools menu.  In recent versions, you can also access it from the right hand menu button as shown below.

!["Selenium Firefox menu button"](/images/20140606/FirefoxMenuButtonSelenium.png)

I generally open Selenium in a sidebar, but opening as a Popup is the more common option that's available in all environments.  Shown below is how that looks.

!["Selenium IDE Pop Up"](/images/20140606/SeleniumScreenShot1.png)

<h2><a id="SimpleSeleniumTestcase" name="SimpleSeleniumTestCase">A Simple Selenium Test Case</a></h2> 

Let's dive right in to Selenium with a simple test case.  Make sure that the red recording button in the upper right is "On" as shown in the image above.  You can toggle it if you're not sure -- the "On" state is when it looks pushed in.  Next, in the Selenium window's address bar, where it says "Base URL", let's enter:  https://github.com/codesolid . One thing to notice at this point is that Selenium will not automatically go to the base URL.  You'll need to navigate it in the firefox address bar as well.  This is because really what we're entering here is the URL of the "Web Application Under Test" -- in other words, we're telling Selenium where to record our activity.

The fun begins when you navigate a page under that base URL.  On the CodeSolid github page, there should be a link to a "tutorials" project.  Let's click on that link in Firefox.

Firefox navigates to the link, but if you'll notice, the Selenium IDE has now recorded your activity.  At this point the Selenium IDE window should look something like this.

!["Selenium Test Recording"](/images/20140606/SeleniumScreenShot2.png)

If you toggle the recording button to the "Off state", you should be able to prove you've recorded your session by playing it back.  First, in the window that's viewing the CodeSolid tutorials, navigate to somewhere on the web that's not as popular as the CodeSolid tutorials, like Google for example.  Now, underneath the Base URL bar, 
you should see two green play buttons.  Click either of these (at this point it doesn't matter which).  You should see Firefox navigate back to the CodeSolid Github 
account and click through to the tutorials page.

<h2><a id="TestingWebElements" name="TestingWebElements">Testing Web Elements</a></h2>

As interesting as it is, a recorded web session is not yet a test.  It has the first part -- a description in code of the steps that take place -- but we haven't yet added a way to compare some expected value to an actual value.  We'll add a simple check to see if some element on the page contains the text we expect it to.  On the CodeSolid tutorials page, if you scroll down past the links to the tutorials, there's a readme file.

!["CodeSolid Readme"](/images/20140606/CodeSolidReadme.png)

Github displays this in an H1 tag, but to make sure we're finding the right H1 tag, let's open firebug using the firebug button:

!["Firebug"](/images/20140606/FirebugButton.png)

!["Firebug"](/images/20140606/FirebugWindow.png)
