<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
   <head>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      <title>KWIC Administration - Index Updated</title>
   </head>
   <body>
      Added the following to the index:
      <div id="url">
         URL: ${url}
      </div>
      <div id="description">
         Description: ${description}
      </div>
      <div id="description">
         SHA-1 Index: ${index}
      </div>
      <div id="shifts">
         Circular Shifts:<br/>
         ${shifts}
      </div>
   </body>
</html>
