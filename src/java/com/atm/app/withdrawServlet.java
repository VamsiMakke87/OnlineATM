/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demo.app;

import com.atm.dao.ATMDAO;
import com.atm.dao.TransactionDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author dell
 */
@WebServlet(name = "withdrawServlet", urlPatterns = {"/withdraw"})
public class withdrawServlet extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws IOException {
            
            PrintWriter out=response.getWriter();
            HttpSession session=request.getSession();
            TLSEmail t=new TLSEmail();
            TransactionDAO tr;
            ATMDAO atm;
        try {
            atm = new ATMDAO();
            tr=new TransactionDAO();
            int amnt=Integer.parseInt(request.getParameter("withamt"));
            String actnum=session.getAttribute("acntnum").toString();
            String name=session.getAttribute("name").toString();
            String mail=session.getAttribute("mail").toString();
            int mainbal=Integer.parseInt(atm.getValue(actnum, "balance"));
            if(mainbal>amnt){
                mainbal-=amnt;
                atm.setBalance(actnum, mainbal);
                t.withdraw(mail,name,actnum,amnt);
                session.setAttribute("balance",mainbal);
                tr.updateTransaction(actnum,"Withdraw -"+amnt+" - -");
                session.setAttribute("transaction", tr.getTransaction(actnum));
                session.setAttribute("successfull", "Transaction Successfull");
                response.sendRedirect("menu.jsp");
            }
            else{
                session.setAttribute("withdraw", 0);
                response.sendRedirect("withdraw.jsp");  
            }
         } catch (Exception ex) {
            session.setAttribute("unsuccessfull", "Unsuccessfull,Please try again after some time");
            response.sendRedirect("withdraw.jsp");    
        }
    }




}
