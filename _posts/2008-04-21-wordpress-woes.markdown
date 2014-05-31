---
author: admin
comments: true
date: 2008-04-21 18:02:28+00:00
layout: post
slug: wordpress-woes
title: WordPress Woes
wordpress_id: 101
categories:
- Miscellaneous
---

I've been upgrading some of my blogs to WordPress 2.5, and the results have not been pretty. The mistakes I've been making along the way are pretty amateurish ones, but I'm professional enough to make my amateurish mistakes first on low priority blogs of my own rather than on one of my important blogs or on a client's blog.

Mainly what I've been bumping into is deleting my backups prematurely. Don't do that. Backup everything, and keep everything for a week until you find out what the upgrade broke. That way you'll keep your old "wp-content" directory, and the "upload" directory off of that, which (unless you changed the setting for this) contains all the images that Windows Live Writer uploaded automatically for you over the course of the months, ever since you went through my [Windows Live Writer tutorial](http://www.particlewave.com/internet-marketing/2007/12/20/windows-live-writer-tutorial/) video -- for example. (Ironically, the image link to that tutorial was one of the images I lost, so I just had to re-create it. Live by the sword, die by the sword, etc.).

So if you're going to do an upgrade, I recommend you start by reading Lorelle van Fossen's [Upgrade Preparation Checklist](http://www.blogherald.com/2008/03/07/wordpress-upgrade-preparation-checklist/). And then where Lorelle says Backup Everything, you might substitute: "Backup everything, and then don't be an idiot and delete it right away once things are up and running -- do extensive testing over several weeks and then delete the backups."
