---
author: admin
comments: true
date: 2013-05-14 03:12:16+00:00
layout: post
slug: pros-and-cons-of-test-driven-development
title: Pros and Cons of Test Driven Development
wordpress_id: 1478
categories:
- Software
- Testing, JUnit, TestNG Tutorials
tags:
- Test Automation
- Test Driven Development
- Unit Testing
---

If you ask anyone who works with me, I'm a huge fan of automated unit testing in general and Test Driven Development (TDD) in particular. Ever since I first tried it out on a project eleven years ago, I've been convinced that Test Driven Development is a huge improvement over the number one software development "methodology" in the U.S.: code and fix.

But if TDD is so cool, the last sentence begs a very important question: why is code and fix so popular? Should you be considering more unit testing for your next project, or will you do just fine without it? Are there legitimate reasons not to consider automated unit testing? How much testing is enough?  What are the pros and cons of Test Driven Development and automated unit testing?


### Pros





	
  * If you're a software manager, the number one benefit to consider for automated unit testing is reduced costs. This may seem paradoxical -- how can writing **more** code cost you **less**? The answer is that unit tests let your developers find bugs earlier in the development process than they would otherwise.  As a result of this, empirical studies such as the one conducted by [Nagappan et al](http://research.microsoft.com/en-us/groups/ese/nagappan_tdd.pdf). at Microsoft and IBM have shown a drop in the defect density of between 40% and 90% for teams using test driven development.

	
  * Unit tests are especially valuable as a safety net when the code needs to be changed to either add new features or fix an existing bug, since the  sanity check for new changes. Since maintenance accounts for between 60 and 90% of the software life cycle, it's hard to overstate how the time taken up front to create a decent set of unit tests can pay for itself over and over again over the lifetime of the project.

	
  * Another big plus to automated unit testing is something that rarely gets much mention but is crucially important. Writing unit tests during development means you'll always have a stub for exercising a given method in the debugger. Getting my start 20,000 years ago as I did as a C and C++ programmer -- there be pointers there! -- running your code through the debugger as you were writing it was something that All-Right-Thinking-Programmers(tm) had to do. As my friend, Jim Alisago, put it early on in my career, "You might as well start in the debugger because you're going to end up there anyway." I sometimes get the impression reading the literature that Unit Testing replaces debugging -- on the contrary, to me, it enables it and enhances it.

	
  * Testing while writing also forces you to try to make your interfaces clean enough to be tested. It's sometimes hard to see the advantage of this until you work on a body of code where it wasn't done, and the only way to exercise and focus on a given piece of code is to run the whole system and set a break-point. Unit tested code therefore tends to be less tightly coupled than code that sprang fully formed from the clever mind of a cowboy developer.

	
  * Unit tests are a fantastic learning tool for developers using new technologies. Whenever I encounter something obtuse in some bit of software documentation, I find myself reaching for a small set of unit tests. Unit tests let me quickly find where the skeletons are buried in a new framework.

	
  * Along the same lines serve as documentation for existing code, showing developers new to the code how it works.




### Cons





	
  * There is of course the up front cost of writing the unit tests.   I believe that in almost all cases this cost will be amortized over the life of the project.  However, if a quick and dirty prototype is needed in a hurry, this initial time cost may not be acceptable.  A caveat is in order here, however:  many a quick and dirty prototype has been rushed from developer conference to deliverable without stopping at go or collecting $200.  Today's quick and dirty prototype is often tomorrow's legacy albatross.

	
  * Speaking of legacy code, it is far, far easier to introduce unit testing at the start of a coding effort than it is to add unit tests during the maintenance phase of a project.  It's more difficult enough that Michael Feathers was able to write a wonderful book about it.  However, as the Chinese are alleged to say, the best time to plant a tree was 20 years ago, but the second best time is today.

	
  * Like any good technique, unit testing can be carried to an extreme. My own experience has been that the biggest benefits come from a moderate effort, with the tests always exercising the code in the simplest way possible.  If you find yourself frequently re-factoring your tests, there's a good chance you're spending too much time on the test suite.  Similarly, there's not much point to optimizing your tests with stubs and mocks if they don't take that long to run yet.

	
  * Unit tests of course need to be maintained to be useful -- ideally keeping the error / failure rate at zero.  This of course means there are times when the tests themselves have to change to keep track of new changes to the code, but the whole point of the test suite is to bring the impact of your changes into sharp focus.  Breaking existing callers depending on an interface is not a big deal -- but doing it without knowing you did it is a real problem.

	
  * Unit tests are just unit tests.  Although, as we mentioned above, they do have a role in documentation, unit tests are not a substitute for documentation. Contrary to what many agile texts would have you believe, not all comments are "smells".  Similarly, the planning that takes place in the process of producing initial specifications almost always justifies a reasonable effort.  Nor are unit tests a substitute for integration testing, acceptance testing, or other automated / manual testing done by sharp quality engineers.


