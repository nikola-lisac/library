package org.bildit.library.controllers;

import javax.validation.Valid;

import org.bildit.library.model.User;
import org.bildit.library.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Nikola Lisicic (Goran je nesto dodao takodje, mozda, povremeno, vise je smetao)
 *
 */
@Controller
public class RegisterController {
	
	@Autowired
	private UserServiceImpl userService;
	
	@RequestMapping(value="/newuser",method=RequestMethod.POST)
	public String newUser(Model model, @Valid @ModelAttribute("user") User user, BindingResult result){
		if (userService.containsUsername(user.getUsername())) {
			result.rejectValue("username", "DuplicateKey.user.username", "This username is taken.");
			return "register";
		}
		
		if (!user.getPassword().equals(user.getConfirmPassword())) {
			result.rejectValue("confirmedPassword", "user.confirmedPassword", "Passwords do not match.");
			return "register";
		}
		if(!user.getPassword().matches("^(?=.*[0-9])(?=.*[a-z])([a-z0-9_-]+)$")) {
			result.rejectValue("password", "user.password", "Password must contain characters and numeric values.");
			return "register";
		}
		if(user.getPassword().length()>20) {
			result.rejectValue("password", "user.password", "Password must be 20 characters at most.");
			return "register";
		}
		if(result.hasErrors()){
			return "register";
		}
		userService.saveUser(user);
		model.addAttribute("message", "You registered successfully.");
		return "home";
	}
	
	
}
