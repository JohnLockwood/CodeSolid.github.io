---
author: admin
comments: true
date: 2012-07-06 20:12:14+00:00
layout: post
slug: hire-this-ruby-developer-trapped-in-a-microsoft-programmers-body
title: How I Became a Ruby on Rails Contractor (Trapped in a Microsoft Programmer's
  Body)
wordpress_id: 888
categories:
- Miscellaneous
tags:
- Ruby on Rails
---

I work in a Microsoft shop during the day working mostly on legacy code written in C, C++, C#, and some VB.NET. There was an incident at work recently that turned me into a Rails Contractor, through some interesting alchemy involving pointy hair, which I will describe below.

But before telling you my war story, a bit about my Rails experience: I learned Rails the same way I learned every language and framework I know -- by coding in it in my spare time.  I've made my living as a programmer / web developer doing that since about 1993.  Currently I'm working on a private Rails project, which is probably a bad thing since I can't point to it on Github and show you my stuff.  I'll fix that shortly by doing some public rails work.

Now on to the war story. As I said, I work mainly on legacy code. In his excellent book on the subject, Michael Feathers defines legacy code as code that's not under test. That's what I work on -- vast expanses of code through which dozens of programmers have slogged since Regan was in office. The first development time unit tests in the system were tests that I added, and I only came here some four years ago, so that tells you how long this untested code had been hacked on. So needless to say, it's been a challenge to get tests in after the fact.

Here is our software "process": For major releases, our software goes through QA, running whatever tests we have. The quality is poor, so we have multiple service packs to fix it, which also go through QA. But because our test coverage isn't what it should be, the quality of the service packs is poor. Our solution to this is to send out "Emergency Patches" -- releases that don't go through QA testing at all.

Yes, you really read that right, and it really said that.  To fix our quality problems, we patch our software, test the patch, but we don't send the software back to QA to run whatever scant regression tests we have -- we just send the patch out the door.  Problem?  What problem?

**The Software Death Spiral**

Yesterday one of my bosses asked me why we were having regressions in one area of the product. I work on database drivers, and the area in question was our handling of Oracle LONGs, a data type that Oracle deprecated in 1997, when Bill Clinton was president. I replied that we don't have regression tests for it in the suite that QA runs, and that it wasn't too much work to add them, but would leave us in the position of certifying our product against another vendor's deprecated type. So this development manager talks to the manager of technical support, who's job it is to tell us which fixes we ship without testing, which she determines according to the customer screaming for the fix.

Our process is CSDD: Customer Screaming Driven Development.

The answer I received was that the support manager "agreed" with me that we don't need regression tests for LONGs. We just need "something very targeted and specifc: a fix for this one problem..."

In other words, the manager's solution for the regressions he was asking me about was to do more patches in that area without regression testing, and to attribute the idea to me!

It was at that very moment that I became a Ruby on Rails Contractor.  To be sure, I was occupying the space of a nominal .NET / C# / C++ / C programmer, but now I was a Rails Contractor sitting in the wrong chair.  Echos of my old career as a contractor came back to me, where I was able to fight and win the battle of Test First Development in a Java shop, before Ruby on Rails was invented.  As a result, we found and killed all kinds of problems that we wouldn't have otherwise.  (That was shortly after teaching myself Java, as it happens.  Learn.  Do.  Learn more.  Do more.  That's the cycle).

Rails is not a magic bullet, of course, but at least a framework that includes unit tests baked in -- as well as gems like Rspec and FactoryGirl and Capybara -- has a fighting chance of attracting one or two managers who understand that testing is important.

Leave me a comment if you're such a manager. I'd love to hear from you.
