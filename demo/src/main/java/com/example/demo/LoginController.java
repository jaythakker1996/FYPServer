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

	//Values brought from session factory i.e. dependency injection
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
		Success success=new Success(true);
		return success;
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
			return restCalls.getApiCall(zomatoUrl,null);
		else
			return "Failed after call";
	}
	
	@RequestMapping("/search")
	public String getSearch(Search search)
	{
		
		return "sagarsoft";
	}
	
}
