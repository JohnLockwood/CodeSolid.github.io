---
author: admin
comments: true
date: 2009-10-08 00:52:00+00:00
layout: post
slug: reading-and-writing-text-files-and-binary-files-in-net
title: Reading and Writing Text Files and Binary Files in .NET
wordpress_id: 464
categories:
- Miscellaneous
---

This short sample shows different ways to read and write binary files and text files. In particular, note the two different techniques for reading and writing text files.

`   
    
    using System;
    using System.Collections.Generic;
    using System.Text;
    using System.IO;
    
    // This sample demonstrates simple techniques for reading  and writing text and binary files
    
    class SimpleFileIO
    {
        static void Main(string[] args)
        {
            try
            {
                SimpleFileIO sfio = new SimpleFileIO();
    
                // Step into each of these functions to see how to read / write 
                // different types of files.
                sfio.TextFilesOneWay();
                sfio.TextFilesAnotherWay();
                sfio.BinaryFiles();
            }
            catch (Exception e)
            {
                Console.WriteLine(e.Message + "\n" + e.StackTrace);
            }
            finally
            {
                Console.WriteLine("\nPress any key to continue...");
                Console.ReadKey();
            }
        }
    
        void TextFilesOneWay()
        {
            // Use the constructor of the StreamWriter and StreamReader 
            // classes to use a read and write text files
            string filename = "SimpleFileIO_hello.txt";
            StreamWriter sw = new StreamWriter(filename);
            sw.WriteLine("Hello world");
            sw.Close();
    
    
            // Same technique to read it back
            StreamReader sr = new StreamReader(filename);
            string contents = sr.ReadLine();
            sr.Close();
    
    
            // Prove it
            Console.WriteLine("\nUsing StreamWriter / StreamReader"); 
            Console.WriteLine("Read and wrote file: {0} with contents:\n{1}",
                filename, contents);
    
    
            // Clean up
            File.Delete(filename);
        }
    
        void TextFilesAnotherWay()
        {
            string filename = "SimpleFileIO_hello.txt";
    
            // Another way to read and write text files 
            // uses the File class's static methds to get a TextWriter / TextReader
    
            // Writing -- use CreateText to get a TextWriter on a new text file 
            TextWriter tw = File.CreateText(filename);
            tw.WriteLine("Hello world");
            tw.Close();
    
    
            // Use OpenText to get a TextReader
            TextReader tr = File.OpenText(filename);
            string contents = tr.ReadLine();
            tr.Close();
    
    
            // Prove it
            Console.WriteLine("\nUsing TextWriter / TextReader");
            Console.WriteLine("Read and wrote file: {0} with contents:\n{1}",
                filename, contents);
    
    
            // Clean up
            File.Delete(filename);
        }
    
        void BinaryFiles()
        {
            // Write binary files using BinaryWriter / Binary Reader
            string filename = "SimpleFileIO_binary.bin";
    
    
            // Use BinaryWriter constructor on a file
            BinaryWriter bw = new BinaryWriter(
                File.Open(filename, FileMode.Create));
            // Write some bytes (for example)
            for (byte i = byte.MinValue; i < byte.MaxValue; i++)
            {
                bw.Write(i);
            }
            bw.Close();
    
    
            // Read them back, with a simple test
            BinaryReader br = new BinaryReader(
                File.Open(filename, FileMode.Open));
            bool readFailed = false;
            for (byte i = byte.MinValue; i < byte.MaxValue ; i++)
            {
                if (br.ReadByte() != i)
                    readFailed = true;
            }
            br.Close();
    
    
            // Prove it
            Console.WriteLine("\nWrite and read of binay file {0}:  {1}",
                filename,
                readFailed ? "failed" : "succeded");
    
            // Clean up
            File.Delete(filename);
        }
    }

`
