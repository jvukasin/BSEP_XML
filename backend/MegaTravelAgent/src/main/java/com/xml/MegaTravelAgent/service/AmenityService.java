package com.xml.MegaTravelAgent.service;

import com.xml.MegaTravelAgent.model.Amenity;
import com.xml.MegaTravelAgent.repository.AmenityRepository;
import org.hibernate.engine.jdbc.env.spi.AnsiSqlKeywords;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
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


    // zbog cross tabele amenity_accommodation unit, update amenitija mora rucno da se radi
    public List<Amenity> updateAmenities(List<Amenity> amenities) {

        // prvo obrisi one kojih vise nema :'(
        for (Amenity a: amenityRepository.findAll()) {

            boolean exists = false;

            for (Amenity newA: amenities) {
                if (newA.getId().equals(a.getId())) {
                    exists = true;
                    break;
                }
            }

            if (!exists) {
                amenityRepository.deleteById(a.getId());
            }

        }

        // potom vidi da li ima novih i proveri da li su stari menjani
        for (Amenity a: amenities) {


            Amenity oldAmenity = amenityRepository.findOneById(a.getId());

            if (oldAmenity == null) {
                amenityRepository.save(a);
            } else {
                amenityRepository.save(update(oldAmenity, a));
            }

        }

        return amenityRepository.findAll();
    }

    private Amenity update(Amenity oldAmenity, Amenity newAmenity) {

        if (!oldAmenity.getName().equals(newAmenity.getName())) {
            oldAmenity.setName(newAmenity.getName());
        }

        if (!oldAmenity.getFaIcon().equals(newAmenity.getFaIcon())) {
            oldAmenity.setFaIcon(newAmenity.getFaIcon());
        }

        return oldAmenity;
    }



}
