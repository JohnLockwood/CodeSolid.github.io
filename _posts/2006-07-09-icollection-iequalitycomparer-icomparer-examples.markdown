---
author: admin
comments: false
date: 2006-07-09 01:02:52+00:00
layout: post
slug: icollection-iequalitycomparer-icomparer-examples
title: ICollection, IEqualityComparer, IComparable Examples
wordpress_id: 171
categories:
- Miscellaneous
---

`
    
    
    using System;
    using System.Collections;
    using System.Collections.Generic;
    using System.Text;
    using NUnit.Framework;
    using NUnit.Framework.Tests;
    using System.Threading;
    
    namespace ExamSamples.Bullet1
    {
        [TestFixture]
        public class NewBullet1Tests
        {
            [Test]
            public void TestICollection()
            {
                ArrayList al = new ArrayList();
                al.Add("Something");
                al.Add("Something Else");
    
                // ArrayList implements ICollection
                ICollection col = al as ICollection;
                Assert.IsNotNull(col);
    
                // Iterate in thread safe way using
    	    // IColleciton.SyncRoot
                // -- just an example in our case
                try
                {
                    Monitor.Enter(col.SyncRoot);
                    foreach (String item in al)
                        Assert.AreNotEqual(item, String.Empty);
                    // This failed.  ArrayList is not thread safe
                    // Assert.IsTrue(col.IsSynchronized);
                }
                finally
                {
                    Monitor.Exit(col.SyncRoot);
                }
    
                Assert.AreEqual(col.Count, 2);
                Assert.IsFalse(col.IsSynchronized);
            }
    
            [Test]
            public void TestIComparable()
            {
                ArrayList al = new ArrayList();
                al.Add("Something");
                al.Add("Something Else");
    
                // No, IComparer is used on objects in collections,
                // not collections
                IComparer ic = al as IComparer;
                Assert.IsNull(ic);
    
                IComparable icomparable = al as IComparable;
                Assert.IsNull(icomparable);
    
                // Sort some fish (see fish implementation below)
                SortedList sl = new SortedList();
    
                // Insert in nonsorted order
                sl.Add(new Fish("Fancy Goldfish",
                    Fish.Finsize.medium), null);
                sl.Add(new Fish("Angelfish",
                    Fish.Finsize.large), null);
                sl.Add(new Fish("Glassfish",
                    Fish.Finsize.small), null);
    
                // Keys are now in sorted order
                Fish f = (Fish)sl.GetKey(0);
                Assert.IsTrue(f.FinSize == Fish.Finsize.small);
    
                f = (Fish)sl.GetKey(2);
                Assert.IsTrue(f.FinSize == Fish.Finsize.large);
            }
    
            [Test]
            public void TestIEqualityComparer()
            {
                Cookie c1 = new Cookie();
    
                Cookie c2 = new Cookie();
                Cookie c3 = new Cookie();
    
                c1.Name = "Keebler";
                c1.HasChips = true;
    
                c2.Name = "Swanson";
                c2.HasChips = true;
    
                c3.Name = "Keebler";
                c3.HasChips = false;
    
                // We've overriden Object.Equals as well,
                // so this succceeds
                Assert.AreEqual(c1, c3);
    
                // Here's the IEqualityComparer version
                Assert.IsTrue(c1.Equals(c1, c3));
            }
        }
    
        public class Fish : IComparer, IComparable
        {
            public enum Finsize { small, medium, large };
    
            private String _name;
    
            private Finsize _finsize;
    
            public Finsize FinSize
            {
                get
                {
                    return _finsize;
                }
            }
    
    
            public Fish(String name, Finsize size)
            {
                _name = name;
                _finsize = size;
            }
    
    
            #region IComparer Members
            public int Compare(object x, object y)
            {
                if (x == null ||
    		((Fish)x)._finsize < ((Fish)y)._finsize)
                    return -1;
    
                if (y == null ||
    		((Fish)y)._finsize < ((Fish)x)._finsize)
                    return 1;
    
                return 0;
            }
            #endregion
    
            #region IComparable Members
    
            public int CompareTo(object obj)
            {
                // This sorts in ascending order
    	    // -- small fins first in our case.
                return Compare(this, obj);
            }
    
            #endregion
        }
    
        public class Cookie : IEqualityComparer
        {
            private String _name;
            private Boolean _hasChocolateChips;
    
            public String Name
            {
                get
                {
                    return _name;
                }
                set
                {
                    _name = value;
                }
            }
    
            public Boolean HasChips
            {
                get
                {
                    return _hasChocolateChips;
                }
                set
                {
                    _hasChocolateChips = value;
                }
            }
    
    
            // Needed for our test to pass. Probably not for a
    	// hashtable.
            // Object.Equals here
            public override bool Equals(object obj)
            {
                return Equals(this, obj);
            }
    
            // also therefore need to override Object.GetHashCode
            public override int GetHashCode()
            {
                return GetHashCode(this);
            }
    
            #region IEqualityComparer Members
            public new bool Equals(Object x, Object y)
            {
                Cookie cx = x as Cookie;
                Cookie cy = y as Cookie;
                return (GetHashCode(cx) == cy.GetHashCode(cy));
            }
    
    
            public int GetHashCode(object obj)
            {
                Cookie c = obj as Cookie;
                // Easy -- defer to one of our strings
    	    // to hash by name of cookie.
                return (c.Name.GetHashCode());
            }
            #endregion
        }
    }
    

`

