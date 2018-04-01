package com.example.demo;

public class SelectRest {
	private String restId;
 	private String utime;
    private String unumber;
    private double longitude;
    private double ucost;
    private double latitude;
    private String ucuisine;
    
	public String getRestId() {
		return restId;
	}
	public void setRestId(String restId) {
		this.restId = restId;
	}
	public String getUtime() {
		return utime;
	}
	public void setUtime(String utime) {
		this.utime = utime;
	}
	public String getUnumber() {
		return unumber;
	}
	public void setUnumber(String unumber) {
		this.unumber = unumber;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getUcost() {
		return ucost;
	}
	public void setUcost(double ucost) {
		this.ucost = ucost;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public String getUcuisine() {
		return ucuisine;
	}
	public void setUcuisine(String ucuisine) {
		this.ucuisine = ucuisine;
	}
	public SelectRest(String restId, String utime, String unumber, double longitude, double ucost, double latitude,
			String ucuisine) {
		super();
		this.restId = restId;
		this.utime = utime;
		this.unumber = unumber;
		this.longitude = longitude;
		this.ucost = ucost;
		this.latitude = latitude;
		this.ucuisine = ucuisine;
	}
	public SelectRest() {
		super();
		// TODO Auto-generated constructor stub
	}
    
	
}
