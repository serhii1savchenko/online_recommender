package com.recommender.myapp.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;

import com.recommender.myapp.dao.UserDao;
import com.recommender.myapp.dao.RoleDao;
import com.recommender.myapp.model.Role;
import com.recommender.myapp.model.User;
import com.recommender.myapp.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private Md5PasswordEncoder passwordEncoder;
	
	@Override
	public void save(User user) {
		user.setPassword(passwordEncoder.encodePassword(user.getPassword(), user));
		Role userRole = roleDao.getUserRole();
		user.setRole(userRole);
		userDao.insert(user);
	}

	@Override
	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}
	
}