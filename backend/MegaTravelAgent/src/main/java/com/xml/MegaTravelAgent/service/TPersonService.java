package com.xml.MegaTravelAgent.service;

import com.xml.MegaTravelAgent.model.TPerson;
import com.xml.MegaTravelAgent.model.Agent;

import com.xml.MegaTravelAgent.repository.TPersonRepository;
import com.xml.MegaTravelAgent.soap.reqres.AgentSOAP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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


    public void updateAgents(List<AgentSOAP> agentsSOAP) {

        for (AgentSOAP aSoap: agentsSOAP) {
            TPerson person =  tPersonRepo.findOneByUsername(aSoap.getUsername());
            
            if (person == null) {
                Agent agent = new Agent();
                agent.setName(aSoap.getFirstname());
                agent.setLastname(aSoap.getLastname());
                agent.setEmail(aSoap.getEmail());
                agent.setUsername(aSoap.getUsername());
                agent.setPassword(aSoap.getPassword());
                agent.setRegistrationNumber(aSoap.getRegistrationNumber());
                agent.setRole("agent");

                tPersonRepo.save(agent);
                continue;
            } else {
                if (!person.getRole().equals("agent")) {
                    // todo transform user to agent
                }
            }
        }

    }
}
