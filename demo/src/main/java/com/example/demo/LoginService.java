package com.example.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LoginService {

	@Autowired
	private LoginRepository loginRepository;
	@Autowired
	private RestaurantRepository restaurantRepository;
	@Autowired
    private LocationRepository locationRepository;
	@Autowired
	private GridRepository gridRepository;
	@Autowired
	private MovieRepository movieRepository;
	@Autowired
	private UserRepository userRepository;
	
	List<Location> loclist;
	
	public List<User> getAllLogin() {
		List<User> user=new ArrayList<>();
		loginRepository.findAll()
		.forEach(user::add);
		return user;
	}
	
	public Success getLogIn(String username,String password){
		User a=loginRepository.findOne(username);
		Success s=new Success(false);
		if(a!=null)
		{
			String pass=a.getPassword();
			if(password.equals(pass))
			{
				s.setSuccess(true);
				return s;
			}
			else {
				s.setSuccess(false);
				return s;
			}
		}
		else {
			s.setSuccess(false);
			return s;
		}
		
	}
	
	public void addUserData(Userdata ud) {
		User u=loginRepository.getOne(ud.getEmail());
		ud.setId(u.getId());
		System.out.println(""+ud.getId());
		userRepository.save(ud);
	}

	public Success addUser(User user) {
		loginRepository.save(user);
		return new Success(true);
	}

	public String homePage() {
		return("API V1");	
	}
	
	public void addRest(Restaurant rest)
	{
		restaurantRepository.save(rest);
	}
	
	public void addGrid(Grid grid)
	{
		gridRepository.save(grid);
	}
	
	public double radiusLatLon(double lat1,double lat2,double lon1,double lon2)
	{
		double dist=Math.acos(Math.sin(lat1)*Math.sin(lat2)+ Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon1 - lon2))*6371;
		return dist;
	}
	
	public Map<String, List<Restaurant>> getAllResult(SearchJson sjson) {
		Map<String, List<Restaurant>> map = new HashMap<String, List<Restaurant>>();
		//List<Restaurant> list=new ArrayList<>();
		//restaurantRepository.findAll()
		//.forEach(list::add);
		
		map.put("Results", this.getML(sjson));
		return map;
	}
	
	public Details getFinalRest(SelectRest rest) {
		List<Restaurant> list=new ArrayList<>();
		restaurantRepository.findAll().forEach(list::add);
		int i=0;
		Details det=new Details();
		URestCalls restCalls = new URestCalls();
		System.out.println("Hereeeee");
		while(i!=6600)
		{
			if(list.get(i).getRestId().equals(rest.getRestId()))
			{
				det.setArea(list.get(i).getArea());
				det.setCuisine(list.get(i).getCuisine());
				det.setName(list.get(i).getName());
				det.setEst_cost_per_person(list.get(i).getEst_cost_per_person());
				det.setLatitude(list.get(i).getLatitude());
				det.setLongitude(list.get(i).getLongitude());
				det.setRating(list.get(i).getRating());
				JSONObject job=restCalls.UberCall(rest.getLatitude(),rest.getLongitude(),list.get(i).getLatitude(),list.get(i).getLongitude(),2);	
				double uberCost=((job.getJSONArray("prices").getJSONObject(3).getDouble("high_estimate"))+(job.getJSONArray("prices").getJSONObject(3).getDouble("low_estimate")))/2;
				det.setUber_cost(uberCost);
				System.out.println("cost "+uberCost);
				det.setTotal_cost(uberCost+list.get(i).getEst_cost_per_person());
				det.setDistance(job.getJSONArray("prices").getJSONObject(3).getDouble("distance"));
				det.setTotal_time(30+(job.getJSONArray("prices").getJSONObject(3).getDouble("duration")/30));
			}
			i++;
		}
		return det;
	}
	
	public List<Restaurant> getML(SearchJson sjson){
		List<Restaurant> list=new ArrayList<>();
		List<Restaurant> rest=new ArrayList<>();
		restaurantRepository.findAll().forEach(list::add);
		double uRating=3.0;
		String uCusine=sjson.getCuisine();
		double uCost=sjson.getBudget();
		double uLatitude=sjson.getLatitude();
		double uLongitude=sjson.getLongitude();
		double uTime=sjson.getTime();
		CustQueue que=new CustQueue();
		loclist=locationRepository.findAll();
		List<Grid> grid=gridRepository.findAll();
		
		for(int i=0;i<6000;i++)
		{
			if(list.get(i).getRating()>=uRating)
			{
				String cu=list.get(i).getCuisine();
				if(cu.contains(uCusine)||uCusine.equals("Any"))
				{
					double l=0;
					double d=0;
					double t=0;
					
					int userHotspot=this.Hotspot(uLatitude, uLongitude);
					System.out.println("Here userhotspot "+userHotspot+"\n");
					int restHotspot=this.Hotspot(list.get(i).getLatitude(),list.get(i).getLongitude());
					System.out.println("Here resthotspot "+restHotspot+"\n");
					
					Grid gridval=new Grid();
					
					for(int j=0;j<4211;j++)
					{
						if(grid.get(j).getGfrom()==userHotspot&&grid.get(j).getGto()==restHotspot)
						{
							gridval=grid.get(j);
						}
						else if(grid.get(j).getGfrom()==restHotspot&&grid.get(j).getGto()==userHotspot)
						{
							gridval=grid.get(j);
						}
					}
					if(userHotspot==restHotspot)
					{
						gridval.setCost(25);
						gridval.setDistance(2.0);
						gridval.setGfrom(userHotspot);
						gridval.setGto(restHotspot);
						gridval.setTime(5*60);
					}
					double rCost=list.get(i).getEst_cost_per_person();
					double cCost=2*gridval.getCost();
					if((rCost+cCost)<=uCost)
					{
						if(rCost>cCost)
							l=(uCost-rCost-cCost)*(uCost-rCost-cCost);
						else
						{
							l=(uCost-rCost-cCost)*(uCost-rCost-cCost)*1.45;
						}
					}
					
					double distance=(gridval.getDistance())*1.61;
					double time=2*(gridval.getTime()/60);
					d=distance*distance*20;
					
					System.out.println("\n Here l = "+gridval.getCost()+" d = "+d+" t = "+gridval.getTime()+" distance :"+gridval.getDistance()+"\n");
					if((time+30)<=uTime)
					{
						t=((uTime-time)*(uTime-time))*10;
					}
					if(((time+30)<=uTime)&&((rCost+cCost)<=uCost))
					{	
						double temp=d+l+t-(list.get(i).getRating()*10000);
						System.out.println("\n Here l = "+l+" d = "+d+" t = "+t+" Rating :"+list.get(i).getRating()*10000+" temp :"+temp+"\n");
						que.Qadd(list.get(i),temp);
					}	
				}
			}
		}
		
		for(int x=0;x<10;x++)
		{	
			if(que.Wget(x)!=0)
				rest.add(que.Qget(x));
			else
			{
				System.out.println("Here");
				Restaurant r=new Restaurant();
				r.setName("NULL");
				r.setCuisine("NULL");
				r.setArea("NULL");
				r.setEst_cost_per_person(0);
				r.setLatitude(0);
				r.setLongitude(0);
				r.setRestId("0");
				rest.add(r);
			}
		}
		return rest;
	}
	
	public int Hotspot(double lat,double lon)
	{
		int result=1;
		double temp1=100000000;
		double temp2=0;
		for(int i=0;i<80;i++)
		{
			
			temp2=this.radiusLatLon(lat,loclist.get(i).getLatitude(),lon,loclist.get(i).getLongitude());
			if(temp2<temp1)
			{
				temp1=temp2;
				result=i+1;
			}
		}
		
		return result;
	}
	
	public  Map<String, List<Movie>> getMovie(SearchJson sjson){
		Map<String, List<Movie>> map = new HashMap<String, List<Movie>>();
		List<Movie> list=new ArrayList<>();
		movieRepository.findAll()
		.forEach(list::add);
		
		map.put("Results", list);
		return map;
		
	}
	
	public List<Movie> getMovieML(SearchJson sjson){
		List<Movie> list=new ArrayList<>();
		List<Movie> rest=new ArrayList<>();
		movieRepository.findAll().forEach(list::add);
		double uCost=sjson.getBudget();
		double uLatitude=sjson.getLatitude();
		double uLongitude=sjson.getLongitude();
		double uTime=sjson.getTime();
		CustQueue que=new CustQueue();
		loclist=locationRepository.findAll();
		List<Grid> grid=gridRepository.findAll();
		for(int i=0;i<=1000;i++)
		{
			int userHotspot=this.Hotspot(uLatitude, uLongitude);
			System.out.println("Here userhotspot "+userHotspot+"\n");
			double lat=Double.parseDouble(list.get(i).getLat());
			double lng=Double.parseDouble(list.get(i).getLng());
			int restHotspot=this.Hotspot(lat,lng);
			System.out.println("Here resthotspot "+restHotspot+"\n");
			
			Grid gridval=new Grid();
			
			for(int j=0;j<4211;j++)
			{
				if(grid.get(j).getGfrom()==userHotspot&&grid.get(j).getGto()==restHotspot)
				{
					gridval=grid.get(j);
				}
				else if(grid.get(j).getGfrom()==restHotspot&&grid.get(j).getGto()==userHotspot)
				{
					gridval=grid.get(j);
				}
			}
			if(userHotspot==restHotspot)
			{
				gridval.setCost(25);
				gridval.setDistance(2.0);
				gridval.setGfrom(userHotspot);
				gridval.setGto(restHotspot);
				gridval.setTime(5*60);
			}
			
		}
		
		return rest;
	}
	
	
}
