---
author: admin
comments: true
date: 2012-07-07 02:09:58+00:00
layout: post
slug: testing-devise-authentication-in-rails-3-1
title: Testing Devise Authentication in Rails 3.1
wordpress_id: 910
categories:
- Miscellaneous
tags:
- Capybara
- Devise
- RSpec
- Ruby
- Ruby on Rails
- Testing
- Unit Testing
---

I ran into a bit of a hiccup while writing tests in RSpec for a site I'm working on which is using Devise for authentication.  I spent a short time working through the documentation at [testing Devise using rspec and controller tests](https://github.com/plataformatec/devise/wiki/How-To:-Controllers-and-Views-tests-with-Rails-3-(and-rspec)), only to end up stubbing my toe on the following caveat:



> Authenticated routes in Rails 3
If you choose to authenticate in routes.rb, you lose the ability to test your routes via assert_routing (which combines assert_recognizes and assert_generates, so you lose them also). It’s a limitation in Rails: Rack runs first and checks your routing information but since functional/controller tests run at the controller level, you cannot provide authentication information to Rack which means request.env[‘warden’] is nil and Devise generates one of the following errors:

  NoMethodError: undefined method 'authenticate!' for nil:NilClass
  NoMethodError: undefined method 'authenticate?' for nil:NilClass
The solution is to test your routes via integration tests. Authenticating your routes has its advantages, this is one of the disadvantages.




This was just the error I was getting when running my controller tests, so it looked like I needed to go after them in in integration tests.

Fortunately with a bit of Google-Fu, it turned out this is not an unusual problem. I was lucky to run into Andy Lindeman's excellent post on [Testing Devise with RSpec and Capybara](http://alindeman.github.com/2011/09/25/testing-devise-with-rspec-request-specs-and-capybara.html). 

First I had to install CapyBara, which I hadn't worked with before (See Lindeman's article for details on how to install this).  I based my solution on Lindeman's code, but with a couple of variations.  First, I wanted to wrap his code in a module so I could call it from any test.  Also, I modified his code to ensure that if the user was not registered, we'd register him, and if he was registered, we'd log him in.  This helped me make the code he provided easier to use and more re-entrant.

To accomplish this in RSpec, I added the following line to spec_helper.rb in the spec directory:

[cc lang="ruby"]
    config.extend RequestMacros, :type => :request
[/cc]

That done, I added the module code to a new file, spec/support/request_macros.rb.  As you can see in the snippet below, it looks for the user in the database, and if not found, registers the user (which logs the user in as a side effect).  If the user is found, it logs the user in.  (I've coded the email and password here along the way, but this could be DRY'ed up a bit by getting the user information from FactoryGirl).

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

Finally, because in integration testing we're not really testing Rails objects but are dealing with input and output as the web browser sees it, I had to set up a test page to verify that things were working as planned.  I'll no doubt yank this page later as I develop "real tests" for the functionality I want, and develop the functionality along with the tests.  In the meantime, the target test page (views/people/test.html.erb) is simplicity itself:

[cc lang="rails"]
<%= 
if user_signed_in? 
  "true"
else
  "false"
end
%>

[/cc]

With this done and the route for the test in place (we don't need a controller method because the default rendering will pick up the view), we're now ready for the test itself, in spec/requests/people_spec.rb. (Assuming you want to test the "PeopleController", if not, modify this and the view location to suit). You'll see we test that we can log in a second time as well:

[cc lang="ruby"]
require 'spec_helper'

describe "People", :type => :request do

  login_site_admin 
  
  it "signs_me_in" do
   visit "/people/test"
   page.should have_content("true")
  end
  
  it "signs_me_in_again" do
   visit "/people/test"
   page.should have_content("true")
  end
end
[/cc]

Run the spec as below to verify that it works!

[cc lang="bash"]
rake spec SPEC=spec/requests/people_spec.rb
[/cc]

Hopefully that will save you a few minutes of time.  Enjoy!
