package com.megatravel.authservice.controller;


import javax.validation.Valid;

import com.megatravel.authservice.model.Admin;
import com.megatravel.authservice.model.Role;
import com.megatravel.authservice.model.TPerson;
import com.megatravel.authservice.repository.RoleRepository;
import com.megatravel.authservice.service.RoleService;
import org.owasp.encoder.Encode;

import com.megatravel.authservice.dto.UserDTO;
import com.megatravel.authservice.dto.UserInfoDTO;
import com.megatravel.authservice.dto.UserListDTO;
import com.megatravel.authservice.model.User;
import com.megatravel.authservice.security.TokenUtils;
import com.megatravel.authservice.service.Logging;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.megatravel.authservice.service.TPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private TPersonService tPersonService;

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    public RoleService roleService;

    private Logging logger = new Logging(this);


    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<UserInfoDTO>> getAll(){
        List<UserListDTO> users = tPersonService.findAllUsers();
        return new ResponseEntity(users, HttpStatus.OK);
    }

    @RequestMapping(value = "/blocked", method = RequestMethod.GET)
    public ResponseEntity<List<UserInfoDTO>> getAllBlocked(){
        List<UserListDTO> users = tPersonService.findAllBlocked();
        return new ResponseEntity(users, HttpStatus.OK);
    }

    @RequestMapping(value = "/block/{username}", method = RequestMethod.PUT)
    @PreAuthorize("hasAuthority('BLOCK_USER')")
    public ResponseEntity<List<UserListDTO>> blockUser(@PathVariable("username") String username, HttpServletRequest request){
        String token = tokenUtils.getToken(request);
        String admin = tokenUtils.getUsernameFromToken(token);

        logger.logInfo("U_BLOCK - Username: " + admin + "; IP: " + request.getRemoteAddr());
        try{
            tPersonService.blokUser(username);
            logger.logInfo("U_BLOCK_SUCCESS - Username: " + admin + "; IP: " + request.getRemoteAddr());
        }catch(Exception e){
            logger.logWarning("U_BLOCK_ERR - Username: " + admin + "; IP: " + request.getRemoteAddr() + "; Message: " + e.getMessage());
        }
        List<UserListDTO> users = tPersonService.findAllUsers();
        return new ResponseEntity(users,HttpStatus.OK);
    }

    @RequestMapping(value = "/activate/{username}", method = RequestMethod.PUT)
    @PreAuthorize("hasAuthority('ACTIVATE_USER')")
    public ResponseEntity<List<UserListDTO>> activateUser(@PathVariable("username") String username, HttpServletRequest request){
        String token = tokenUtils.getToken(request);
        String admin = tokenUtils.getUsernameFromToken(token);
        logger.logInfo("U_ACTIVATE - Username: " + admin + "; IP: " + request.getRemoteAddr());
        try{
            tPersonService.activateUser(username);
            logger.logInfo("U_ACTIVATE_SUCCESS - Username: " + admin + "; IP: " + request.getRemoteAddr());
        }catch(Exception e){
            logger.logWarning("U_ACTIVATE_ERR - Username: " + admin + "; IP: " + request.getRemoteAddr() + "; Message: " + e.getMessage());
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        List<UserListDTO> users = tPersonService.findAllUsers();
        return new ResponseEntity(users,HttpStatus.OK);
    }

    @RequestMapping(value = "/remove/{username}", method = RequestMethod.DELETE)
    @PreAuthorize("hasAuthority('REMOVE_USER')")
    public ResponseEntity<List<UserListDTO>> removeUser(@PathVariable("username") String username, HttpServletRequest request){
        String token = tokenUtils.getToken(request);
        String admin = tokenUtils.getUsernameFromToken(token);
        logger.logInfo("U_REMOVE - Username: " + admin + "; IP: " + request.getRemoteAddr());
        try{
            tPersonService.remove(username);
            logger.logInfo("U_REMOVE_SUCCESS - Username: " + admin + "; IP: " + request.getRemoteAddr());
        }catch(Exception e){
            logger.logWarning("U_REMOVE_ERR - Username: " + admin + "; IP" + request.getRemoteAddr() + "; Message: " + e.getMessage());
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        List<UserListDTO> users = tPersonService.findAllUsers();
        return new ResponseEntity(users,HttpStatus.OK);
    }

    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    public ResponseEntity<?> getUser(HttpServletRequest request) {
        String token = tokenUtils.getToken(request);
        String username = tokenUtils.getUsernameFromToken(token);

        if(username != "" && username != null) {
            TPerson u = (TPerson) tPersonService.findOneByUsername(username);
            if(u instanceof User){
                u = (User) u;
            }else if(u instanceof Admin){
                u = (Admin) u;
            }
            UserInfoDTO ui = new UserInfoDTO(u.getUsername(), u.getEmail(), u.getName(), u.getLastname());
            return new ResponseEntity<UserInfoDTO>(ui, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<UserDTO> saveUser(@Valid @RequestBody UserDTO userDTO) {
        logger.logInfo("UREG");
        User exists = (User) tPersonService.findOneByUsername(Encode.forHtml(userDTO.getUsername()));

        if(!mailValid(userDTO.getEmail())) {
            logger.logError("UREG_MAIL_ERR");
            logger.logError("UREG_FAIL");
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        } else if (!namesValid(userDTO.getFirstname())) {
            logger.logError("UREG_FIRSTNAME_ERR");
            logger.logError("UREG_FAIL");
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        } else if (!namesValid(userDTO.getLastname())) {
            logger.logError("UREG_LASTNAME_ERR");
            logger.logError("UREG_FAIL");
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        } else if (!usernameValid(userDTO.getUsername())) {
            logger.logError("UREG_UNAME_ERR");
            logger.logError("UREG_FAIL");
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        } else if (!passValid(userDTO.getPassword())) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        if(exists != null) {
            logger.logError("UREG_UNAME_EXISTS_ERR");
            logger.logError("UREG_FAIL");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        User user = new User();

        user.setName(userDTO.getFirstname());
        user.setLastname(userDTO.getLastname());
        user.setEmail(userDTO.getEmail());
        user.setUsername(userDTO.getUsername());
        user.setRole("user");
        user.setStatus("active");


        // generisati salt pomocu BCrypta i uraditi hesiranje sifre
        String salt = BCrypt.gensalt();
        String hashedPass = BCrypt.hashpw(userDTO.getPassword(), salt);
        user.setPassword(hashedPass);

        // izvuci User rolu iz baze i dodeli korisniku
        Long id = new Long(2);
        Role role = roleService.findOneById(id);
        List<Role> roles = new ArrayList<Role>();
        roles.add(role);
        user.setRoles(roles);

        user = tPersonService.save(user);

        logger.logInfo("UREG_SUCCESS");
        return new ResponseEntity<>(new UserDTO(), HttpStatus.CREATED);

    }




    // * * * UTILS * * *

    public boolean namesValid(String text) {

        if(text.isEmpty()) {
            return false;
        }
        for(Character c : text.toCharArray()) {
            if (Character.isWhitespace(c) || !Character.isLetter(c)) {
                return false;
            }
        }

        return true;
    }

    public boolean usernameValid(String text) {

        if(text.isEmpty()) {
            return false;
        }
        if(text.contains(";") || text.contains(">") || text.contains("<")) {
            return false;
        }
        for(Character c : text.toCharArray()) {
            if (Character.isWhitespace(c)) {
                return false;
            }
        }

        return true;
    }

    public boolean passValid(String pass) {
        if(pass.isEmpty()) {
            return false;
        }
        Pattern pattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$");
        Matcher matcher = pattern.matcher(pass);

        return matcher.matches();
    }

    public boolean mailValid(String mail) {
        if(mail.isEmpty()) {
            return false;
        }

        Pattern pattern = Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");
        Matcher matcher = pattern.matcher(mail);

        return matcher.matches();
    }
}
