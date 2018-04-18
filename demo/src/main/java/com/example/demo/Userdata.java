package com.example.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;



@Table(name="userdata")        
@Entity
public class Userdata {

	@Id
	@GenericGenerator(name="serial" , strategy="increment")
	@GeneratedValue(generator="serial")
	private int serial;
	
	@Column(name = "user_id")
	private int id;
	private String restId;
	private String email;
	private int rating;
	
	
	public int getSerial() {
		return serial;
	}
	public void setSerial(int serial) {
		this.serial = serial;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRestId() {
		return restId;
	}
	public void setRestId(String restId) {
		this.restId = restId;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public Userdata(int id, String restId, int rating,String email,int serial) {
		super();
		this.serial=serial;
		this.id = id;
		this.restId = restId;
		this.rating = rating;
		this.email=email;
	}
	public Userdata() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Userdata(Userdata ud) {
		super();
		this.serial=ud.serial;
		this.id = ud.id;
		this.restId = ud.restId;
		this.rating = ud.rating;
		this.email=ud.email;
	}
	
	
}
