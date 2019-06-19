package com.xml.MegaTravelMBA.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.Valid;

import org.owasp.encoder.Encode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xml.MegaTravelMBA.dto.UserDTO;
import com.xml.MegaTravelMBA.model.UserStatus;
import com.xml.MegaTravelMBA.model.temp.UserTemp;
import com.xml.MegaTravelMBA.service.Logging;
import com.xml.MegaTravelMBA.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;

	@Autowired
	private PasswordEncoder encoder;
	
	private Logging logger = new Logging(this);
	
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
		logger.logInfo("UREG");
		UserTemp exists = userService.findOneByUsername(Encode.forHtml(userDTO.getUsername()));
		
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
		
		logger.logInfo("UREG_SUCCESS");
		return new ResponseEntity<>(new UserDTO(), HttpStatus.CREATED);
		
	}
	

	@RequestMapping(value = "/{id}/{status}", method = RequestMethod.PUT)
	public ResponseEntity<?> changeUserStatus(@PathVariable("id") Long id, @PathVariable("status") UserStatus status)
	{
		return new ResponseEntity<>(HttpStatus.OK);

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
