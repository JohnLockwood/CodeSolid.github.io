---
author: admin
comments: false
date: 2006-07-02 01:24:04+00:00
layout: post
slug: when-is-a-test-not-a-test
title: When is a test not a test.
wordpress_id: 169
categories:
- Software
- JUnit
---

When it's a classroom!

OK, I admit it, I'm being obtuse.  As programmers sometimes say to being caught in the act of being obtuse, "Why Do You Think They Call It 'Code'?"

I often write about JUnit tests, and and one could say that I was able to parlay my current contract based on my experiences with test first design, since the client in this case was interested in someone experienced with Xtreme Programming, and test first design and development is one of XPs cornerstone processes.

And indeed, at work it wasn't long before we had an NUnit test suite going -- I've been plugging my way through our new database test suite for a few days now.  The coolest thing (in my opinion) to happen to unit testing in the time since I first used JUnit is automatic test composition.  Back in the JUnit days, you had to create (and -- worse -- you had to maintain) a TestSuite.  In NUnit, there's no such restriction, simply mark the test classes and methods you want with the appropriate [TestFixture] and [Test] attributes, respectively, and you're off and running.  We're using Namespaces to organize our tests in a visually appealing and logical way.

Today when I got Visual Studio installed, it also occured to me that NUnit is an ideal "snippet viewer" sort of an application, the kind of thing that's ideal for organizing and writing code to learn new things and excercise unfamiliar classes -- which of course is just the sort of thing if you're preparing for a certification exam.  So a test (in the software sense) is not a test when it's a classroom to help you prepare for a test (in the academic sense).

It's all good.

So I'm going to work in that way for awhile, developing my sample "apps" (i.e., learning exercises) in NUnit, and perhaps I'll publish them up here for what it's worth.
