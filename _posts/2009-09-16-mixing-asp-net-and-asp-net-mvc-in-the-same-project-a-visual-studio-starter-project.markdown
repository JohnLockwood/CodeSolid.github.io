---
author: admin
comments: true
date: 2009-09-16 07:01:00+00:00
layout: post
slug: mixing-asp-net-and-asp-net-mvc-in-the-same-project-a-visual-studio-starter-project
title: Mixing ASP.NET and ASP.NET MVC in the same project – A Visual Studio Starter
  Project
wordpress_id: 444
categories:
- Miscellaneous
tags:
- ASP.NET
- ASP.NET MVC
---

 

There are lots of good reasons to dive into Stephen Walther’s ASP.NET MVC Framework Unleashed. I won’t go into all of them now – perhaps I’ll write a review when I’m further into it. My short recommendation is to go buy it, especially if you’re going to do so by clicking the link at the right, whereby I’ll make a whopping 29 cents or whatever it is.

 

And meantime, in a paradox that proves once again that information doesn’t always need to be perfect to be useful, I believe one of Walther’s weaker chapters (in terms of eloquence) was simultaneously one of his stronger chapters (in terms of usefulness). This is the chapter on deployment, which deals (in part) with how you can add ASP.NET MVC to an existing ASP.NET application. This was a really useful chapter in its broad outline, even if the layout of the file “diffs” left a lot to be desired, leaving some of the details a bit sketchy. The short version is that you sit down for a couple of hours over coffee and merge files and add dependencies.

 

I used the information Walther provided to put together a starter Visual Studio 2008 project that allows you to use ASP.NET “classic” web forms together with ASP.NET MVC models / views / controllers in the same project. (I also chose the unit testing option so there are tests built in). I’ll try to do some more pounding on it before you read this, so it should be workable enough that the only thing you’ll want to do is rename all the namespaces and so forth from AspNetStarter to whatever you want to call your application.

 

Anyway, I hope you find it useful. If so I put it together. If not, I had nothing to do with it. Here it is as a 389 Kb or so [zip file](http://www.particlewave.com/asp_net/AspNetStarter.zip).
