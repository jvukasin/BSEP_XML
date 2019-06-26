package com.xml.MegaTravelAgent.dto;

import java.util.ArrayList;
import java.util.List;

public class ChatDTO {

    String senderUsername;
    List<MessageDTO> Messages = new ArrayList<>();

    public ChatDTO() {
    }

    public ChatDTO(String senderUsername, List<MessageDTO> messages) {
        this.senderUsername = senderUsername;
        Messages = messages;
    }

    public String getSenderUsername() {
        return senderUsername;
    }

    public void setSenderUsername(String senderUsername) {
        this.senderUsername = senderUsername;
    }

    public List<MessageDTO> getMessages() {
        return Messages;
    }

    public void setMessages(List<MessageDTO> messages) {
        Messages = messages;
    }
}

