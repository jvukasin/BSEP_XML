package com.megatravel.authservice.controller;

import com.megatravel.authservice.dto.UserInfoDTO;
import com.megatravel.authservice.model.TPerson;
import com.megatravel.authservice.model.User;
import com.megatravel.authservice.repository.TPersonRepository;
import com.megatravel.authservice.service.TPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private TPersonService tPersonService;


    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<UserInfoDTO>> getAll(){
        List<User> users = tPersonService.findAllUsers();
        List<UserInfoDTO> usersDTO = new ArrayList<UserInfoDTO>();
        for(User u: users){
            usersDTO.add(new UserInfoDTO(u.getUsername(), u.getEmail(), u.getName(), u.getLastname()));
        }
        return new ResponseEntity(usersDTO, HttpStatus.OK);
    }


    @RequestMapping(value = "/block/{username}", method = RequestMethod.PUT)
    public ResponseEntity<?> blockUser(@PathVariable("username") String username){
        tPersonService.blokUser(username);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/activate/{username}", method = RequestMethod.PUT)
    public ResponseEntity<?> activateUser(@PathVariable("username") String username){
        tPersonService.activateUser(username);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/remove/{username}", method = RequestMethod.DELETE)
    public ResponseEntity<?> removeUser(@PathVariable("username") String username){
        tPersonService.remove(username);
        return new ResponseEntity(HttpStatus.OK);
    }




}
