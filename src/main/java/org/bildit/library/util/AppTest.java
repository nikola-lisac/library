package org.bildit.library.util;

import java.util.Arrays;
import java.util.List;

import org.bildit.library.model.Book;
import org.bildit.library.model.User;
import org.bildit.library.service.impl.BookServiceImpl;
import org.bildit.library.service.impl.UserServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppTest {
	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext("org.bildit.library.config");
		UserServiceImpl userService = (UserServiceImpl) ctx.getBean("userService");
		BookServiceImpl bookService = (BookServiceImpl) ctx.getBean("bookService");
//		List<User> users = Arrays.asList(new User("Nikola", "Lisièiæ"), new User("Ognjen", "Mišiæ"),
//				new User("Goran", "Milovanoviæ"), new User("Bojan", "Aleksiæ"), new User("Novica", "Sekuliæ"));
//		List<Book> books = Arrays.asList(new Book("Author 1", "Title 1", 12), new Book("Author 2", "Title 2", 8),
//				new Book("Author 2", "Title 3", 3), new Book("Author 3", "Title 4", 9),
//				new Book("Author 3", "Title 5", 1));
//
//		if (userService.batchAddUsers(users)) {
//			System.out.println("successfully added " + users.size() + " users.");
//		}
//		if (bookService.batchAddBooks(books)) {
//			System.out.println("successfully added " + books.size() + " books.");
//		}
		((AnnotationConfigApplicationContext) ctx).close();
	}
}
