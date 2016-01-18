package org.bildit.library.dao;

import java.util.List;
import java.util.Set;

import org.bildit.library.model.Book;

public interface BookDao {

	Set<Book> getAllBooks();
	boolean deleteBook(Book book);
	Book getBookById(Long id);
	Book getBookByName(String bookName);
	boolean saveBook(Book book);
	void updateBook(Book book);
	boolean batchAddBooks(List<Book> books);
	
}
