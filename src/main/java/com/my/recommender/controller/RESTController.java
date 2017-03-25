package com.my.recommender.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Handles all REST requests
 */

@RestController
public class RESTController {
	
	@PostMapping( "createUser" )
	public String createUser() {
		return "home";
	}
	
}
