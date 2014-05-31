---
author: admin
comments: false
date: 2006-06-04 00:34:07+00:00
layout: post
slug: using-ant-and-xdoclet-with-jdo
title: Using Ant and XDoclet with JDO
wordpress_id: 162
categories:
- Software
---

One of the simple joys in programming is trying out new little hacks designed to prove a concept or try out something previously unfamiliar.

When I last used Java, Ant was a fairly new thing, and for some reason I had a lot of resistance to learning it.  Now, Ant and Maven are fairly ubiquitous.   Also, I've been wanting to take on some work in XDoclet, since it seems like almost everywhere you look -- whether in EJB or in "lightweight" POJO tools like hibernate or JDO, there's still some heavyweight XML configuration or metadata that needs to either get generated or maintained.   (No wonder Java 1.5 adds support for attributes -- and no wonder again that Ruby on Rails finds "favoring convention over configuration" to be a perfectly legitimate marketing strategy).

Anyhow, I do have some working code hacked together for using Ant and XDoclet and JDO -- here's the [Zip File](http://www.particlewave.com/hacks/AntXdocletJDOHacks.zip).  src.com.particlewave.apps contains the java code, and build.xml is of course the Ant script.  To run it, you'll need [ant](http://ant.apache.org/manual/index.html) and [junit](http://www.junit.org/index.htm) and you'll need to have a lib directory with the unzipped jars from the [XDoclet libraries](http://xdoclet.sourceforge.net/xdoclet/install.html) available to ant on it's classpath.  For my first hack I was just working on getting XDoclet working to create JDO configuration files.  Some good help in that direction came from JPox's excellent [XDoclet JDO example](http://www.jpox.org/docs/1_1/xdoclet.html).  Another sample I discovered later is [here](http://www.solarmetric.com/Software/Documentation/3.4.0/docs/ref_guide_integration_xdoclet.html).

The jpox sample helped me debug a nasty and obscure problem with my own code.  It turns out that -- for jdo at least, I haven't tried xdoclet in general on this -- you need to have your comments in the form:

`
/**
   *  Double asterisks on first comment line.
   *
*/
`

not

`
/*
   * See the single asterisk on first comment line.
   *
*/
`

Using a single asterisk will create a JDO file, but even if your @jdo.whatever tags are all correct, the file will be empty.  This was a bit cumbersome to track down, so I point it out in case someone else puts their leg in the same bear trap.

Enjoy!
