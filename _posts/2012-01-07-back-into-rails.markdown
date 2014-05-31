---
author: admin
comments: true
date: 2012-01-07 17:10:21+00:00
layout: post
slug: back-into-rails
title: Back into Rails
wordpress_id: 798
categories:
- Miscellaneous
---

I haven't wanted to really dig into a software language in a long time, but I've been brushing up my Ruby and Rails skills and enjoying it thoroughly. It's still fun even if I only get to do it in my spare time. I haven't had the pleasure of making money at it yet, and currently I make a living digging up dinosaur bones in what we will charitably call C++ since that's what the file extensions seem to denote.

Dinosaur bones. Well, what the hell, I'm an old guy. We tend to do that.

So what's the Rails frenzy about? Well it's certainly not about working, at least in the short term. There isn't a Rails job within 50 miles of here. Sacramento is the land that time forgot, like that Twilight Zone episode where the guy gets off the train and it's the 1920's.

The real genesis of the thing started when I wanted to put a web site together that would support and allow open Direct Democracy. The idea is that we'd take Congressional voting records and develop a sort of report card as to the extent to which our representatives did -- or didn't -- represent us. Of course, the foregone conclusion is that they don't, but in the process of finding that out, we might arrive at a more perfect union.

Stranger things have happened.

I started out trying to tackle this beast in PHP, and dug into CakePHP as a way of avoiding the two problems I always had to avoid when working on Rails:



	
  * The hosting sucks.

	
  * Trying to work on Rails in Windows is like trying to speak Chinese in Venezuela. Sure, you can do it, but if your goal is Chinese, you're really better off in China. Which in the case of Rails means a Linux box or a Mac.


The problem was that solving this problem via CakePHP is like solving having car trouble by buying a toy car. CakePHP runs brilliantly on Apache Windows and I'm sure hosting would be a breeze, but compared to Rails it turns out to be somewhat limiting, both in the maturity of the platform and the size of the community of developers and contributors.

So as we speak, a copy of Paragon Hard Disk Manager 11 is running in the background, backing up a big old Windows partition before moving a bunch of stuff around and installing dual boot Linux, which it will also help me do. Then this laptop will become a laptop again, and my wife can use the desktop Linux machine that's just sitting in the living room gathering dust as a real desktop machine. The total cost of this solution is well over a thousand bucks less than the Macbook Air I'm drooling over, but until we find Jenniffer a job here in the US (she's a Drafter with lots of Autodesk if you know anyone), I'm in "let's do it on the cheap" mode.

So in the last few weeks I've been re-exploring the Rails world, and finding all sorts of goodies:

	
  * [GitHub](https://github.com/). OK, GitHub has been there for several years, but I only recently set up an account, first for my CakePHP stuff and later for the Rails work. I paid a few bucks to be able to keep private repositories, but I'm also possibly going to do some Open Source work.

	
  * [RSpec](http://blog.davidchelimsky.net/2007/05/14/an-introduction-to-rspec-part-i/).  OK, yes, Cucumber too but to my untrained eyes so far I must say Cucumber looks wordy enough to step outside the boundaries of [YAGNI](http://en.wikipedia.org/wiki/You_ain't_gonna_need_it), whereas RSpec still seems pretty clean.  Tomayto, Tomahto.

	
  * Russ Olsen's [Eloquent Ruby](http://www.amazon.com/Eloquent-Ruby-Addison-Wesley-Professional/dp/0321584104/ref=sr_1_1?ie=UTF8&qid=1325954089&sr=8-1).  This is one of those books that does a great job of putting the language into some sort of context, and does so in a way both teaches you more about the language and lets you make sense of it at the same time.  This one's worth a look.

	
  * [Devise](https://github.com/plataformatec/devise).  Want to have feature rich web site registration and authentication pretty much ready to rock and roll in about half an hour?  There you go.  By way of comparison, I spent maybe half a day on some stock CakePHP code to do the same thing, and it wasn't even close to as done.

	
  * [Open Democracy](https://github.com/rbjarnason/open-active-democracy) and [GovKit](https://github.com/opengovernment/govkit), some open source projects making rolling an open democracy web site less of a development effort (oh well, so much for teaching myself things) and more of an integration effort (hey presto, learn by doing).  And hey look, the [Sunlight Foundation](http://sunlightfoundation.com/) -- I'm digging stuff up even as we speak.


So there you go.  Learn Ruby.  It's the patriotic thing to do.
