package com.my.recommender.model;

import java.io.InputStream;

public class Film{

	private int id;
	private String title;
	private int year;
	private InputStream poster;
	private double avgRating;
	private double exactUserRating;

	public Film(int id, String title, int year, InputStream poster) {
		this.id = id;
		this.title = title;
		this.year = year;
		this.poster = poster;
	}

	public Film(String title, int year, InputStream poster) {
		this.title = title;
		this.year = year;
		this.poster = poster;
	}

	public Film() {
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public InputStream getPoster() {
		return poster;
	}
	public void setPoster(InputStream poster) {
		this.poster = poster;
	}
	public double getAvgRating() {
		return avgRating;
	}
	public void setAvgRating(double avgRating) {
		this.avgRating = avgRating;
	}
	public double getExactUserRating() {
		return exactUserRating;
	}
	public void setExactUserRating(double exactUserRating) {
		this.exactUserRating = exactUserRating;
	}
	
	@Override
	public String toString() {
		return "Film [id=" + id + ", title=" + title + ", year=" + year + "]";
	}

}
