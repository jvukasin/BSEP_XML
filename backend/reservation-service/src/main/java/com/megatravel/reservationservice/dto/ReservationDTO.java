package com.megatravel.reservationservice.dto;

import java.util.Date;

import com.megatravel.reservationservice.model.Reservation;

public class ReservationDTO {

    private Long id;
    private Date startDate;
    private Date endDate;
    private boolean isSuccessful;
    private Long accommodationUnitId;
    private double price;
    private UserInfoDTO reservator;

    public ReservationDTO() {
    }

    public ReservationDTO(Long id, Date startDate, Date endDate, double price, boolean isSuccessful, Long accommodationUnitId, UserInfoDTO reservator) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
        this.isSuccessful = isSuccessful;
        this.accommodationUnitId = accommodationUnitId;
        this.reservator = reservator;
    }
    
    
    public ReservationDTO(Reservation reservation)
    {
    	id = reservation.getId();
    	startDate = reservation.getStartDate();
    	endDate = reservation.getEndDate();
    	price = reservation.getPrice();
    	isSuccessful = reservation.isIsSuccessful();
    	accommodationUnitId = reservation.getAccommodationUnit().getId();
    	reservator = new UserInfoDTO(reservation.getReservator());
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public boolean isSuccessful() {
        return isSuccessful;
    }

    public void setSuccessful(boolean successful) {
        isSuccessful = successful;
    }

    public Long getAccommodationUnitId() {
        return accommodationUnitId;
    }

    public void setAccommodationUnitId(Long accommodationUnitId) {
        this.accommodationUnitId = accommodationUnitId;
    }

    public UserInfoDTO getReservator() {
        return reservator;
    }

    public void setReservator(UserInfoDTO reservator) {
        this.reservator = reservator;
    }
}
