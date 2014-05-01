package org.example.web.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.example.dao.UserDao;
import org.example.domain.User;
import org.springframework.beans.factory.annotation.Autowired;

public class UserService {
	public static UserService userService = new UserService();
	public static final String GET_USER = "SELECT * FROM USER";
	public static final String INSERT_USER = "Insert into user ";
	
	@Autowired
	private UserDao userDao;
	
	public List<User> getAllUsers() throws ClassNotFoundException, SQLException {
		List<User> users = userDao.getAllUsers();
		return users;
	}

	public List<User> getUserByName(String name) throws ClassNotFoundException, SQLException {
		//String SQL_WHERE_CAS = " where name='" + name + "'";
		List<User> users = userDao.getUserByName(name);
		//		GET_USER + SQL_WHERE_CAS);
		return users;
	}

	public void CreateUser(User user) throws SQLException, ClassNotFoundException {
		String SQL_WHERE_CASE = " VALUES('" + user.getUserName() + "','"
				+ user.getUserPassword() + "')";
		
		userDao.createUser(user);
		
		//DataServiceHelper.getInstance().executeUpdateQuery(
		//		INSERT_USER + SQL_WHERE_CASE);
	}
}