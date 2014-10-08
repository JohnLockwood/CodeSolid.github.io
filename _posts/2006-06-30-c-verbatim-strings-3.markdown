---
author: admin
comments: false
date: 2006-06-30 03:44:12+00:00
layout: post
slug: c-verbatim-strings-3
title: C# Verbatim Strings
wordpress_id: 166
categories:
- Software
---

Just doing a bit of hacking with the C Sharp mono compiler this evening.  (I'm waiting for Visual Studio to ship -- what else?).  Meantime I may have found my first error in Donis Marshall's [Programming Microsoft Visual C# 2005:  The Language](http://www.amazon.com/gp/redirect.html?link_code=ur2&tag=sacramentorea-20&camp=1789&creative=9325&location=http%3A%2F%2Fwww.amazon.com%2Fgp%2Fproduct%2F0735621810%2Fqid%3D1151639146%2Fsr%3D2-1%2Fref%3Dpd_bbs_b_2_1%3Fs%3Dbooks%26v%3Dglance%26n%3D283155) -- which doesn't bode incredibly well given that I'm only on page 23 or so.

Looking at verbatim strings, Marshall neglects to point out that to escape a string inside a verbatim string you use double quotes, not backslash quote.

Here's a braindead example of the right way to do it.  Mono users use mcs VerbatimString.cs to compile.


`
using System;
using System.Text;

public class VerbatimStrings
{
	public static void Main()
	{
	String verbatim =

@"This is a
verbatim string, and it can go
on and on, using ""quote"" characters
etc but everything else
is not escaped, like tab characters, \t
and so forth."

	System.Console.Write(verbatim);
	}
}`

You know, it took about three times as long to write the blog post as it did to figure it out to begin with.  I love trying to write code in HTML.  There should be a wordpress plugin or the like for that...
