package com.megatravel.reservationservice.dto;

import com.megatravel.reservationservice.model.Message;

import java.util.Date;
import java.util.List;

public class UserReservationDTO {

    private Date startDate;
    private Date endDate;
    private double price;
    private AccommodationInfoDTO accommodation;
    private List<MessageDTO> messages;

    public UserReservationDTO() {
    }

    public UserReservationDTO(Date startDate, Date endDate, double price, AccommodationInfoDTO accommodation, List<MessageDTO> messages) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
        this.accommodation = accommodation;
        this.messages = messages;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public AccommodationInfoDTO getAccommodation() {
        return accommodation;
    }

    public void setAccommodation(AccommodationInfoDTO accommodation) {
        this.accommodation = accommodation;
    }

    public List<MessageDTO> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageDTO> messages) {
        this.messages = messages;
    }
}
