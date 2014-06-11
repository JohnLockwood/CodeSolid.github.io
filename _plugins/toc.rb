module Jekyll
  class TableOfContentBlock < Liquid::Block
	include Liquid::StandardFilters

    OPTIONS_SYNTAX = %r{^([a-zA-Z0-9.+#-]+)((\s+\w+(=[0-9,-]+)?)*)$}
 	def initialize(tag_name, markup, tokens)
 		super
 		if markup.strip =~ OPTIONS_SYNTAX
        	@cssClass = $1
        end
        if defined?($2) && $2 != ''
        	@submenu = $2
        end
 	end

    def render(context)
    	hasSubmenu = "No submenu"
    	if @submenu
    		hasSubmenu = "Submenu!"
    	end
       <<-HTML
<div class="#{@cssClass}">
  Hello.  Submen status:  #{hasSubmenu}
</div>
      HTML

 	end
  end
end

Liquid::Template.register_tag('TableOfContent', Jekyll::TableOfContentBlock)
