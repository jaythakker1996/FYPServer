package com.example.demo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LoginService {

	@Autowired
	private LoginRepository loginRepository;
	
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

	public void saveValues() {
		Role mySet=new Role();
		//loginRepository.save(new User(1,"abc","abc","","",mySet,1));
		//loginRepository.save(new User(2,"jay","abc","","",mySet,1));
	}
}
