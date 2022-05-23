<%--
    Document   : deposit
    Created on : 14-Jun-2021, 10:05:17 PM
    Author     : dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="design.css">
        <title>Deposit</title>
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
            <input type="submit" formaction="logout.jsp" id="logoutb" value="Logout">
          </form>
            <form id="depform" action="deposit">
                <strong>Deposit</strong><br><br>
                Enter Amount:<input autocomplete="off" type="text" name="depamt" required="*" pattern="[0-9]{1,4}|[1-4][0-9]{4}" id="depinput">
                <br><br><button id="depbutton" type="submit">Deposit</button>
            </form>
    </body>
</html>
