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
@WebServlet(name = "depositServlet", urlPatterns = {"/deposit"})
public class depositServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
       
        ATMDAO atm=new ATMDAO();
        TransactionDAO tr=new TransactionDAO();
        HttpSession session=request.getSession();
        TLSEmail t=new TLSEmail();
        String actnum=session.getAttribute("acntnum").toString();
        int amnt=Integer.parseInt(request.getParameter("depamt"));
        int mainbal;
        try {
            mainbal = Integer.parseInt(atm.getValue(actnum, "balance"));
            mainbal+=amnt;
             String name=session.getAttribute("name").toString();
            String mail=session.getAttribute("mail").toString();
            atm.setBalance(actnum, mainbal);
            session.setAttribute("balance",mainbal);
            session.setAttribute("successfull", "Transaction Successfull");
            t.deposit(mail,name, actnum, amnt);
            tr.updateTransaction(actnum,"Deposit +"+amnt+" - -");
            session.setAttribute("transaction", tr.getTransaction(actnum));
            response.sendRedirect("menu.jsp");
        } catch (Exception ex) {
            session.setAttribute("unsuccessfull", "Unsucessfull,Please try again after some time");
            response.sendRedirect("menu.jsp");
        }
    }

   
}
