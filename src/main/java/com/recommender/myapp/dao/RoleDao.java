package com.recommender.myapp.dao;

import com.recommender.myapp.model.Role;

public interface RoleDao {
	
	public Role getUserRole();
	public Role getAdminRole();
	
}
