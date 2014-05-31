---
author: admin
comments: true
date: 2009-08-24 20:03:00+00:00
layout: post
slug: boxing-and-unboxing-in-non-generic-collections-versus-generic-collections
title: Boxing and Unboxing in Non-Generic Collections versus Generic Collections
wordpress_id: 412
categories:
- Miscellaneous
---

Note that the example below has no output.

 `   
    
    /* 
     * This example shows a non-generic list with an array of objects, 
     * and the boxing and unboxing that are needed to add and retrieve integers
     * from such an array.  It also shows a generic list and how this simplifies
     * access to a value type such as int.
     */
    
    using System;
    using System.Collections;
    using System.Collections.Generic;
    using System.Text;
    
    class ListsExample
    {
        public void CreateNonGenericList()
        {
            ArrayList list = new ArrayList();
    
            // Boxing
            Object o1 = (Object)1;
    
            // Boxing again
            Object o2 = 2.ToString();
            
            list.Add(o1);
            list.Add(o2);
    
            // Add myself
            list.Add(this); 
    
            // Have to unbox (or cast) to get explicit type out
            // Run time exception if get it wrong.
    
            // Unbox
            int i = (int)list[0];
    
            // Unbox
            string s = (string)list[1];
    
            // Cast
            ListsExample myclass = (ListsExample) list[2];
        }
    
        public void CreateGenericList()
        {
            List<int> list = new List<int>(10);
            for (int i = 0; i < 10; i++)
            {
                list.Add(i);
            }
    
            // Can access any variable directly as an int without casting
            int four = list[5];
        }
    
        static void Main(string[] args)
        {
            ListsExample lists = new ListsExample();
            lists.CreateGenericList();
            lists.CreateNonGenericList();      
        }
    }

`
