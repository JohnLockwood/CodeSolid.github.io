---
author: admin
comments: true
date: 2009-09-29 02:50:00+00:00
layout: post
slug: copying-moving-deleting-creating-and-reading-files-with-or-without-deep-love-poetry
title: Copying, Moving, Deleting, Creating, and Reading Files (With Or Without Deep
  Love Poetry)
wordpress_id: 442
categories:
- Miscellaneous
---

`
    
    
    using System;
    using System.IO;
    
    class FileAndFileInfoExample
    {
        static void Main(string[] args)
        {
            // Show some mechanisms for creating, moving, 
    	// copying and deleting files
            try
            {
                // Get a couple of filenames
                string file1 = @"C:\FileInfoExample_Sample.txt";
                string file2 = @"C:\FileInfoExample_Sample2.txt";
    
                // Create a text file.
                TextWriter tw = File.CreateText(file1);
    
                // Write a moving love poem into it.
                tw.WriteLine("I had a girl.");
                tw.WriteLine("Her name was Sue.");
                tw.WriteLine("Loved her dear,");
                tw.WriteLine("Loved her true.");
                tw.WriteLine("Till one day");
                tw.WriteLine("She broke my heart.");
                tw.WriteLine("Took a shotgun -- ");
                tw.WriteLine("Blew me apart.");
                
                // Sealed with a kiss for Sue
                tw.Close();
    
                // Copy our beautiful creation
                File.Copy(file1, file2);
    
                // Delete the original
                File.Delete(file1);
    
                // Move the new file back using File
                File.Move(file2, file1);
    
                // Using FileInfo instead this time...
                FileInfo fi = new FileInfo(file1);
    
                // Copy it again.
                fi.CopyTo(file2);
    
                // Delete the original again
                fi.Delete();
    
                // Get file2
                fi = new FileInfo(file2);
    
                // Move it back to one.
                fi.MoveTo(file1);
    
                // Show our poem to the world
                string [] lovePoem = File.ReadAllLines(file1);
                foreach (string profoundLine in lovePoem)
                {
                    Console.WriteLine(profoundLine);
                }
    
                // Make sure no jealous poets steal our work
                fi = new FileInfo(file1);
                fi.Delete();
            }
            catch (Exception e)
            {
                Console.WriteLine(e.Message);
            }
    
            Console.ReadKey();
        }
    }
    
    

`
