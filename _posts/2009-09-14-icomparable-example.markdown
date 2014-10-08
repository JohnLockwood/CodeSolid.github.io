---
author: admin
comments: true
date: 2009-09-14 19:53:00+00:00
layout: post
slug: icomparable-example
title: IComparable Example
wordpress_id: 509
categories:
- Miscellaneous
---

Here's a quick IComparable example. The memorization feat for this interface is that CompareTo returns <0 when “this” is less than the object passed to it. The rest follows easily: return 0 when both objects are equal and > 0 when this instance greater than obj passed to CompareTo. Remember, this is self centered so everything’s in terms of this.

`   
    
    using System;
    using System.Collections;
    using System.Collections.Generic;
    using System.Text;
    
    // A simple class that implements IComparable 
    // interface to sort by title
    class Book : IComparable
    {  
        private string m_title;
        private string m_author;
    
        public Book(string _title, string _author)
        {
            m_title = _title;
            m_author = _author;
        }
        
        public string Title
        {
            get { return m_title; }
            set { m_title = value; }
        }
    
        public string Author
        {
            get { return m_author; }
            set { m_author = value; }
        }
      
        // We let Visual Studio do the stub implementation, 
        // then we filled it out. 
        #region IComparable Members
    
        public int CompareTo(object obj)
        {
    	// This instance comes before null.
            if (obj == null)
                return 1;
    
            else if (!(obj is Book))
            {
                throw new ArgumentException("Object passed to Book::CompareTo is not a Book");
            }
            else
            {
                return Title.CompareTo(((Book)obj).Title);
            }
        }
    
        public override string ToString()
        {
            return Title + " by " + Author;
        }
    
        #endregion
    }
    
    class IComparableTest
    {
        static void Main(string[] args)
        {
            SortedList list = new SortedList();
     
            // Add in unsorted order.  Sorted list will sort by key (Book) using
            // the CompareTo method we implemented
            list.Add(new Book("The thrill of victory", "Some Lucky Winning Guy"), 0);
            list.Add(new Book("A Loser Speaks", "Melvin Nobody"), 1);
            list.Add(new Book("Zymurgy Unleashed", "Aaron Firstman"), 2);
     
            for (int i = 0; i < list.Count; i++)
            {            
                Console.WriteLine("Entered at:  {0}, a Book:  {1}", list.GetByIndex(i), list.GetKey(i));
            }
    
            // Uncomment this to see if running in debugger:
            // Console.ReadKey();
        }
    }

`
