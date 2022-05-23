

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Transfer</title>
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
        <form action="transfer" id="trform">
          <p id="trinvalid">
          <%
              if(session.getAttribute("sameacntnum")!=null){
                  session.removeAttribute("sameacntnum");
                  out.println("Can't Transfer to own account!");
              }
              if(session.getAttribute("invalid")!=null){
                  session.removeAttribute("invalid");
                  out.println("Unauthorized Account Number");
              }

              if(session.getAttribute("withdraw")!=null){
                      session.removeAttribute("withdraw");
                      out.println("Not Enough Balance!");
                  }
              %>
            </p>
            <strong>Transfer</strong><br>
            Enter Recipient Account Number:<br><input id="trinput" autocomplete="off" type="text" name="traamt" required="*" pattern="[0-9a-zA-Z]{10}"><br>
            Enter Amount:<input id="trinput" autocomplete="off" type="text" name="depamt" required="*" pattern="[0-9]{1,4}|[1-4][0-9]{4}"><br>
            <br><button type="submit" id="depbutton">Transfer</button>

          </form>

    </body>
</html>
