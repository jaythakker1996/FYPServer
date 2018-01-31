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
	
	public Map<String, List<Restaurant>> getAllResult() {
		Map<String, List<Restaurant>> map = new HashMap<String, List<Restaurant>>();
		List<Restaurant> list=new ArrayList<>();
		restaurantRepository.findAll()
		.forEach(list::add);
		map.put("Results", list);
		return map;
	}
}
