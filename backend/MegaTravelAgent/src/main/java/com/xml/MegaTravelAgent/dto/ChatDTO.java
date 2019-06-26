package com.xml.MegaTravelAgent.dto;

import com.xml.MegaTravelAgent.model.Message;

import java.util.ArrayList;
import java.util.List;

public class ChatDTO {

    String senderUsername;
    List<MessageDTO> senderMessages = new ArrayList<>();
    List<MessageDTO> receiverMessages = new ArrayList<>();

    public ChatDTO() {
    }

    public String getSenderUsername() {
        return senderUsername;
    }

    public void setSenderUsername(String senderUsername) {
        this.senderUsername = senderUsername;
    }

    public List<MessageDTO> getSenderMessages() {
        return senderMessages;
    }

    public void setSenderMessages(List<MessageDTO> senderMessages) {
        this.senderMessages = senderMessages;
    }

    public List<MessageDTO> getReceiverMessages() {
        return receiverMessages;
    }

    public void setReceiverMessages(List<MessageDTO> receiverMessages) {
        this.receiverMessages = receiverMessages;
    }
}
