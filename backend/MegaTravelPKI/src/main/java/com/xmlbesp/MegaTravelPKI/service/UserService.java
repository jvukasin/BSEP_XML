package com.xmlbesp.MegaTravelPKI.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmlbesp.MegaTravelPKI.model.Certificate;
import com.xmlbesp.MegaTravelPKI.model.User;
import com.xmlbesp.MegaTravelPKI.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepo;
	
	public User findOneById(Long id) {
		return userRepo.findOneById(id);
	}
	
	public List<User> findAll() {
		return userRepo.findAll();
	}
}
