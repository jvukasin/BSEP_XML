package com.xml.MegaTravelMBA.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xml.MegaTravelMBA.model.Agent;


public interface AgentRepository extends JpaRepository<Agent, Long> 
{
	  
	Agent findOneById(Long id);

}