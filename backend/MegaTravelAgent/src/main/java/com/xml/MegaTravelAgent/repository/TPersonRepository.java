package com.xml.MegaTravelAgent.repository;

import com.xml.MegaTravelAgent.model.TPerson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TPersonRepository extends JpaRepository<TPerson,String> {
	TPerson findOneByUsername(String username);
}
