package com.demo.app;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;


public class TLSEmail {
    
        static Authenticator auth;
        static Properties props;
        public static void access(){
            final String fromEmail = "gmail@gmail.com"; //Your valid gmail id
		final String password = "******"; // correct password for gmail id 
		
		System.out.println("TLSEmail Start");
		props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com"); 
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true"); 
		props.put("mail.smtp.starttls.enable", "true"); 
		
		auth = new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		};
        }
         
	public static void send(String toEmail,String name) {  //tfaoff
		access();
		Session session = Session.getInstance(props, auth);
		EmailUtil.sendEmail(session, toEmail,"Login Detected", "Hello "+name+",\n Someone logged into your account.\nIf it is you kindly ignore this message else consider changing your PIN and turn on Two-Factor Authenication in profile for better protection");
		
	}
        public static void withdraw(String toEmail,String name,String acntnum,int amnt){
            access();
	    Session session = Session.getInstance(props, auth);
	    EmailUtil.sendEmail(session, toEmail,"Wihtdraw", "Hello "+name+",\nRs."+amnt+" is debited succesfully from your account xxxxxxx"+acntnum.substring(7, 10));
		
        }
        public static void deposit(String toEmail,String name,String acntnum,int amnt){
            access();
	    Session session = Session.getInstance(props, auth);
	    EmailUtil.sendEmail(session, toEmail,"Deposit","Hello "+name+",\nRs."+amnt+" is credited succesfully to your account xxxxxxx"+acntnum.substring(7, 10));
		
        }
        public static void transfer(String frommail,String tomail,String fromanum,String toanum,String tid,int sender,int recipient,int amnt){
            access();
	    Session session = Session.getInstance(props, auth);
	    EmailUtil.sendEmail(session, frommail,"Transfer", "Hello, \nRs."+amnt+" is debited succesfully from your account xxxxxxx"+fromanum.substring(7, 10)+" to xxxxxxx"+toanum.substring(7, 10)+"\nTransaction ID:"+tid+"\nBalance: Rs."+sender);
	    EmailUtil.sendEmail(session, tomail,"Transfer", "Hello, \nRs."+amnt+" is credited succesfully to your account xxxxxxx"+toanum.substring(7, 10)+" from xxxxxxx"+fromanum.substring(7, 10)+"\nTransaction ID:"+tid+"\nBalance: Rs."+recipient);

        }
        public static void sendpinOtp(String toEmail,String name,int otp){  //changepinotp
                access();
                Session session = Session.getInstance(props, auth);
		EmailUtil.sendEmail(session, toEmail,"CHANGEPIN OTP", "Hello "+name+",\n The OTP to reset your PIN is: "+otp);
		
        }
        
         public static void sendOtp(String toEmail,String name,int otp){  //tfaotp
                access();
                Session session = Session.getInstance(props, auth);
		EmailUtil.sendEmail(session, toEmail,"TFA OTP", "Hello "+name+",\nThe OTP to acess your account is: "+otp);
		
        }

    public void changemailOtp(String toEmail, String name, int otp) { 
                 access();
                Session session = Session.getInstance(props, auth);
		EmailUtil.sendEmail(session, toEmail,"CHANGE MAIL OTP", "Hello "+name+",\nThe OTP to change your mail is: "+otp);
    }
    
    public void mailchanged(String name,String oldmail,String newmail) {
                access();
                Session session = Session.getInstance(props, auth);
		EmailUtil.sendEmail(session, oldmail,"Mail Changed", "Hello "+name+",\n Your Mail has been changed from "+oldmail+" to "+newmail+".");
                EmailUtil.sendEmail(session, newmail,"Mail Changed", "Hello "+name+",\n Your Mail has been changed from "+oldmail+" to "+newmail+".");

    }

	
}
