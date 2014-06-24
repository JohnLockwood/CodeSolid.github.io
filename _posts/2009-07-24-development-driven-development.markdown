---
author: admin
comments: true
date: 2009-07-24 03:22:17+00:00
layout: post
slug: development-driven-development
title: Development Driven Development
wordpress_id: 365
categories:
- Miscellaneous
---

Some time ago I waxed ecstatic over JUnit automated unit tests here, and I think the software community has gotten even more wild-eyed over automated tests in the meantime, with the latest buzz about Behavior Driven Development.  For my part, lately I've begun to question all this conventional wisdom.  I've begun to believe that the fact that programmers love testing so much for the same reason Twitter is so popular:  writing short little works is more fun than reading something long and difficult.  

You see, MY test case is elegant and concise, whereas YOUR legacy code is convoluted and difficult.

Don't get me wrong.  My legacy code is convoluted and difficult, too, but it's a lot more convoluted and difficult for you than it is for me, because I wrote it, and I have a better than even chance of knowing where the tasty bits are and what I was thinking.  Your code is sometimes worse than mine, and sometimes better, but either way I don't know it as well as my code until I start grepping and reading and stepping through it in the debugger.  After a while doing that, I become more proficient in your code, but it's still YOUR code.

During the day I make my living reading and fixing YOUR code.  Well not yours exactly, but Other People's Code.  The particular compilation of Other People's Code that pays my bills lately has been evolving since the 1980s, with very little in the way of automated testing.  Yet my employers are selling the product and making enough revenue to hire me and a bunch of other guys to work on it.  To be sure, it may be nearing the end of its life cycle, in which case I may need to find OTHER Other People's code to go work on, but I guarantee you that in the meantime my wife doesn't mind when she cooks dinner.

When I first got to this job I lamented the fact that there were no native automated unit tests.  I wrote a few, but not nearly as many as I'd like, nor nearly as many as we need.  QA has also been recently trying to add a lot more automation, though they're written in the tool we're developing -- not the language I'm working in.  More and more I just try to bite the bullet and debug and fix the problems that get logged by ad hoc testing against Other People's Code, and I try not to let my overwhelming agile genius keep me from having a good time.

As a younger programmer than I am now, at my first true development job, my agile genius was at the height of his awesome career.  As I was explaining some ingenious and difficult idea to my manager, he detected my agile genius at work.  He rewarded my heroic efforts with the following response, which I offer up to you now as a Development Driven Development manifesto:

He said, "Just write the damned code."

**Just Write The Damned Code**

I don't think many developers are going to go for this as a way of doing business, because just writing the damned code is not really theoretical, and we developers love our theories.  This sounds too much like chaos, and too many of us have suffered from chaos as a model of software development.

And do I really mean we should give up all testing, finish our edits and just do an svn commit without further ado?  No, of course I mean that we should test our code, step through it in the debugger, examine it, review it, think about it, clean it up and the like.  In fact, I submit that the compelling love of automated testing is precisely a reflection of the fact that while I'm an accomplished professional with a serious work ethic who always debugs and tests his code before checking it in, Other People as we all know are the dreaded perpetrators of Other People's Code.

The sleight of hand here of course is that Other People are also the dreaded perpetrators of Other People's Automated Tests.  In my non-day job, rather than getting paid to work on a codebase that has no unit tests, I'm not paid (yet) to work on a project written in Ruby, where automated testing is "baked-right-in".  Of course, the tests that script/generate will spit out as a by-product of its work all pass, but more importantly, one of the Ruby third party gems I use heavily has sucked up more of its fair share of my time because its tests fail whenever I start running a new version of Ruby or make another change.  

Am I just being ornery and jealous of my time that I don't want to spend the happy part of my coding weekend updating tests that say that a user should end up on page X when they're finished with some task, after I just explicitly made the change so they'd end up on a page Y?  Really, we need tests to tell us what page people are going to now?  Did we think they'd suddenly start hopping over to pets.com?  And do I care that the string format of a DateTime object changed and now your tests are failing not because the dates are wrong but because of that incidental?

Test Driven Development works like this:

1) Write test case, color should be red.
2) Watch test case fail, color is not red.
3) Write code to make red.
4) Test case passes.

Development Driven Development works like this:

1) Write code to make red.
2) Verify red.

Yes, I've had cases where automated tests have alerted me to real bugs, so I don't want to damn them out of hand.  But I think a lot of the religious fervor surrounding unit testing boils down to something like this:  "I don't want to step through your code, and I haven't yet created anything valuable enough that some other poor sap has to step through mine and I can get paid for it while being cursed as a fool."

So I'm changing my goal, from writing unit tests for Other People's Code to just debugging that code as best I can while writing my own code.

I'd rather be a hammer than a nail.  As the great software engineer, Louis XIV, said, "Other People, C'est Moi!"
