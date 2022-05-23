/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demo.app;

import com.atm.dao.ATMDAO;
import com.atm.dao.Generator;
import com.atm.dao.TransactionDAO;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.catalina.connector.Response;

/**
 *
 * @author dell
 */
@WebServlet("/menu")
public class LoginServlet extends HttpServlet{
    
    public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException{
        
        PrintWriter out=res.getWriter();
        HttpSession session=req.getSession();
        try{
            String acntnum=req.getParameter("anum").toUpperCase();
            int pin=Integer.parseInt(req.getParameter("pin"));
            ATMDAO atm=new ATMDAO();
            TLSEmail mail=new TLSEmail();
            Generator g=new Generator();
            TransactionDAO tr=new TransactionDAO();
                if(atm.isValid(acntnum, pin)){
                    session.setAttribute("acntnum", acntnum);
                    session.setAttribute("pin", pin);
                    session.setAttribute("mail", atm.getValue(acntnum,"mail"));
                    session.setAttribute("name", atm.getValue(acntnum,"firstname"));
                    session.setAttribute("lastname", atm.getValue(acntnum,"lastname"));
                    session.setAttribute("balance", atm.getValue(acntnum, "balance"));
                    session.setAttribute("transaction", tr.getTransaction(acntnum));
                    session.removeAttribute("invalid");
                    if(atm.getValue(acntnum, "tfa").equals("On")){
                        session.setAttribute("Tfa", "On");
                        session.setAttribute("tfa", "true");
                        session.removeAttribute("changepin");
                        res.sendRedirect("generate");
                    }
                    else{
                        session.setAttribute("access","true");
                        session.setAttribute("Tfa", "Off");
                        session.removeAttribute("changepin");
                        mail.send(atm.getValue(acntnum,"mail"),atm.getValue(acntnum, "firstname"));
                        res.sendRedirect("menu.jsp");
                    }
                }
                else{ 
                    if(session.getAttribute("invalid")==null)
                        session.setAttribute("invalid",1);
                    res.sendRedirect("login.jsp");
                    }
        }
        catch(Exception e){
            out.println("Sorry!!The server is down right now.");
            out.println("Please try again after some time:)");
        }
  
    
    }
    
}
