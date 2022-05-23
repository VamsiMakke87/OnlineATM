/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atm.dao;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dell
 */

@WebServlet("/tid")
public class TidDAO extends HttpServlet{
    Connection con;
    void connect() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        String url="jdbc:mysql://localhost:3306/usersdb";
        String uname="root";
        String pass="";
        con=DriverManager.getConnection(url,uname,pass);
    }
    
    public String getValue(String tid,String s)throws Exception{
        connect();
        Statement st=con.createStatement();
        String query="select * from tid where transactionid='"+tid+"'";
        ResultSet rs=null;
        try{
        rs=st.executeQuery(query);
        rs.next();
        return rs.getString(s);
        }catch(SQLException e){
            return "no";
        }
    }
    
    
    public void setValues(String tid,String from,String to,String time,int amnt)throws Exception{
         connect();
        PreparedStatement st=con.prepareStatement("insert into tid values (?,?,?,?,?)");
        st.setString(1, tid);
        st.setString(2, from);st.setString(3, to);
        st.setString(4, time);st.setString(5,""+amnt);
        int count=st.executeUpdate();
    }
    
    @Override
   public void doPost(HttpServletRequest request, HttpServletResponse response)throws IOException {
       PrintWriter out=response.getWriter();
       HttpSession session=request.getSession();
       String tid=request.getParameter("tid");
       session.setAttribute("tid",tid);
       String s;
        try {
            s = getValue(tid,"transactionid");
            if(s.equals("no")){
                session.setAttribute("tidnotfound", "true");
               // out.print("1");
            }
            else
            {
                String acntnum=session.getAttribute("acntnum").toString();
                if(getValue(tid,"from").equals(acntnum) || getValue(tid, "to").equals(acntnum)){
                    session.setAttribute("from", getValue(tid,"from"));session.setAttribute("to", getValue(tid,"to"));
                     session.setAttribute("time", getValue(tid,"time"));session.setAttribute("amnt", getValue(tid,"amount"));
                    session.setAttribute("tidfound",s);//out.print("2");
                }
                else{
                    session.setAttribute("tidnotfound", "true");//out.print("3");
                }
            }
           response.sendRedirect("transactiondetails.jsp");
        } catch (Exception ex) {
        }
   }
}
