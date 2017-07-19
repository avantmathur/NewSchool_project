package com.revature.dao;

import com.revature.bean.User;

public interface UserDao  {

	public User  getUserByUserName(User user);
	public void  saveUser(User user);
}
