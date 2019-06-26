package com.xml.MegaTravelAgent.dto;

import com.xml.MegaTravelAgent.model.Reservation;

import java.util.Date;

public class ReservationDTO {

    private Long id;
    private Date startDate;
    private Date endDate;
    private double price;
    private boolean isSuccessful;
    private Long accommodationUnitId;
    private String accommodationUnitName;
    private String usernameReservator;
    private boolean isSelfReserved;

    public ReservationDTO() {
    }

    public ReservationDTO(Long id, Date startDate, Date endDate, double price, boolean isSuccessful, Long accommodationUnitId, String accommodationUnitName, String usernameReservator, boolean isSelfReserved) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
        this.isSuccessful = isSuccessful;
        this.accommodationUnitId = accommodationUnitId;
        this.accommodationUnitName = accommodationUnitName;
        this.usernameReservator = usernameReservator;
        this.isSelfReserved = isSelfReserved;
    }

    public ReservationDTO(Reservation r) {
        this.id = r.getId();
        this.startDate = r.getStartDate();
        this.endDate = r.getEndDate();
        this.price = r.getPrice();
        this.isSuccessful = r.isSuccessful();
        this.accommodationUnitId = r.getAccommodationUnit().getId();
        this.accommodationUnitName = r.getAccommodationUnit().getName();
        this.usernameReservator = r.getUsernameReservator();
        this.isSelfReserved = r.isSelfReserved();
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

    public String getUsernameReservator() {
        return usernameReservator;
    }

    public void setUsernameReservator(String usernameReservator) {
        this.usernameReservator = usernameReservator;
    }

    public String getAccommodationUnitName() {
        return accommodationUnitName;
    }

    public void setAccommodationUnitName(String accommodationUnitName) {
        this.accommodationUnitName = accommodationUnitName;
    }

    public boolean isSelfReserved() {
        return isSelfReserved;
    }

    public void setSelfReserved(boolean selfReserved) {
        isSelfReserved = selfReserved;
    }
}
