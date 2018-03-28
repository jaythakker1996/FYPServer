package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Id;

@Table(name="location")        
@Entity
public class Location {
	@Id
	@GenericGenerator(name="loc_id" , strategy="increment")
	@GeneratedValue(generator="loc_id")
	private int gridId;
	private double latitude;
	private double longitude;
	
	public int getGridId() {
		return gridId;
	}
	public void setGridId(int gridId) {
		this.gridId = gridId;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public Location(int gridId, double latitude, double longitude) {
		super();
		this.gridId = gridId;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	public Location() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Location(Location loc) {
		gridId=loc.gridId;
		latitude=loc.latitude;
		longitude=loc.longitude;
	}
}
