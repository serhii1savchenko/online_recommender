package com.my.recommender.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.my.recommender.dao.FilmDao;

@Component
public class FilmServiceImpl {
	
	@Autowired
	private FilmDao filmDao;
	
	
	
}
