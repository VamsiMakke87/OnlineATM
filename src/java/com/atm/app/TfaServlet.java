/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demo.app;

import com.atm.dao.Generator;
import com.demo.app.TLSEmail;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author dell
 */
@WebServlet("/tfa")
public class TfaServlet extends HttpServlet{
    
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{ 
        try{
        HttpSession s=req.getSession();
        int otp=Integer.parseInt(req.getParameter("otp"));
        int motp=(int)s.getAttribute("otp");
        if(otp==motp){
            s.setAttribute("access", "true");
            s.removeAttribute("otp");
         if(s.getAttribute("mailtwo")!=null)
            res.sendRedirect("updatedetails");
         else
             res.sendRedirect("menu.jsp");
        }
        else{
            s.setAttribute("otpX", 0);
            res.sendRedirect("tfa.jsp");
        }
        }catch(Exception e){
            res.sendRedirect("login.jsp`");
        }
    }
}
