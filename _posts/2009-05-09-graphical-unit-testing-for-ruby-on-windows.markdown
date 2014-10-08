---
author: admin
comments: true
date: 2009-05-09 04:46:46+00:00
layout: post
slug: graphical-unit-testing-for-ruby-on-windows
title: Graphical Unit Testing for Ruby on Windows
wordpress_id: 331
categories:
- Miscellaneous
tags:
- Ruby
- Ruby on Rails
- Unit Testing
---

In my last post I discussed some of the pros and cons of Ruby on Rails versus .NET.  One of my complaints at the time was that Ruby lacks a good graphical unit test runner, because I'm a sucker for a green bar.  I also complained that running tests is cumbersome on Windows.

Tonight I decided to see if there was a green bar to be had anywhere in the Ruby world, and the answer is this:  well, yes, if you're running on Linux, and if you're running Windows, well, sort of.  The good news is that Ruby does have a graphical test runner -- in my case it lives in ruby\lib\ruby\1.8\test\unit\ui\gtk2.  You can learn how to set up [Ruby GTK on Windows here](http://ruby-gnome2.sourceforge.jp/hiki.cgi?Install+Guide+for+Windows).   Also even though the source clearly wants gtk2, and the web site warns that that's not fully supported on Windows, it does appear to be working.  I must say the treeview is a lot more primitive than NUnit's, and you don't get to see which tests passed -- though of course you get the more important information about which ones failed and where.  And most of all, you get the green bar.  Sweet, beautiful green telling you the world is OK.

And in spite of what the docs say about ActiveSupport::TestCase being derived from Object, it's not -- it's derived from ::Test::Unit::TestCase as it should be, at least in the code I'm looking at.  Like I said before:  Ruby documentation makes Jesus cry.  And I'm not even religious.
