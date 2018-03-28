package com.example.demo;

import java.util.List;
import java.util.Map;

import org.json.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

	//Values brought from session factory ie dependency injection
	@Autowired
	private LoginService loginService;
    @Autowired
    private LocationRepository locationRepository;
    
	@RequestMapping("/")
	public String homePage(){
		return loginService.homePage();
	}
	
	@RequestMapping("/auth/login")
	public List<User> getAllLogin(){
		return loginService.getAllLogin();
	}
	//Everything in {} represents a variable in the URL
	@RequestMapping("/auth/login/{username}and{password}")
	public Success getLogIn(@PathVariable String username,@PathVariable String password){
		return loginService.getLogIn(username,password);
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/subscribe")
	public Success addUser(@RequestBody User user)
	{
		return loginService.addUser(user);
	}
	
	
	@RequestMapping("/auth/categories")
	public String getCategories()
	{	
		String zomatoUrl = "https://developers.zomato.com/api/v2.1/categories";

		RestCalls restCalls=new RestCalls();
		
		if(restCalls!=null)
			return restCalls.postApiCall(zomatoUrl,null);
		else
			return "Failed after call";
	}

	@RequestMapping("/auth/FillTable")
	public void addRest()
	{
		RestCalls restCalls=new RestCalls();
		//1 chembur to bandra and verosva beach 
		//19.138387, 72.811423
		//19.031754, 72.928811
		//19.072450727939987 stopped here 
		
		//19.033464, 72.812994
		//19.008419, 72.899604
		
		//19.010918, 72.809946
		//18.978750, 72.857891
		
		//18.983690, 72.799054
		//18.959025, 72.847935
		
		//18.972326, 72.785794
		//18.898422, 72.845102
		for(double latitude=18.898422;latitude<18.972326;latitude=(latitude+0.00452185866))
		{
			for(double longitude=72.845102;longitude>72.785794;longitude=longitude-(1/(2*111.320*Math.cos(latitude))))
			{
			
					String zomatoUrl = "https://developers.zomato.com/api/v2.1/search?lat="+latitude+"&lon="+longitude+"&radius=500&sort=real_distance&order=asc";
					
					
					if(restCalls!=null)
					{
						
						JSONObject jsonObj=restCalls.postApiCallJ(zomatoUrl, null);
						int resultsFound = jsonObj.getInt("results_found");
						int resultsShown= jsonObj.getInt("results_shown");
						JSONArray arr = jsonObj.getJSONArray("restaurants");
						System.out.println("result "+zomatoUrl+" count "+latitude+" "+longitude); 
						for (int i = 0; i < arr.length(); i++)
						{
						    String id = arr.getJSONObject(i).getJSONObject("restaurant").getString("id");
						    String name = arr.getJSONObject(i).getJSONObject("restaurant").getString("name");
						    double lat=arr.getJSONObject(i).getJSONObject("restaurant").getJSONObject("location").getDouble("latitude");
						    double longi=arr.getJSONObject(i).getJSONObject("restaurant").getJSONObject("location").getDouble("longitude");
						    String area=arr.getJSONObject(i).getJSONObject("restaurant").getJSONObject("location").getString("locality");
						    double cost = (arr.getJSONObject(i).getJSONObject("restaurant").getDouble("average_cost_for_two"))/2;
						    String cuisine = arr.getJSONObject(i).getJSONObject("restaurant").getString("cuisines");
						    double rating=arr.getJSONObject(i).getJSONObject("restaurant").getJSONObject("user_rating").getDouble("aggregate_rating");
						    
			 
						    Restaurant rest=new Restaurant(id,name,area,longi,cost,lat,cuisine,rating);
						    
						    loginService.addRest(rest);
						}
						System.out.println("resultsFound="+resultsFound+" resultsShown="+resultsShown);
						
					}
					
					else;
			}
		}
		System.out.println("COMPLETE");
}
	
	
	@RequestMapping("/auth/cab/{slat}and{slong}and{elat}and{elong}and{seat}")
	public String getCab(@PathVariable Double slat,@PathVariable Double slong,@PathVariable Double elat,@PathVariable Double elong,@PathVariable Integer seat) {
	
		RestCalls restCalls = new RestCalls();
		if(restCalls!=null)
			//return restCalls.UberCall();
			return restCalls.getApiCall(slat,slong,elat,elong,seat);
		else
			return "Failed after call";
	}
	
	
	
	@RequestMapping(method=RequestMethod.POST,value="/auth/search")
	public Map<String, List<Restaurant>> getSearch(@RequestBody SearchJson sjson)
	{
		
		return loginService.getAllResult(sjson);
	}
	
	@RequestMapping("/auth/FillLocation")
	public void fillLoc()
	{
		//1 chembur to bandra and verosva beach 
		//19.138387, 72.811423
		//19.031754, 72.928811
		//19.072450727939987 stopped here 

		//19.033464, 72.812994
		//19.008419, 72.899604
		
		//19.010918, 72.809946
		//18.978750, 72.857891

		//18.983690, 72.799054
		//18.959025, 72.847935
		
		//18.972326, 72.785794
		//18.898422, 72.845102
		double l[]=new double[10];
		double lo[]=new double[10];
		l[0]=19.138387;
		lo[0]=72.811423;
		l[1]=19.031754;
		lo[1]=72.928811;
		l[2]=19.033464;
		lo[2]=72.812994;
		l[3]=19.008419;
		lo[3]= 72.899604;
		l[4]=19.010918;
		lo[4]=72.809946;
		l[5]=18.978750;
		lo[5]=72.857891;
		l[6]=18.983690;
		lo[6]=72.799054;
		l[7]=18.959025;
		lo[7]=72.847935;
		l[8]=18.972326;
		lo[8]=72.785794;
		l[9]=18.898422;
		lo[9]=72.845102;
		
		for(int i=0;i<10;i=i+2)
			for(double latitude=l[i+1];latitude<l[i];latitude=(latitude+(0.00452185866*4)))
			{
				
				for(double longitude=lo[i+1];longitude>lo[i];longitude=longitude-(4/(2*111.320*Math.cos(latitude))))
				{
					Location loc = new Location();
				    loc.setLatitude(latitude);
				    loc.setLongitude(longitude);
				    System.out.println("GridId "+loc.getGridId()+" "+loc.getLatitude());
					locationRepository.save(loc);
				}
			}
     }
	
	@RequestMapping("/auth/FillGrid")
	public void grid()
	{
		URestCalls restCalls = new URestCalls();
		List<Location> loc= locationRepository.findAll();
		List<Location> rloc= locationRepository.findAll();
		
		for(int i=0;i<=30;i++)
			for(int j=30;j<=60;j++)
			{
				if(i!=j)
				{
					
					JSONObject job=restCalls.UberCall(loc.get(i).getLatitude(),loc.get(i).getLongitude(),rloc.get(j).getLatitude(),rloc.get(j).getLongitude(),2);	
					System.out.println(job.getJSONArray("prices").getJSONObject(3).getDouble("distance")+"\n"+((job.getJSONArray("prices").getJSONObject(3).getDouble("high_estimate"))+(job.getJSONArray("prices").getJSONObject(3).getDouble("low_estimate")))/2+"\n"+job.getJSONArray("prices").getJSONObject(3).getDouble("duration"));
					Grid gridval=new Grid();
					gridval.setCost(((job.getJSONArray("prices").getJSONObject(3).getDouble("high_estimate"))+(job.getJSONArray("prices").getJSONObject(3).getDouble("low_estimate")))/2);
					gridval.setDistance(job.getJSONArray("prices").getJSONObject(3).getDouble("distance"));
					gridval.setGfrom(i+1);
					gridval.setGto(j+1);
					gridval.setTime(job.getJSONArray("prices").getJSONObject(3).getDouble("duration"));
					loginService.addGrid(gridval);
				}
			}
			
	}

	
	@RequestMapping("/auth/eliminate")
	public void elli()
	{

		List<Location> loc= locationRepository.findAll();
		List<Location> rloc= locationRepository.findAll();
		
		for(int i=0;i<=84;i++)
			for(int j=0;j<=84;j++)
			{
				if(i!=j)
				if(loginService.radiusLatLon(loc.get(i).getLatitude(), rloc.get(j).getLatitude(), loc.get(i).getLongitude(), rloc.get(j).getLongitude())<1)
				 {
					locationRepository.delete(loc.get(i));
					System.out.println(loc.get(i));
				}
			}
	}
	
}
