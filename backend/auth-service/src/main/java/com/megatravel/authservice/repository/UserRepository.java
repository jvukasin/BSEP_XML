package com.megatravel.authservice.repository;

import com.megatravel.authservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User,String> {
	User findOneByUsername(String username);
}
