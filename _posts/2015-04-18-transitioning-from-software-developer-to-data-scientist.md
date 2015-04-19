---
author: John Lockwood
layout: post
title: "Transitioning from Software Developer to Data Scientist" 
excerpt: "Or how I learned to stop worrying and started programming in RRRR, because it made me sound like a pirate."
categories:
- Miscellaneous

---

A little over a month ago, I reported that I was making my first explorations of transitioning out of software development (per se) and into a role as a data scientist.  At the time I was pretty much just kicking the tires a bit, but since then I've begun to cultivate my interest more seriously.  It's been a pretty whirlwind month of learning and trying out new things, and here are some highlights:

* At my current day job, I was able to take advantage of an "Innovation and Planning Sprint" to scratch the surface of Hadoop by writing my first MapReduce job. The goal was to dig into some of our splunk logs and report on statistics of certain error rates.  Although I was able to get a Hadoop standalone instance working with a MapReduce job against a pared-down data set, it turned out that a much cleaner and more fruitful approach in this case was to first filter the splunk data to reduce the output volume, and then to treat it as a "small data" project using IPython and Pandas.

* In the meantime, toward the end of March I happened across the Coursera [Data Science Specialization](https://www.coursera.org/specialization/jhudatascience/1?utm_medium=listingPage) program, which focuses on Data Science using R.
I decided to take the first two courses concurrently, since really the material and final project for the first course wasn't teaching me anything new, I was just filling a pre-requisite.  The second course, Roger Peng's course in [R Programming](https://www.coursera.org/course/rprog), has been a lot of fun.  I'm finding it quite straightforward but not trivially so, given my background as a developer.  However, many of the reviews I've read from entry level programmers have pointed out how challenging it is for that audience, and given that it's taken me some modest time and effort to work through the problems myself, I can certainly see their point.  Meantime I've been hacking quite a bit on my own both on the course material and on other R resources.  Most of that's unstructured, but here are some [learning exercises on tapply](/tapply) that beginners might find useful.  I'm not sure if I want to spend the time to work several such examples into a consistent R resource -- it's quite likely that I won't given what else I have going on.  We're on week three of the course and so far I'm acing it, but stay tuned.

* Once I signed up for the course, I discovered something I really hadn't expected to find, a [Sacramento Data Science Meetup](http://www.meetup.com/Sacramento-MOOCs/events/221884366/) consisting of other folks who were also taking the course.  We met in Roseville and are currently planning to get together every couple of weeks, and it seems like a smart and congenial group of folks.

So anyway, that's it for now.  This statistical / data analysis angle on programming is so far turning out to be quite entertaining, and we'll see how it all works out.
