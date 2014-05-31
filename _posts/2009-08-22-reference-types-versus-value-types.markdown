---
author: admin
comments: true
date: 2009-08-22 19:43:00+00:00
layout: post
slug: reference-types-versus-value-types
title: Reference Types Versus Value Types
wordpress_id: 411
categories:
- Miscellaneous
---

Value types include:

 

  
  * Simple types such as numeric types (int, long, float, decimal) and bool ([Full List](http://msdn.microsoft.com/en-us/library/s1ax56ch.aspx)) 
   
  * Enumerations 
   
  * User defined structs . 
 

All value types are derived from System.ValueType.

 

As the name suggests, value types directly contain values. The values are stored on the stack, i.e.:

 

int i = 1;

 

Value types generally cannot contain null values, with the exception of [Nullable](http://www.particlewave.com/blog/2009/08/20/nullable-types-example/) types.

 

Because value types directly contain values, assigning a value type makes a copy of the value. In contrast, assigning a reference type to a new variable makes a new reference pointing to the original type.

 

``

 
    
    using System;
    
    // A really simple Reference class for testing -- 
    // wrap an int.
    class ReferenceTest
    {
        private int val;
    
        public int Val
        {
            get 
            {
                return val;
            }
            set
            {
                val = value;
            }
        }
    
        public override string ToString()
        {
            return val.ToString();
        }
    };
    
    class ValueVersusReference
    {
        static void Main()
        {
          
          ReferenceTest ref1, ref2;
          ref1 = new ReferenceTest();
          ref1.Val = 42;
          // ref2 points to same memory as ref1
          ref2 = ref1;
          // therefore changing ref1 will be reflected in ref2
          ref1.Val = 52;
          Console.WriteLine("ref1 = {0}\nref2 = {1}", ref1, ref2);
    
          int val1, val2;
          val1 = 42;
          // val2 is a copy of val1
          val2 = val1;
          // changing val1 has no effect on val2
          val1 = 52;
          Console.WriteLine("val1 = {0}\nval2 = {1}", val1, val2);
        }
    }









This code outputs: 


`
  
    
    ref1 = 52
    ref2 = 52
    val1 = 52
    val2 = 42

`
