---
author: admin
comments: false
date: 2006-08-10 20:16:07+00:00
layout: post
slug: contract-firms-two-car-funerals-and-quality-assurance
title: Contract Firms, Two Car Funerals, and Quality Assurance
wordpress_id: 175
categories:
- Miscellaneous
---

Last Thursday, the manager on the contract I was working for called me in to his office to let me know he was paying a lot more for me to the contract firm I was working under than he thought he was.

This was a conversation that was destined to go poorly.   In fact, it was pretty much downhill from the point where he told me he could get two programmers for what I was making.

And here I thought my rates were pretty reasonable, at market or at worst only about 10% above.  And of course they were, but it turns out I was only making 52% of what the contract firm was billing for me.

I wonder what languages the two programmers who replace me will speak.  Probably Elbonian.

Anyway, the upshot is that on Friday I decided that I wanted to work on LeadReply -- my neglected pet project -- for free more than I wanted a 25% pay cut.  So that's what I'm doing, and it's well underway.

It's an interesting project, in that it's completely underfunded.  I have scope to work on it for maybe two months, and certainly to do everything I want to do is four months or so of work at minimum.

Today I was working on the classes that will parse the leads when they come in.  There's no rocket science there, just a lot of looking at text emails of various types and pulling strings out.  I was almost done the parsing code for most of the types I needed to handle on the first day, when it occurred to me that I was using something of a brute force approach, and needed to do some easy refactoring to make the code less fat and more maintainable.

I hated to bite the bullet and do this, since string parsing code is so ad hoc and yet so menial that it's always little fun.  But then I told myself something that was destined to be put on this blog.  I thought, "You know, you might as well do this job right, since after all you're not getting paid for it."

That's how I also do the jobs I'm paid for, of course -- but in that case where's the joke?
