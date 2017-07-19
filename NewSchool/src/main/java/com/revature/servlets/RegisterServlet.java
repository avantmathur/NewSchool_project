package com.revature.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.bean.User;
import com.revature.service.Emailer;
import com.revature.service.Service;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws IOException, ServletException{
		
		User clientUser = new User();
		
		String username = req.getParameter("username").toLowerCase();
		String email = req.getParameter("email").toLowerCase();
		String fname = req.getParameter("fname");
		String lname = req.getParameter("lname");
		String password = null;
		if(req.getParameter("password").equals(req.getParameter("password2"))){
			password = req.getParameter("password");
		}else{
			resp.sendRedirect("register.html");
		}
		int roleId = 0;
		if(req.getParameter("roleOptions").equals("1") || req.getParameter("roleOptions").equals("2")){
			roleId = Integer.parseInt(req.getParameter("roleOptions"));
		}
		
		if(username == null || email == null || fname == null || lname == null || roleId == 0){
			Thread th = new Thread(new Emailer(req));
			th.start();

			resp.sendRedirect("register.html");
		}
		clientUser.setUserUsername(username);
		clientUser.setUserEmail(email);
		clientUser.setUserFirstName(fname);
		clientUser.setUserLastName(lname);
		clientUser.setUserPassword(password);
		clientUser.setUserRoleId(roleId);
		User clientUsertwo = Service.checkUser(clientUser);
		if(clientUsertwo == null){
			Service.setUser(clientUser);
			resp.sendRedirect("/NewSchool/");
		}else{
			resp.sendRedirect("register.html");
		}
		
	}
}
