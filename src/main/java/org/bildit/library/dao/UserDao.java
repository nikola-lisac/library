package org.bildit.library.dao;

import java.util.List;
import java.util.Set;

import org.bildit.library.model.User;

public interface UserDao {

	Set<User> getAllUsers();
	boolean deleteUser(User user);
	User getUserById(Long id);
	User getUserByUsername(String username);
	boolean saveUser(User user);
	void updateUser(User user);
	boolean batchAddUsers(List<User> users);
	boolean containsUsername(String username);
}
