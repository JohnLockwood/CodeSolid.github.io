---
author: John Lockwood
layout: post
title: 	First Impressions of Cassandra

excerpt:  How I got a whole bunch of new Cassandra pals and wrote hello world for the umpti-Nth time in my career.
categories:
- Miscellaneous
---

Recently I attended a Sacramento Java User's Group meeting for the first time in many years, and saw an excellent presentation by Jega Aravandy on some work he's doing with Casandra, Solr, Spring, and Angular.js. It was exciting work in its own right, and Jega came equipped with plenty of swag that someone from Datastax had asked him to bring along. So between my wife and I we walked away with newfound insights along with three USBs with Cassandra VMs on them, two coffee mugs and a tee shirt.

Not a bad night's haul.

A friend from work was also there, so we've been chatting about Cassandra since.  We've been discussing whether we should spend some of the money that the company is encouraging us to spend on training on Datastax's [Introductory Course in Cassandra](http://www.datastax.com/what-we-offer/products-services/training/apache-cassandra-core-concepts-skills-and-tools).  

My ethical dilemma is that I can spend the company's money on this cool bauble I want to learn, but I really think the chances of getting to use this cool bauble at this company are not good.  So even though they might well still pay for it, it doesn't seem right to let me increase my marketability at their expense unless part of the market is internal.

Compromise:  Hello World
==

Struck down by fear at the edge of this dense ethical forest, I retreated to the safety of a trusted old friend -- Hello World.  So I told my work-friend that I'd write my other friend (Hello World) using Cassandra over the weekend, and last night just like that I did it.

Come on people, it's Hello World.  It's not meant to be hard.  The only place Hello World was ever hard was Websphere, and that's only because Websphere is a disaster.

A bit circular, perhaps, but QED.

But enough about Websphere.  Cassandra isn't a disaster, so Hello World was easy.  As it should be.

How Easy Was It?
==

I have VMWare Player installed, so I tried inserting one of the USBs with the Cassandra VM on it and double clicking on the Cassandra-2.0.6.ova file.  VMWare complained a bit at first, but gave me a message along the lines of "Should I stop complaining and load this?", so I wiesely chose yes.

Being instructed to stop complaining, VMWare dutifully launched the VM, which was good enough to tell me I should try to get to the CSQL console at http://some\_local\_address:some\_port\.  

I typed that URL into Chrome, and, hey presto, without installing Cassandra I was in a cqlsh terminal writing Hello World in it.  This basically involved a process of Googling things like "Cassandra create table", finding out I needed a keyspace first, and working my way forward through "Cassandra insert" and "Cassandra select".  Nothing was hard, and if you wanted to make it even easier you could [read this overview](http://planetcassandra.org/install-cassandra-ova-on-vmware/).  My cassandra-newbie fingers made a mistake or two along the way to full hello-worldly glory, but it was nothing csql and I couldn't work through together amicably.

My Datastax-sponsored Friends
==

On the human side of my experience, it seems to me that there is a pitched battle going on among NoSQL vendors for developer mind-share.  To give a non-Cassandra example, when I took another look at MongoDB recently, the download process required registration first.  I sometimes enter my correct details in those just to see how much hard sell I'll get before the salesman and I get tired of one another.  

On the other hand, I come to Cassandra via a clever Datastax swag USB stick. So I didn't have to fill out a form, and no salesman has called me. I did however get a lot of cool new Datastax-sponsored friends on Twitter when I mentioned that I had mastered the non-challenge of hello world.  

Full disclosure:  one of them offered me more Datastax swag if I share my experiences in getting started.  So I'm going to point them to this article.  

If they're still my friends after this poor ouvre, then they'll be the best industry-sponsored pals I ever made.