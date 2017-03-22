package com.recommender.myapp.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.recommender.myapp.dao.UserDao;
import com.recommender.myapp.model.User;
import com.recommender.myapp.model.Film;

public class UserDaoImpl implements UserDao {
	
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public void insert(User user) {
		String sql = "INSERT INTO users (name, password) VALUES (?, ?)";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, user.getName());
			ps.setString(2, user.getPassword());
			ps.executeUpdate();
			ps.close();
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
	public List<User> getAll() {
		List<User> users = new ArrayList<User>();
		String sql = "SELECT * FROM users";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				User user = new User(
					rs.getInt("idUsers"),
					rs.getString("name"),
					rs.getString("password")
				);
				users.add(user);
			}
			rs.close();
			ps.close();
			return users;
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
	public User getById(int id) {
		String sql = "SELECT * FROM users WHERE idUsers = ?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			User user = null;
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				user = new User(
					rs.getInt("idUsers"),
					rs.getString("name"),
					rs.getString("password")
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
	public List<Film> getUserFilms(int userId) {
		List<Film> films = new ArrayList<Film>();
		String sql = "SELECT film.idFilm, film.title, film.yr, film.poster FROM film INNER JOIN rating ON film.idFilm = rating.filmId " + 
					 "WHERE rating.userId = ?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Film film = new Film(
					rs.getInt(1),
					rs.getString(2),
					rs.getInt(3),
					rs.getBinaryStream(4)
				);
				films.add(film);
			}
			rs.close();
			ps.close();
			return films;
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
	public void remove(int id) {
		String sql = "DELETE FROM users WHERE idUsers = ?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
			ps.close();
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