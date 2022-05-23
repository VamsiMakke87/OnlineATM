package com.demo.app;


import com.atm.dao.ATMDAO;
import com.atm.dao.Generator;
import com.atm.dao.TidDAO;
import com.atm.dao.TransactionDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/transfer")
public class transferServlet extends HttpServlet {
    
    
        public String getTime(){
        SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss ");
        Date d=new Date();
        return(sdf.format(d));
    }
    
    protected  void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
         
        ATMDAO atm=new ATMDAO();
        TransactionDAO tr=new TransactionDAO();
        TidDAO td=new TidDAO();
        Generator g=new Generator();
        TLSEmail t=new TLSEmail();
        PrintWriter out=response.getWriter();
        HttpSession session=request.getSession();
        try{
        String actnum=request.getParameter("traamt").toUpperCase();
        int amnt=Integer.parseInt(request.getParameter("depamt"));
        String anum=session.getAttribute("acntnum").toString();
        int sender=Integer.parseInt(atm.getValue(anum, "balance"));
        if(actnum.equals(anum)){
            session.setAttribute("sameacntnum", 1);
            response.sendRedirect("transfer.jsp");
        }
        else if(atm.getValue(actnum, "firstname")==null){
            session.setAttribute("invalid", 0);
            response.sendRedirect("transfer.jsp");
        }
        else if(amnt>sender){
            session.setAttribute("withdraw", 0);
            response.sendRedirect("transfer.jsp");
        }
        else{
            int recipient;
            try {
                String time=getTime();
                String tid=g.tID();
                recipient = Integer.parseInt(atm.getValue(actnum, "balance"));
                recipient+=amnt;
                sender-=amnt;
                String tomail=atm.getValue(actnum, "mail");
                String frommail=atm.getValue(anum, "mail");
                atm.setBalance(actnum, recipient);atm.setBalance(anum, sender);
                session.setAttribute("balance",sender);
                session.setAttribute("successfull", "Transaction Successfull");
                tr.updateTransaction(actnum,time+tid+" +"+amnt+" "+anum+" "+actnum);
                tr.updateTransaction(anum,time+tid+" -"+amnt+" "+anum+" "+actnum);
                session.setAttribute("transaction", tr.getTransaction(anum));
                td.setValues(tid, anum, actnum, time, amnt);
                t.transfer(frommail,tomail, anum, actnum,tid,sender,recipient, amnt);
                response.sendRedirect("menu.jsp");
            } catch (Exception ex) {
                response.sendRedirect("menu.jsp");
            }
        }
        }catch(Exception e){
            session.setAttribute("unsuccessfull", "Unsuccessfull,Please try again after some time");
            out.print(e);
        }
    }
    
}
