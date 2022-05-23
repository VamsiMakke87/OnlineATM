/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atm.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TransactionDAO {
    
    Connection con;
    void connect() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        String url="jdbc:mysql://localhost:3306/usersdb";
        String uname="root";
        String pass="";
        con=DriverManager.getConnection(url,uname,pass);
    }

    public String getValue(String acntnum,String s)throws Exception{
        connect();
        Statement st=con.createStatement();
        String query="select * from transaction where acntnum='"+acntnum+"'";
        ResultSet rs=null;
 
        rs=st.executeQuery(query);
        rs.next();
        return rs.getString(s);
       
    }
    

    public List<String> getTransaction(String acntnum) throws Exception{
        connect();
        Statement st=con.createStatement();
        String query="select * from transaction where acntnum='"+acntnum+"'";
        List<String> li=new ArrayList<>();
        ResultSet rs=st.executeQuery(query);
        rs.next();
        li.add(rs.getString("transaction1"));li.add(rs.getString("transaction2"));
        li.add(rs.getString("transaction3"));li.add(rs.getString("transaction4"));li.add(rs.getString("transaction5"));
        
        
        return li; 
    }
    
    public boolean updateTransaction(String acntnum,String value) throws Exception{
        connect();
        value=value;
        String t1=getValue(acntnum, "transaction1");
        String t2=getValue(acntnum, "transaction2");
        String t3=getValue(acntnum, "transaction3");
        String t4=getValue(acntnum, "transaction4");
        PreparedStatement st1=con.prepareStatement("update transaction set transaction1=? where acntnum=?");
        st1.setString(1, value);
        st1.setString(2, acntnum);
        int count=st1.executeUpdate();
        PreparedStatement st2=con.prepareStatement("update transaction set transaction2=? where acntnum=?");
        st2.setString(1, t1);
        st2.setString(2, acntnum);
        count=st2.executeUpdate();  
        PreparedStatement st3=con.prepareStatement("update transaction set transaction3=? where acntnum=?");
        st3.setString(1, t2);
        st3.setString(2, acntnum);
        count=st3.executeUpdate();
        PreparedStatement st4=con.prepareStatement("update transaction set transaction4=? where acntnum=?");
        st4.setString(1, t3);
        st4.setString(2, acntnum);
        count=st4.executeUpdate();  
        PreparedStatement st5=con.prepareStatement("update transaction set transaction5=? where acntnum=?");
        st5.setString(1, t4);
        st5.setString(2, acntnum);
        count=st5.executeUpdate();  
        
        return true;
    }
}
