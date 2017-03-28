package com.my.recommender.dao.impl;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.Base64Utils;

import com.my.recommender.dao.FilmDao;
import com.my.recommender.dao.RatingDao;
import com.my.recommender.model.Film;
import com.my.recommender.model.User;

@Repository
public class FilmDaoImpl implements FilmDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private RatingDao ratingDao;

	@Override
	public void insert(Film film) {
		String sql = "INSERT INTO films (title, yr, poster) VALUES (?, ?, ?)";
		Connection conn = null;
		try {
			conn = jdbcTemplate.getDataSource().getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, film.getTitle());
			ps.setInt(2, film.getYear());
			ps.setBinaryStream(3, film.getPosterForDownload());
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
		String sql = "SELECT * FROM films";
		Connection conn = null;
		try {
			conn = jdbcTemplate.getDataSource().getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Film film = new Film(
						rs.getInt("idFilm"),
						rs.getString("title"),
						rs.getInt("yr"),
						blobAsString(rs.getBlob("poster"))
						);
				films.add(film);
			}
			rs.close();
			ps.close();
			return films;
		} catch (SQLException | UnsupportedEncodingException e) {
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
		String sql = "SELECT * FROM films WHERE idFilm = ?";
		Connection conn = null;
		try {
			conn = jdbcTemplate.getDataSource().getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			Film film = new Film();
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				film = new Film(
						rs.getInt("idFilm"),
						rs.getString("title"),
						rs.getInt("yr"),
						blobAsString(rs.getBlob("poster"))
						);
			}
			rs.close();
			ps.close();
			return film;
		} catch (SQLException | UnsupportedEncodingException e) {
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
	public List<Film> getTopNFilmsWithAvgRating(int userId, int n) {
		List<Film> films = new ArrayList<Film>();
		String sql = "SELECT idFilm, title, yr, poster, avgRating FROM films "+
					 "WHERE idFilm NOT IN ( SELECT filmId FROM ratings WHERE userId = ? ) "+
					 "ORDER BY avgRating DESC LIMIT ? ";
		Connection conn = null;
		try {
			conn = jdbcTemplate.getDataSource().getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, userId);
			ps.setInt(2, n);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Film film = new Film(
						rs.getInt("idFilm"),
						rs.getString("title"),
						rs.getInt("yr"),
						blobAsString(rs.getBlob("poster"))
						);
				film.setAvgRating(ratingDao.getFilmAverageRating(film.getId()));
				films.add(film);
			}
			rs.close();
			ps.close();
			return films;
		} catch (SQLException | UnsupportedEncodingException e) {
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
		String sql = "SELECT users.idUser, users.name, users.password FROM users INNER JOIN ratings ON users.idUser = ratings.userId " + 
				"WHERE ratings.filmId = ?";
		Connection conn = null;
		try {
			conn = jdbcTemplate.getDataSource().getConnection();
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
		String sql = "DELETE FROM films WHERE idFilm = ?";
		Connection conn = null;
		try {
			conn = jdbcTemplate.getDataSource().getConnection();
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

	static String blobAsString(java.sql.Blob blob2) throws SQLException, UnsupportedEncodingException{ 
		java.sql.Blob blob = blob2; 
		byte[] encodeBase64 = Base64Utils.encode(blob.getBytes(1, (int) blob.length())); 
		blob.free(); 
		return new String(encodeBase64, "UTF-8"); 
	}

}
