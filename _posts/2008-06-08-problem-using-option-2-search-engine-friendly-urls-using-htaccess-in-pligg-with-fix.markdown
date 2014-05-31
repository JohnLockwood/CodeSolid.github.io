---
author: admin
comments: true
date: 2008-06-08 04:47:21+00:00
layout: post
slug: problem-using-option-2-search-engine-friendly-urls-using-htaccess-in-pligg-with-fix
title: Problem Using Option 2 Search Engine Friendly URLs using htaccess in Pligg,
  With Fix
wordpress_id: 109
categories:
- Miscellaneous
---

For most of my usual real estate readers, this post will be pretty technical and you may want to skip it. It deals with a fix I found in working on Houssee.com, and it has a sort of Dilbert-like appeal to those of you who've ever worked on a software team.

One of the reasons I eventually left software is the inordinate amount of time we used to spend making the case that 

#define BROKEN true

should be changed to   
  
#define BROKEN false

Usually there's some vague sociological reason for leaving the first line in, like a manager who found a consultant who told him it should be that way.

In this case the reason is:

**"I Know More About Apache Than YOU Do, Neener Neener Neener"**

For those of you who are still struggling to get "Option 2" search engine friendly URLs to work in Pligg using .htaccess, you can take comfort in the fact that the developers over there know more about Apache than you do.

But meantime enjoy the fix below! ;)

JohnLockwood ![JohnLockwood is online now](http://forums.pligg.com/images/chestnut/statusicon/user_online.gif)  

New Pligger

Pligg Version: 0.90

Join Date: May 2008

Posts: 2 

Downloads: 1

Uploads: 0

Thanks: 0

Thanked 0 Times in 0 Posts 

**The Fix**

* * *

 

Guys,   
  
I believe I found the fix for this. Unless anyone knows of a reason why it shouldn't be included, I suggest the developers roll it into the code and see if it doesn't save a lot of people grief in the next release.   
  
Here it is:   
  
In .htaccess, find the line that reads:   
  
Options +Indexes +FollowSymlinks   
  
Change it to this:   
  
Options +Indexes +FollowSymlinks -MultiViews   
  
The problem in a nutshell is that depending on how Apache is configured, ^upcoming/... (for example) never fires off the rewrite rule, but instead resolves to upcoming.php?/....   
  
The fix above solved it for my host (kattare.com), and now option 2 is running brilliantly.   
  
Hope that helps.

![Old](http://forums.pligg.com/images/chestnut/statusicon/post_old.gif) Today, 05:09 PM 

[![chuckroast's Avatar](http://forums.pligg.com/avatars/chuckroast.gif?dateline=1196985379)](http://forums.pligg.com/members/chuckroast.html)

(User name deleted)

Pligg Developer

Pligg Version: 9.9

Pligg Template: Arbuckle

Join Date: Jun 2006

Location: PA

Posts: 1,750 

Downloads: 43

Uploads: 15

Thanks: 130

Thanked 371 Times in 223 Posts 

MultiViews has always been an issue.. It's not listed in the requirements because most servers don't use it.. There is no way we can make a list of requirements of things "not" to use.

![Old](http://forums.pligg.com/images/chestnut/statusicon/post_old.gif) Today, 09:13 PM 

JohnLockwood ![JohnLockwood is online now](http://forums.pligg.com/images/chestnut/statusicon/user_online.gif)  

New Pligger

Pligg Version: 0.90

Join Date: May 2008

Posts: 2 

Downloads: 1

Uploads: 0

Thanks: 0

Thanked 0 Times in 0 Posts 

Who said anything about listing it in the requirements? If your code DEPENDS on the thing being turned off, and you know that, and the fix is easy, then one would think that someone with source code access who cares about having a quality product that works for as many installed users as possible would take a minute to add the 1/3 of a line of code that it takes to turn it off. If it was off to begin with, fine, no harm done, it'll still be off. If it was on to begin with, the "issue" will now be a "non-issue" because the "bug" will be "fixed".   
  


Quote:

Originally Posted by xxxxx

MultiViews has always been an issue.. It's not listed in the requirements because most servers don't use it.. There is no way we can make a list of requirements of things "not" to use.

Yeah, and there's no way I can make a list of all the bugs there are in your code. Mostly I find it fairly robust, and I'm not trying to sound ungrateful, but I stubbed my toe hard on this one, and I'm not the first one, hence the thread. I sent you the fix. Here it is again: Add -MultiViews to the Options line in .htaccess. If you'd rather show off how much more you know about Apache configuration than your users than have a product that works in as many environments as possible, leave it broken.
