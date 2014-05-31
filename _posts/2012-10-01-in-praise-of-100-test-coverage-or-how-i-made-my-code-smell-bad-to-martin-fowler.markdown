---
author: admin
comments: true
date: 2012-10-01 01:48:17+00:00
layout: post
slug: in-praise-of-100-test-coverage-or-how-i-made-my-code-smell-bad-to-martin-fowler
title: In Praise of 100% Test Coverage, or How I Made My Code Smell Bad to Martin
  Fowler
wordpress_id: 1047
categories:
- Miscellaneous
---

I am working on a Rails project that I have "in production" (barely), albeit in a state that's not even pre-beta enough to announce.

So it's not an application yet, but it sports 97 specs and 100% test coverage.

Somewhere along the line it had something like 80% coverage, and I decided it would be fun to see what it took to get it up to 100%. Along the way I thought I'd read about 100% coverage and what a good thing it was, only to find that most of the folks talking about it were doing the whole post-modern-er than thou number on it. You've seen these arguments. Take a perfectly good tool in the programmer's arsenal, then talk about why it's not a magic bullet, (in the absence of anyone making any kind of serious claim that it is).

Proably the smartest guy engaging in this sort of thing is Martin Fowler, who is way smarter than me, and certainly taken more seriously among the cool kids. Here is his [code coverage article](http://martinfowler.com/bliki/TestCoverage.html). But you also have [Brian Marick](http://www.exampler.com/testing-com/writings/coverage.pdf), whom Folwer considers "very wise" for having said "I expect a high level of coverage. Sometimes managers require one. There's a subtle difference."

Let's look at some of the arguments that are proffered against code coverage, and see what we can make of them.

Fowler writes:


>  _If you make a certain level of coverage a target, people will try to attain it. The trouble is that high coverage numbers are too easy to reach with low quality testing. At the most absurd level you have AssertionFreeTesting. But even without that you get lots of tests looking for things that rarely go wrong distracting you from testing the things that really matter._


The thread that runs through both Marick's argument and Fowler's is that if you make high coverage numbers a requirement, people will find a way around it. Well, yes, I agree, but so what? People are always going to find a way to do the wrong thing, but here's the problem: speeding laws aren't bad just because people use radar detectors, and when a murderer finds a clever way to hide a body, we don't take the law against murder off the book -- we try to improve our forensics.

Now, does code coverage distract me from testing the things that really matter? Of course not. Code coverage is just one tool. Shortly after I had reached 100% coverage on this project, I pushed it into "production" (there are no customers for it yet) for the first time, and tried using it with someone else, since one of my other favorite tools for improving software quality is to get it off my desk as quickly as possible so we can work out the "works on my machine" bugs. Of course, as soon as I did that, I found a problem and had to go after it.  And of course, the fix wasn't to improve how many times each line of my 100% covered code was hit -- the fix was to drive the coverage numbers down temporarily by writing more code and tests to solve the problem.

Now, if I hadn't focused on code coverage first, could I have found that problem on my own?  Well, I could have thought about better ways to test my code, and I still try to do that even though I'm shooting for 100% coverage. But in terms of this problem, I don't think it would have turned it up.  The reason we have QA even though we have unit tests is that individual developers have blind spots, and testing is hard.

Let's look at another passage of Fowler's:


> _So what is the value of coverage analysis again? Well it helps you find which bits of your code aren't being tested. It's worth running coverage tools every so often and looking at these bits of untested code. Do they worry you that they aren't being tested?_


__At this point he goes on to quote Marick again:


> _If a part of your test suite is weak in a way that coverage can detect, it's likely also weak in a way coverage can't detect._


To me, there are a number of problems with "running coverage tools every so often and looking at these bits of untested code".

First, running the tools less often means I'm forced to think about testing every so often. Now if I'm doing the right thing, I shouldn't have to be forced to think about it, I should be thinking about it all the time. But the truth is, as much as I love to test, testing is hard, and it consumes time.  That being the case, tools that keep me motivated to do it are my friends.  Test coverage is one such tool.  Yes, the "atta boy" it gives me is not any kind of real assurance that I've done my job well, but it helps to have it anyway.

Second, running the tools every so often means I have a LOT more code to look at when I do run them. When I was working on the bug above, my coverage got down to about 96%.  That's actually a lot more of a drop than I expect to see when I'm further into the project and there's a lot more code. But the real point is, after I'd done the tests I could think to do and gotten them passing, and done some manual testing as well, going after that 4% didn't really take a lot of time. Moreover, that 4% all had to do with code that was fresh in my mind, which is a lot easier to write real tests for than something I look at "every so often".  Getting up to 100% coverage was a chore when I first did it, and my project is brand new.  Doing it on an existing project would be that much harder, to be sure, and that's the point.  I'd much rather invest a half hour here and there to keep the tests at 100% writing tests on fresh code than puzzling through uncovered code to try to find the naughty bits.  It's just easier.

Finally, keeping the code 100% covered means I always know exactly where to go to find the parts that aren't -- they're the parts I just worked on.  Yes, the tool tells where the uncovered parts are, but I'd rather not wade through all the noise that would be generated by all the other code that isn't covered, trying to see what I missed in my tests of what I just wrote.

During the day I work in a shop where most of our code is legacy C and C++.  One of the best practices if you have a compiler that does type checking is to have zero warnings -- which is trivial to do if you're on a team that does it, because if you see any warnings you know you just put them there and you can fix them before you check in the code.   On one of the projects I worked on, in contrast, the team has let things go to the point where there were well over 1000 warnings the last time I counted, and that was years ago.   Of course, most of these warnings are trivial, but there are bound to be some real bugs in over 1000 of them.  Do you have any idea where?  Nope.  Does anyone else on the team?  Nope.

Now, given the number of lines in the code, I could say that a certain number of warnings is acceptable -- certainly we have well, well over 90% of our code that _doesn't_ have a warning in it. Fowler says a level of test coverage of 80-90% is good enough, and writes that he "would be suspicious of anything like 100% - it would smell of someone writing tests to make the coverage numbers happy, but not thinking about what they are doing."

The problem with that approach is that the same team that has the 1,000 warnings also has zero developer-run unit tests, tons of run time ASSERTS in debug mode -- you name it.  And surprise surprise, we spend an enormous amount of time chasing down issues that are embarrassing for a product that has been in production as long as ours has.

So maybe I'm not thinking about what I'm doing when I push for 100% coverage.  Maybe.  But on the team with the ASSERTs and the warnings and the no-unit tests and all the rest -- I KNOW we're not thinking about what we're doing.

No broken windows means just that -- NO broken windows.The problem with letting things be "just good enough" is that once your quality standards are just good enough, wait a few years and you'll inevitably get to "please, somebody kill me."
