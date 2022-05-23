

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
          <link rel="stylesheet" href="design.css">
        <title>Login</title>
        <script type="text/javascript">
            window.history.forward();

            function noBack() {
              window.history.forward();
            }
        </script>
    </head>
    <body class="login">
         <%
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Expires", "0");
            %>
            <form autocomplete="off" action="menu" method="post" id="loginform">
                <strong>E-BANKING</strong><br><br>
            Enter Account Number:<br> <input autocomplete="off" type="text" name="anum" required="*" pattern="[0-9a-zA-Z]{10}" id="logininput"><br><br>
            Enter PIN:<br><input type="password" name="pin"required="*" pattern="[0-9]{4}" id="logininput"><br>
            <a href="forgotpin.jsp">Forgot PIN</a><br>
            <button type="submit" id="loginbutton">Login</button><br>
          <p class="invalid">
        <%
            if(session.getAttribute("invalid")!=null){
                session.removeAttribute("invalid");
                out.println("Invalid Input");

            }
            %>

      </p>
    </form>
    </body>
</html>
