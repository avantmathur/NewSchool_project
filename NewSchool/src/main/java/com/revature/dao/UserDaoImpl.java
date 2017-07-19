package com.revature.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.bean.User;

public class UserDaoImpl implements UserDao {


	private static final String USERNAME = "school_db";
	private static final String PASSWORD = "p4ssw0rd";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";

	
	@Override
	public void saveUser(User user) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
		String sql = "INSERT INTO users(u_first_name, u_last_name, u_password, u_email, u_username, u_role_id) VALUES(?,?,?,?,?,?)";
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);){
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUserFirstName());
			ps.setString(2, user.getUserLastName());
			ps.setString(3, user.getUserPassword());
			ps.setString(4, user.getUserEmail());
			ps.setString(5, user.getUserUsername());
			ps.setInt(6, user.getUserRoleId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public User getUserByUserName(User user) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
		User returnedUser = null;
		String sql = "SELECT * FROM users WHERE u_username = ?";
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);){
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUserUsername());
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				returnedUser = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return returnedUser;
	}


}
