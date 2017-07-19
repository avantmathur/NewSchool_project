package com.revature.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.bean.User;

@WebServlet("/teacher")
public class TeacherServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws IOException, ServletException{
		resp.setHeader("Cache-Control","no-cache"); 
		resp.setHeader("Cache-Control","no-store"); 
		
		HttpSession session = req.getSession();
		User clientUser = (User)session.getAttribute("user");
		if(clientUser == null){
			resp.sendRedirect("/NewSchool/");
		}else{
			System.out.println("Namaste");
			String userInfo = new ObjectMapper().writeValueAsString(clientUser);

			resp.getWriter().write(userInfo);

		}
		
	}
}

