package com.recommender.myapp.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.recommender.myapp.dao.FilmDao;
import com.recommender.myapp.model.Film;
import com.recommender.myapp.model.User;

public class FilmDaoImpl implements FilmDao {
	
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public void insert(Film film) {
		String sql = "INSERT INTO film (title, yr, poster) VALUES (?, ?, ?)";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, film.getTitle());
			ps.setInt(2, film.getYear());
			ps.setBinaryStream(3, film.getPoster());
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
	public List<Film> getAll() {
		List<Film> films = new ArrayList<Film>();
		String sql = "SELECT * FROM film";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Film film = new Film(
					rs.getInt("idFilm"),
					rs.getString("title"),
					rs.getInt("yr"),
					rs.getBinaryStream("poster")
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
	public Film getById(int id) {
		String sql = "SELECT * FROM film WHERE idFilm = ?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			Film film = null;
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				film = new Film(
					rs.getInt("idFilm"),
					rs.getString("title"),
					rs.getInt("yr"),
					rs.getBinaryStream("poster")
				);
			}
			rs.close();
			ps.close();
			return film;
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
	public List<User> getFilmUsers(int filmId) {
		List<User> users = new ArrayList<User>();
		String sql = "SELECT users.idUsers, users.name, users.password FROM users INNER JOIN rating ON users.idUsers = rating.userId " + 
					 "WHERE rating.filmId = ?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, filmId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				User user = new User(
					rs.getInt(1),
					rs.getString(2),
					rs.getString(3)
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
	public void remove(int id) {
		String sql = "DELETE FROM film WHERE idFilm = ?";
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