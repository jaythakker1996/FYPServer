package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LoginService {

	@Autowired
	private LoginRepository loginRepository;
	
	public List<Login> getAllLogin() {
		List<Login> login=new ArrayList<>();
		loginRepository.findAll()
		.forEach(login::add);
		return login;
	}
	
	public Login getLogIn(String username){
		return loginRepository.findOne(username);
	}

	public void addUser(Login login) {
		loginRepository.save(login);
	}

	public void saveValues() {
		loginRepository.save(new Login(1,"abc","abc"));
		loginRepository.save(new Login(2,"abcd","abc"));
	}
}
