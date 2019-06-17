package com.xml.MegaTravelAgent.controller;

import com.xml.MegaTravelAgent.dto.CityDTO;
import com.xml.MegaTravelAgent.dto.CountryDTO;
import com.xml.MegaTravelAgent.service.CityService;
import com.xml.MegaTravelAgent.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.io.IOException;
import java.util.HashMap;

@RestController
@RequestMapping("/location")
public class LocationController {

    @Autowired
    CityService cityService;

    @Autowired
    CountryService countryService;

    @RequestMapping(value = "/cities", method = RequestMethod.GET)
    public HashMap<Long, CityDTO> cities() throws IOException {

        return cityService.getCities();

    }

    @RequestMapping(value = "/countries", method = RequestMethod.GET)
    public HashMap<Long, CountryDTO> countries() throws IOException {

        return countryService.getCountries();

    }

    @RequestMapping(value = "/countries/{id}/cities", method = RequestMethod.GET)
    public HashMap<Long, CityDTO> countries(@PathVariable Long id) throws IOException {

        return cityService.getCountryCities(id);

    }
}
