package com.megatravel.accommodationservice.dto;

import com.megatravel.accommodationservice.model.AccommodationCategory;

public class AccommodationCategoryDTO 
{
	private int value;

	public AccommodationCategoryDTO()
	{
		
	}
	
	public AccommodationCategoryDTO(AccommodationCategory acc)
	{
		this.value = acc.getValue();
	}
	
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
}
