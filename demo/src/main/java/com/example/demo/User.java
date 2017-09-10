package com.example.demo;

import java.util.Date;
import java.util.Set;

import javax.persistence.*;


@Table(name="users")
@Entity
public class User {

	@Column(name = "user_id")
	private int id;
	@Id
	private String email;
	private String password;
	private String name;
	private int number;
	private String address;
	@Column(name = "date_of_birth")
	private Date dob;
    private String roles;
	private long active;
	
	
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name = "email")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Column(name = "password")
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Column(name = "name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "number")
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	@Column(name = "active")
	public long getActive() {
		return active;
	}
	public void setActive(long active) {
		this.active = active;
	}
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	
	
	public User(int id, String email, String password, String name, int number, Role roles, long active, String address, Date dob) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.name = name;
		this.number = number;
		this.roles = roles.getRole();
		this.active = active;
		this.address=address;
		this.dob=dob;
	}
	
	public User(User user)
	{
		this.id = user.getId();
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.name = user.getName();
		this.number = user.getNumber();
		this.active = user.getActive();
		this.roles=user.getRoles();
		this.dob=user.getDob();
		this.address=user.getAddress();
	}
	
	public User() {
		
	}
	
}
