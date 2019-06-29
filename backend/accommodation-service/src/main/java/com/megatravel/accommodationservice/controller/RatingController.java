package com.megatravel.accommodationservice.controller;

import com.megatravel.accommodationservice.dto.RatingAverageDTO;
import com.megatravel.accommodationservice.dto.RatingDTO;
import com.megatravel.accommodationservice.service.AccommodationUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping(value = "/ratings")
public class RatingController {

    @Autowired
    private AccommodationUnitService accService;

    @Autowired
    private RestTemplate template;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getAURatings(@PathVariable Long id)
    {
        ResponseEntity<List<RatingDTO>> response = template.exchange(
                "http://localhost:8331/getAURatings?id="+id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<RatingDTO>>(){});
        List<RatingDTO> retVal = response.getBody();
        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }

    @RequestMapping(value = "/average/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getAverageRating(@PathVariable Long id)
    {
        ResponseEntity<RatingAverageDTO> response = template.exchange(
                "http://localhost:8330/getRatingAverage?id="+id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<RatingAverageDTO>(){});
        return response;
    }

}
