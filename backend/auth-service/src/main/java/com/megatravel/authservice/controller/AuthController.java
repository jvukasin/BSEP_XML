package com.megatravel.authservice.controller;

import com.megatravel.authservice.model.TPerson;
import com.megatravel.authservice.model.User;
import com.megatravel.authservice.model.UserTokenState;
import com.megatravel.authservice.security.CustomUserDetailsService;
import com.megatravel.authservice.security.TokenUtils;
import com.megatravel.authservice.security.auth.JwtAuthenticationRequest;
import com.megatravel.authservice.service.Logging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
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
    public RestTemplate restTemplate;

    @Autowired
    public TokenUtils tokenUtils;

    @Autowired
    public AuthenticationManager manager;

    @Autowired
    public CustomUserDetailsService userDetailsService;

    public Logging logger = new Logging(this);

    @RequestMapping(value="/login",method = RequestMethod.POST)
    public ResponseEntity<?> loginUser(@RequestBody JwtAuthenticationRequest authenticationRequest, HttpServletResponse response, Device device, HttpServletRequest hr){

         logger.logInfo("ULOG. Username: " + authenticationRequest.getUsername() + ", IP ADDRESS: " + hr.getRemoteAddr());

        if(!inputValid(authenticationRequest.getUsername())) {
             logger.logError("ULOG_UNAME_ERR. Username: " + authenticationRequest.getUsername());
            return new ResponseEntity<>(new UserTokenState("error",0), HttpStatus.NOT_FOUND);
        }

        System.out.println("DODAJE U KONTEKST: " + authenticationRequest.getUsername());
        final Authentication authentication = manager
                .authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));


        SecurityContextHolder.getContext().setAuthentication(authentication);
        HttpEntity<JwtAuthenticationRequest> HReq=new HttpEntity<JwtAuthenticationRequest>(authenticationRequest);
        //posalji zahtev servisu da stavi u kontekst
        try {
            ResponseEntity<?> responseReservation = restTemplate.postForEntity("http://reservation-service/resSecurity/setAuthentication", HReq, ResponseEntity.class);
        } catch (Exception e) {
            logger.logWarning("RES_SER_DOWN");
        }

        try {
            ResponseEntity<?> responseAccommodation = restTemplate.postForEntity("http://accommodation-service/accSecurity/setAuthentication", HReq, ResponseEntity.class);
        } catch (Exception e) {
            logger.logWarning("ACC_SER_DOWN");
        }

        TPerson user =  (TPerson) authentication.getPrincipal();
        System.out.println("DODAO: " + user.getUsername());
        // VRATI DRUGI STATUS KOD
        if(user == null) {
             logger.logError("ULOG_FAIL. "+ authenticationRequest.getUsername() + " is not authorized.");
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        String jwt = tokenUtils.generateToken(user.getUsername(), device);

        int expiresIn = 3600;

         logger.logInfo("ULOG_SUCCESS");

        return ResponseEntity.ok(new UserTokenState(jwt,expiresIn));
    }


    // proveriti jos da li je ovako dobro - istrazi
    @RequestMapping(value="/logout", method = RequestMethod.POST)
    public ResponseEntity<?> logOut(HttpServletRequest request, HttpServletResponse response){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null)
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        logger.logInfo("ULOG_OUT");
        //poslati svima zahtev da izbrisu kontekst
        try {
            ResponseEntity<?> responseReservation = restTemplate.postForEntity("http://reservation-service/resSecurity/logout", null, null);
        } catch (Exception e) {
            e.printStackTrace();
            logger.logWarning("RES_SER_DOWN");
        }
        try {
            ResponseEntity<?> responseAccommodation = restTemplate.postForEntity("http://accommodation-service/accSecurity/logout", null, null);
        } catch (Exception e) {
            e.printStackTrace();
            logger.logWarning("ACC_SER_DOWN");
        }
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
