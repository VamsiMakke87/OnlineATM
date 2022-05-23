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

/**
 *
 * @author dell
 */
@WebServlet("/forgotpin")
public class forgotpinServlet extends HttpServlet{
    
    protected void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{
        
        ATMDAO atm=new ATMDAO();
        HttpSession s=req.getSession();
        try{
                String anum=req.getParameter("anum");
                String mail=req.getParameter("mail").toLowerCase();
                String regmail=atm.getValue(anum, "mail");
                if(regmail!=null){
                    if(regmail.equalsIgnoreCase(mail)){
                        s.setAttribute("acntnum", anum);
                        s.setAttribute("mail", mail);
                        s.setAttribute("name", atm.getValue(anum, "firstname"));
                        s.setAttribute("changepin", "true");
                        res.sendRedirect("generate");
                    }
                    else{
                        s.setAttribute("mailX", 0);
                        res.sendRedirect("forgotpin.jsp");
                    }
                }
                else{
                    s.setAttribute("acntX", 0);
                    res.sendRedirect("forgotpin.jsp");
                }
        }catch(Exception e){
            res.sendRedirect("login.jsp");
        }
        
    }
    
}
