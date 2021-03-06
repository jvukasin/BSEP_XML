package com.megatravel.accommodationservice.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.megatravel.accommodationservice.model.Amenity;


public interface AmenityRepository extends JpaRepository<Amenity, Long>
{
	/*
	@Modifying
    @Transactional
	@Query(value = "INSERT INTO Amenity (fa_icon, name) values\r\n" + 
			"(?1, ?2);", nativeQuery = true)
	void save(String icon, String name);
	*/
}
	 