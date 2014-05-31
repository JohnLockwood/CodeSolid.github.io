---
author: admin
comments: true
date: 2009-09-11 00:25:00+00:00
layout: post
slug: net-regular-expressions-example-basic-use-of-match-and-matchcollection
title: '.NET Regular Expressions Example: Basic use of Match and MatchCollection'
wordpress_id: 446
categories:
- Miscellaneous
tags:
- .NET
- Regular Expressions
---

Here is the first of what should really be several .NET Regular Expressions examples. I firmly believe that in any given environment, you’re going to end up spending most of your programming career manipulating strings one way or another. Because of this, learning .NET’s regular expression support -- unlike many of the other topics you’re likely to encounter on the certification exam, is not one of those Use It and Lose It things. It’s something that’s really useful.

`   
    
    using System;
    using System.Collections.Generic;
    using System.Text;
    using System.Text.RegularExpressions;
    
    namespace GlobalizationDrawingText
    {
        class RegularExpressionExample1
        {
            static void Main(string[] args)
            {
                // Let's set up a needle to search for and a couple of haystacks
                Regex re = new Regex("needle");
                string haystack = "hay hay hay needle hay haybeatle hay_dirt hay hay";
                string needleless /* to say */ = "hay hay hay hay hay";
    
                // Do some really simple matching
                Match match1 = re.Match(haystack);
                Match match2 = re.Match(needleless);
                Console.WriteLine("Haystack had "
                    + (match1.Success ? "a needle." : "no needles."));
                Console.WriteLine("Needleless had " 
                    + (match2.Success ? "a needle." : "no needles."));
    
                // Let's use MatchCollection to gather some hay.
                // Motivation:  what the hay.
                re = new Regex(@"hay\w*\b");
                MatchCollection mc = re.Matches(haystack);
                foreach (Match hay in mc)
                {
                    Console.WriteLine("Found:  {0}", hay.Value);
                }
    
                Console.ReadKey();
            }
        }
    }

`
