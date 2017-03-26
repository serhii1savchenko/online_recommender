package com.my.recommender.service;

import java.io.File;

public interface RecommenderService {

	File getParsedDataFile();
	void insertToDatabase(File file);
	File getLastFromDatabase();
	
	double getPrediction(int userId, int filmId);
	
}