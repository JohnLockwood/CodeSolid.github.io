---
author: admin
comments: true
date: 2012-07-13 03:26:45+00:00
layout: post
slug: getting-started-with-typo-2
title: Getting Started With Typo
wordpress_id: 1000
categories:
- Ruby on Rails
tags:
- Ruby on Rails
- Typo
---

Why do Rails developers like me blog on WordPress, which is written in PHP, instead of Typo, which is written in Ruby on Rails?

The answer is simple, really, maybe even obvious:  WordPress works better.  Wordpress has been in development longer, and it has more of everything, more plugins, more polish, more templates.  As of today, for example, Wordpress has some 20,244 plugins, while Typo has about 25.

It's a bit of a landslide, really.

The reason I wanted to take a look at Typo is that it fits in with my professional development goals, which are:




	
  * To work on some passive income Rails projects to improve my skills.

	
  * To work on some open source projects to further develop my skills and give something back.

	
  * To share my experiences here.

	
  * Finally, as the inevitable result, to land some paid Rails work.



Sure, being early in development, Typo's not right (yet) for the casual blogger.  But at the same time, it's an ideal project on which a Rails developer can hope to make important contributions.

So...

**Getting Typo Up and Running**

The [Typo blog](http://fdv.github.com/typo/) tells us that we're supposed to start with something more or less like this:

[cc]
$ gem install typo
$ typo install some/path
[/cc]

Trying out the first line, we get a clue as to the sort of development fun we can have with this product:

We're greeted with the following in nice red letters:

[cc]
-------------------------------------------------------------------------------

Since version 6.0, Typo is no longer available as a gem. 
Please download Typo source at http://typosphere.org/stable.tgz 

To install Typo from scratch: https://github.com/fdv/typo/wiki/Installing-Typo
Upgrading Typo from an older version: https://github.com/fdv/typo/wiki/Upgrading-to-typo-6.0

-------------------------------------------------------------------------------
[/cc]

After you get that message, the gem that's no longer available will be installed.  

That's OK, let's not let it deter us from doing the right thing according to the warning.  Getting the stable release specified in the link and unzipping it, let's visit the [documentation](https://github.com/fdv/typo/wiki/Installing-Typo) as suggested.

I've just worked with the doc and updated the wiki page fix some inaccuracies and omissions, so hopefully it's current (as of today at least).  So you should be able to work your way through that to the point where you're ready to run "rails server" without trouble.  

I was able to get it running, and was able to run edge server pretty easily too.  Just get the latest:

[cc]
git clone git://github.com/fdv/typo.git
[/cc]

Run the instructions above and you should be good to go!

