---
author: admin
comments: true
date: 2009-04-19 21:29:13+00:00
layout: post
slug: aspnet-versus-ruby-on-rails
title: ASP.NET versus Ruby On Rails
wordpress_id: 319
categories:
- Ruby on Rails
---

My friend and college housemate [Daniel Steinberg](http://dimsumthinking.com/blog/) stopped by  recently and caught me red-handed in yet another heinous act of last week's marketing.  By "last week's marketing", I mean that I should know better than to say this site is about a specific platform / language/ technology.  This is generally the wrong approach for a computer-linguistic gadabout to take.  John Lockwood putting up a web site about a programming language is like Madonna putting up a web site about her one true love.  The next words out of her mouth are bound to be:  "Woops, no I mean my other one true love."

So with those other strange bedfellows out of the way, we now turn our attention to a comparison of ASP.NET and Ruby on Rails from my point of view as a fellow without a firm entrenchment in either technology.  This isn't meant to start or feed a language war because, as I say, I'm not passionately committed to either technology.  I do think that both environments offer a lot that's good, and both have more than enough things that bug the heck out of me.  So this article quite simply is my list of what I like and don't like about each environment.

**What I Like About Ruby on Rails**



	
  * **Prototype
**Rails has excellent baked-in support for two great related JavaScript libraries -- Prototype and Script.acul.us.  It's about this hard to get started:
<%= javascript_include_tag :defaults %>.  Well, OK, it's a little harder than that, but I found that with [Prototype's beautiful documentation](http://www.prototypejs.org/api), I was able to go from zero to productive in a few hours.  What's more, I actually found myself liking JavaScript for the first time since -- oh, who am I kidding, I always hated it.  Prototype could change my mind, though.  I wrote something that started to look pretty good in Firefox, then fired up Internet Explorer fully expecting half the page to re-materialize in a particle accelerator in Switzerland.  But lo and behold, it worked the way it did in Firefox. You're kidding, right?  JavaScript did that?

But then I saw her face.  Now I'm a believer.  


	
  * **link_to_remote**
How easy can we make AJAX?  Does a single function call work for you?  It does for me.  Here's a tip though from someone who knocked his head briefly against this wall:  unless you're updating multiple DOM elements (and maybe even if you are), then forget about RJS.  Something in this format:  "your_response.html.erb" is your friend.  


	
  * **Lots of Other Stuff
**If you're a Rails programmer, lest I lose the goodwill from the next section, let me say this first: overall, I think Ruby on Rails is the bee's knees, or perhaps the cat's pajamas.  Well, either way, it's something cool that belongs to some critter. I'm not sure I can frame why exactly, but it's a lot of fun to program in.  There's something about the way it all fits together that's very satisfying.


**What I Don't Like About Ruby on Rails**



	
  * **Smugness and Sleight of Hand**
Rails is an utterly cool framework, but that doesn't mean that there aren't parts of it that aren't either smoke and mirrors or mere social gushiness.  For example, if you have a phrase that goes Don't Repeat Yourself with the clever acronym of "DRY", then you probably shouldn't say it over and over and over and over and over again.  Honestly.  Shut up already.  Even Assembly Language had macros.  We get it.  Give it a REST already.  Oh, and speaking of REST, remember your other catch phrase, You Ain't Going to Need It?  Well, guess what:  you still have sessions, and so regarding REST, you ain't.  


	
  * **What, No Windows?
**Apparently Rails runs a lot better on Linux than it does on Windows.  I say apparently because I haven't bitten the bullet yet to set up an Ubuntu partition, but it seems to be the gist of the literature.  I have no reason to doubt it given that as of a couple of weeks ago my no-model unit test framework (which at the time barely beat "Hello World" in terms of complexity) took 13 seconds to load and run.  


	
  * **You Call This Documentation?
**Unlike the Prototype documentation (which as I said, is beautiful), the documentation for Rails falls into two categories, as near as I can tell:

	
    * Web pages that link to things that aren't there any more, as though the framework were trying to bury the competition beneath the sheer weight of 404 errors.

	
    * Function documentation that looks something like this:my_rails_function(object, gizmo, options = {}, html_options = {} )
_object:  some well defined object that the documentation describes in depth
gizmo:  some gizmo that's also pretty well defined but fairly self evident
blah blah blah lorem ipsit dolor yada yada.
_
Where's the discussion of "options" and "html_options", you ask?  If you're lucky, they're somewhere else on the same page, twenty lines down or under the discussion of some related function.  More likely they're in the source.  Apparently if you're a Rails programmer, a side benefit of saying Don't Repeat Yourself over and over is having function parameters handled only in the source and not in the documentation.





So much for the pros and cons of Rails.  Let's see if we can come up with some similar virtues and trash talk for Microsoft's ASP.NET.

**What I Like About ASP.NET**



	
  * **Visual Studio
**Visual Studio is a competent and mature Integrated Environment, with an outstanding debugger and great features such as code completion.  Now, before you say, "But John, Rails has that, too!", I should point out that, yes, I tried out RubyMine (in Beta) and Aptana Studio, and so far I'm a bit underwhelmed.  Yes, Chris Williams was totally nice when I entered a bug wherein the code completion didn't work, and he put a fix in for 1.3.0 within 24 hours of me entering it, and yes I appreciate that.  But 1.3 isn't out yet, and in Visual Studio, it just works.  Admittedly, Microsoft's tool has been around a lot longer, and yes, they have huge resources, the comparison is unfair, etc.  But if you're going to lure me out of Visual SlickEdit and the command line, you'd better have great code completion and an awesome debugger, because otherwise I'm comfortable already.  


	
  * **MSDN
**Remember those missing options and html_options from the Rails doc?  Well, if I'm sitting at my keyboard, say "Go", give me twenty seconds, and I'll rattle off all the parameters for CreateWindowEx and what they mean, including acceptable values for multi-valued parameters.  The MSDN documentation for ASP.NET is as good as the Rails documentation is poor.  


	
  * **Jobs
**The last two times I looked, the number of jobs for ASP.NET developers was beating the number of jobs for Ruby on Rails developers by a factor of about 11 to 1.  If you look at the pyramid for Abraham Maslow's [hierarchy of needs](http://en.wikipedia.org/wiki/Maslow%27s_hierarchy_of_needs), you'll see that employment (in "Safety"), comes well before Esteem and Self-actualization.  The Beatles presciently wrote about the allure of Rails versus the hegemony of Microsoft thus:

_Your lovin' gives me a thrill
But your lovin' don't pay my bills._

	
  * __**NUnit
**What can I say.  I'm a tramp for cheap visual thrills.  I like to see the bar turn green.


**What I Don't Like About ASP.NET**



	
  * **High Cost of Ownership
**I mentioned jobs before, but in some respects this is a moot point or at least not the only point, since what I have discovered about myself long ago is that the guy I really like working and writing code for is me.  SQLServer and .NET hosting simply don't have the same sorts of economies as the LAMP world, and I'm sure Rails is on a path to being that cost effective even if it's not quite there yet.  Thus, to me much of the debate breaks down into a personal decision between entrepreneur versus employee.  


	
  * **Vendor Lock
**As slow as Rails is on Windows, as far as I know the only implementation to date of LINQ to SQL (which one might nickname "ActiveRecord for Microsfoties") is against SQL Server.  Yes, it's cool that LINQ works with native objects, XML, and SQL, but to date I haven't yet needed to do a right outer join of my Employees table and my HttpRequest object, and I don't anticipate that changing.  


	
  * **Their Model View Controller Support is a Disaster
**This may have changed since I last looked at it, but on my last ASP.NET contract they had an MVC implementation that completely broke browser navigation and was a nightmare to code to.  I'm not an MVC snob, so I'm quite happy with some pages and code behind talking to a well defined model interface if that's how you want to do things.  However I do think that if you're going to use the MVC pattern (or any other design pattern), it should work better than the absence of it does.  Rails forces MVC on you, but it works just fine.  MVC is an optional nightmare in ASP.NET, unless your manager is of that all too common variety that prefers buzzwords over common sense -- in which case it becomes a required nightmare.


Thank you for hanging in there for this completely idiosyncratic view of some of the pros and cons of Ruby on Rails versus ASP.NET.  I've barely scratched the surface, I'm sure, but when it comes to language wars, that's probably all one should do.  In my opinion, programming always represents a certain dynamic tension between "How cool is that?" and "Who's cutting me a check this week?"  Go out and code something, folks.  What if they gave a language war and nobody came?
