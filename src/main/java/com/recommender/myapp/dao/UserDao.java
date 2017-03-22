package com.recommender.myapp.dao;

import java.util.List;

import com.recommender.myapp.model.Film;
import com.recommender.myapp.model.User;

public interface UserDao {
	
	public void insert (User user);
	public List<User> getAll();
	public User getById (int id);
	public List<Film> getUserFilms (int id);
	public void remove(int id);
	
}
