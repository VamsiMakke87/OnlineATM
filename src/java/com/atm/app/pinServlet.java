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

/**
 *
 * @author dell
 */
@WebServlet("/changepin")
public class pinServlet extends HttpServlet{
    
    protected void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{
        
        ATMDAO atm=new ATMDAO();
        HttpSession s=req.getSession();
        try{
           // String acntnum=req.getParameter("anum").toLowerCase();
            String acntnum=s.getAttribute("acntnum").toString().toLowerCase();
            int n=Integer.parseInt(req.getParameter("new"));
            int c=Integer.parseInt(req.getParameter("confirm"));
            int otp=Integer.parseInt(req.getParameter("otp"));
            int motp=(int)s.getAttribute("otp");
            if(atm.getValue(acntnum,"pin")==null){
                s.setAttribute("acntX", 0);
                res.sendRedirect("changepin.jsp");
            } 
            else if(otp!=motp){
                s.setAttribute("otpX", 0);
                res.sendRedirect("changepin.jsp");
            }
            else if(n!=c){
                s.setAttribute("pinX", 0);
                res.sendRedirect("changepin.jsp");
            }
            else{
                if(atm.setPin(acntnum, n)){
                    s.removeAttribute("otp");
                    if(s.getAttribute("pin")!=null){
                        s.setAttribute("pin",n);
                        res.sendRedirect("logout.jsp");
                    }
                    else
                        res.sendRedirect("logout.jsp");
                }
            }
        }catch(Exception e){
            res.sendRedirect("menu.jsp");
        }
    }
    
    
}
