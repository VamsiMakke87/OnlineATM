

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
          <link rel="stylesheet" href="design.css">
        <title>Transaction</title>
    </head>
    <body>
        <%
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Expires", "0");
              if(session.getAttribute("access")==null)
                      response.sendRedirect("login.jsp");
              else{

            %>
            <form>
            <input type="image" formaction="menu.jsp" id="homeicon" src="icons/home.png">
            <button id="logoutb" formaction="logout.jsp" type="submit">Logout</button>
          </form>
        <table>
            <caption>Last 5 Transactions</caption>
            <tr>
                <th>Date</th><th>Time</th><th>Transaction ID</th><th>Amount</th><th>From</th><th>To</th>
            </tr>
            <%
                List<String> li=(List<String>)session.getAttribute("transaction");
                for(String s:li){
                    String st[]=s.split(" ");
                %>
                <tr>
                    <td><%=st[0]%></td><td><%=st[1]%></td><td><%=st[2]%></td><td><%=st[3]%></td><td><%=st[4]%></td><td><%=st[5]%></td>
                </tr>
                <%
                    }
                    %>


        </table>
                    <%
                        }
                %>
    </body>
</html>
