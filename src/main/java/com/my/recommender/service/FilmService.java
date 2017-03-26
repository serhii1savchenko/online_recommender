package com.my.recommender.service;

import java.util.List;

import com.my.recommender.model.Film;

public interface FilmService {
	
	List<Film> getAllFilms();
	List<Film> getTopRecommended(int userId);
	Film getFilmById(int filmId);
	
}