package com.xml.MegaTravelAgent.dto;

import java.util.ArrayList;
import java.util.List;

public class ChatDTO {

    String agentUsername;
    String userUsername;
    List<MessageDTO> Messages = new ArrayList<>();

    public ChatDTO() {
    }


    public ChatDTO(String agentUsername, String userUsername, List<MessageDTO> messages) {
        this.agentUsername = agentUsername;
        this.userUsername = userUsername;
        Messages = messages;
    }

    public String getAgentUsername() {
        return agentUsername;
    }

    public void setAgentUsername(String agentUsername) {
        this.agentUsername = agentUsername;
    }

    public String getUserUsername() {
        return userUsername;
    }

    public void setUserUsername(String userUsername) {
        this.userUsername = userUsername;
    }

    public List<MessageDTO> getMessages() {
        return Messages;
    }

    public void setMessages(List<MessageDTO> messages) {
        Messages = messages;
    }
}

