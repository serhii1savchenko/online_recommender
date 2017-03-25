package com.my.recommender.dao;

import java.io.File;

public interface DataDao {
	
	void insert(File file);
	
	String getLast();
	
}