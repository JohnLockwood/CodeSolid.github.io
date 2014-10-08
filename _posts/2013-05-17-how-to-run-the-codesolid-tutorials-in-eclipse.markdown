---
author: admin
comments: true
date: 2013-05-17 20:04:09+00:00
layout: post
slug: how-to-run-the-codesolid-tutorials-in-eclipse
title: How to Run the CodeSolid Tutorials in Eclipse
wordpress_id: 1548
categories:
- Tutorials
tags:
- Eclipse
- Indigo
- Java
- Spring
- Spring Tool Suite
- Tutorials
---

If you've taken a look at the [Github Repository](https://github.com/CodeSolid/tutorials) for the CodeSolid tutorials, you may have noticed that the project files all use IntelliJ Idea.  I use the Ultimate Edition (currently Version 12) for most of my development, but recently I promised to look into using these project files in Eclipse.  I had a feeling it would work since I have sourced the Maven project -- i.e., the POM files, those named pom.xml in the root of each tutorial -- but being the good test infected developer that I am, I try not to take "that should work" for granted.

Well, the good news in this case is that not only should it work, but it actually does.  I have tested it out on the Spring Tool Suite version 3.2, as well as on a more vanilla version of Eclipse (Indigo -- version 3.7).  Because we're importing Maven files, Maven is resolving the dependencies -- meaning the Spring Tutorial works even without the Spring Tool Suite.  (How cool is that -- it's magic!)  To run the projects, start by right clicking on any empty space in the Package Explorer window, as shown below.

![EclipseImport](/images/tutorials_in_eclipse/EclipseImport.jpg)

From the menu, select Import.  In the Import dialog box, you want to select Maven, then Existing Maven Project, as shown below:

![EclipseImport2](/images/tutorials_in_eclipse/EclipseImport2.png)

If you then navigate to the root of the tutorials directory, you can select which tutorials you want to import.  I'm writing this article when there are only two tutorials available -- you'll likely see more projects available.  Select the project(s) you want, click finish, and you're done!  You can now run the tutorials in Eclipse.  Enjoy!

![EclipseImport3](/images/tutorials_in_eclipse/EclipseImport3.png)