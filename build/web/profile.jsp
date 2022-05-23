<%--
    Document   : profile
    Created on : 18-Jun-2021, 8:37:45 PM
    Author     : dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="design.css">
        <title>Profile</title>
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
          <from id="trform">
            <strong>Profile</strong>
            <div>
                Name:${name} ${lastname}<br>
                Mail:${mail}<br>
                Balance:${balance}<br>
                Two Factor Authenication :${Tfa}<br>
                <a href="editdetails.jsp">Edit Details</a>
            </div>
          </form>
    </body>
</html>
