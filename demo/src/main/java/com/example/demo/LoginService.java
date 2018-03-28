package com.example.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
	
	public List<Restaurant> getML(SearchJson sjson){
		List<Restaurant> list=new ArrayList<>();
		List<Restaurant> eResult=new ArrayList<>();
		restaurantRepository.findAll().forEach(list::add);
		double uRating=2.5;
		String uCusine=sjson.getCuisine();
		double uCost=sjson.getBudget();
		double uLatitude=sjson.getLatitude();
		double uLongitude=sjson.getLongitude();
		double uTime=sjson.getTime();
		CustQueue que=new CustQueue();
		for(int i=0;i<6000;i++)
		{
			if(list.get(i).getRating()>=uRating)
			{
				String cu=list.get(i).getCuisine();
				if(cu.contains(uCusine))
				{
					double l=-5000;
					double d;
					double t=-5000;
					
					int userHotspot=this.Hotspot(uLatitude, uLongitude);
					System.out.println("Here userhotspot "+userHotspot+"\n");
					int restHotspot=this.Hotspot(list.get(i).getLatitude(),list.get(i).getLongitude());
					System.out.println("Here resthotspot "+restHotspot+"\n");
					
					List<Grid> grid=gridRepository.findAll();
					Grid gridval=new Grid();
					
					for(int j=0;j<100;j++)
					{
						if(grid.get(j).getGfrom()==userHotspot&&grid.get(j).getGto()==restHotspot)
						{
							gridval=grid.get(j);
						}
					}
					if(userHotspot==restHotspot)
					{
						gridval.setCost(25);
						gridval.setDistance(2);
						gridval.setGfrom(userHotspot);
						gridval.setGto(restHotspot);
						gridval.setTime(15);
					}
					double rCost=list.get(i).getEst_cost_per_person();
					double cCost=2*gridval.getCost();
					if((rCost+cCost)<=uCost)
					{
						if((uCost-rCost-cCost)>0)
							l=(uCost-rCost-cCost)*(uCost-rCost-cCost)/10;
						else{
							l=(uCost-rCost-cCost)*(uCost-rCost-cCost)/10;
							l=-l;
						}
						
					}
					
					double distance=(gridval.getDistance())*1.61;
					double time=(gridval.getTime()/30)+30;
					
					d=-1*distance*distance*100;
					
					if(time<=(uTime+60))
					{
						if(uTime-time>0)
							t=((uTime-time)*(uTime-time))*2;
						else {
							t=((uTime-time)*(uTime-time))*2;
							t=-t;
						}
					}
					double temp=d+l+t;
					System.out.println("\n Here l = "+l+" d = "+d+" t = "+t+" temp :"+temp+"\n");
					que.Qadd(list.get(i),temp);
						
				}
			}
		}
		List<Restaurant> rest=new ArrayList<>();
		
		for(int x=0;x<10;x++)
			rest.add(que.Qget(x));
		
		return rest;
	}
	
	public int Hotspot(double lat,double lon)
	{
		int result=1;
		List<Location> list=locationRepository.findAll();
		double temp1=100000000;
		double temp2=0;
		for(int i=0;i<80;i++)
		{
			
			temp2=this.radiusLatLon(lat,list.get(i).getLatitude(),lon,list.get(i).getLongitude());
			if(temp2<temp1)
			{
				temp1=temp2;
				result=i;
			}
		}
		
		return result;
	}
}
