package org.bildit.library.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.bildit.library.model.User;
import org.bildit.library.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

	@Autowired
	private UserServiceImpl userService;

	@RequestMapping(value = { "/", "home", "index" })
	public String goHome(Model model) {
		String[] userAuth = getPrincipal();
		model.addAttribute("user", userAuth[0]);
		model.addAttribute("auth", userAuth[1]);
		if (userAuth[1].equals("ROLE_ADMIN")) {
			model.addAttribute("username", userAuth[0]);
			return "forward:/admin/control";
		} else if (userAuth[1].equals("ROLE_USER")) {
			return "forward:/user/control";
		}
		return "home";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "forward:/login?logout";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		System.out.println("CALLED GET LOGIN");
		return "login";
	}

	@RequestMapping("/register")
	public String register(Model model) {
		model.addAttribute("user", new User());
		return "register";
	}

	@RequestMapping("/user/control")
	public String userCtrl(Model model) {
		model.addAttribute("type", "USER");
		return "control";
	}

	@RequestMapping("/admin/control")
	public String adminCtrl(Model model) {
		model.addAttribute("type", "ADMIN");
		return "control";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String newUser(Model model, @Valid @ModelAttribute("user") User user, BindingResult result) {
		if (userService.containsUsername(user.getUsername())) {
			result.rejectValue("username", "DuplicateKey.user.username", "This username is taken.");
			return "register";
		}

		if (!user.getPassword().equals(user.getConfirmPassword())) {
			result.rejectValue("confirmPassword", "user.confirmPassword", "Passwords do not match.");
			return "register";
		}
		if (!user.getPassword().matches("^(?=.*[0-9])(?=.*[a-z])([a-z0-9_-]+)$")) {
			result.rejectValue("password", "user.password", "Password must contain characters and numeric values.");
			return "register";
		}
		if (user.getPassword().length() > 20) {
			result.rejectValue("password", "user.password", "Password must be 20 characters at most.");
			return "register";
		}
		if (result.hasErrors()) {
			return "register";
		}
		userService.saveUser(user);
		model.addAttribute("message", "You registered successfully.");
		return "home";
	}

	// ovako dobijamo ko je ulogovan i ovlaštenja
	protected static String[] getPrincipal() {
		String username = null;
		String authority = null;
		String[] userAuth = { "anon", "anon" };
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
			authority = ((UserDetails) principal).getAuthorities().toArray()[0].toString();
			userAuth = new String[] { username, authority };
		} else {
			username = principal.toString();
		}
		return userAuth;
	}
}
