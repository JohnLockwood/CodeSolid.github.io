---
author: admin
comments: true
date: 2009-09-26 19:42:00+00:00
layout: post
slug: a-quick-driveinfo-example
title: A Quick DriveInfo Example
wordpress_id: 461
categories:
- Miscellaneous
---

Too easy.

`   
    
    using System;
    using System.IO;
    
    public class DriveInfoExample
    {
        public static void Main(string [] args)
        {
            DriveInfo [] dilist = DriveInfo.GetDrives();
            foreach (DriveInfo di in dilist)
            {
                Console.WriteLine("Drive:  {0}, type: {1}",
                di.Name, di.DriveType);
            }
            Console.ReadKey();
        }
    }

`
