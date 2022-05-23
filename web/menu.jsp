                                                                                                      

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html id="menuhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
          <link rel="stylesheet" href="design.css">
        <title>Menu</title>
    </head>
    <body id="menubody">
         <%
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Expires", "0");
             if(session.getAttribute("access")==null)
                     response.sendRedirect("login.jsp");
             session.setAttribute("changepin","true");
             if(session.getAttribute("unsuccessfull")==null)
                session.setAttribute("unsuccessfull", "");
             if(session.getAttribute("successfull")==null)
                session.setAttribute("successfull", "");
             if(session.getAttribute("update")==null)
                session.setAttribute("update", "");


            %>
            <form>
            <button id="logoutb" formaction="logout.jsp" type="submit">Logout</button></form><br><br>
          <strong> Welcome ${name},</strong>
          <span color="green">${successfull} ${update}</span> <span id="depinvalid">${unsuccessfull} </span>
          <%
              session.removeAttribute("successfull");
              session.removeAttribute("unsuccessfull");
              session.removeAttribute("update");
              %>
        <form>
        <%-- <input type="submit" formaction="logout.jsp" id="logoutb" value="logout"><br><br><br><br> --%>
        <input type="image" formaction="viewbalance.jsp" id="menuicon" src="icons/viewbalance.png">
<!--        <input type="image" formaction="deposit.jsp" id="menuicon" src="icons/deposit.png">
        <input type="image" formaction="withdraw.jsp" id="menuicon" src="icons/withdraw.png">-->
        <input type="image" formaction="transfer.jsp" id="menuicon" src="icons/transfer.png">
        <input type="image" formaction="generate" id="menuicon" src="icons/changepin.png">
        <input type="image" formaction="transaction.jsp" id="menuicon" src="icons/transaction.png">
        <input type="image" formaction="findmytid.jsp" id="menuicon" src="icons/findmytid.png">
        <input type="image" formaction="profile.jsp" id="menuicon" src="icons/info.png">
       </form>


    </body>
</html>
