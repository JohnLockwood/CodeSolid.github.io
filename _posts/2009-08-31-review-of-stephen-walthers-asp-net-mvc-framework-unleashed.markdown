---
author: admin
comments: true
date: 2009-08-31 15:59:00+00:00
layout: post
slug: review-of-stephen-walthers-asp-net-mvc-framework-unleashed
title: Review of Stephen Walther’s ASP.NET MVC Framework Unleashed
wordpress_id: 445
categories:
- Miscellaneous
tags:
- ASP.NET
- ASP.NET MVC
---

 

_This is a reprint of a review I did on Amazon.com._

 

I bought Stephen Walther's ASP.NET MVC Framework Unleashed after several months of working on a legacy C and C++ project which looks nothing like Walther's book. Following that I spent many a happy Saturday and Sunday ramping up on Ruby on Rails, but finally giving up to revisit my Microsoft skills. ASP.NET MVC is a serious attempt by Microsoft to lure wayward Ruby on Rails types like me back into the fold, and I consider myself re-folded. 

 

Given my background, any halfway decent book on ASP.NET MVC would probably have been welcome, but Walther's book is especially welcome because it makes a serious attempt to treat ASP.NET MVC in the context of Test Driven Development. Well, all of that is what you'd expect if you take the cynical view that Microsoft is competing for developer mind share against Ruby on Rails, where the tests are "baked right in" (of course they all pass by default, but that's another story). But Walther's book is far more than a cheerleading book about TDD, and actually gets you into how to structure your code to support it. Using a generic repository class and relying heavily on the dependency injection pattern, Walther leads you through setting up your code so that it's loosely coupled, testable, and (presumably) easily refactored. 

 

Admittedly, it sometimes looks like Walther's approach takes too much manual lifting compared to Ruby on Rails -- and that's especially true when you run into Microsoft annoyances like not being able to refresh an Entity Data Model from the database. On the other hand, it's hard for an experienced software developer not to take a skeptical view of the magical rails "we can build a blog in 15 minutes" approach to the world. But still I feel that the techniques Walther talks about are the kind of thing that I really need to try out on an application or two. 

 

I gave the book four stars because there were a few sections that I thought could have been explained better. For example, I used his chapter on deployment almost immediately to create an application that supports both "legacy" ASP.NET and ASP.NET MVC, and the information there worked great, but I felt the file differences could have been narrowed down a bit more. Also in his discussion of the generic repository, I felt like we lost sight of the model there for awhile. Since there was a lot of ground to cover, a general overview discussing how the Entity Data Framework, the model, the repository and the service layer were all going to tie together might have been helpful. My final quibble is that I felt there could have been a bit more material on the relationships among the Entity Data Framework, LINQ, and LINQ to SQL. I think Walther's book gave the impression that the EDF and LINQ are orthogonal, whereas other Microsoft sources lead me to believe they're more complimentary. 

 

Despite its flaws, however, I want to leave you with the title of my review, that this is one of the best programming books I've read in a long time. It's the kind of book that expands your horizons and makes you want to try things out the way the author has outlined. To be sure, Walther's book is not the book you want to read if you goal is to hack together an unstructured ASP.NET MVC application in record time. But if your goal is to learn ASP.NET MVC while becoming a better programmer along the way, this book merits your serious attention and you should be pulling your credit card out now.
