---
author: admin
comments: true
date: 2009-09-21 13:54:00+00:00
layout: post
slug: whats-so-funny-about-asp-net-master-pages-love-and-understanding
title: What's So Funny About the UpdatePanel Control, Love and Understanding?
wordpress_id: 522
categories:
- Miscellaneous
tags:
- ASP.NET
- ASP.NET Deployment
- ASP.NET MVC
---

[I recently reported](http://www.particlewave.com/blog/2009/09/18/certification-and-its-discontents/) that I had a domain for an ASP.NET site to hack around on with nothing on it yet. Since then I’ve made some progress and had some fun coding some ASP.NET, so I can now show off a minor feature on a new web site, the [Featured ASP.NET Certification Question](http://www.aspworkbench.com/blog/).

 

This isn’t exactly Google or even StackOverflow yet, to be sure, but there were nevertheless a fair bit of learning and practice goals along the way to this simple looking page.

 

**The Site and The Control**

 

First, I ported the master page from this site, so that was worth remembering how to do. Master pages can also expose properties to their content pages, which I haven’t done yet, but perhaps I’ll get into that when I add authentication to the site.

 

The next steps were to set up a brain-dead and stubbed out custom control, ExamQuestionControl, while on the lower decks working out the database structure and connection strings for development and production and testing along the way using NUnit tests ([Yes, Dan](http://www.particlewave.com/blog/2009/07/23/development-driven-development/), NUnit Tests). Once those were in place I began testing out the Plain Old CLR Objects and a simple, one method repository consisting of “QuestionFindByID”, which was all I needed at this point. I implemented the repository using very basic (and hence certification-friendly) calls into the ADO.NET Connected Classes (one of which forms the basis for my first Featured Question). Once NUnit had lifted the database up by its bootstraps, It was quite a simple matter to un-stub the formerly brain-dead ExamQuestionControl.

 

Once the control was more or less working, I decided (as if setting myself up to take a look at the AJAX chapter) that I didn’t like the fact that the “Show Answer” button posted back the entire page to the server. Microsoft’s UpdatePanel control provided an elegantly simple way to AJAX enable my control to avoid a full page refresh. No seriously. If your hatred of Microsoft is interfering with your Buddhist practice, sit still and contemplate the UpdatePanel control, and you’ll be filled with loving kindness.

 

**Early Hiccups**

 

In the earliest stages of the work, getting the hosting set up and so forth, I ran into a few noteworthy hurdles. One was that I needed to compile the site and bin deploy the code-behind pages. Other problems involved [deploying an MVC site to godaddy.com](http://stackoverflow.com/questions/266205/is-there-a-way-that-i-can-run-a-asp-net-mvc-project-on-godaddy-com-shared-web-hos/1416036#1416036), but as you can see from the link, a kind fellow traveler provided the solution. Before I found that link, I had to re-code the master pages to inherit from System.Web.UI.MasterPage instead of System.Web.Mvc.ViewMasterPage. Once I found that link, however, I was able to restore the ViewMasterPage with no problems.

 

What I would recommend to your attention when you get to the deployment phase of your project is to right click on your project and check out the “Publish…” menu option. As others pointed out in the earlier link, the easiest thing to do turned out to be to publish to a local directory and upload files. The link above shows you how to bin deploy MVC, and that trick no doubt works on other weird dependency issues.

 

Once I’d played with that a bit I decided to clean up what I’d originally uploaded, and noticed then that Visual Studio’s publish doesn’t copy over image files referenced in your CSS if you select the option to only copy what’s needed. Copy those, end of problem.

 

[![image](http://www.particlewave.com/images/blog/Whats.NETMasterPagesLoveandUnderstanding_115DD/image_thumb.png)](http://www.particlewave.com/images/blog/Whats.NETMasterPagesLoveandUnderstanding_115DD/image.png)
