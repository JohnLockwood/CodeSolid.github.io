---
author: admin
comments: true
date: 2012-07-06 19:29:02+00:00
layout: post
slug: yet-another-code-formatting-test
title: Yet Another Code Formatting Test
wordpress_id: 877
categories:
- Miscellaneous
---

I wasn't too satisfied with the last code formatting gizmo I used.  So I've been experimenting with  kpumuk's awesome [CodeColorer](http://wordpress.org/extend/plugins/codecolorer/) plugin.  I'm loving the results, see below...

[cc lang="ruby"]
module RequestMacros 
  def login_site_admin
   before(:each) do
      # Ensure registered
      email = "someone@test.com"
      password = "password"
      user = User.find_by_email(email)
      if user.nil?
        visit "/users/sign_up"
        fill_in "Email",                 :with => email 
        fill_in "Password",              :with => password
        fill_in "Password confirmation", :with => password
        click_button "Sign up"
        page.should have_content("successfully")
      else
        visit "/users/sign_in"
        fill_in "Email",                 :with => email 
        fill_in "Password",              :with => password
        click_button "Sign in"
      end
   end
  end
end
[/cc]

So far my tests in the Visual Editor didn't work out too well even when following the directions.  However I'm very pleased with the result I get in the non-visual editor, and one can always type one's own HTML for any post with code in it.
