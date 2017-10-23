package com.example.demo;

public class Search {
	private String time;
	private String cuisine;
	private int budget;
	private int numberOfPersons;
		
	public Search() {
		
	}

	public Search(String time, String cuisine, int budget, int numberOfPersons) {
		this.time = time;
		this.cuisine = cuisine;
		this.budget = budget;
		this.numberOfPersons = numberOfPersons;
	}

	//getters
	public String getTime() {
		return time;
	}

	public String getCuisine() {
		return cuisine;
	}

	public int getBudget() {
		return budget;
	}

	public int getNumberOfPersons() {
		return numberOfPersons;
	}

	//setters
	
	public void setTime(String time) {
		this.time = time; 
	}
	
	public void setCuisine(String cuisine) {
		this.cuisine = cuisine; 
	}
	
	public void setBudget(int budget) {
		this.budget = budget; 
	}
	
	public void setNumberOfPersons(int numberOfPersons) {
		this.numberOfPersons = numberOfPersons; 
	}
	
}
