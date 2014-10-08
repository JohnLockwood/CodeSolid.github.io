---
author: admin
comments: true
date: 2012-03-03 17:07:38+00:00
layout: post
slug: checking-for-mysql_query-in-mysqlclient-lib-no
title: checking for mysql_query() in mysqlclient.lib... no
wordpress_id: 859
categories:
- Ruby on Rails
---

This week in Ruby pain, I was trying to install the mysql gem and was having a bit of difficulty.

The solution turned out to be some combination of the following commands:

{% prism bash %}
sudo apt-get install libmysql-ruby
sudo aptitude install libmysqlclient16-dev
sudo ln -s /usr/lib/mysql/ /usr/local/lib/mysql

More details on the original error:
ERROR:  Error installing mysql:
	ERROR: Failed to build gem native extension.

        /home/john/.rvm/rubies/ruby-1.9.2-p290/bin/ruby extconf.rb
checking for mysql_query() in -lmysqlclient... no
checking for main() in -lm... yes
checking for mysql_query() in -lmysqlclient... no
checking for main() in -lz... yes
checking for mysql_query() in -lmysqlclient... no
checking for main() in -lsocket... no
checking for mysql_query() in -lmysqlclient... no
checking for main() in -lnsl... yes
checking for mysql_query() in -lmysqlclient... no
checking for main() in -lmygcc... no
checking for mysql_query() in -lmysqlclient... no
*** extconf.rb failed ***
Could not create Makefile due to some reason, probably lack of
necessary libraries and/or headers.  Check the mkmf.log file for more
details.  You may need configuration options.
{% endprism %}




