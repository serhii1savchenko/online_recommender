package com.recommender.myapp.service;

import com.recommender.myapp.model.User;

public interface UserService {
	
	public void save (User user);
	public User findByUsername (String username);
	
}