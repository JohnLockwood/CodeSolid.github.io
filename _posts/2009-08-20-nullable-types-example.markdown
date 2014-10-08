---
author: admin
comments: true
date: 2009-08-20 20:49:00+00:00
layout: post
slug: nullable-types-example
title: Nullable Types Example
wordpress_id: 403
categories:
- Miscellaneous
---

The memory for reference types is allocated on the system heap, so it makes sense that reference variables can be null. Value types, in contrast, cannot be assigned a "null” value by default. Still, there are times when you may want to declare a int (for example) and allow it to be set null – one obvious use is in reading and writing data from a database. Enter the Nullable type. Here’s a brief sample that shows how these types work.

 

``

 
    
    using System;
    
    class NullableExample
    {
        static void Main()
        {
    	// A basic value type can be assigned to, but cannot be null
    	int normal;
    	normal = 1;
    	
    	// The line below if uncommented produces:
    	// error CS0037: Cannot convert null to 'int' because 
    	// it is a value type
    
    	// normal = null;
    
    	// Declare a Nullable value
    	int? nullable = null;
    
    	// The type? syntax used above is a synonym for Nullable<type>
    	// So the following is equivalent
    	System.Nullable<system.int32> nullable2 = 5;
    
    	// Display something -- the null for nullable displays nothing
            System.Console.WriteLine(
    		"normal = {0}, nullable = {1}, nullable2 = {2}", 
    		normal, nullable, nullable2);
    
    	// HasValue returns true if the Nullable type has a value
    	Console.WriteLine(
    		"nullable.HasValue = {0}, nullable2.HasValue = {1}",
    		nullable.HasValue, nullable2.HasValue);
    
    	// Value returns the value or throws an exception if there is no value
    	try
    	{
    		Console.WriteLine("Doesn't display:  {0}", nullable.Value);
    	}
    	catch (System.InvalidOperationException e)
    	{
    		Console.WriteLine("Exception caught: " + e);
    	}	
    
        }
    }


`
  



  

**Output**



  



  
    
    normal = 1, nullable = , nullable2 = 5
    nullable.HasValue = False, nullable2.HasValue = True
    Exception caught: System.InvalidOperationException: Nullable object must have a value.
       at System.ThrowHelper.ThrowInvalidOperationException(ExceptionResource resource)
       at NullableExample.Main()


`
