package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Table(name="grid")        
@Entity
public class Grid {

	@Id
	@GenericGenerator(name="loc_id" , strategy="increment")
	@GeneratedValue(generator="loc_id")
	private int gid;
	private int gfrom;
	private int gto;
	private double distance;
	private double time;
	private double cost;

	
	
	public int getGid() {
		return gid;
	}



	public void setGid(int gid) {
		this.gid = gid;
	}



	public int getGfrom() {
		return gfrom;
	}



	public void setGfrom(int gfrom) {
		this.gfrom = gfrom;
	}



	public int getGto() {
		return gto;
	}



	public void setGto(int gto) {
		this.gto = gto;
	}



	public double getDistance() {
		return distance;
	}



	public void setDistance(double distance) {
		this.distance = distance;
	}



	public double getTime() {
		return time;
	}



	public void setTime(double time) {
		this.time = time;
	}



	public double getCost() {
		return cost;
	}



	public void setCost(double cost) {
		this.cost = cost;
	}



	public Grid(int gid,int gfrom, int gto, double distance, double time, double cost) {
		super();
		this.gid=gid;
		this.gfrom = gfrom;
		this.gto = gto;
		this.distance = distance;
		this.time = time;
		this.cost = cost;
	}



	public Grid() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Grid(Grid grd) {
		gid=grd.gid;
		gfrom=grd.gfrom;
		gto=grd.gto;
		distance=grd.distance;
		time=grd.time;
		cost=grd.cost;
	}
	
}
