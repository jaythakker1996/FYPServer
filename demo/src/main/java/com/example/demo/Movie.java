package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="movie")        
@Entity
public class Movie {

	@Id
	private int id;
	private String movie_name;
	private String theatre_name;
	private String lat;
	private String lng;
	private String sub_region;
	private String timings;
	private String prices;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMovie_name() {
		return movie_name;
	}
	public void setMovie_name(String movie_name) {
		this.movie_name = movie_name;
	}
	public String getTheatre_name() {
		return theatre_name;
	}
	public void setTheatre_name(String theatre_name) {
		this.theatre_name = theatre_name;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	public String getSub_region() {
		return sub_region;
	}
	public void setSub_region(String sub_region) {
		this.sub_region = sub_region;
	}
	public String getTimings() {
		return timings;
	}
	public void setTimings(String timings) {
		this.timings = timings;
	}
	public String getPrices() {
		return prices;
	}
	public void setPrices(String prices) {
		this.prices = prices;
	}
	public Movie(int id, String movie_name, String theatre_name, String lat, String lng, String sub_region,
			String timings, String prices) {
		super();
		this.id = id;
		this.movie_name = movie_name;
		this.theatre_name = theatre_name;
		this.lat = lat;
		this.lng = lng;
		this.sub_region = sub_region;
		this.timings = timings;
		this.prices = prices;
	}
	
	
	public Movie() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Movie(Movie m) {
		super();
		this.id = m.id;
		this.movie_name = m.movie_name;
		this.theatre_name = m.theatre_name;
		this.lat = m.lat;
		this.lng = m.lng;
		this.sub_region = m.sub_region;
		this.timings = m.timings;
		this.prices = m.prices;
	
		// TODO Auto-generated constructor stub
	}
	
	
	}
