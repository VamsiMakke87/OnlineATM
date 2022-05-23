<%--
    Document   : withdraw.jsp
    Created on : 14-Jun-2021, 3:41:03 PM
    Author     : dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Withdraw</title>
        <link rel="stylesheet" href="design.css">
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
            <form id="depform">
              <p id="depinvalid">
                <%
                    if(session.getAttribute("withdraw")!=null){
                        out.println("Not Enough Balance!");
                        session.removeAttribute("withdraw");
                    }
                %>
              </p>
                <strong>Withdraw</strong><br><br>
                Enter Amount:<input id="depinput" autocomplete="off" type="text" name="withamt" required="*" pattern="[0-9]{1,4}|[1-4][0-9]{4}">
                <br><br>
                  <button type="submit" formaction="withdraw" id="depbutton">Withdraw</button>

              </form>
    </body>
</html>
