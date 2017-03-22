package com.recommender.myapp.dao;

import java.util.List;

import com.recommender.myapp.model.Film;
import com.recommender.myapp.model.User;

public interface FilmDao {
	
	public void insert (Film film);
	public List<Film> getAll();
	public Film getById (int id);
	public List<User> getFilmUsers (int id);
	public void remove(int id);
	
}
