package com.revature.service;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import com.revature.bean.User;



public class Emailer implements Runnable{
	private HttpServletRequest req;
	final String person_sender = "axel.foley2017@gmail.com";
	final String password = "revature2017";
	
	public Emailer(HttpServletRequest req){
		this.req = req;
	}
	
	@Override
	public void run() {
		User u = (User)(req.getSession().getAttribute("user"));
		String receiver = u.getUserEmail();
		
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(person_sender, password);
			}
		  });

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(person_sender));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(receiver));
			message.setSubject("Welcome to School, " + u.getUserFirstName() + " " + u.getUserLastName());
			message.setText("Welcome to School, " + u.getUserFirstName() + " " + u.getUserLastName() + "!\n" 
						  + "You've registered with the following information: \n"
						  + "Email: " + u.getUserEmail() + "\n"
				  		  + "Name: " + u.getUserFirstName() + " " + u.getUserLastName() + "\n"
		  		  		  + "Username: " + u.getUserUsername() + "\n"
  		  		  		  + "Password: " + u.getUserPassword());

			Transport.send(message);

			System.out.println("Finish");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
