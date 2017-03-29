package com.my.recommender.service.impl;

import java.util.List;

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
		boolean userPresence = false;
		boolean filmPresence = false;
		// If there still no such user or film in 'ratings' table
		List<Rating> allExistingRatings = ratingDao.getAll();
		for (Rating element : allExistingRatings){
			if(element.getUserId()==rating.getUserId()) { userPresence = true; }
			if(element.getFilmId()==rating.getFilmId()) { filmPresence = true; }
		}
		// We insert them
		ratingDao.insert(rating);
		// And renew recommender engine ==> recommender contains this rating
		if (!userPresence || !filmPresence) { rec.renewRecommender(); }
		// If user and film already exist in table
		if (userPresence && filmPresence){
			try {
				rec.getRecommender().setPreference(rating.getUserId(), rating.getFilmId(), (float) rating.getRating());
			} catch (TasteException e) {
				e.printStackTrace();
			}
		}
	}
	
}
