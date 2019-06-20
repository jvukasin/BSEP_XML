package com.megatravel.reservationservice.repository;

import com.megatravel.reservationservice.model.TPerson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TPersonRepository extends JpaRepository<TPerson,String> {

	TPerson findOneByUsername(String username);
}
