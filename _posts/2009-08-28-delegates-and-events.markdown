---
author: admin
comments: true
date: 2009-08-28 17:08:00+00:00
layout: post
slug: delegates-and-events
title: Delegates and Events
wordpress_id: 426
categories:
- Miscellaneous
---

In .NET, delegates are classes that implement type safe function pointers.  Like all function pointers, they can be used whereever you need dynamic execution of code.  One use of delegates that is ubiquitous in .NET is as a mechanism for handling events.  Both ASP.NET Webforms and the Windows development Winforms classes uses such delegates as event handlers.  So does our little program below, which shows how to translate keypresses into events, then handle certain events as they're raised.

As a side note, this was the first sample I developed using Visual Studio 2010 beta (though it compiles and runs fine under 2005 as well).

 
    
    <code>
    using System;
    
    /*
     *  Since we'll be raising events, we create a simple
     *  EventArgs class that knows about System.ConsoleModifiers 
     *  (i.e. keyboard states like CTRL, ALT, SHIFT)
     */
    public class KeyEventArgs : EventArgs
    {
        private ConsoleModifiers _cm;
    
        public KeyEventArgs(ConsoleModifiers cm)
        {
            _cm = cm;
        }
        public ConsoleModifiers Modifiers
        {
            get { return _cm; }
        }
    }
    
    /*
     * Define an event delegate.  Our publisher defines instances
     * of this delegate.  Our subscribers add event handlers to the 
     * publisher's delegates for the events they're interested in.
     */
    public delegate void KeyEventDelegate(object obj, KeyEventArgs e);
    
    /* 
     * A simple event publisher.  Translate keypresses into events that
     * a subscriber can subscribe to.
     * */
    class KeyPublisher
    {
        // Translate keypresses into events
        public void KeypressToEvent(ConsoleKeyInfo pressed)
        {
            // No event generated for a plain old key
            Console.WriteLine("Pressed:  {0}", pressed.KeyChar);
    
            // Now the plot thickens
            if (pressed.Modifiers != 0)
            {
                KeyEventArgs kea = new KeyEventArgs(pressed.Modifiers);
    
                // Alt key pressed?
                if ((pressed.Modifiers & ConsoleModifiers.Alt) != 0)
                {
                    // I.e., if someone has subscribed
                    if (AltKeyPressed != null)
                    {
                        // Raise an event
                        AltKeyPressed(this, kea);
                    }
                }
                
                // Etc...
                if ((pressed.Modifiers & ConsoleModifiers.Control) != 0)
                {
                    if (ControlKeyPressed != null) 
                        ControlKeyPressed(this, kea);
                }
    
                if ((pressed.Modifiers & ConsoleModifiers.Shift) != 0)
                {
                    if (ShiftKeyPressed != null)
                        ShiftKeyPressed(this, kea);
                }
            }
                            
        }
    
        public static event KeyEventDelegate AltKeyPressed = null;
        public static event KeyEventDelegate ShiftKeyPressed = null;
        public static event KeyEventDelegate ControlKeyPressed = null;
    }
    
    /*
     * Create some simple event hanlders we can use.  We'll wire these
     * up below in our sample "Main"
     */
    class KeySubscriber
    {
        // Function signature matches our delegate
        public void HandleControlKey(Object ojb, KeyEventArgs e)
        {
            Console.WriteLine("Control key pressed");
        }
    
        // Function signature matches our delegate
        public void HandleAltKey(Object ojb, KeyEventArgs e)
        {
            Console.WriteLine("Alt key pressed");
        }   
    
        // But I'm not interested in Shift keys
    }
    
    
    class EventHandlerExample
    {
        static void Main(string[] args)
        {
            // Create a publisher to fire events
            KeyPublisher pub = new KeyPublisher();
    
            // Create a subscriber to respond to them
            KeySubscriber sub = new KeySubscriber();
    
            // Link event handlers to events
            KeyPublisher.AltKeyPressed += sub.HandleAltKey;
            KeyPublisher.ControlKeyPressed += sub.HandleControlKey;
            
            // Read keys and translate to events
            ConsoleKeyInfo keyInfo;
            bool finished = false;
            while (! finished)
            {
                keyInfo = Console.ReadKey();
                switch (keyInfo.KeyChar)
                {
                    case 'Q':
                    case 'q':
                        finished = true;
                        break;
    
                    default:
                        // Publish!
                        pub.KeypressToEvent(keyInfo);
                        break;
                }
            }
        
        }
    }
    
    
    </code>





Output of course varies depending on what keys you press. One possibility:




    
    <code>
    aPressed:  a
    bPressed:  b
    ☻Pressed:  ☻
    Control key pressed
    ◄Pressed:  ◄
    Control key pressed
    ♦Pressed:  ♦
    Control key pressed
    aPressed:  a
    Alt key pressed
     Pressed:
    Alt key pressed
    Control key pressed
    </code>
