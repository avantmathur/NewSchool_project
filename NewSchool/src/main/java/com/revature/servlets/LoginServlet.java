package com.revature.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.bean.User;
import com.revature.service.Service;

@WebServlet("/login")
public class LoginServlet extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setHeader("Cache-Control","no-cache"); 
		resp.setHeader("Cache-Control","no-store"); 

		User clientUser = new User();
	
		String username = req.getParameter("username").toLowerCase();
		String password = req.getParameter("password");
		
		
		clientUser.setUserUsername(username);
		clientUser.setUserPassword(password);
		
		clientUser = Service.checkUser(clientUser); 
		
		if(clientUser != null){
			
			HttpSession session = req.getSession(); 
			
			session.setAttribute("user", clientUser); 
			System.out.println(clientUser.getUserRoleId());
			if(clientUser.getUserRoleId() == 1){
				req.getRequestDispatcher("Student.html").forward(req,  resp);
			}else if(clientUser.getUserRoleId() == 2){
				req.getRequestDispatcher("Teacher.html").forward(req,  resp);
			}
		}else{
			resp.sendRedirect("/NewSchool/");
		}
	}

}

