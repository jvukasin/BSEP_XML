package com.xml.MegaTravelAgent.service;

import com.xml.MegaTravelAgent.dto.CountryDTO;
import com.xml.MegaTravelAgent.model.Country;
import com.xml.MegaTravelAgent.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;

@Service
public class CountryService {

    @Autowired
    CountryRepository countryRepo;

    public Optional<Country> findById(Long id) {
        return countryRepo.findById(id);
    }

    public Collection<Country> findAll() {
        return countryRepo.findAll();
    }

    public HashMap<Long, CountryDTO> getCountries() {

        Collection<Country> countries = findAll();

        HashMap <Long, CountryDTO> countriesMap = new HashMap<>();

        for (Country c: countries) {

            countriesMap.put(c.getId(), new CountryDTO(c.getId(), c.getName()));

        }

        return countriesMap;

    }




}
