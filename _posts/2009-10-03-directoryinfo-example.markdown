---
author: admin
comments: true
date: 2009-10-03 19:46:00+00:00
layout: post
slug: directoryinfo-example
title: DirectoryInfo Example
wordpress_id: 462
categories:
- Miscellaneous
---

In the spirit of our DriveInfo example, which was a little slip of a thing, here's a DriveInfo example. 

`
    
    
    using System;
    using System.Collections.Generic;
    using System.IO;
    
    class DirectoryInfoExample
    {
        static void Main(string[] args)
        {
            string dir = @"C:\";
            Console.WriteLine("Listing directories underneath {0}", dir);
            DirectoryInfo diList = new DirectoryInfo(dir);
            foreach (DirectoryInfo di in diList.GetDirectories())
            {
                Console.WriteLine("{0}", di.FullName);
            }
            Console.WriteLine("Listing files in {0}", dir);
    
            foreach (FileInfo fi in diList.GetFiles())
            {
                Console.WriteLine("File name:  {0}\nCreated:  {1},\nLength:   {2} bytes", 
                     fi.Name, fi.CreationTime, fi.Length);
            }
            Console.ReadKey();     
        }
    }
    

`
