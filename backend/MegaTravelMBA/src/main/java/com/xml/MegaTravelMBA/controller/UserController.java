package com.xml.MegaTravelMBA.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xml.MegaTravelMBA.dto.UserDTO;
import com.xml.MegaTravelMBA.model.temp.UserTemp;
import com.xml.MegaTravelMBA.model.temp.UserTokenState;
import com.xml.MegaTravelMBA.service.UserService;
import org.owasp.encoder.Encode;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String getUser(@PathVariable("id") String username) {
		return "getUser izvrsen!";
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@PreAuthorize("hasAuthority('DELETE_USER')")
	public String deleteUser(@PathVariable("id") String username) {
		return "User obrisan!";
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<UserDTO> saveUser(@Valid @RequestBody UserDTO userDTO) {
		
		UserTemp exists = userService.findOneByUsername(Encode.forHtml(userDTO.getUsername()));
		
		if(!mailValid(userDTO.getEmail())) {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		} else if (!namesValid(userDTO.getFirstname()) || !namesValid(userDTO.getLastname())) {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		} else if (!usernameValid(userDTO.getUsername())) {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
		
		if(exists != null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		UserTemp user = new UserTemp();
		
		user.setFirstName(userDTO.getFirstname());
		user.setLastName(userDTO.getLastname());
		user.setEmail(userDTO.getEmail());
		user.setUsername(userDTO.getUsername());
		
		// generisati salt pomocu BCrypta i uraditi hesiranje sifre
		String salt = BCrypt.gensalt();
		String hashedPass = BCrypt.hashpw(userDTO.getPassword(), salt);
		user.setPassword(hashedPass);
		
		user = userService.save(user);
		
		return new ResponseEntity<>(new UserDTO(), HttpStatus.CREATED);
		
	}
	
	
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
	
	public boolean mailValid(String mail) {
		if(mail.isEmpty()) {
			return false;
		}
		
		Pattern pattern = Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");
		Matcher matcher = pattern.matcher(mail);
		
		return matcher.matches();
	}
	
}
