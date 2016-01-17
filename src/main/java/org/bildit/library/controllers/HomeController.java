package org.bildit.library.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping(value = { "/", "home", "index" })
	public String goHome() {
		System.out.println("YO GUISE THIS IS GOING THROUGH HOME");
		return "home";
	}

	@RequestMapping("/login")
	public String login() {
		return "login";
	}
}
