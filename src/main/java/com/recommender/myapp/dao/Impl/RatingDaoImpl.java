package com.recommender.myapp.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.recommender.myapp.dao.RatingDao;
import com.recommender.myapp.model.Rating;

public class RatingDaoImpl implements RatingDao {
	
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public void insert(Rating rating) {
		String sql = "INSERT INTO rating (userId, filmId, rating) VALUES (?, ?, ?)";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
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
		String sql = "UPDATE rating SET rating = ? WHERE idRating = ?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
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
		String sql = "SELECT * FROM rating";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
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
		String sql = "SELECT * FROM rating WHERE userId = ? AND filmId = ?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
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
		String sql = "SELECT AVG(rating) AS avg FROM rating WHERE filmId = ?";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
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
