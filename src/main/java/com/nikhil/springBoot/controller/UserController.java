package com.nikhil.springBoot.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nikhil.springBoot.dto.UserDTO;
import com.nikhil.springBoot.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	UserDetailsService userDetailsService;

	@Autowired
	private UserService userService;
	
	@GetMapping("/home")
	public ModelAndView home(ModelAndView mav) {
		mav.addObject("message", "Welcome Aliens");
		mav.setViewName("welcome");
		return mav;
	}

	@GetMapping("/registration")
	public ModelAndView registerPage(ModelAndView mav, UserDTO userDto, @ModelAttribute("message") String message) {
		mav.addObject("user", userDto);
		mav.addObject("message", message);
		mav.setViewName("register");
		return mav;
	}

	@PostMapping("/registration")
	public ModelAndView saveUser(ModelAndView mav, @ModelAttribute UserDTO user, RedirectAttributes redirectAttribute) {
		userService.saveUser(user);
		redirectAttribute.addFlashAttribute("message", "User registered successfully...");
		mav.setViewName("redirect:/registration");
		return mav;
	}
	
	@GetMapping("/login")
	public ModelAndView login(ModelAndView mav) {
		mav.addObject("message", "loginpage");
		mav.setViewName("login");
		return mav;
	}
	
	@GetMapping("logout-success")
	public String logout() {
		return "login";
	}
	


}
