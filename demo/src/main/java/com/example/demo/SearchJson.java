package com.example.demo;

import javax.persistence.Entity;

public class SearchJson {
	
	private int time;
	private int budget;
	private double longitude;
    private double noOfPerson;
    private double latitude;
    private String cuisine;
    
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public int getBudget() {
		return budget;
	}
	public void setBudget(int budget) {
		this.budget = budget;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getNoOfPerson() {
		return noOfPerson;
	}
	public void setNoOfPerson(double noOfPerson) {
		this.noOfPerson = noOfPerson;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public String getCuisine() {
		return cuisine;
	}
	public void setCuisine(String cuisine) {
		this.cuisine = cuisine;
	}
	public SearchJson(int time, int budget, double longitude, double noOfPerson, double latitude, String cuisine) {
		super();
		this.time = time;
		this.budget = budget;
		this.longitude = longitude;
		this.noOfPerson = noOfPerson;
		this.latitude = latitude;
		this.cuisine = cuisine;
	}
	public SearchJson() {
		super();
	}
    
    
    
}
