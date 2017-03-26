package com.my.recommender.model;

import java.io.InputStream;

public class Film{

	private int id;
	private String title;
	private int year;
	private String poster;
	private double avgRating;
	private double exactUserRating;
	private InputStream posterForDownload;

	public Film(int id, String title, int year, String poster) {
		this.id = id;
		this.title = title;
		this.year = year;
		this.poster = poster;
	}

	public Film(String title, int year, String poster) {
		this.title = title;
		this.year = year;
		this.poster = poster;
	}
	
	//for downloading TO database
	public Film(String title, int year, InputStream posterForDownload) {
		this.title = title;
		this.year = year;
		this.posterForDownload = posterForDownload;
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
	public String getPoster() {
		return poster;
	}
	public void setPoster(String poster) {
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
	public InputStream getPosterForDownload() {
		return posterForDownload;
	}
	public void setPosterForDownloa(InputStream posterForDownload) {
		this.posterForDownload = posterForDownload;
	}
	
	@Override
	public String toString() {
		return "Film [id=" + id + ", title=" + title + ", year=" + year + "]";
	}

}
