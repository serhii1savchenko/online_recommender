package com.my.recommender.dao.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.my.recommender.dao.DataDao;

@Repository
public class DataDaoImpl implements DataDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void insert(File file) {
		String sql = "INSERT INTO files (file) VALUES (?)";
		Connection conn = null;
		try {
			conn = jdbcTemplate.getDataSource().getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setBinaryStream(1, new FileInputStream(file));
			ps.executeUpdate();
			ps.close();
		} catch (SQLException | FileNotFoundException e) {
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
	public String getLast() {
		String sql = "SELECT * FROM files ORDER BY idFile DESC LIMIT 1";
		Connection conn = null;
		String path = "temp.txt";
		try {
			conn = jdbcTemplate.getDataSource().getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Blob blob = rs.getBlob("file");
                InputStream inputStream = blob.getBinaryStream();
                OutputStream outputStream = new FileOutputStream(path);
 
                int bytesRead = -1;
                byte[] buffer = new byte[4096];
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
                inputStream.close();
                outputStream.close();
                System.out.println("File recieved frob DB!");
			}
			rs.close();
			ps.close();
			return path;
		} catch (SQLException | IOException e) {
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
