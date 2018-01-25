package com.example.demo;

import java.util.List;
import java.util.Map;

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
			return restCalls.postApiCall(zomatoUrl,null);
		else
			return "Failed after call";
	}

	@RequestMapping("/FillTable")
	public String addRest()
	{
		String zomatoUrl = "https://developers.zomato.com/api/v2.1/search?entity_id=3&entity_type=city&start=80&count=20";

		RestCalls restCalls=new RestCalls();
		
		if(restCalls!=null)
			return restCalls.postApiCall(zomatoUrl,null);
		else
			return "Failed after call";
}
	
	
	@RequestMapping("/cab/{slat}and{slong}and{elat}and{elong}and{seat}")
	public String getCab(@PathVariable Double slat,@PathVariable Double slong,@PathVariable Double elat,@PathVariable Double elong,@PathVariable Integer seat) {
	
		RestCalls restCalls = new RestCalls();
		if(restCalls!=null)
			//return restCalls.UberCall();
			return restCalls.getApiCall(slat,slong,elat,elong,seat);
		else
			return "Failed after call";
	}
	
	@RequestMapping("/search")
	public Map<String, List<Restaurant>> getSearch()
	{
		
		return loginService.getAllResult();
	}
}
