package com.megatravel.authservice.controller;


import javax.validation.Valid;

import org.owasp.encoder.Encode;

import com.megatravel.authservice.dto.UserDTO;
import com.megatravel.authservice.dto.UserInfoDTO;
import com.megatravel.authservice.dto.UserListDTO;
import com.megatravel.authservice.model.User;
import com.megatravel.authservice.security.TokenUtils;
import com.megatravel.authservice.service.Logging;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.megatravel.authservice.service.TPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

//    private Logging logger = new Logging(this);


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

    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    public ResponseEntity<?> getUser(HttpServletRequest request) {
        String token = tokenUtils.getToken(request);
        String username = tokenUtils.getUsernameFromToken(token);

        if(username != "" && username != null) {
            User u = (User) tPersonService.findOneByUsername(username);
            UserInfoDTO ui = new UserInfoDTO(u.getUsername(), u.getEmail(), u.getName(), u.getLastname());
            return new ResponseEntity<UserInfoDTO>(ui, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<UserDTO> saveUser(@Valid @RequestBody UserDTO userDTO) {
//        logger.logInfo("UREG");
        User exists = (User) tPersonService.findOneByUsername(Encode.forHtml(userDTO.getUsername()));

        if(!mailValid(userDTO.getEmail())) {
//            logger.logError("UREG_MAIL_ERR");
//            logger.logError("UREG_FAIL");
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        } else if (!namesValid(userDTO.getFirstname())) {
//            logger.logError("UREG_FIRSTNAME_ERR");
//            logger.logError("UREG_FAIL");
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        } else if (!namesValid(userDTO.getLastname())) {
//            logger.logError("UREG_LASTNAME_ERR");
//            logger.logError("UREG_FAIL");
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        } else if (!usernameValid(userDTO.getUsername())) {
//            logger.logError("UREG_UNAME_ERR");
//            logger.logError("UREG_FAIL");
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        } else if (!passValid(userDTO.getPassword())) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        if(exists != null) {
//            logger.logError("UREG_UNAME_EXISTS_ERR");
//            logger.logError("UREG_FAIL");
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

        user = tPersonService.save(user);

//        logger.logInfo("UREG_SUCCESS");
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
