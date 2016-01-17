package org.bildit.library.controllers;

import java.util.List;

import org.bildit.library.model.Book;
import org.bildit.library.model.User;
import org.bildit.library.service.impl.BookServiceImpl;
import org.bildit.library.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

	@Autowired
	private UserServiceImpl userService;
	@Autowired
	private BookServiceImpl bookService;

	@RequestMapping(value = { "/", "home", "index" })
	public String goHome(Model model) {
		List<User> allUsers = userService.getAllUsers();
		model.addAttribute("users", allUsers);
		return "home";
	}

	@RequestMapping("/user")
	public String goUser(@RequestParam("username") String username, Model model) {
		model.addAttribute("user", userService.getUserByUsername(username));
		model.addAttribute("books", bookService.getAllBooks());
		List<Book> listOfBooks = userService.getUserByUsername(username).getListOfBooksApproved();
		model.addAttribute("usersBooks", listOfBooks);
		return "user";
	}

	@RequestMapping("/rentbook")
	public String rentBook(@RequestParam("username") String username, @RequestParam("bookname") String bookName,
			Model model) {
		userService.rentBookToUser(username, bookService.getBookByName(bookName));
		return "redirect:/user?username="+username;
	}
	
	@RequestMapping("/register")
	public String register(Model model){
		model.addAttribute("user", new User());
		return "register";
	}
}
