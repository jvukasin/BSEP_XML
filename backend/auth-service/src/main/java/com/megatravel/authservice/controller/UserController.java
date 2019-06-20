package com.megatravel.authservice.controller;

import com.megatravel.authservice.dto.UserInfoDTO;
import com.megatravel.authservice.dto.UserListDTO;
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
        List<UserListDTO> users = tPersonService.findAllUsers();
        return new ResponseEntity(users, HttpStatus.OK);
    }


    @RequestMapping(value = "/block/{username}", method = RequestMethod.PUT)
    public ResponseEntity<List<UserListDTO>> blockUser(@PathVariable("username") String username){
        tPersonService.blokUser(username);
        List<UserListDTO> users = tPersonService.findAllUsers();
        return new ResponseEntity(users,HttpStatus.OK);
    }

    @RequestMapping(value = "/activate/{username}", method = RequestMethod.PUT)
    public ResponseEntity<List<UserListDTO>> activateUser(@PathVariable("username") String username){
        tPersonService.activateUser(username);
        List<UserListDTO> users = tPersonService.findAllUsers();
        return new ResponseEntity(users,HttpStatus.OK);
    }

    @RequestMapping(value = "/remove/{username}", method = RequestMethod.DELETE)
    public ResponseEntity<List<UserListDTO>> removeUser(@PathVariable("username") String username){
        tPersonService.remove(username);
        List<UserListDTO> users = tPersonService.findAllUsers();
        return new ResponseEntity(users,HttpStatus.OK);
    }




}
