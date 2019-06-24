package com.xml.MegaTravelAgent.controller;

import com.xml.MegaTravelAgent.dto.UserInfoDTO;
import com.xml.MegaTravelAgent.model.Agent;
import com.xml.MegaTravelAgent.model.User;
import com.xml.MegaTravelAgent.model.UserTokenState;
import com.xml.MegaTravelAgent.security.CustomUserDetailsService;
import com.xml.MegaTravelAgent.security.TokenUtils;
import com.xml.MegaTravelAgent.security.auth.JwtAuthenticationRequest;
import com.xml.MegaTravelAgent.service.TPersonService;
import com.xml.MegaTravelAgent.soap.client.AuthClient;
import com.xml.MegaTravelAgent.soap.client.IAuthClient;
import com.xml.MegaTravelAgent.soap.reqres.FetchAgentsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
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
    private TokenUtils tokenUtils;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private TPersonService tPersonService;

    @Autowired
    private IAuthClient authClient;

    @RequestMapping(value="/login",method = RequestMethod.POST)
    public ResponseEntity<?> loginUser(@RequestBody JwtAuthenticationRequest authenticationRequest, HttpServletResponse response, Device device, HttpServletRequest hr){

        // logger.logInfo("ULOG. Username: " + authenticationRequest.getUsername() + ", IP ADDRESS: " + hr.getRemoteAddr());

        if(!inputValid(authenticationRequest.getUsername())) {
            // logger.logError("ULOG_UNAME_ERR. Username: " + authenticationRequest.getUsername());
            return new ResponseEntity<>(new UserTokenState("error",0), HttpStatus.NOT_FOUND);
        }


        Authentication authentication;
        try {
            authentication = manager
                .authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            System.out.println("Bad credentials!");
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);

        }


        SecurityContextHolder.getContext().setAuthentication(authentication);
        Agent agent = (Agent) authentication.getPrincipal();

        // VRATI DRUGI STATUS KOD
        if(agent == null) {
            // logger.logError("ULOG_FAIL. "+ authenticationRequest.getUsername() + " is not authorized.");
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        String jwt = tokenUtils.generateToken(agent.getUsername(), device);

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

    @RequestMapping(value="/agents", method = RequestMethod.GET)
    public ResponseEntity<?> fetchAgents(){

        FetchAgentsResponse response = authClient.fetchAgents();

        tPersonService.updateAgents(response.getAgentSOAP());

        return new ResponseEntity<>(HttpStatus.OK);
    }



    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ResponseEntity<?> getUser(HttpServletRequest request) {
        String token = tokenUtils.getToken(request);
        String username = tokenUtils.getUsernameFromToken(token);

        if(username != "" && username != null) {

            Agent a = (Agent) tPersonService.findOneByUsername(username);
            UserInfoDTO ui = new UserInfoDTO(a.getUsername(), a.getEmail(), a.getName(), a.getLastname());

            return new ResponseEntity<UserInfoDTO>(ui, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
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
