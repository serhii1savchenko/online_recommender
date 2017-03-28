package com.my.recommender.service.impl;

import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.my.recommender.dao.FilmDao;
import com.my.recommender.dao.RatingDao;
import com.my.recommender.mahoutRecommender.Recommender;
import com.my.recommender.model.Film;
import com.my.recommender.service.FilmService;

@Component
public class FilmServiceImpl implements FilmService{
	
	@Autowired
	private Recommender rec;
	
	@Autowired
	private FilmDao filmDao;
	
	@Autowired
	private RatingDao ratingDao;

	@Override
	public List<Film> getAllFilms() {
		return filmDao.getAll();
	}
	
	@Override
	public Film getFilmById(int filmId) {
		return filmDao.getById(filmId);
	}

	@Override
	public List<Film> getAllFilmsWithAvgRating() {
		List<Film> films = filmDao.getAll();
		for(Film film : films){
			film.setAvgRating(ratingDao.getFilmAverageRating(film.getId()));
		}
		return films;
	}

	@Override
	public Film getFilmByIdWithAvgRatingAndUserRating(int userId, int filmId) {
		Film film = filmDao.getById(filmId);
		film.setAvgRating(ratingDao.getFilmAverageRating(filmId));
		film.setExactUserRating(ratingDao.getByUserFilm(userId, filmId).getRating());
		return film;
	}
	
	@Override
	public List<Film> getTopRecommended(int userId) {
		List<RecommendedItem> recommendedItems = null;
		try {
			recommendedItems = rec.getRecommender().recommend(userId, 5);
		} catch (TasteException e) {
			e.printStackTrace();
		}
		List<Film> recommendedFilms = null;
		if (!recommendedItems.isEmpty()){
			for (RecommendedItem item : recommendedItems){
				Film film = filmDao.getById((int)item.getItemID());
				film.setExactUserPrediction((double)item.getValue());
				recommendedFilms.add(film);
			}
		}
		return recommendedFilms;
	}

	
}
