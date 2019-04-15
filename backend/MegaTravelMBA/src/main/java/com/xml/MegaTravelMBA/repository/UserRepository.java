package com.xml.MegaTravelMBA.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xml.MegaTravelMBA.model.temp.UserTemp;

public interface UserRepository  extends JpaRepository<UserTemp, String> {
	UserTemp findOneByUsername(String username);
}
