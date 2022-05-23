<%--
    Document   : changepin
    Created on : 15-Jun-2021, 2:48:24 PM
    Author     : dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Change PIN</title>
        <link rel="stylesheet" href="design.css">
    </head>
    <body>
        <%
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Expires", "0");
            if(session.getAttribute("mail")==null)
                    response.sendRedirect("login.jsp");

            %>
            <form>
              <input type="image" formaction="menu.jsp" id="homeicon" src="icons/home.png">
              <button id="logoutb" formaction="logout.jsp" type="submit">Logout</button>
            </form>
        <form action="changepin" id="trform">
          <p id="trinvalid">
            <%
                session.setAttribute("changepin","true");
                if(session.getAttribute("acntX")!=null){
                    session.removeAttribute("invalid");
                        out.println("Invalid Account Number");
                }
                if(session.getAttribute("pinX")!=null){
                    session.removeAttribute("pinX");
                        out.println("PIN doesn't match");
                }
                if(session.getAttribute("otpX")!=null){
                        session.removeAttribute("otpX");
                        out.println("Invalid OTP");
                }
                %>
          </p>
            <strong>Change PIN</strong><br>
            <div id="divtext">(OTP sent to ${mail})</div>
            Enter OTP:<input autocomplete="off" type="text" id="depinput" name="otp" required="*" pattern="[0-9]{6}"><br>
            New PIN      :<input autocomplete="off" type="password"id="depinput" name="new" required="*" pattern="[0-9]{4}"><br>
            Confirm PIN:<input autocomplete="off" type="password" id="depinput" name="confirm" required="*" pattern="[0-9]{4}"><br>
            <a href="generate">Resend OTP</a><br>
            <button type="submit" id="depbutton">Change PIN</button>
        </form>
    </body>
</html>
