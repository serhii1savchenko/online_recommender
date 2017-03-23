package com.recommender.myapp.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.recommender.myapp.dao.RoleDao;
import com.recommender.myapp.model.Role;

public class RoleDaoImpl implements RoleDao{
	
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public Role getUserRole() {
		String sql = "SELECT * FROM role WHERE role = USER";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			Role user = null;
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				user = new Role(
					rs.getInt("idRole"),
					rs.getString("role")
				);
			}
			rs.close();
			ps.close();
			return user;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
				conn.close();
				} catch (SQLException e) {}
			}
		}
	}

	@Override
	public Role getAdminRole() {
		String sql = "SELECT * FROM role WHERE role = ADMIN";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			Role admin = null;
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				admin = new Role(
					rs.getInt("idRole"),
					rs.getString("role")
				);
			}
			rs.close();
			ps.close();
			return admin;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
				conn.close();
				} catch (SQLException e) {}
			}
		}
	}

}