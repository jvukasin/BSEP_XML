package com.xml.MegaTravelAgent.dto;

import com.xml.MegaTravelAgent.model.Rating;

import java.util.Date;

public class RatingDTO
{
    private Long id;
    private int value;
    private int approved;
    private Date postingDate;
    private String comment;
    private Long accommodationUnitId;
    private String reservator;

    public RatingDTO() {
    }

    public RatingDTO(Long id, int value, int approved, Date postingDate, String comment, Long accommodationUnitId, String reservator) {
        this.id = id;
        this.value = value;
        this.approved = approved;
        this.postingDate = postingDate;
        this.comment = comment;
        this.accommodationUnitId = accommodationUnitId;
        this.reservator = reservator;
    }

    public RatingDTO(Rating r) {
        this.id = r.getId();
        this.value = r.getValue();
        this.approved = r.isApproved() ? 1 : 0;
        this.postingDate = r.getPostingDate();
        this.comment = r.getComment();
        this.accommodationUnitId = r.getAccommodationId();
        this.reservator = r.getReservator();
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

    public int getApproved() {
        return approved;
    }

    public void setApproved(int approved) {
        this.approved = approved;
    }

    public Date getPostingDate() {
        return postingDate;
    }

    public void setPostingDate(Date postingDate) {
        this.postingDate = postingDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getAccommodationUnitId() {
        return accommodationUnitId;
    }

    public void setAccommodationUnitId(Long accommodationUnitId) {
        this.accommodationUnitId = accommodationUnitId;
    }

    public String getReservator() {
        return reservator;
    }

    public void setReservator(String reservator) {
        this.reservator = reservator;
    }
}
