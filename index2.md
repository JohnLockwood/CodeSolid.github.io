---
layout:	default
title:  Hello World!
description: The usual starter page, wherein the world is greeted with the standard Hello I hope it is dynamic.
---
# Hello World

I will now try out the prism plugin:

{% prism javascript linenos=1,4 %}
var obj = { 'foo': true, 'bar': false };

for (key in obj) {
  console.log(obj[key]);
}
{% endprism %}