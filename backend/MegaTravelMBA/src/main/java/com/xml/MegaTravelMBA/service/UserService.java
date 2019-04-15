package com.xml.MegaTravelMBA.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xml.MegaTravelMBA.model.temp.UserTemp;
import com.xml.MegaTravelMBA.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepo;
	
	public List<UserTemp> findAll() {
		return userRepo.findAll();
	}
	
	public UserTemp findOneByUsername(String username) {
		return userRepo.findOneByUsername(username);
	}
	
	public UserTemp save(UserTemp user) {
		return userRepo.save(user);
	}
	
	public void remove(String username) {
		userRepo.deleteById(username);
	}
	
	
}
