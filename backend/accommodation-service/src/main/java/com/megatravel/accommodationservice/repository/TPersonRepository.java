package com.megatravel.accommodationservice.repository;

import com.megatravel.accommodationservice.model.TPerson;
import com.megatravel.accommodationservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TPersonRepository extends JpaRepository<TPerson,String> {
	TPerson findOneByUsername(String username);
}
