package com.megatravel.authservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import com.megatravel.authservice.soap.reqres.AgentSOAP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.megatravel.authservice.dto.AgentDTO;
import com.megatravel.authservice.dto.UserListDTO;
import com.megatravel.authservice.model.Agent;
import com.megatravel.authservice.model.TPerson;
import com.megatravel.authservice.model.User;
import com.megatravel.authservice.repository.TPersonRepository;

import exceptions.BusinessException;

@Service
public class TPersonService {

    @Autowired
    private TPersonRepository tPersonRepo;

    public TPerson findOneByUsername(String username) {
        return tPersonRepo.findOneByUsername(username);
    }

    public List<TPerson> findAll() {
        return tPersonRepo.findAll();
    }

    //vraca listu korisnika (type = USER)
    public List<UserListDTO> findAllUsers() {
        List<TPerson> users = tPersonRepo.findAll();
        List<UserListDTO> retVal = new ArrayList<UserListDTO>();
        for (TPerson person : users) {
            if (person instanceof User) {
                User user = (User) person;
                retVal.add(new UserListDTO(user.getUsername(),user.getEmail(),user.getName(), user.getLastname(), user.getStatus()));
            }
        }
        return retVal;
    }

	// vraca listu agenta, koristi se za SOAP
	public List<AgentSOAP> findAllAgentsSOAP() {

		List<TPerson> persons = tPersonRepo.findAll();
		List<AgentSOAP> retVal = new ArrayList<>();

		for (TPerson person : persons) {
			if (person instanceof Agent) {

				Agent agent = (Agent) person;
				AgentSOAP a = new AgentSOAP();

				a.setFirstname(person.getName());
				a.setLastname(person.getLastname());
				a.setEmail(person.getEmail());
				a.setUsername(person.getUsername());
				a.setPassword(person.getPassword());
				a.setRegistrationNumber(((Agent) person).getRegistrationNumber());

				retVal.add(a);
			}
		}
		return retVal;
	}

    public void blokUser(String username) {
        User user = (User) tPersonRepo.findOneByUsername(username);
        user.setStatus("Blocked");
        tPersonRepo.save(user);
    }

    public void activateUser(String username) {
        User user = (User) tPersonRepo.findOneByUsername(username);
        user.setStatus("Active");
        tPersonRepo.save(user);

    }

    public void remove(String username){
        tPersonRepo.deleteById(username);
    }

    public User save(User user) { return tPersonRepo.save(user); }
    
    
    
    
    
    
    //* * * AGENT STUFF * * * ;)
    
    public String addAgent(AgentDTO agentDTO)
    {
    	Agent agent = new Agent();
    	
    	if(agentDTO.getFirstname() == null || agentDTO.getFirstname().equals(""))
    	{
    		throw new BusinessException("Firstname is not valid.");
    	}
    	if(agentDTO.getLastname() == null || agentDTO.getLastname().equals(""))
    	{
    		throw new BusinessException("Lastname is not valid.");
    	}
    	if(agentDTO.getEmail() == null || agentDTO.getEmail().equals(""))
    	{
    		throw new BusinessException("Email is not valid.");
    	}
    	if(agentDTO.getUsername() == null || agentDTO.getUsername().equals(""))
    	{
    		throw new BusinessException("Username is not valid.");
    	}
    	if(agentDTO.getPassword() == null || agentDTO.getPassword().equals(""))
    	{
    		throw new BusinessException("Password is not valid.");
    	}
    	if(agentDTO.getRegistrationNumber() == null || agentDTO.getRegistrationNumber().equals(""))
    	{
    		throw new BusinessException("Registration number is not valid.");
    	}

    	
    	if(!isEmailValid(agentDTO.getEmail()))
    	{
    		throw new BusinessException("Email is not valid.");
    	}
    	if(isUsernameTaken(agentDTO.getUsername()))
    	{
    		throw new BusinessException("Username is already taken.");
    	}
    	
    	agent.setName(agentDTO.getFirstname());
    	agent.setLastname(agentDTO.getLastname());
    	agent.setEmail(agentDTO.getEmail());
    	agent.setUsername(agentDTO.getUsername());
    	agent.setRegistrationNumber(agentDTO.getRegistrationNumber());
    	agent.setRole("agent");

		String salt = BCrypt.gensalt();
		String hashedPass = BCrypt.hashpw(agentDTO.getPassword(), salt);

		agent.setPassword(hashedPass);

    	tPersonRepo.save(agent);
    	return agent.getUsername();
    }

    
    // * * * AGENT UTILS * * *

    private boolean isEmailValid(String email)
    {
	   String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
	   return email.matches(regex);
    }
    
    public boolean isUsernameTaken(String username)
    {
    	try 
    	{
    		TPerson temp = tPersonRepo.findById(username).get();
    		return true;
    	}
    	catch(NoSuchElementException e)
    	{
    		return false;
    	}
    }
  

}
