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
	
	@RequestMapping("/login/{username}and{password}")
	public Success getLogIn(@PathVariable String username,@PathVariable String password){
		return loginService.getLogIn(username,password);
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/login")
	public Success addUser(@RequestBody User user)
	{
		return loginService.addUser(user);
	}
}
