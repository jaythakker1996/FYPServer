package com.example.demo;

public class Details {

	private String restId;
 	private String name;
    private String area;
    private double longitude;
    private double est_cost_per_person;
    private double latitude;
    private String cuisine;
    private double rating;
    private double total_cost;
    private double total_time;
    private double distance;
    private double uber_cost;
    
	public String getRestId() {
		return restId;
	}
	public void setRestId(String restId) {
		this.restId = restId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getEst_cost_per_person() {
		return est_cost_per_person;
	}
	public void setEst_cost_per_person(double est_cost_per_person) {
		this.est_cost_per_person = est_cost_per_person;
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
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	public double getTotal_cost() {
		return total_cost;
	}
	public void setTotal_cost(double total_cost) {
		this.total_cost = total_cost;
	}
	public double getTotal_time() {
		return total_time;
	}
	public void setTotal_time(double total_time) {
		this.total_time = total_time;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public double getUber_cost() {
		return uber_cost;
	}
	public void setUber_cost(double uber_cost) {
		this.uber_cost = uber_cost;
	}
	public Details() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Details(String restId, String name, String area, double longitude, double est_cost_per_person,
			double latitude, String cuisine, double rating, double total_cost, double total_time, double distance,
			double uber_cost) {
		super();
		this.restId = restId;
		this.name = name;
		this.area = area;
		this.longitude = longitude;
		this.est_cost_per_person = est_cost_per_person;
		this.latitude = latitude;
		this.cuisine = cuisine;
		this.rating = rating;
		this.total_cost = total_cost;
		this.total_time = total_time;
		this.distance = distance;
		this.uber_cost = uber_cost;
	}
    
    
    
    
}
