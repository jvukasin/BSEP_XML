package com.megatravel.reservationservice.dto;

import com.megatravel.reservationservice.model.Message;

import java.util.Date;

public class MessageDTO {

    private Long id;
    private String content;
    private Long reservationId;
    private Date date;
    private UserInfoDTO sender;
    private UserInfoDTO receiver;

    public MessageDTO() {
    }

    public MessageDTO(Long id, String content, Long reservationId, Date date, UserInfoDTO sender, UserInfoDTO receiver) {
        this.id = id;
        this.content = content;
        this.reservationId = reservationId;
        this.date = date;
        this.sender = sender;
        this.receiver = receiver;
    }

    public MessageDTO(Message m){
        this.id = m.getId();
        this.content = m.getContent();
        this.reservationId = m.getReservation().getId();
        this.date = m.getDate();
        this.sender = new UserInfoDTO(m.getSender());
        this.receiver = new UserInfoDTO(m.getReceiver());
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

    public UserInfoDTO getSender() {
        return sender;
    }

    public void setSender(UserInfoDTO sender) {
        this.sender = sender;
    }

    public UserInfoDTO getReceiver() {
        return receiver;
    }

    public void setReceiver(UserInfoDTO receiver) {
        this.receiver = receiver;
    }
}


