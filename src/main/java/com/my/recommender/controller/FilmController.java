package com.my.recommender.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.my.recommender.service.FilmService;
import com.my.recommender.service.RecommenderService;

@Controller
@SessionAttributes("id")
public class FilmController {
	
	@Autowired
	FilmService filmService;
	
	@Autowired
	RecommenderService recommenderService;
	
	@GetMapping( "user/allFilms" )
	public String allFilms(Model model) {
		model.addAttribute("films", filmService.getAllFilmsWithAvgRating());
		return "allFilms";
	}
	
	
	@GetMapping( "user/film/{filmId}" )
	public String allFilms(Model model, @PathVariable("filmId") int filmId, @SessionAttribute int id) {
		model.addAttribute("film", filmService.getFilmByIdWithAvgRatingAndUserRating(id, filmId));
		model.addAttribute("prediction", recommenderService.getPrediction(id, filmId));
		return "film";
	}
	
}
