require 'rake'
require 'yaml'
require 'fileutils'
require 'rbconfig'

# Load the configuration file
CONFIG = YAML.load_file("_config.yml")

# Get and parse the date
DATE = Time.now.strftime("%Y-%m-%d")

# Directories
POSTS = "_posts"


desc "Completely rebuilds and pushes site"
task :all do
  Rake::Task["build:pro"].invoke
  Rake::Task["push_source"].invoke
  Rake::Task["push_master"].invoke  
  puts "Rake all finished."
end

task :debug do
  puts "#{DATE}"
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

desc "Test"
task :test do
  puts("\n## Moving to master directory?")
  Dir.chdir("../master")
  File.unlink("hello.txt")  
end

desc "Commit & push source branch"
task :push_source do
  puts "\n## Staging modified files in source"
  status = system("git add -A .")
  puts status ? "Success" : "Failed"
  puts "\n## Committing a site build at #{Time.now.utc}"
  message = "Build site at #{Time.now.utc}"
  status = system("git commit -m \"#{message}\"")
  puts status ? "Success" : "Failed"
  puts "\n## Pushing commits to remote"
  status = system("git push ")
  puts status ? "Success" : "Failed"
end


desc "Commit & push to master branch"
task :push_master do

  puts("Copying resume")
  cp '../../../../JohnPersonal/JohnLockwoodResume.doc', '../master'


  puts("Switching to master directory")
  Dir.chdir("../master")
  
  #puts("Removing extra Rakefile (hack alert)")
  #system("rm hello.txt")

  puts "\n## Staging modified files in master"
  status = system("git add -A .")
  puts status ? "Success" : "Failed"
  puts "\n## Committing a site build at #{Time.now.utc}"
  message = "Build site at #{Time.now.utc}"
  status = system("git commit -m \"#{message}\"")

  puts "\n## Pushing master"
  status = system("git push")
  puts status ? "Success" : "Failed"
  Dir.chdir("../source")

end

# rake post["Title"]
desc "Create a post in _posts"
task :post, :title do |t, args|
  title = args[:title]
  template = CONFIG["post"]["template"]
  extension = CONFIG["post"]["extension"]
  editor = CONFIG["editor"]
  check_title(title)
  filename = "#{DATE}-#{transform_to_slug(title, extension)}"
  content = read_file(template)
  create_file(POSTS, filename, content, title, editor)
end

# Create the file with the slug and open the default editor
def create_file(directory, filename, content, title, editor)
  FileUtils.mkdir(directory) unless File.exists?(directory)
  if File.exists?("#{directory}/#{filename}")
    raise "The file already exists."
  else
    write_file(content, title, directory, filename)
    if editor && !editor.nil?
      sleep 1
      execute("#{editor} #{directory}/#{filename}")
    end
  end
end

# Chech the title
def check_title(title)
  if title.nil? or title.empty?
    raise "Please add a title to your file."
  end
end


# Transform the filename and date to a slug
def transform_to_slug(title, extension)
  characters = /("|'|!|\?|:|\s\z)/
  whitespace = /\s/
  "#{title.gsub(characters,"").gsub(whitespace,"-").downcase}.#{extension}"
end

# Read the template file
def read_file(template)
  File.read(template)
end

# Save the file with the title in the YAML front matter
def write_file(content, title, directory, filename)
  parsed_content = "#{content.sub("title:", "title: \"#{title}\"")}"
  File.write("#{directory}/#{filename}", parsed_content)
  puts "#{filename} was created in '#{directory}'."
end

# Execute a system command
def execute(command)
  system "#{command}"
end

