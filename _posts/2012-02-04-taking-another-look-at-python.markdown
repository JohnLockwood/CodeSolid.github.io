---
author: admin
comments: true
date: 2012-02-04 14:22:26+00:00
layout: post
slug: taking-another-look-at-python
title: Taking Another Look At Python
wordpress_id: 811
categories:
- Miscellaneous
tags:
- Perl
- Python
- Ruby
---

The first few times I encountered Python was in a local bookstore, and I think the same book was there each time.  Each time I was immediately put off by this unfortunate volume, which seemed to suggest that the greatest thing about Python was the whitespace, and gave me the impression that whitespace was more significant than it was.

Not quite.  It turns out that there is a [Whitespace language](http://en.wikipedia.org/wiki/Whitespace_(programming_language)) (who knew?), but Python isn't it.  Well, I do hate making mistakes, but it seems the myth about whitespace being significant in Python is pretty common, enough so for at least [one author](http://www.secnetix.de/olli/Python/block_indentation.hawk) to dispel it.

I'm into my third or fourth look at Phython now, and I'm starting to get why so many programmers like it.  First, Python's design and culture are based on being as simple and as obvious as possible. Forcing correct indentation is one example.  The Pythonic question to ask is not why you should be forced to indent correctly, but for heaven's sake, why would you want the freedom to do it wrong?  "As Alex Martelli put it in his _Pyhon Cookbook_ (2nd ed., p. 230): 'To describe something as clever is NOT considered a compliment in the Python culture.'" ([Wikipedia](http://en.wikipedia.org/wiki/Python_(programming_language))).

The obviousness of Python shines through brilliantly in the Windows binaries, including Idle, which has the incredible good manners to include a really good searchable help system (that's also available directly through a chm file).  A windows help system on Windows?  What were these people thinking?  The Ruby approach:  "You can't do Ruby on Windows, Get a  Mac, and by the way, documentation violates the DRY principle".

Before the flame wars start I should point out that I like Ruby enough to be volunteering on an open source project that's written in it and would love to work in a Ruby shop.  Ruby has some incredible strengths, but clarity is not one of them.  As Clark Maurer [nailed it](http://blog.slickedit.com/2007/09/comparing-python-to-perl-and-ruby/) in his demonstration of a typical Ruby yield statement plus block mess:  "What planet did this come from?"

To me it seems like being clever is a hallmark of youth.  As a younger man I was a clever C++ programmer.  Older programmers have generally slogged through enough of other people's trying-to-be-clever code to hold being prosaic and obvious as virtues.  Yes, if I had a down and dirty script that had to get done, I'd rather write it in Perl, where I can be clever, but six months later, I'd rather maintain it in Python, when the obviousness I was forced to adopt at the start begins to pay off.

Being clever is fun, and the speed boost is exhilarating.  If you're being clever, believe me, I understand the allure.  But take my advice -- cut it out and be obvious.  Your future self will thank you for it.
