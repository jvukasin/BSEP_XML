package com.megatravel.authservice.service;

import com.megatravel.authservice.dto.UserListDTO;
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



}
