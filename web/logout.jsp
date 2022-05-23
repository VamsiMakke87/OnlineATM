<%-- 
    Document   : logout
    Created on : 14-Jun-2021, 3:46:36 PM
    Author     : dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
              session.invalidate();
            response.sendRedirect("login.jsp");
            %>
    </body>
</html>
