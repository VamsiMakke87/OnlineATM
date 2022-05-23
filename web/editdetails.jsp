<%--
    Document   : editdetails.jsp
    Created on : 18-Jun-2021, 9:22:56 PM
    Author     : dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <link rel="stylesheet" href="design.css">
        <title>Edit</title>
    </head>
    <body>
        <%
//            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
//            response.setHeader("Pragma", "no-cache");
//            response.setHeader("Expires", "0");
             if(session.getAttribute("access")==null)
                     response.sendRedirect("login.jsp");

            %>
            <form>
              <input type="image" formaction="menu.jsp" id="homeicon" src="icons/home.png">
              <button id="logoutb" formaction="logout.jsp" type="submit">Logout</button>
          </form>
            <form method="post" id="trform">
                **Fill those text-fields you wish to update**<br>
                First Name:<input id="trinput" autocomplete="off" type="text" name="fname" pattern="[a-zA-Z]{3,}"><br>
                Last Name :<input id="trinput" autocomplete="off" type="text" name="lname" pattern="[a-zA-Z]{3,}"><br>
                Enter Mail :<input id="trinput" autocomplete="off" type="text" name="mail" pattern="^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9-]+.[a-zA-z]+$"><br>
                <%
                    String tfa=session.getAttribute("Tfa").toString();
                    if(tfa.equals("On")){
                    %>
                    Two Factor Authenication:<input type="radio" name="tfaradio" value="On" checked="checked"> On
                    <input type="radio" name="tfaradio" value="Off"> Off<br>
                    <%
                       }
                           else{
                        %>
                        Two Factor Authenication:<input type="radio" name="tfaradio" value="On"> On
                        <input type="radio" name="tfaradio" value="Off" checked="checked"> Off <br>
                         <%
                            }

                        %>
                        <button type="submit" formaction="generate" id="depbutton">Update</button><br>
            </form>
    </body>
</html>

