---
author: admin
comments: true
date: 2012-07-11 19:47:20+00:00
layout: post
slug: troubleshooting-passenger-and-devise-problems-on-ruby-on-rails
title: Troubleshooting Passenger and Devise Problems on Ruby on Rails
wordpress_id: 954
categories:
- Miscellaneous
tags:
- Devise
- Passenger
- Ruby
- Ruby Logging
- Ruby on Rails
---

I spent more time than I'd like to admit yesterday on a problem with Devise that I was seeing on a Rails application that I was deploying to production.  Like most head-scratcher bugs that we as software developers track down, the solution often appears blindingly obvious -- once we find it, of course.  What happened was that my Devise routes, which had worked fine on the development server, suddenly stopped working when I moved the app to production.

At first all I saw were 404 errors.  Thinking it was a Passenger issue, my first stop was to try finding out about Passenger logging.  This led me to set up my apache configuration file for this virtual host as follows:

[cc lang="XML"]

  ServerName myservername.com
  PassengerLogLevel 3                             <-- Add this line!
  DocumentRoot /var/www/apps/prjectdirectory/public
  
	Options -Multiviews
	AllowOverride None
	Order allow,deny 
        Allow from all
  


[/cc]

Since this was an early deployment effort, I also temporarily moved config/environments/development.rb to config/environments/production.rb, thus picking up some of the development time debug settings.

By the time I'd done this, the page was at least starting to point me in the right direction, by displaying the error:

[cc]
No route matches {:controller=>“devise/home”}.
[/cc]

Well, that was a little more helpful than a 404 message, but not much.  The error output from passenger in the Apache log wasn't much help either.  Google-Fu, StackOverflow -- nothing helpful.

Meantime, one thing that kept nagging at me was the fact that I was also seeing the problem if I ran the app under WebBrick using production settings (i.e.:  "RAILS_ENV=production rails s").  

**Looking in the Wrong Place**

Often I find that a few minutes before a daunting software puzzler turns into trivial child's play, I stop looking for the solution in the "wrong" (according to hindsight) place.  In this case, the fact that this was also failing in WebBrick led me to conclude that what I should be troubleshooting in this case was not passenger -- but the Rails app itself.  Never mind that it worked in development -- it wasn't working here, in not one but two web servers.  

At this point I turned on Rails logging.  (Yay -- good answer -- and the crowd went wild!)

Adding the following line to production.rb was all I needed:

[cc lang="ruby"]
config.log_level = :debug
[/cc]

Doing that, restarting the server and navigating to mysite.com/users/sign_up, the log (in the default location of "log/production.log" showed me this call stack:

[cc]
Started GET "/users/sign_up" for 207.183.247.194 at 2012-07-10 23:56:21 +0000
Processing by Devise::RegistrationsController#new as HTML
  Rendered devise/_links.erb (0.5ms)
  Rendered devise/registrations/new.html.erb within layouts/application (4.4ms)
  Rendered layouts/_dev_nav.html.erb (0.8ms)
Completed 500 Internal Server Error in 18ms
[/cc]

At this point the log helpfully was pointing me to just the page where the error was happening.  Looking at the partial in "views/layouts/_dev_nav.html.erb", the advice of the articles I'd found about the problem along the way now made sense:  this was a scoping problem in a link_to call.  Actually there were a few such calls, but the solution was the same in each case:  Add a "/" to the beginning of the controller "name".  In other words, change this:

[cc lang="ruby"]


<%= link_to "Home", :controller => "home", :action => "index" %>


[/cc]

To this:

[cc lang="ruby"]


<%= link_to "Home", :controller => "/home", :action => "index" %>


[/cc]

This makes sure that "home" isn't considered to be a Devise controller, even in the context of rendering a Devise view.

In simpler terms, the best way to debug Passenger and Devise problems on Rails is not to treat them as Passenger or Devise problems.   Underneath whatever's broken is a Rails application, and that Rails application has excellent logging available.
