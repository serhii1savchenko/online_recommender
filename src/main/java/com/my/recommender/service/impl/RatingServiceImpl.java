package com.my.recommender.service.impl;

import org.apache.mahout.cf.taste.common.TasteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.my.recommender.dao.RatingDao;
import com.my.recommender.mahoutRecommender.Recommender;
import com.my.recommender.model.Rating;
import com.my.recommender.service.RatingService;

@Component
public class RatingServiceImpl implements RatingService {
	
	@Autowired
	private RatingDao ratingDao;
	
	@Autowired
	private Recommender rec;

	@Override
	public double getRating(int userId, int filmId) {
		return ratingDao.getByUserFilm(userId, filmId).getRating();
	}

	@Override
	public void insertRating(Rating rating) {
		ratingDao.insert(rating);
		try {
			rec.getRecommender().setPreference(rating.getUserId(), rating.getFilmId(), (float) rating.getRating());
		} catch (TasteException e) {
			e.printStackTrace();
		}
	}
	
}
