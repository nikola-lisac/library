package org.bildit.library.service;

import java.util.List;

import org.bildit.library.model.Book;
import org.bildit.library.model.User;

public interface BookService {
	List<Book> getAllBooks();
	boolean deleteBook(Book book);
	Book getBookById(Long id);
	Book getBookByName(String bookName);
	boolean saveBook(Book book);
	void updateBook(Book book);
	boolean batchAddBooks(List<Book> books);
	void rentBook(Book book, User user);
	void returnBook(Book book, User user);
}
