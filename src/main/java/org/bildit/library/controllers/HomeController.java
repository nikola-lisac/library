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

	@RequestMapping(value = { "/", "home", "index" })
	public String goHome(Model model) {
		List<User> allUsers = userService.getAllUsers();
		model.addAttribute("users", allUsers);
		return "home";
	}

	@RequestMapping("/register")
	public String register(Model model){
		model.addAttribute("user", new User());
		return "register";
	}
}
