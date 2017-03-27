package com.my.recommender.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.my.recommender.service.FilmService;
import com.my.recommender.service.UserService;

@Controller
@SessionAttributes("id")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	FilmService filmService;
	
	@GetMapping( "myFilms" )
	public String myFilms(Model model, @SessionAttribute int id) {
		model.addAttribute("films", userService.getUserFilmsWithAvgRatingAndUserRating(id));
		return "myFilms";
	}
	
	@GetMapping( "user/topRecommended" )										// top 10 movies for current user
	public String topRecommended(Model model, @SessionAttribute int id) {
		model.addAttribute("films", filmService.getTopRecommended(id));
		return "topRecommended";
	}
	
}