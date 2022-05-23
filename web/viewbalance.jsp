<%--
    Document   : viewbalance
    Created on : 14-Jun-2021, 3:15:16 PM
    Author     : dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
          <link rel="stylesheet" href="design.css">
        <title>View Balance</title>
    </head>
    <body>
        <%
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Expires", "0");
            if(session.getAttribute("access")==null)
                    response.sendRedirect("login.jsp");

            %>
              <form>
            <input type="image" formaction="menu.jsp" id="homeicon" src="icons/home.png">
            <button id="logoutb" formaction="logout.jsp" type="submit">Logout</button>
            </form>
        <div id="balancetext"><c:out value="Balance in your account is: ${balance}"></c:out></div>

    </body>
</html>
