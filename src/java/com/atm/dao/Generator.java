/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.atm.dao;

import com.demo.app.TLSEmail;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

/**
 *
 * @author dell
 */
@WebServlet("/generate")
public class Generator extends HttpServlet{
    
    
    HttpSession session;
    TLSEmail m=new TLSEmail();
   Random r=new Random();
    public int pingen(){
        int x=1000+r.nextInt(8999);
        return x;
    }
    public int otpgen(){
        int x=100000+r.nextInt(899999);
        return x;
    }
    
    public void changePin(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{
        int otp=otpgen();
        session.setAttribute("otp", otp);
        String toEmail=session.getAttribute("mail").toString();
        String name=session.getAttribute("name").toString();
        m.sendpinOtp(toEmail,name, otp); 
         
    }
    
    public void tfa_mail(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{
        int otp=otpgen();
        session.setAttribute("otp", otp);
        String toEmail=session.getAttribute("mail").toString();
        String name=session.getAttribute("name").toString();
        System.out.println("*1");
        m.sendOtp(toEmail,name, otp); 
    }
    
    public void change_mail(HttpServletRequest req,HttpServletResponse res,String toEmail)throws ServletException,IOException{
        int otp=otpgen();
        session.setAttribute("otp", otp);
        String name=session.getAttribute("name").toString();
        m.changemailOtp(toEmail,name, otp); 
    }
    
    char capGen() {
		return (char)(r.nextInt(26)+'A');
	}
	
	char smallGen() {
		return (char)(r.nextInt(26)+'a');
	}
	int numGen() {
		return 100000+(r.nextInt(899999));
	}
       public  String tID() {
		return ""+capGen()+smallGen()+""+numGen()+capGen()+smallGen()+numGen();
	}
        
    public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{ 
        session=req.getSession();
        if(session.getAttribute("changepin")!=null){
            session.removeAttribute("changepin");
            changePin(req,res);
            res.sendRedirect("changepin.jsp");
            
        }
        else if(session.getAttribute("mailtwo")!=null){
            String mail=session.getAttribute("mailtwo").toString();
            change_mail(req, res, mail);
            res.sendRedirect("tfa.jsp");
        }
        else if(session.getAttribute("tfa")!=null){
            session.removeAttribute("tfa");
            tfa_mail(req,res);
            res.sendRedirect("tfa.jsp");
        }
          
    }
    
     public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{
         session=req.getSession();
         boolean b=false;
         if(!req.getParameter("fname").equals("")){
             b=true;
             session.setAttribute("fname2",req.getParameter("fname"));
         }
         if(!req.getParameter("lname").equals("")){
             b=true;
             session.setAttribute("lname2",req.getParameter("lname"));
         }
         if(req.getParameter("tfaradio").equals("On")){
             b=true;
             session.setAttribute("Tfa2","On");
            }
         else{
             b=true;
             session.setAttribute("Tfa2","Off");
            }
         if(!req.getParameter("mail").equals("")){
            b=true;
            String mail=req.getParameter("mail");
            session.setAttribute("mailtwo", mail);
            change_mail(req, res, mail);
            res.sendRedirect("tfa.jsp");
       }
        else if(b)
            res.sendRedirect("updatedetails");
     }
 
    
}
