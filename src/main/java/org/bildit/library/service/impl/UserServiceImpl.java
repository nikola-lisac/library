package org.bildit.library.service.impl;

import java.util.List;
import java.util.Set;

import org.bildit.library.dao.UserDao;
import org.bildit.library.model.Book;
import org.bildit.library.model.User;
import org.bildit.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Ognjen Mišiæ Ovo je samo wrapper klasa za dao jer inaèe ne želite u
 *         servisni sloj dati pristup direktno dao metodama. Dodan metod koji
 *         izdaje knjigu korisniku.
 *
 */
@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao udao;
	
	@Autowired
	private BookServiceImpl bookservice;

	@Override
	public Set<User> getAllUsers() {
		// TODO Auto-generated method stub
		return udao.getAllUsers();
	}

	@Override
	public boolean deleteUser(User user) {
		// TODO Auto-generated method stub
		return udao.deleteUser(user);
	}

	@Override
	public User getUserById(Long id) {
		// TODO Auto-generated method stub
		return udao.getUserById(id);
	}

	@Override
	public User getUserByUsername(String username) {
		// TODO Auto-generated method stub
		return udao.getUserByUsername(username);
	}

	@Override
	public boolean saveUser(User user) {
		// TODO Auto-generated method stub
		return udao.saveUser(user);
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		udao.updateUser(user);
	}

	@Override
	public boolean batchAddUsers(List<User> users) {
		// TODO Auto-generated method stub
		return udao.batchAddUsers(users);
	}

	@Override
	public void rentBookToUser(String username, Book book) {
		// TODO Auto-generated method stub
		User user = udao.getUserByUsername(username);
		user.getListOfBooksApproved().add(book);
		bookservice.rentBook(book, user);
		updateUser(user);
	}

	@Override
	public void returnBook(String username, Book book) {
		// TODO Auto-generated method stub
		User user = udao.getUserByUsername(username);
		user.getListOfBooksApproved().remove(book);
		bookservice.returnBook(book, user);
		updateUser(user);
	}

	@Override
	public boolean containsUsername(String username) {
		// TODO Auto-generated method stub
		return udao.containsUsername(username);
	}

}
