package com.megatravel.authservice.controller;

import com.megatravel.authservice.model.User;
import com.megatravel.authservice.model.UserTokenState;
import com.megatravel.authservice.security.CustomUserDetailsService;
import com.megatravel.authservice.security.TokenUtils;
import com.megatravel.authservice.security.auth.JwtAuthenticationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @RequestMapping(value="/login",method = RequestMethod.POST)
    public ResponseEntity<?> loginUser(@RequestBody JwtAuthenticationRequest authenticationRequest, HttpServletResponse response, Device device, HttpServletRequest hr){

        // logger.logInfo("ULOG. Username: " + authenticationRequest.getUsername() + ", IP ADDRESS: " + hr.getRemoteAddr());

        if(!inputValid(authenticationRequest.getUsername())) {
            // logger.logError("ULOG_UNAME_ERR. Username: " + authenticationRequest.getUsername());
            return new ResponseEntity<>(new UserTokenState("error",0), HttpStatus.NOT_FOUND);
        }


        final Authentication authentication = manager
                .authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));


        SecurityContextHolder.getContext().setAuthentication(authentication);

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        headers.add("Content-Type", "application/json");
        HttpEntity<JwtAuthenticationRequest> HReq=new HttpEntity<JwtAuthenticationRequest>(authenticationRequest,headers);
        //posalji zahtev servisu da stavi u kontekst
        ResponseEntity<?> responseEntity = restTemplate.postForEntity("http://reservation-service/resSecurity/setAuthentication", HReq, JwtAuthenticationRequest.class);
        ResponseEntity<?> res = restTemplate.postForEntity("http://accommodation-service/accSecurity/setAuthentication", HReq, JwtAuthenticationRequest.class);

        User user =  (User) authentication.getPrincipal();
//
//		if(!user.isActive()) {
//			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//		}
        // VRATI DRUGI STATUS KOD
        if(user == null) {
            // logger.logError("ULOG_FAIL. "+ authenticationRequest.getUsername() + " is not authorized.");
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        String jwt = tokenUtils.generateToken(user.getUsername(), device);

        int expiresIn = 3600;

        // logger.logInfo("ULOG_SUCCESS");

        return ResponseEntity.ok(new UserTokenState(jwt,expiresIn));
    }

    // proveriti jos da li je ovako dobro - istrazi
    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public ResponseEntity<?> logOut(){
        SecurityContextHolder.clearContext();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public boolean inputValid(String text) {

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
}
