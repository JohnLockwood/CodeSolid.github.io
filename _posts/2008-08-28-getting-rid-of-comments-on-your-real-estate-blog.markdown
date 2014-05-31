---
author: admin
comments: true
date: 2008-08-28 17:42:36+00:00
layout: post
slug: getting-rid-of-comments-on-your-real-estate-blog
title: Getting Rid of Comments On Your Real Estate Blog
wordpress_id: 283
categories:
- Miscellaneous
---

I recently had as many people beginning a discussion with me about real estate directly from my real estate blog in a couple of weeks as I formerly enjoyed all year long. One of these folks is a fairly well motivated buyer who's now working with one of my agents.

Anecdotally, then, my directly blog-related conversion rate went up maybe 1700% or so.




[![image](http://www.particlewave.com/internet-marketing/wp-content/uploads/2008/08/image-thumb.png)](http://www.particlewave.com/internet-marketing/wp-content/uploads/2008/08/image.png)What changed to make this happen? Well, one of the recent things I did was to completely get rid of comments on my blog.

**Conversion, Not Conversation**

When I got rid of comments, I wasn't expecting to increase my conversion rate from the blog. I wasn't expecting to decrease it, either. I didn't really expect it to change at all. I got rid of comments because I was sick of having to open up the comments page only to find yet another anti-Realtor troll trying to get me worked up.

I do have an idea about why I'm getting more contacts from my blog now that users are forced to use my contact page instead of a comment form, however.

I think that not having comments on the blog helps to sends a message -- albeit on a very subtle level -- that I'm blogging to make contact with customers, not to socialize.

Maybe to the extent that you're goofing off on your blog / Facebook / Twitter / Myspace, you **look **like you're goofing off on your blog / Facebook / Twitter / Myspace.

**But John, You Can't Get Rid Of Comments!**

Of course I can. Web 2.0 is a great buzzword, but I've spent enough time as a software developer to be comfortable with my geek credentials. Because of this, my goal is to have a successful online business, not to be more buzzword compliant by bandying ersatz version numbers.

Besides, if your real estate clients really wanted a nerd and not a Realtor®, more real estate agents would market themselves at Star Trek conventions.

Don't forget your Vulcan ears.

I'm not saying you should get rid of comments if they're really working for you, but for those who think they are, have you experimented with not having them?

From my point of view, I'm spending hundreds of hours every year blogging to help me reach customers, not to have a conversation (if you can call it that) with [these losers](http://www.collegehumor.com/video:1771556). You wouldn't let a drunken, semi-literate moron ruin your open house or get in your face in your office. Why should you put up with them on a web site to which you've equally dedicated time and money?

Similarly, when a buyer visits your open house, do you put your feet on the coffee table and say, "Come on in and have a House 2.0 Konversation, dood!", or do you try to conduct yourself as a professional with whom your visitor might want to do business?

Of course, you should have your phone number on every page of the site, and you should have a contact form equally prominently available. You're not trying to hide from legitimate customers. If my experience is any guide, your clients will begin to appreciate that you're not just hanging around the Office Water Cooler 2.0, and respond accordingly. You may even find yourself making more money 1.0.

**How To Get Rid of Comments on Wordpress**

As always, before you make any changes you'll want to **back up your existing files**. Also if you're not comfortable coding, don't follow these steps. If you are it should take you fifteen minutes or less. 

The way I went about this was to first create a brain dead function in my theme's directory's function.php file: 

`
    
    <?php
    
    // ...
    
    function show_comments()
    {
        return false;
    }<br>?>

`


The reason for this is simply to make it easier to change my mind later by returning true, if indeed I change my mind later. You may not have a functions.php file, but if not, show_comments() is a good reason to create one -- again, it goes in your theme directory.




Next, I wrapped the call to comments_popup_link in index.php, changing the line 

`
    
    <div>comments_popup_link(...whatever)...</div>
    

`


to 

`
    
    if (show_comments())
    {
        <div>comments_popup_link(...whatever)...</div>
    }

`


If you're OK with not showing comments that you already have, you can also go after single.php and change the call to comments_template to read:

`
    
    if (show_comments())
    {
        comments_template();
    }

`


Keeping the comments you have already means you would need to go into comments.php and leave the part that displays the comments while removing the comment submission form (or better yet, wrapping the form in a show_comments conditional, as above).




Once you've done these steps, if you have no other references to comments-post.php in your themes you can go into your Wordpress root directory and rename comments-post.php to something like comments-post.my_secret_backup. This will ensure that you're now not only free from trolls with keyboards, you're also more secure from spambots (which suck up server time sending you spam about little blue pills and the like).




Finally, if you're sick of trackback spam, too, while you're in the Wordpress root go ahead and rename wp-trackback.php to something like wp-trackback.I_hate_trackback_spam.
