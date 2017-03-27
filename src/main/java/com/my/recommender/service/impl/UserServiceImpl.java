package com.my.recommender.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.my.recommender.dao.RatingDao;
import com.my.recommender.dao.UserDao;
import com.my.recommender.model.Film;
import com.my.recommender.model.User;
import com.my.recommender.service.UserService;

@Component
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private RatingDao ratingDao;
	
	@Override
	public void insertUser (User user){
		userDao.insert(user);
	}

	@Override
	public List<Film> getUserFilmsWithAvgRatingAndUserRating(int userId) {
		List<Film> films = userDao.getUserFilms(userId);
		for(Film film : films){
			film.setAvgRating(ratingDao.getFilmAverageRating(film.getId()));
			film.setExactUserRating(ratingDao.getByUserFilm(userId, film.getId()).getRating());
		}
		return films;
	}
	
}
