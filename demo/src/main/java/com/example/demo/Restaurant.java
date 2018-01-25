package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="restaurant")        
@Entity
public class Restaurant {
		@Id
		private String restId;
	 	private String name;
	    private String area;
	    private double longitude;
	    private double est_cost_per_person;
	    private double latitude;
	    private String cuisine;
	    
	    public Restaurant(){
	    	name="llol";	
	    	area="llol";	
	    	est_cost_per_person=10;
	    }

	    
	    
		public Restaurant(String restId,String name, String area, double longitude, double est_cost_per_person, double latitude,
				String cuisine) {
			super();
			this.name = name;
			this.area = area;
			this.longitude = longitude;
			this.est_cost_per_person = est_cost_per_person;
			this.latitude = latitude;
			this.cuisine = cuisine;
		}



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
	    
}
