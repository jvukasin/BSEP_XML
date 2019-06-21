package com.megatravel.accommodationservice.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.megatravel.accommodationservice.dto.AmenityDTO;
import com.megatravel.accommodationservice.model.Amenity;
import com.megatravel.accommodationservice.repository.AmenityRepository;

import exceptions.BusinessException;

@Service
public class AmenityService 
{
	@Autowired 
	AmenityRepository amenityRepo;

	public Long add(AmenityDTO dto) 
	{
		if(dto.getName() == null || dto.getName().equals(""))
		{
			throw new BusinessException("Name attribute is missing.");
		}
		
		if(dto.getFaIcon() == null || dto.getFaIcon().equals(""))
		{
			throw new BusinessException("Icon attribute is missing.");
		}
		
		Amenity amenity = new Amenity();
		amenity.setName(dto.getName());
		amenity.setFaIcon(dto.getFaIcon());
		amenity.setId(null);
		
		amenityRepo.save(amenity.getFaIcon(), amenity.getName());
		
		return amenity.getId();
	}

	public void delete(Long id) 
	{
		try
		{
			amenityRepo.findById(id);
			
			amenityRepo.deleteById(id);
		}
		catch(NoSuchElementException e)
		{
			throw new BusinessException("No element found.");
		}
	}

	public Collection<AmenityDTO> findAll()
	{
		List<AmenityDTO> retVal = new ArrayList<>();

		for (Amenity a: amenityRepo.findAll()) {
			retVal.add(new AmenityDTO(a));
		}

		return retVal;
	}
	
	
	
	
	
}
