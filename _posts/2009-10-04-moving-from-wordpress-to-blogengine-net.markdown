---
author: admin
comments: true
date: 2009-10-04 03:49:00+00:00
layout: post
slug: moving-from-wordpress-to-blogengine-net
title: Moving from Wordpress to BlogEngine.Net
wordpress_id: 549
categories:
- Miscellaneous
---

I’ve been in the process of migrating the site from a Wordpress blog hosted on HostingRails.com to a BlogEngine.Net blog hosted at Godaddy.com. You can consider this a guide to setting up a BlogEngine.Net site at Godaddy.com, but first, two words of caution about doing such a migration.

 

Your first word of caution: fiction writers say that if you want to make a character interesting, present him with adversity. Unfortunately, presenting characters with adversity in real life doesn’t make us any more interesting – it only makes us write tutorials. Expect delays.

 

Your second word of caution: If you’re not writing about ASP.NET or adding a blog to an ASP.NET web site, or if you don’t need to be on an ASP.NET platform for some other good reason, what the hell are you thinking? BlogEngine.net has some cool features, but for the most part **_Wordpress works significantly better_**. There, I came out and said it.

 

**Godaddy’s Support for BlogEngine.NET**

 

[![image](http://www.particlewave.com/images/blog/MovingfromWordpresstoBlogEngine.Net_1250F/image_thumb.png)](http://www.particlewave.com/images/blog/MovingfromWordpresstoBlogEngine.Net_1250F/image.png)I wasn’t able to do what I tried in the first go around, which was to just download the BlogEngine.net 1.5 release candidate and push it up to the site. Perhaps I was tired after a long day of work, and your mileage may vary, but what I ended up doing was installing BlogEngine.Net it from Godaddy’s application control panel. From the Hosting management page, click on the “Your Applications Link” (shown at left), then select “Blog” from the left hand navbar and then BlogEngine.Net. In my case I got a compatibility warning because I was running IIS 7.0 in [Integrated Mode rather than Classic Mode](http://learn.iis.net/page.aspx/244/how-to-take-advantage-of-the-iis7-integrated-pipeline/) (who knew), so I had to click on “Purchase Compatible” then “Select compatible hosted domain” to work through what was going wrong, then return to “Content / IIS Configuration” to get it squared away. Once I did that it was pretty much smooth sailing, but at one point I either messed up the application, so I needed to make sure my blog directory (where blogengine.net was installed) was configured as an application:       
     
[![image](http://www.particlewave.com/images/blog/MovingfromWordpresstoBlogEngine.Net_1250F/image_thumb_3.png)](http://www.particlewave.com/images/blog/MovingfromWordpresstoBlogEngine.Net_1250F/image_3.png)

 

 

Clicking the edit pencil button brings up this window, and you want to make sure Set Application Root is checked:

 

    
[![image](http://www.particlewave.com/images/blog/MovingfromWordpresstoBlogEngine.Net_1250F/image_thumb_4.png)](http://www.particlewave.com/images/blog/MovingfromWordpresstoBlogEngine.Net_1250F/image_4.png)

 

**Importing Wordpress Posts to BlogEngine.Net**

 

You’re first step for this part of your journey is to get the BlogML export tool from the [Codeplex BlogML downloads page](http://blogml.codeplex.com/Release/ProjectReleases.aspx?ReleaseId=171). You want the download that says “Wordpress BlogML Export”. When you unzip this file, you’ll get a single export.php that works as a replacement for the default Wordpress export page. Backup up export.php in your <wordpress folder>/wp-admin directory, install this file, and run the Wordpress export from the admin panel to create a BlogML export.

 

Now at this point you might want to give some thought to manually reviewing this file and removing duplicate posts, which could be a very tedious process. The export file created by this tool is likely to have any number of versions of some of your posts, and when you import into BlogEngine.net, your blog is going to stutter like a Porky Pig cartoon. When you run the BlogEngine.net import tool, there’s a handy checkbox “Remove Duplicate Posts” you can check. It doesn’t do anything, but you can check it.

 

 

[![image](http://www.particlewave.com/images/blog/MovingfromWordpresstoBlogEngine.Net_1250F/image_thumb_5.png)](http://www.particlewave.com/images/blog/MovingfromWordpresstoBlogEngine.Net_1250F/image_5.png)

 

Maybe if you’re running BlogEngine.Net 1.5 this has been fixed and it does something. That would be nice.

 

Another quirky aspect of this tool is that it doesn’t give you any sort of progress indicator letting you know what’s going on, so while it’s doing the import it pretty much looks like it’s gone to lunch. I recommend pushing the two validate buttons before you start the import, that way you know everything’s set up correctly, then be prepared to wait several minutes while it does its thing.

 

When it finishes, your posts will be there, complete with Porky Pig stutters, but you’ll find your categories and tags weren’t imported. Oh well, what did I tell you at the beginning? Adversity won’t make you interesting, it will only make you write a tutorial.

 

**Converting Your Wordpress Theme**

 

Next your ready to convert your Wordpress theme. You’re probably not going to like this part, but here’s how I did it. Open up your favorite text editor and file difference tool, pick a target third party BlogEngine.net theme such as “Standard”, and code yourself a theme, there, fella. [Here’s how mine turned out](http://www.aspworkbench.com/blog/), combining the ASPWorkbench / ParticleWave theme with the Standard theme.

 

So there you have it! Delete your duplicate posts, re-enter your categories, and rock and roll.
