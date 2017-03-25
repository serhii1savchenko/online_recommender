package com.my.recommender.dao.impl;

import org.springframework.stereotype.Repository;

import com.my.recommender.dao.RatingDao;
import com.my.recommender.model.Rating;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

@Repository
public class RatingDaoImpl implements RatingDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public void insert(Rating rating) {
		String sql = "INSERT INTO ratings (userId, filmId, rating) VALUES (?, ?, ?)";
		Connection conn = null;
		try {
			conn = jdbcTemplate.getDataSource().getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, rating.getUserId());
			ps.setInt(2, rating.getFilmId());
			ps.setDouble(3, rating.getRating());
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
	public void update(int id, double newRating) {
		String sql = "UPDATE ratings SET rating = ? WHERE idRating = ?";
		Connection conn = null;
		try {
			conn = jdbcTemplate.getDataSource().getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setDouble(1, newRating);
			ps.setInt(2, id);
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
	public List<Rating> getAll() {
		List<Rating> ratings = new ArrayList<Rating>();
		String sql = "SELECT * FROM ratings";
		Connection conn = null;
		try {
			conn = jdbcTemplate.getDataSource().getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Rating rating = new Rating(
					rs.getInt("idRating"),
					rs.getInt("userId"),
					rs.getInt("filmId"),
					rs.getDouble("rating")
				);
				ratings.add(rating);
			}
			rs.close();
			ps.close();
			return ratings;
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
	public Rating getByUserFilm(int userId, int filmId) {
		String sql = "SELECT * FROM ratings WHERE userId = ? AND filmId = ?";
		Connection conn = null;
		try {
			conn = jdbcTemplate.getDataSource().getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, userId);
			ps.setInt(2, filmId);
			Rating rating = null;
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				rating = new Rating(
					rs.getInt("idRating"),
					rs.getInt("userId"),
					rs.getInt("filmId"),
					rs.getDouble("rating")
				);
			}
			rs.close();
			ps.close();
			return rating;
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
	public double getFilmAverageRating(int filmId) {
		String sql = "SELECT AVG(rating) AS avg FROM ratings WHERE filmId = ?";
		Connection conn = null;
		try {
			conn = jdbcTemplate.getDataSource().getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, filmId);
			Double filmAVG = null;
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				filmAVG = rs.getDouble("avg");
			}
			rs.close();
			ps.close();
			return filmAVG;
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
