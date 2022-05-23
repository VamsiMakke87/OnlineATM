/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demo.app;

import com.atm.dao.ATMDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/updatedetails")
public class detailsServlet extends HttpServlet{
    
     public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{ 
        ATMDAO atm=new ATMDAO();
         TLSEmail t=new TLSEmail();
        HttpSession session=req.getSession();
        String acntnum;
        try{
            acntnum=session.getAttribute("acntnum").toString();
        if(session.getAttribute("fname2")!=null){
            String fname=session.getAttribute("fname2").toString();
            atm.setFirstName(acntnum, fname);
            session.setAttribute("name", fname);
            session.setAttribute("update", "Details Updated");
            session.removeAttribute("fname2");
        }
        if(session.getAttribute("lname2")!=null){
            String lname=session.getAttribute("lname2").toString();
            atm.setLastName(acntnum, lname);
            session.setAttribute("lastname", lname);
            session.setAttribute("update", "Details Updated");
            session.removeAttribute("lname2");
        }
        if(session.getAttribute("mailtwo")!=null){
            String mail=session.getAttribute("mailtwo").toString();
            String oldmail=session.getAttribute("mail").toString();
            String name=session.getAttribute("name").toString();
            atm.setMail(acntnum,mail);
            t.mailchanged(name, oldmail, mail);
           // t.mailchanged(mail, name, oldmail, mail);
            session.setAttribute("mail",mail);
            session.setAttribute("update", "Details Updated");
            session.removeAttribute("mailtwo");
        }
        String tfa=session.getAttribute("Tfa2").toString();
        atm.setTfa(acntnum,tfa);
        session.setAttribute("Tfa", tfa);
        session.removeAttribute("Tfa2");
        res.sendRedirect("menu.jsp");
        }catch(Exception e){
            session.setAttribute("unsuccessfull", "Unsuccessfull,Please try again after some time");
            res.sendRedirect("menu.jsp");
        }
        
    }
}
