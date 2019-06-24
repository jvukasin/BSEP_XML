package com.xml.MegaTravelAgent.dto;


import com.xml.MegaTravelAgent.model.SpecificPrice;

import java.util.Date;

public class SpecificPriceDTO {

    private Long id;
    private double price;
    private Date startDate;
    private Date endDate;

    public SpecificPriceDTO() {
    }

    public SpecificPriceDTO(Long id, double price, Date startDate, Date endDate) {
        this.id = id;
        this.price = price;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public SpecificPriceDTO(SpecificPrice sp) {
        this.id = sp.getId();
        this.price = sp.getPrice();
        this.startDate = sp.getStartDate();
        this.endDate = sp.getEndDate();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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
}
