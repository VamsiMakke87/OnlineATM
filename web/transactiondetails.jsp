<%-- 
    Document   : transactiondetails
    Created on : 03-Sep-2021, 4:54:40 PM
    Author     : dell
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="design.css">
        <title>Transaction Details</title>
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
            <strong>Transaction</strong>
            <div>
                <%
                    if(session.getAttribute("tidfound")==null)
                    {
                        session.removeAttribute("tidnotfound");
                    %>
                    Sorry!!Your Account doesn't have <br>any transaction with transaction id ${tid}.<br>
                    Please check the Transaction ID again :).
                    
                    <%
                        }
                            else{
                            session.removeAttribute("tidfound");
                        %>
                        Transaction ID : ${tid}<br>
                        Date &AMP; Time : ${time}<br>
                        From : ${from}<br>
                        To : ${to}<br>
                        Amount : ${amnt}
                        <%
                            }
                            %>
            </div>
            <a href="findmytid.jsp">&ll; Back</a>
          </form>
    </body>
</html>
