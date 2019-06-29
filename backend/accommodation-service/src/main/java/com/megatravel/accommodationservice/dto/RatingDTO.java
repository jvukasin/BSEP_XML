package com.megatravel.accommodationservice.dto;

import java.util.Date;

public class RatingDTO {

    private Long id;
    private int value;
    private boolean approved;
    private Date posting_date;
    private String comment;
    private Long accommodation_id;
    private String reservator;

    public RatingDTO() {
    }

    public RatingDTO(Long id, int value, boolean approved, Date posting_date, String comment, Long accommodation_id, String reservator) {
        this.id = id;
        this.value = value;
        this.approved = approved;
        this.posting_date = posting_date;
        this.comment = comment;
        this.accommodation_id = accommodation_id;
        this.reservator = reservator;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public Date getPosting_date() {
        return posting_date;
    }

    public void setPosting_date(Date posting_date) {
        this.posting_date = posting_date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getAccommodation_id() {
        return accommodation_id;
    }

    public void setAccommodation_id(Long accommodation_id) {
        this.accommodation_id = accommodation_id;
    }

    public String getReservator() {
        return reservator;
    }

    public void setReservator(String reservator) {
        this.reservator = reservator;
    }
}
