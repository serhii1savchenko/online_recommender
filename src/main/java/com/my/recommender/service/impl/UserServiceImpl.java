package com.my.recommender.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.my.recommender.dao.impl.UserDaoImpl;
import com.my.recommender.model.User;

@Component
public class UserServiceImpl {
	
	@Autowired
	private UserDaoImpl userDao;
	
	public void insertUser (User user){
		userDao.insert(user);
	}
	
}
