---
author: admin
comments: true
date: 2009-06-27 17:59:41+00:00
layout: post
slug: using-ruby-fixtures-at-the-console
title: Using Ruby Fixtures at the Console
wordpress_id: 344
categories:
- Ruby on Rails
tags:
- Testing
---

At the risk of showing how (not) far I am along the learning curve given my Month of Sundays Rails project, here's a great beginner sort of   [rails tip](http://www.railsforum.com/viewtopic.php?id=11479) I found on how to load fixtures into the console.

The meat of it is this, which I like because it simultaneously shows you how to set up your environment correctly for the console, and for rake.  Not a bad little tutorial for two lines worth of stuff:
`
rake db:fixtures:load RAILS_ENV=test
script/console test
`
