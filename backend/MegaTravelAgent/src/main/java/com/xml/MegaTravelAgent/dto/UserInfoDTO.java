package com.xml.MegaTravelAgent.dto;

import com.xml.MegaTravelAgent.model.TPerson;

public class UserInfoDTO {

    private String username;
    private String email;
    private String firstname;
    private String lastname;

    public UserInfoDTO() {
    }

    public UserInfoDTO(String username, String email, String firstname, String lastname) {
        this.username = username;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
    }
    
    public UserInfoDTO(TPerson person)
    {
    	username = person.getUsername();
    	email = person.getEmail();
    	firstname = person.getName();
    	lastname = person.getLastname();
    	
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
