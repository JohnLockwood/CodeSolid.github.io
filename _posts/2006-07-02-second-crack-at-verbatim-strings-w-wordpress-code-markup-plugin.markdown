---
author: admin
comments: true
date: 2006-07-02 00:41:03+00:00
layout: post
slug: second-crack-at-verbatim-strings-w-wordpress-code-markup-plugin
title: Using the Wordpress Code Markup Plugin
wordpress_id: 168
categories:
- Software
---

Well, I just found Bennet's excellent [Code Markup Plugin](http://www.thunderguy.com/semicolon/wordpress/code-markup-wordpress-plugin/), and with it, I was able to pretty quickly fix the post about C Sharp Verbatim Strings.

I knew some smart guy would have written that, and sure enough, he did.

With very little extra work, I was able to get some cool formatting going.



## Cool Formatting


  






    
    
    <code class="code" allow="none">
    using System;
    using System.Text;
    
    public class Hello
    {
    
    	public static void Main()
    	{
    	String verbatim =
    
    @"This is a
    verbatim string, and it can go
    on and on, using ""quote"" characters
    etc but everything else
    is not escaped, like tab characters, \t
    and so forth.";
    
    	System.Console.Write(verbatim);
    	}
    }
    </code>
    



