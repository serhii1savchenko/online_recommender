package com.my.recommender.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.my.recommender.dao.FilmDao;
import com.my.recommender.model.Film;
import com.my.recommender.service.FilmService;

@Component
public class FilmServiceImpl implements FilmService{
	
	@Autowired
	private FilmDao filmDao;

	@Override
	public List<Film> getAllFilms() {
		return filmDao.getAll();
	}

	@Override
	public List<Film> getTopRecommended(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Film getFilmById(int filmId) {
		return filmDao.getById(filmId);
	}
	
	
	
}
