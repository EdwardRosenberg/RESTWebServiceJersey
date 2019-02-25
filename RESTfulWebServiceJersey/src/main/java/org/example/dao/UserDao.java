package org.example.dao;

import java.util.List;

import org.example.domain.User;

public interface UserDao {

	List<User> getAllUsers();

	List<User> searchUsers(User user);

	User getUser(long id);

	void createUser(User user);

	void updateUser(User user);

	void deleteUser(long id);
}
