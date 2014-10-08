---
author: admin
comments: true
date: 2012-11-10 18:20:06+00:00
layout: post
slug: translating-application-html-erb-to-haml
title: Translating Application.html.erb to Haml
wordpress_id: 1161
categories:
- Miscellaneous
tags:
- HAML
- Ruby Localization
---

As part of my [latest pet Rails](http://johnlockwood.com/2012/11/07/learnapp/) project, I'm sharpening up my nascent HAML skills a bit.  

If you're thinking about learning HAML, let me assure you a little:  it's not that hard.  Compared to programming generally, Rails in particular, HTML, and all the other stuff you learned before it occurred to you to learn HAML, HAML is pretty simple stuff.  The best way to learn it is to simply dive in as the tutorial suggests and translate a file or two from your current project.  That will teach you the basics.  When you've done a file or two, you're ready to take on translating a layout such as application.html.erb to haml.  This is a bit more of a challenge -- but still not a big deal, really.

I posted a couple of code samples that show the before and after to Gist.  The before of course is [application.html.erb](https://gist.github.com/4051866).  The translated file also has a couple of modifications, because as I was working on this I was also internationalizing the site, but that's OK, it's good for a challenge.  Here is [application.html.haml](https://gist.github.com/4051775).

Going through the HAML, first we have a doctype declaration:

[cc lang="ruby"]
!!!
[/cc]

This will turn into an HTML5 doctype declaration:

[cc lang="html"]

[/cc]

Along with the [HAML documetation](http://haml.info/docs/yardoc/file.REFERENCE.html), the head section of the layout is a sort of mini-haml lesson all by itself, so let's take a look at that:

[cc lang="ruby"]
%head
  %title= content_for?(:title) ? yield(:title) : I18n.t("site_title")
  %meta{:name => "description", :content=> "#{ content_for?(:description) ? yield(:description) : (I18n.t "site_description")}"}
  %meta{:charset => "utf-8"}
  %meta{:author => "John Lockwood"}{:cheese => "limburger, :friends => "moe larry"}'
  =stylesheet_link_tag    "application", :media => "all"
  =javascript_include_tag "application"
  =csrf_meta_tags
  = yield(:head)
[/cc]

The head tag and the title tag show the basic HAML conventions of using %tagname for tags, and indentation for nesting one tag within another.  After title we have an equals sign, =, to interpret the rest of the line as ruby code.  We're setting up a flexible content_for block so we can set the title on a per-page basis, and we've modified the original line to use an internationalized string:  I18n.t("site_title").  Anyway, the main point here is that we can have whatever ruby expression we want after the = and it will be evaluated and output to the page.

When we get to the meta tags, things start to get a bit interesting.  To get HTML attributes, HAML uses a syntax that looks like Ruby hash attributes.

Thus the HAML:

[cc lang="ruby]
%curly_request{:cheese => "limburger, :friends => "moe larry"}
[/cc]

renders in HTML as:

[cc lang="html]

...

[/cc]

So far so good, but what if you need to build your HTML attributes using Ruby code?  Not to worry, HAML provides for Ruby interpolation using a syntax akin to string interpolation in Ruby.  For example:

[cc lang="ruby"]
%meta{:name => "description", :content=> "#{ content_for?(:description) ? yield(:description) : (I18n.t "site_description")}"}
[/cc]

In the code above, we're creating a meta description tag, and our content attribute for this tag is the interpolated ruby expression:

[cc lang="ruby"]
content_for?(:description) ? yield(:description) : (I18n.t "site_description")
[/cc]

So we've made our description flexible, and we've internationalized our default site description, so your site can be Google optimized for English speakers as "The best site for learning about eating cheese", while your Guatemalan visitors will see "El mejor sitio para aprender comer queso."

International haml and cheese!  How cool is that?

I'm tempted to leave it there on a tasty note, but let me just steer you clear of one little HAML gotcha to avoid.  Make sure when specifying attributes to not add any space before your attribute list.  The first whitespace it sees signals the content of the tag, so you need:

[cc lang="ruby"]
%meta{:name => "description"}
[/cc]

not

[cc lang="ruby"]
%meta {:name => "description"}
[/cc]

The second tag will evaluate to:

[cc lang="html"]
{:name => "description"}
[/cc]

Definitely not what you want.  What will the Guatemalans think of you then?

