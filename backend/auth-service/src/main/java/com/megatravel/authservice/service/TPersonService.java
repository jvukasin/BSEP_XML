package com.megatravel.authservice.service;

import com.megatravel.authservice.model.TPerson;
import com.megatravel.authservice.model.User;
import com.megatravel.authservice.repository.TPersonRepository;
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

    //vraca listu korisnika (type = USER)
    public List<User> findAllUsers() {
        List<TPerson> users = tPersonRepo.findAll();
        List<User> retVal = new ArrayList<User>();
        for (TPerson person : users) {
            if (person instanceof User) {
                User user = (User) person;
                retVal.add(user);
            }
            return retVal;
        }
        return null;
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





}
