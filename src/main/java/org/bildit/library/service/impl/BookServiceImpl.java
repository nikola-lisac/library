package org.bildit.library.service.impl;

import java.util.List;

import org.bildit.library.dao.BookDao;
import org.bildit.library.model.Book;
import org.bildit.library.model.User;
import org.bildit.library.service.BookService;
import org.bildit.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("bookService")
public class BookServiceImpl implements BookService {

	@Autowired
	private BookDao bdao;

	@Override
	public List<Book> getAllBooks() {
		// TODO Auto-generated method stub
		return bdao.getAllBooks();
	}

	@Override
	public boolean deleteBook(Book book) {
		// TODO Auto-generated method stub
		return bdao.deleteBook(book);
	}

	@Override
	public Book getBookById(Long id) {
		// TODO Auto-generated method stub
		return bdao.getBookById(id);
	}

	@Override
	public Book getBookByName(String bookName) {
		// TODO Auto-generated method stub
		return bdao.getBookByName(bookName);
	}

	@Override
	public boolean saveBook(Book book) {
		// TODO Auto-generated method stub
		return bdao.saveBook(book);
	}

	@Override
	public void updateBook(Book book) {
		// TODO Auto-generated method stub
		bdao.updateBook(book);
	}

	@Override
	public boolean batchAddBooks(List<Book> books) {
		// TODO Auto-generated method stub
		return bdao.batchAddBooks(books);
	}

	@Override
	public void rentBook(Book book, User user) {
		// TODO Auto-generated method stub
		if (book.getAvailableBooks() > 0) {
			book.getUsersRented().add(user);
			book.setAvailableBooks(book.getAvailableBooks() - 1);
			updateBook(book);
		} else {
			// to implement better response mofo
			System.out.println("NO BOOKS AVAILABLE");
		}
	}

	@Override
	public void returnBook(Book book, User user) {
		// TODO Auto-generated method stub
		if(user.getListOfBooksApproved().contains(book)) {
			user.getListOfBooksApproved().remove(book);
			book.setAvailableBooks(book.getAvailableBooks() + 1);
			updateBook(book);
		}
	}

}
