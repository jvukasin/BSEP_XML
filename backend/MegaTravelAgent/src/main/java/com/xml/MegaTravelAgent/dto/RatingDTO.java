package com.xml.MegaTravelAgent.dto;

public class RatingCommentDTO 
{
    private int value;
    private String text;
    private Long reservationID;
    
    
    
	public RatingCommentDTO() {
		super();
	}
	public RatingCommentDTO(int value, String text,long id) {
		super();
		this.value = value;
		this.text = text;
		this.reservationID = id;
	}
	
	public Long getReservationID() {
		return reservationID;
	}
	public void setReservationID(Long reservationID) {
		this.reservationID = reservationID;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
    
    
    

}
