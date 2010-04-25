<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
   <head>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      <title>KWIC Search Engine</title>
   </head>
   <body>
      <div id="main">
         <div id="label">
            Search:
         </div>
         <div id="entry">
            <form action="" method="post">
               <spring:nestedPath path="search">
                  <spring:bind path="input">
                     <textarea name="${status.expression}" id="whatfor" style="width:400px;" rows="5"><c:out value='${status.value}' escapeXml='true'/></textarea>
                  </spring:bind>
               </spring:nestedPath>
               <input type="submit" value="OK">
            </form>
         </div>
      </div>
   </body>
</html>
