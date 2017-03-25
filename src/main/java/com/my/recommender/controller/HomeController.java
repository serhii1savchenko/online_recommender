package com.my.recommender.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Handles requests for the all pages
 */

@Controller
public class HomeController {

	@GetMapping({ "", "/", "home" })
	public String mainPage() {
		return "home";
	}

	@GetMapping( "login" )
	public String login() {
		return "login";
	}

	@GetMapping( "logout" )
	public String logout() {
		return "logout";
	}
	
	@GetMapping( "registration" )
	public String registration() {
		return "registration";
	}

	@GetMapping( "admin" )
	public String admin() {
		return "adminPage";
	}
	
	@GetMapping( "user" )
	public String user() {
		return "userPage";
	}
	
}
