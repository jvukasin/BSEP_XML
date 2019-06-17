package com.megatravel.accommodationservice.repository;

import com.megatravel.accommodationservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User,String> {
	User findOneByUsername(String username);
}
