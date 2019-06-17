package com.xml.MegaTravelAgent.service;

import com.xml.MegaTravelAgent.dto.CityDTO;
import com.xml.MegaTravelAgent.dto.CountryDTO;
import com.xml.MegaTravelAgent.model.City;
import com.xml.MegaTravelAgent.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;

@Service
public class CityService {

    @Autowired
    CityRepository cityRepo;

    public Optional<City> findById(Long id)
    {
        return cityRepo.findById(id);
    }

    public Collection<City> findAll()
    {
        return cityRepo.findAll();
    }

    public HashMap<Long, CityDTO> getCities() {

        Collection<City> cities = findAll();

        return makeDtoMap(cities);

    }

    public HashMap<Long, CityDTO> getCountryCities(Long countryId) {

            Collection<City> cities = cityRepo.findAllByCountryId(countryId);

            return makeDtoMap(cities);

    }

    private HashMap<Long, CityDTO> makeDtoMap(Collection<City> cities) {
        HashMap<Long, CityDTO> citiesMap = new HashMap<>();


        for (City c: cities) {

            citiesMap.put(c.getId(), new CityDTO(c.getId(), c.getName(),
                    new CountryDTO(c.getCountry().getId(), c.getCountry().getName())));

        }

        return citiesMap;
    }
}
