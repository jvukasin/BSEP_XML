package com.megatravel.reservationservice.repository;

import com.megatravel.reservationservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User,String> {
	User findOneByUsername(String username);
}
