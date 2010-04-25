<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>KWIC Search Engine Results</title>
    </head>
    <body>
       <div id="whatfor">
           Search results for: ${whatfor}
        </div>
       <div id="resultcount">
           Found ${resultcount} links
        </div>
        <div id="resultlist">
           Results:<br/>
           ${result}
        </div>
    </body>
</html>
