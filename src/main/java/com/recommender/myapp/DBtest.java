package com.recommender.myapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.recommender.myapp.dao.FilmDao;
import com.recommender.myapp.dao.RatingDao;
import com.recommender.myapp.dao.UserDao;
import com.recommender.myapp.model.Film;
import com.recommender.myapp.model.Rating;
import com.recommender.myapp.model.User;

public class DBtest {

	public static void main(String[] args) throws FileNotFoundException {

		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");

/*		File file = new File("C:/Users/Serhii/Downloads/split.jpg");
		FileInputStream poster = new FileInputStream(file);

		FilmDao filmDao = (FilmDao) context.getBean("filmDAO");
		Film film = new Film("Split", 2016, poster);
		filmDao.insert(film);

		List<Film> films = filmDao.getAll();
		System.out.println(films.size());
		
		List<User> users = filmDao.getFilmUsers(films.get(0).getId());
		System.out.println(users.size());

		
		UserDao userDao = (UserDao) context.getBean("userDAO");
		User user = new User("Serhii", DigestUtils.md5Hex("123456"));
		userDao.insert(user);
		
		User user1 = userDao.getAll().get(0);
		System.out.println(user1.toString());
		
		List<Film> films = userDao.getUserFilms(user1.getId());
		System.out.println(films.size());

		
		RatingDao ratingDao = (RatingDao) context.getBean("ratingDAO");
		Rating rating = new Rating(1, 1, (double) 4.0);
		ratingDao.insert(rating);
		
		Rating rating1 = ratingDao.getByUserFilm(1, 1);
		System.out.println(rating1.toString());
		
		ratingDao.update(1, (double) 4.5);
		
		System.out.println(ratingDao.getByUserFilm(1, 1));
		System.out.println("avg = "+ratingDao.getFilmAverageRating(1));
*/		
	}
}
