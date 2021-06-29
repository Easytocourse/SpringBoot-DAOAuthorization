package com.easytocourse.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.easytocourse.UserUtility.CustomUserDetails;
import com.easytocourse.model.User;
import com.easytocourse.repository.UserRepository;

@RestController
public class AppController {

	@Autowired
	private UserRepository userRepo;

	@PostMapping("/register")
	public String processRegister(@RequestBody User user) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);

		userRepo.save(user);

		return "register_success";
	}

	@GetMapping("/users")
	public List<User> listUsers() {
		return userRepo.findAll();

	}
	
	String name="";
	@ModelAttribute("foo")
	public Void foo() {
		CustomUserDetails principal = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		name = principal.Fullname();

		return null;

	}
	
	@GetMapping("")
	public String viewHomePage() {
		return "Welcome to Wankhede Stadium, Mumbai";
	}

	@GetMapping("/batting")
	public String batting() {
		return "Welcome to Batting-->" + name;
	}

	@GetMapping("/bowling")
	public String bolwing() {
		return "Welcome to bowling-->" + name;
	}

	@GetMapping("/keeping")
	public String wikectkeeping() {
		return "Welcome to wikcetkeeping-->" + name;
	}

	@GetMapping("/umpiring")
	public String umpiring() {
		return "Welcome to umpring--->" + name;
	}
	
	
	
	
	

}