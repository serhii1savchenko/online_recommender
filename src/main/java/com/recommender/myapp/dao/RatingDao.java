package com.recommender.myapp.dao;

import java.util.List;

import com.recommender.myapp.model.Rating;

public interface RatingDao {
	
	public void insert (Rating rating);
	public void update (int id, double newRating);
	public List<Rating> getAll();
	public Rating getByUserFilm (int userId, int filmId);
	public double getFilmAverageRating (int filmId);
	
}