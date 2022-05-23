<%--
    Document   : forgotpin
    Created on : 15-Jun-2021, 4:41:31 PM
    Author     : dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Forgot PIN</title>
        <link rel="stylesheet" href="design.css">
    </head>
    <body>
        <%
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Expires", "0");

            %>
            <form>
            <input type="image" formaction="login.jsp" id="homeicon" src="icons/home.png">
            </form>
            <form action="forgotpin" id="trform">
            Enter Account Number:<br> <input id="fpinput" autocomplete="off" type="text" name="anum" required="*" pattern="[0-9a-zA-Z]{10}"><br>
            Enter Mail:<br><input id="fpinput" autocomplete="off" type="text" name="mail" required="*" pattern="^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9-]+.[a-zA-z]+$"><br><br>
            <button type="submit" id="depbutton">Submit</button><br>
            <p id="forinvalid">
              <%
                  if(session.getAttribute("mailX")!=null){
                      session.removeAttribute("mailX");
                      out.println("Unregistered Mail-ID");
                  }
                  if(session.getAttribute("acntX")!=null){
                      session.removeAttribute("acntX");
                      out.println("Unregistered Account Number");
                  }
                %>
            </p>
      </form>
  </body>
</html>
