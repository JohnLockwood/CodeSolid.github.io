---
author: admin
comments: true
date: 2009-09-06 19:51:00+00:00
layout: post
slug: early-asp-net-mvc-troubleshooting
title: Early ASP.NET MVC Troubleshooting
wordpress_id: 437
categories:
- Miscellaneous
tags:
- ASP.NET
- ASP.NET MVC
---

After happily switching between Visual 2005 and Visual Studio 2010 beta for several days, I installed Visual Studio 2008 recently so I could get started on ASP.NET MVC. Well Hello World worked more or less without a hitch, but inside that “more or less” are two stumbling blocks I hit which I mention here in case anyone else stubs their toes in the same place:

 

 

  
  1. At first, the Visual Studio install didn’t work. It turned out this was either a virus scanner or automated backup. Turning both off as recommended in the Microsoft troubleshooting docs did the trick.       

   
  2. The second little bit of trouble came when trying to create a new SQL Server database file in the App_Data folder to start playing around with something beyond Hello World. For providing what appears to be the solution (still installing), and for coming up in first place in Google ahead of Microsoft, Pinal Dave is the winner of today’s [gratuitous link to a solution](http://blog.sqlauthority.com/2008/09/21/sql-server-2008-fix-connection-error-with-visual-studio-2008-server-version-is-not-supported).
 

 

[](http://blog.sqlauthority.com/2008/09/21/sql-server-2008-fix-connection-error-with-visual-studio-2008-server-version-is-not-supported/)
