package com.example.demo;

import java.util.List;

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
	
	@RequestMapping("/save")
	public void saveValues(){
		loginService.saveValues();
	}
	
	@RequestMapping("/login")
	public List<User> getAllLogin(){
		return loginService.getAllLogin();
	}
	//Everything in {} represents a variable in the URL
	@RequestMapping("/login/{username}and{password}")
	public Success getLogIn(@PathVariable String username,@PathVariable String password){
		return loginService.getLogIn(username,password);
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/login")
	public Success addUser(@RequestBody User user)
	{
		return loginService.addUser(user);
	}
	
	@RequestMapping("/categories")
	public String getCategories()
	{	
		String zomatoUrl = "https://developers.zomato.com/api/v2.1/categories";

		RestCalls restCalls=new RestCalls();
		
		if(restCalls!=null)
			return restCalls.getApiCall(zomatoUrl,null,0);
		else
			return "Failed after call";
	}

	
	
	@RequestMapping("/cab/{slat}and{slong}and{elat}and{elong}and{seat}")
	public String getCab(@PathVariable Double slat,@PathVariable Double slong,@PathVariable Double elat,@PathVariable Double elong,@PathVariable Integer seat) {
	
		String uberUrl = "https://api.uber.com//v1.2/estimates/price?start_latitude="+slat.toString()+"&start_longitude="+slong.toString()+"&end_latitude="+elat.toString()+"&end_longitude="+elong.toString();
		System.out.println(uberUrl);
		RestCalls restCalls = new RestCalls();
		if(restCalls!=null)
			return restCalls.getApiCall(uberUrl,null,1);
		else
			return "Failed after call";
	}
	
	@RequestMapping("/search")
	public List<Restaurant> getSearch()
	{
		
		return loginService.getAllResult();
	}
}
