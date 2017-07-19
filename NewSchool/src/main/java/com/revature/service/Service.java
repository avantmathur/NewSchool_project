package com.revature.service;


import com.revature.bean.User;
import com.revature.dao.UserDao;
import com.revature.dao.UserDaoImpl;

public class Service {

	/***returns if email and ps exists***/
	public static User checkUser(User us) {
		UserDao dao = new UserDaoImpl();
		User checkUser = null; 
		checkUser = dao.getUserByUserName(us);
		if(checkUser != null){
			if(us.getUserPassword().equals(checkUser.getUserPassword())){
				return checkUser;
			}else{
				return null;
			}
		}
			
		return null;
	}
	
	public static void setUser(User use){
		UserDao dao = new UserDaoImpl();
		dao.saveUser(use);
	}
}
