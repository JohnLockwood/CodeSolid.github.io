desc "Completely rebuilds and pushes site"
task :all do
  Rake::Task["delete"].invoke
  Rake::Task["build:pro"].invoke
  Rake::Task["commit"].invoke
  Rake::Task["deploy"].invoke  
  puts "Rake all finished."
end



desc "Delete _site/"
task :delete do
  puts "\## Deleting _site/"
  status = system("rm -r _site")
  puts status ? "Success" : "Failed"
end

desc "Preview _site/"
task :preview do
  puts "\n## Opening _site/ in browser"
  status = system("open http://0.0.0.0:4000/")
  puts status ? "Success" : "Failed"
end


namespace :build do
  desc "Build _site/ for development"
  task :dev do
    puts "\nServing site"
    system("jekyll serve --baseurl / -w")
  end

  desc "Build _site/ for production"
  task :pro  do
    system("jekyll build")
  end
end

desc "Commit _site/"
task :commit do
  puts "\n## Staging modified files"
  status = system("git add -A")
  puts status ? "Success" : "Failed"
  puts "\n## Committing a site build at #{Time.now.utc}"
  message = "Build site at #{Time.now.utc}"
  status = system("git commit -m \"#{message}\"")
  puts status ? "Success" : "Failed"
  puts "\n## Pushing commits to remote"
  status = system("git push origin source")
  puts status ? "Success" : "Failed"
end


desc "Deploy _site/ to master branch"
task :deploy do
  puts "\n## Deleting master branch"
  status = system("git branch -D master")
  puts status ? "Success" : "Failed"
  puts "\n## Creating new master branch and switching to it"
  status = system("git checkout -b master")
  puts status ? "Success" : "Failed"
  puts "\n## Forcing the _site subdirectory to be project root"
  status = system("git filter-branch --subdirectory-filter _site/ -f")
  puts status ? "Success" : "Failed"
  puts "\n## Switching back to source branch"
  status = system("git checkout source")
  puts status ? "Success" : "Failed"
  puts "\n## Pushing all branches to origin"

  puts "\n## Pushing source"
  status = system("git push origin source")
  puts status ? "Success" : "Failed"

  puts "\n## Pushing master (_site) -- forcing"
  status = system("git push origin +master")
  puts status ? "Success" : "Failed"

end