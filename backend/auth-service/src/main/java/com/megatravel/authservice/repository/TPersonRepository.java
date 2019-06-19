package com.megatravel.authservice.repository;

import com.megatravel.authservice.model.TPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TPersonRepository extends JpaRepository<TPerson,String> {
	TPerson findOneByUsername(String username);
}
