package org.example.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.example.dao.UserDao;
import org.example.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("userDao")
public class UserDaoImpl implements UserDao {

	private Connection connection = null;
	
	@Autowired
	DataSource dataSource;


	@Override
	public List<User> getAllUsers() {

		List<User> users = null;
			try {
				users = executeQuery("SELECT * FROM USER");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return users;
	}

	@Override
	public List<User> getUserByName(String name) {
		List<User> users = null;
		try {
			users = executeQuery("SELECT * FROM USER where name='" + name
					+ "'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;
	}

	@Override
	public void createUser(User user) {

		try {
			executeUpdateQuery("Insert into user VALUES('" + user.getUserName()
					+ "','" + user.getUserPassword() + "')");
			closeConnection(); 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void executeUpdateQuery(String query) {
		Connection con;
		try {
			con = getConnection();
			Statement stmt = con.createStatement();
			stmt.execute(query);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				closeConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public List<User> executeQuery(String query) throws ClassNotFoundException, SQLException {
		Connection con = getConnection();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		List<User> als = convertPojoList(rs);
		closeConnection();
		return als;
	}

	private List<User> convertPojoList(ResultSet rs) {
		List<User> users = new ArrayList<User>();
		try {
			while (rs.next()) {
				User user = new User(rs.getString("name"),
						rs.getString("password"));
				users.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;
	}

	private Connection getConnection() throws ClassNotFoundException, SQLException {
		connection = dataSource.getConnection();
		return connection;
	}

	private void closeConnection() throws SQLException {
		if (isConnectionOpen()) {
			connection.close();
			connection = null;
		}
	}

	private boolean isConnectionOpen() {
		return (connection != null);
	}

}
