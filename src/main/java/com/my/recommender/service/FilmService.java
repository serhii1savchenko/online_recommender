package com.my.recommender.service;

import java.util.List;

import com.my.recommender.model.Film;

public interface FilmService {
	
	List<Film> getAllFilms();
	List<Film> getAllFilmsWithAvgRating();
	
	Film getFilmById(int filmId);
	Film getFilmByIdWithAvgRating( int filmId);
	
	List<Film> getTopRecommended(int userId);
	
}