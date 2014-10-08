---
author: admin
comments: true
date: 2012-02-13 01:50:35+00:00
layout: post
slug: this-week-in-my-unhired-rails-developer-journey
title: This Week in my Unhired Rails Developer Journey
wordpress_id: 827
categories:
- Ruby on Rails
tags:
- Ruby on Rails
- Software
---

I've been getting a lot of good Rails stuff done this week, mixed in with only a little bit of career train wreck.

I'm not one of your cool kids.  I don't own a Mac.  I make mistakes.  Move on.



	
  * I've decided that not only was SlickEdit not worth the upgrade as a Rails environment two years ago, it's still not.  Although in fairness at least they're now doing something for their legacy customers in the way of discounting that they weren't doing when I wrote that other article, in terms of Rails they seem to be pretty much have about the same support as they did two years ago, which is to say, not much.   Making this decision meant I had to make some serious new finger-memory investment in gvim, which I've started to do with the help of the excellent book, [Learning Vi and Vim](http://iccf-holland.org/click5.html).  I've been using gvim in a naive way for years -- it's time to become an expert.

	
  * Adding ruby support to Vim also means getting the right vim plugins.  This is still a work in progress, but I'm doing pretty well already with the [rails plugin](http://www.vim.org/scripts/script.php?script_id=1567) only, so maybe it isn't really a work in progress.

	
  * I jumpstarted my current aprojectx project (that would be:  "a" so it shows up first on Git, and "project x" so I don't have to change its name if I decide it needs to turn into something other than [what it's trying to be lately)](http://www.youtube.com/watch?v=8gFCW3PHBws). Before I met the OpenGovernment folks I had a project started which to date only really had a preliminary theme and Devise set up, which is fairly trivial to do given Devise's clean design.  So that became the basis for the current project.

	
  * Aprojectx is of course checked in to Github as a private project, meaning that not only have I not yet told you what aprojectx is trying to be lately, I have it in a secret hiding place.  So as of this moment it's on [Double Secret Probation](http://www.youtube.com/watch?v=Y0cF2piwjYQ).  Thank God.

	
  * The career train wreck I had earlier in the week did have one advantage, in that it allowed me to ramp up a little on Rspec, Rake, and even a bit into a sort of hand-coded fixture support.  The takeaway for now is that I got rake installed and ready so I can do Behavior Driven Double Secret Probation.  You've no doubt heard of this, especially if unlike me you are are a mac-owning cool kid.

	
  * I wanted to have Rspec in place for the next phase of development, which was to swap out the current database implementation for MongoDB, which means getting rid of ActiveRecord, the first task so far that might take more than about fifteen minutes of easy work, not because it's hard in itself, but because I may have to write some Mongo-based Devise models.  Since that may be of general interest, I might release that as open source, if seven people haven't already done it.

	
  * But alas, before I could do that, I had a bit of a deployment interlude.  I did a trial deployment to a sample EngineYard account; that all went pretty well until I arrived at an error which pointed to the fact that I'd nestled my rails app a level in from the root because conceptually AProjectX also has a non-Rails component to it.  EngineYard doesn't like having your rails app not be the root Github application.  So I think I'll fix that now, now that my gorgeous wife and I are back from getting my bicycle fixed and looking at bikes for her as well.


And with that, it's Monday.
