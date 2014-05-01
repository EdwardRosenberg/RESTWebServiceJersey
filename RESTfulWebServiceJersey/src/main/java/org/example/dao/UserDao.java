package org.example.dao;

import java.util.List;

import org.example.domain.User;

public interface UserDao {

	List<User> getAllUsers();

	List<User> getUserByName(String name);

	void createUser(User user);

}
