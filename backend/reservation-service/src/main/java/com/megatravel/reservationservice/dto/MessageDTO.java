package com.megatravel.reservationservice.dto;

import com.megatravel.reservationservice.model.Message;

import java.util.Date;

public class MessageDTO {

    private Long id;
    private String content;
    private Long reservationId;
    private Date date;
    private String senderUsername;
    private String receiverUsername;
    private boolean isUsersMessage;
    private boolean isAgentsMessage;


    public MessageDTO() {
    }

    public MessageDTO(Long id, String content, long reservationId, Date date, String sender, String receiver) {
        this.id = id;
        this.content = content;
        this.reservationId = reservationId;
        this.date = date;
        this.senderUsername = sender;
        this.receiverUsername = receiver;
    }

    public MessageDTO(Message m) {
        this.id = m.getId();
        this.content = m.getContent();
        this.date = m.getDate();
        this.senderUsername = m.getSender().getUsername();
        this.receiverUsername = m.getReceiver().getUsername();
        this.reservationId = m.getReservation().getId();
        this.isAgentsMessage = m.isIsAgentsMessage();
        this.isUsersMessage = m.isIsUsersMessage();

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getSenderUsername() {
        return senderUsername;
    }

    public void setSenderUsername(String senderUsername) {
        this.senderUsername = senderUsername;
    }

    public String getReceiverUsername() {
        return receiverUsername;
    }

    public void setReceiverUsername(String receiverUsername) {
        this.receiverUsername = receiverUsername;
    }

    public boolean isIsUsersMessage() {
        return isUsersMessage;
    }

    public void setIsUsersMessage(boolean usersMessage) {
        isUsersMessage = usersMessage;
    }

    public boolean isIsAgentsMessage() {
        return isAgentsMessage;
    }

    public void setIsAgentsMessage(boolean agentsMessage) {
        isAgentsMessage = agentsMessage;
    }
}
