
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>TFA</title>
        <link rel="stylesheet" href="design.css">
    </head>
    <body>
       <%
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Expires", "0");
             if(session.getAttribute("pin")==null)
                     response.sendRedirect("login.jsp");
             session.setAttribute("tfa","true");
             session.removeAttribute("changepin");

            %>
        <form>
            <input type="image" formaction="login.jsp" id="homeicon" src="icons/home.png">
            </form>
        <form action="tfa" method="post" id="trform">
            <%
                if(session.getAttribute("mailtwo")!=null){
                %>
          <div id="divtext">(OTP sent to ${mailtwo})</div>
          <%
              }
                else { 
              %>
              <div id="divtext">(OTP sent to ${mail})</div>
              <% } %>
            Enter OTP:<input autocomplete="off" type="text" name="otp" required="*" pattern="[0-9]{6}" id="depinput"><br>
            <a href="generate">Resend OTP</a><br>
            <button type="submit" id="depbutton">Verify</button>
            <p id="trinvalid">
                <%
                    if(session.getAttribute("otpX")!=null){
                        session.removeAttribute("otpX");
                        out.println("Invalid OTP");
                    }
                    %>
            </p>
        </form>

    </body>
</html>
