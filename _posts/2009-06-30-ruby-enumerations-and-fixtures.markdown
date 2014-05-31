---
author: admin
comments: true
date: 2009-06-30 18:37:31+00:00
layout: post
slug: ruby-enumerations-and-fixtures
title: Ruby on Rails Enumerations and Fixtures
wordpress_id: 356
categories:
- Miscellaneous
tags:
- Enumerations
- Fixtures
- Rails Tips
- Ruby
- Ruby on Rails
- Ruby Tips
---

I had to figure out Ruby on Rails enumerations and fixtures today.  

The solution will be here in just a moment, but first, some gratuitous background:  I was working on a Person resource -- or a "Contact" resource if you prefer your people to have a CRM flare to them.  Naturally, hanging off of these People (or Contacts) are as many phone numbers as they might have.

Twenty-first century people have a lot of phones.

Once you start having contacts who own lots of phones, naturally you want to know if you're bugging someone to buy your widgets on their cell phone or their home phone or what have you.

You don't want to store whole strings to do this, so probably you end up with a line in your schema that looks something like this:
`
    t.integer  "phone_type"
`

Well, that's fine, but in your code you don't want to be saying "p.phone_type = 2", because that's not all that readable.  

Now Ruby doesn't have enums (BAD language -- just for that you're going straight to bed with no strong typing, young man).  It does, however, have constants, and this [Rails tip](http://www.rubyfleebie.com/enumerations-and-ruby/) shows you how to use them.

Like me you may have opted for the simple "first approach" of adding the constants to your model, like so.

`
class Phone < ActiveRecord::Base
  # Type constants
  HOME = 1
  WORK = 2
  CELL = 3
  # ... more model code ...
end
`

Well, all that's well and good, but then what do you do about your fixtures?  Remember, your whole point in starting on this Enumeration journey was to get your code more readable, not less, so if you end up having fixtures that look like this, that's not a good outcome:
`
et_the_extra_terestrial:
  id:                     1
  person_id:        1
  phone_num:     9165551212
  phone_type:     1
`

Phone_type:    1  
At about this point, I remembered that one of my Rails books said something about dynamic fixtures.  Looking those up, it was easy enough to get ET's phone looking the way it should in no time.

`
et_the_extra_terestrial:
  id:                     1
  person_id:        1
  phone_num:     9165551212
  phone_type:     <%= Phone::HOME %>
`

And there you have it, Bob's you're uncle, and Drew Barrymore's your hot aunt.
