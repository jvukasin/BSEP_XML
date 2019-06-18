package com.xml.MegaTravelAgent.service;

import com.xml.MegaTravelAgent.model.Amenity;
import com.xml.MegaTravelAgent.repository.AmenityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AmenityService {

    @Autowired
    AmenityRepository amenityRepository;

    private Amenity save(Amenity a) {
        return amenityRepository.save(a);
    }





}
