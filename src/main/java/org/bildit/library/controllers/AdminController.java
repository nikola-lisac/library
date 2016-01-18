package org.bildit.library.controllers;

import javax.validation.Valid;

import org.bildit.library.model.Book;
import org.bildit.library.service.impl.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminController {
	@Autowired
	private BookServiceImpl bookService;

	@RequestMapping(value = "/newbook", method = RequestMethod.POST)
	public String newBook(Model model, @Valid @ModelAttribute("book") Book book, BindingResult result) {

		if (result.hasErrors()) {
			return "newbook";
		}
		return "home";

	}
}
