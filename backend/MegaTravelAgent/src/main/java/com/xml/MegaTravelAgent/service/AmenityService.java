package com.xml.MegaTravelAgent.service;

import com.xml.MegaTravelAgent.model.Amenity;
import com.xml.MegaTravelAgent.repository.AmenityRepository;
import org.hibernate.engine.jdbc.env.spi.AnsiSqlKeywords;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class AmenityService {

    @Autowired
    AmenityRepository amenityRepository;



    private Amenity save(Amenity a) {
        return amenityRepository.save(a);
    }

    public Amenity findOne(Long id) {
        return amenityRepository.findOneById(id);
    }

    public List<Amenity> findAll() {
        return amenityRepository.findAll();
    }

    public List<Amenity> updateAmenities(List<Amenity> amenities) {
        return amenityRepository.saveAll(amenities);
    }



}
