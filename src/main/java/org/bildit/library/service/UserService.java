package org.bildit.library.service;

import java.util.List;

import org.bildit.library.model.Book;
import org.bildit.library.model.User;

public interface UserService {

	List<User> getAllUsers();
	boolean deleteUser(User user);
	User getUserById(Long id);
	User getUserByUsername(String username);
	boolean saveUser(User user);
	void updateUser(User user);
	boolean batchAddUsers(List<User> users);
	void rentBookToUser(String username, Book book);
	void returnBook(String username, Book book);
	boolean containsUsername(String username);
}
