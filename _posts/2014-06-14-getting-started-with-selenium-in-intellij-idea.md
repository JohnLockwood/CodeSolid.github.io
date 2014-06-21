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
<li><a href="#LocatingWebElements">Locating Web Elements</a></li>
<li><a href="#VerifyingExpectedResults">Verifying Expected Results</a></li>
<li><a href="#LastSteps">Final Steps and What's Ahead</a></li>
<li><a href="/selenium-and-intellij-idea-part-two/">Read Part Two of the Article</a></li>
</ul>
</div>

<h2><a id="Introduction" name="Introduction">Introduction</a></h2>

Selenium is a one of the most popular test automation tools.  Selenium lets you write automated tests to drive a variety of popular browsers including Firefox, Chrome, and Internet Explorer. Using Selenium IDE, you can record browser sessions and export the recorded tests to test cases written in a variety of languages.  These languages include Java, Python, Ruby, and .NET. 

In this first part of our Selenium and IntelliJ Idea tutorial, we'll cover working with the IDE itself in Firefox.  We'll learn how to record a web session, and
how to locate and test web elements on the page.  In part two of the article, we'll learn how to take our Selenium tests to the next level by turning them into 
JUnit tests running in IntelliJ Idea.

<h2><a id="Setup" name="Setup">Setting up Firebug and Selenium IDE</a></h2>

The Selenium IDE is an extension for Firefox that should serve as your starting point for your Selenium work.  So of course, you'll need Firefox installed.

Before getting Selenium, however, there's also one more tool we'll need if you don't already have it, Firebug.  Among other things, Firebug makes it easy to examine page elements, something which we'll be doing a lot as we dig into Selenium.  [Download Firebug](https://getfirebug.com/) from this site and install it.  Once it's installed, you should see a little bug on the Firefox menu bar, click it to open Firebug.  We'll be using it in a few minutes.

Now we're ready to get the Selenium IDE from the [Selenium Download Page](http://docs.seleniumhq.org/download/).  Selenium IDE will appear as an option on the tools menu.  In recent versions, you can also access it from the right hand menu button as shown below.

!["Selenium Firefox menu button"](/images/20140606/FirefoxMenuButtonSelenium.png)

I generally open Selenium in a sidebar, but opening as a Popup is the more common option that's available in all environments.  Shown below is how that looks.

!["Selenium IDE Pop Up"](/images/20140606/SeleniumScreenShot1.png)

<h2><a id="SimpleSeleniumTestcase" name="SimpleSeleniumTestCase">A Simple Selenium Test Case</a></h2> 

Let's dive right in to Selenium with a simple test case.  Make sure that the red recording button in the upper right is "On" as shown in the image above.  You can toggle it if you're not sure -- the "On" state is when it looks pushed in.  Next, in the Selenium window's address bar, where it says "Base URL", let's enter:  https://github.com/codesolid . One thing to notice at this point is that Selenium will not automatically go to the base URL.  You'll need to navigate it in the firefox address bar as well.  This is because really what we're entering here is the URL of the "Web Application Under Test" -- in other words, we're telling Selenium where to record our activity.

The fun begins when you navigate a page under that base URL.  On the CodeSolid github page, there should be a link to a "tutorials" project.  Let's click on that link in Firefox.

Firefox navigates to the link, but if you'll notice, the Selenium IDE has now recorded your activity.  At this point the Selenium IDE window should look something like this.

!["Selenium Test Recording"](/images/20140606/SeleniumScreenShot2.png)

If you toggle the recording button to the "Off state", you should be able to prove you've recorded your session by playing it back.  First, in the window that's viewing the CodeSolid tutorials, navigate to somewhere on the web that's not as popular as the CodeSolid tutorials, like Google for example.  Now, back in the Selenium IDE window, underneath the Base URL bar, you should see two green play buttons.  Click either of these (at this point it doesn't matter which).  You should see Firefox navigate back to the CodeSolid Github account and click through to the tutorials page.

<h2><a id="LocatingWebElements" name="LocatingWebElements">Locating Web Elements</a></h2>

As interesting as it is, a recorded web session is not yet a test.  It has the first part -- a description in code of the steps that take place -- but we haven't yet added a way to compare some expected value to an actual value.  Let's add a simple check to see if some element on the page contains the text we expect it to.  On the CodeSolid tutorials page, if you scroll down past the links to the tutorials, there's a readme file.

!["CodeSolid Readme"](/images/20140606/CodeSolidReadme.png)

Github displays this in an H1 tag, but to make sure we're finding the right H1 tag, let's open firebug using the firebug button:

!["Firebug"](/images/20140606/FirebugButton.png)

This opens up the firebug window, as you can see below.  

!["Firebug"](/images/20140606/FirebugWindow.png)

I've added an arrow to point out the inspect button.  With the HTML tab selected as shown in the picture, if you click the inspect button, you'll notice if you hover over any element on the page in the browser window, you'll seee the HTML for that element displaying in the HTML window.  You can then select that element by clicking on it.  Let's go ahead and click first on the inspect button, then in the main browser window, click the header, "Welcome to the CodeSolid Tutorials", that we scrolled to earlier.  This selects the html for the page element we want to verify.  There are a variety of ways to do this, but one of the most versatile is to use a CSS selector.  With the element we want still selected, if you right click on the html displayed in the firebug window you should see a menu that looks something like this:

!["Using a CSS Path Selector"](/images/20140606/FirebugPopupWithCSSPath.png)

Click on "Copy CSS Path".  At this point the CSS path for the element is copied to the clipboard.  If you were to paste it into a text editor, youd see something like:

<div>
"html body.logged_out.env-production.windows.vis-public div.wrapper div.site div.container div.repository-with-sidebar.repo-container.new-discussion-timeline.js-new-discussion-timeline.with-full-navigation div#js-repo-pjax-container.repository-content.context-loader-container div#readme.clearfix.announce.instapaper_body.md article.markdown-body.entry-content h1"
</div>

This represents the entire CSS path to the element.  As you work with Selenium, you may find that your tests are less brittle if you only consider what elements you need at the <b>end</b> of the path -- that way other parts of the page can change and your tests will still work.  For example, in this case, using the section "article.markdown-body.entry-content h1" identifies the element just fine.  For now, however, let's just take the whole path and see how to get Selenium to use it.

<h2><a id="VerifyingExpectedResults" name="VerifyingExpectedResults">Verifying Expected Results</a></h2>

With the CSS for our web element copiled to the clipboard, we can now turn our recording of a Firefox session into a real test.  If you look at the Selenium IDE window,
you should see on the right hand side of it two tabs labeled "Table" and "Source". The Table tab shows the Selenium commands that will run if you run the test.  So far all it has are recorded commands -- now we're going to add a command ourselves.  If you right click just underneath the command that says "clickAndWait   link=tutorials", you'll can then click "Insert New Command".

Note that underneath the Table itself there are three entry fields (that are highlighted in the image below):

!["Selenium Table Tab"](/images/20140606/SeleniumTableTab1.png)

These three fields correspond to the three columns of the table, Command, Target, and Value.  You select or type Selenium commands in the first field, so in that field enter "assertText".  In the second field, start by typing "css=", then just after the "css=", copy the CSS path that we copied from Firebug earlier.  This creates a "locator" expression that will get passed to the assertText function.  You can test that you did this step successfully by clicking on the Find button -- if your locator is working properly you should see the "Welcome to the CodeSolid Tutorials" header flash yellow briefly.  Finally, in the third field, simply type "Welcome to the CodeSolid Tutorials"  Note that for all three fields you should type the text without the quotes.

At this point, if you navigate to another page like Google and re-run your test in the Selenium IDE (clicking one the green play arrows), you should see the test navigate to the tutorials page, and you should see a green bar on the assertText command you entered.  The test passed!

<h2><a id="LastSteps" name="LastSteps">Final Steps and What's Ahead</a></h2>

In our next tutorial, we'll get into how to use what we've created as the basis for a JUnit test suite using IntelliJ idea.  But before moving on, as a final step let's save our test.  In the Selenium IDE, select File / Save Test Case to save your file.  Notice anything special about the file format that Selenium used to save the test file?  It's just regular HTML (well, OK, it's XHTML), with the name of the test in the first row and the Selenium "script" in the remaining table rows.  The
base URL is cleverly stuffed away in the head tag.  

A tool for testing HTML that stores files as HTML?  Don't worry -- the universe is not folding back on itself.  Selenium is just cool!

{% prism html %}
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head profile="http://selenium-ide.openqa.org/profiles/test-case">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="selenium.base" href="https://github.com/codesolid" />
<title>InitialState</title>
</head>
<body>
<table cellpadding="1" cellspacing="1" border="1">
<thead>
<tr><td rowspan="1" colspan="3">InitialState</td></tr>
</thead><tbody>
<tr>
	<td>open</td>
	<td>/CodeSolid</td>
	<td></td>
</tr>
<tr>
	<td>clickAndWait</td>
	<td>link=tutorials</td>
	<td></td>
</tr>
<tr>
	<td>assertText</td>
	<td>css=html body.logged_out.env-production.windows.vis-public div.wrapper div.site div.container div.repository-with-sidebar.repo-container.new-discussion-timeline.js-new-discussion-timeline.with-full-navigation div#js-repo-pjax-container.repository-content.context-loader-container div#readme.clearfix.announce.instapaper_body.md article.markdown-body.entry-content h1</td>
	<td>Welcome to the CodeSolid Tutorials</td>
</tr>
</tbody></table>
</body>
</html>

{% endprism %}

<a href="/selenium-and-intellij-idea-part-two/">Read Part Two of the Article</a>