package com.atm.dao;
import java.sql.*;
import java.util.Random;
import org.apache.tomcat.dbcp.dbcp2.DriverManagerConnectionFactory;


public class ATMDAO  {
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
        String query="select * from user where acntnum='"+acntnum+"'";
        ResultSet rs=null;
        try{
        rs=st.executeQuery(query);
        rs.next();
        return rs.getString(s);
        }catch(SQLException e){
            return null;
        }
    }
    public boolean isValid(String acntnum,int pin) throws Exception {
        connect();
        Statement st=con.createStatement();
        String query="select * from user where acntnum='"+acntnum+"'";
        try{
        ResultSet rs=st.executeQuery(query);
        rs.next();
        if(pin==rs.getInt("pin"))
            return true;
        }catch(Exception e){
            return false;
        }
        st.close();con.close();
        return false;
    }
    
     public void setFirstName(String acntnum,String value)throws Exception{
        connect();
        PreparedStatement st=con.prepareStatement("update user set firstname=? where acntnum=?");
        st.setString(1, value);
        st.setString(2, acntnum);
        int count=st.executeUpdate();
    }
     public void setLastName(String acntnum,String value)throws Exception{
        connect();
        PreparedStatement st=con.prepareStatement("update user set lastname=? where acntnum=?");
        st.setString(1, value);
        st.setString(2, acntnum);
        int count=st.executeUpdate();
    }
    
   public boolean setMail(String acntnum,String value) throws Exception{
       connect();
        PreparedStatement st=con.prepareStatement("update user set mail=? where acntnum=?");
        st.setString(1, value);
        st.setString(2, acntnum);
        int count=st.executeUpdate();  
        return true;
    }
    public boolean setBalance(String acntnum,int value)throws Exception{
        PreparedStatement st=con.prepareStatement("update user set balance=? where acntnum=?");
        st.setInt(1, value);
        st.setString(2, acntnum);
        int count=st.executeUpdate();
        if(count!=0)
            return true;
        return false;
    }
    public boolean setPin(String acntnum,int value)throws Exception{
        connect();
        PreparedStatement st=con.prepareStatement("update user set pin=? where acntnum=?");
        st.setInt(1, value);
        st.setString(2, acntnum);
        int count=st.executeUpdate();
        if(count!=0)
            return true;
        return false;
    }
    
    public void setTfa(String acntnum,String value)throws Exception{
        connect();
        PreparedStatement st=con.prepareStatement("update user set tfa=? where acntnum=?");
        st.setString(1, value);
        st.setString(2, acntnum);
        int count=st.executeUpdate();
    }

   
    public int generatepin(){
        Random r=new Random();
        int x=1000+r.nextInt(8999);
        return x;
    }
}
