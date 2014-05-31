---
author: admin
comments: true
date: 2012-07-14 13:36:07+00:00
layout: post
slug: using-factorygirl-with-the-rails-console
title: Using FactoryGirl with the Rails Console
wordpress_id: 1021
categories:
- Ruby on Rails
tags:
- FactoryGirl
- Rails Console Tips
- Ruby on Rails
- Testing
---

I started doing some development work on the Rais blogging engine, [Typo](http://fdv.github.com/typo), and wanted to use some of the helper methods I found in their factories within a test console.

Here are the steps I had to go through:

[cc]rails c test[/cc]

The inside the console:

[cc lang="ruby"]
Factory.load('spec/factories.rb')
[/cc]

Then you can use your helper methods or load factories as you normally would:

[cc lang="ruby"]

# Use a helper method (if you have some)
my_object = my_helper_method()

# Load a factory normally
user = FactoryGirl.create(:user)

[/cc]

Admittedly if this were harder I would have gotten a longer article out of it, but you have to take the good with the bad.
