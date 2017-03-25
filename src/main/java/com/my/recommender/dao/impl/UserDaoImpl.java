package com.my.recommender.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.my.recommender.model.UserAuth;

@Repository
public class UserDaoImpl{

	private static final String SQL_SELECT_USER_AUTH_BY_NAME = "SELECT users.idUser, users.name, users.password, roles.role FROM users INNER JOIN roles ON users.roleId = roles.idRole WHERE users.name = ?";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public UserAuth getUserAuth(String name) {
		List<UserAuth> list = jdbcTemplate.query(SQL_SELECT_USER_AUTH_BY_NAME, new RowMapper<UserAuth>() {
			@Override
			public UserAuth mapRow(ResultSet rs, int rowNum) throws SQLException {
				return new UserAuth(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
			}
		}, name);
		return list.isEmpty() ? null : list.get(0);
	}
	
	
	
}
