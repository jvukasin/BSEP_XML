package com.megatravel.accommodationservice.controller;

import com.megatravel.accommodationservice.dto.AccommodationUnitDTO;
import com.megatravel.accommodationservice.dto.RatingAverageDTO;
import com.megatravel.accommodationservice.dto.RatingDTO;
import com.megatravel.accommodationservice.dto.RatingIdDTO;
import com.megatravel.accommodationservice.model.AccommodationUnit;
import com.megatravel.accommodationservice.security.TokenUtils;
import com.megatravel.accommodationservice.service.AccommodationUnitService;
import com.netflix.ribbon.proxy.annotation.Http;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/ratings")
public class RatingController {

    @Autowired
    private AccommodationUnitService accService;

    @Autowired
    private RestTemplate template;

    @Autowired
    public TokenUtils tokenUtils;

    @RequestMapping(value = "/approved/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getAUApprovedRatings(@PathVariable Long id)
    {
        ResponseEntity<List<RatingDTO>> response = template.exchange(
                "http://localhost:8333/getAUApprovedRatings?id="+id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<RatingDTO>>(){});
        List<RatingDTO> retVal = response.getBody();
        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }

    @RequestMapping(value = "/average/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getAverageRating(@PathVariable Long id)
    {
        ResponseEntity<List<RatingAverageDTO>> responseAvg = template.exchange(
                "http://localhost:8330/getRatingAverage?id="+id,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<RatingAverageDTO>>(){});

        List<RatingAverageDTO> ratingAverageDTO = (List<RatingAverageDTO>) responseAvg.getBody();
        RatingAverageDTO retVal = ratingAverageDTO.iterator().next();
        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }

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

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<?> postRating(@RequestBody RatingDTO ratingDTO, HttpServletRequest request){
        String token = tokenUtils.getToken(request);
        String username = tokenUtils.getUsernameFromToken(token);
        ratingDTO.setReservator(username);

        // racunanje avg za unit prilikom upisa rejtinga
        ResponseEntity<List<RatingAverageDTO>> responseAvg = template.exchange(
                "http://localhost:8330/getRatingAverage?id="+ratingDTO.getAccommodation_id(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<RatingAverageDTO>>(){});

        List<RatingAverageDTO> ratingAverageDTO = (List<RatingAverageDTO>) responseAvg.getBody();
        RatingAverageDTO avgDTO = ratingAverageDTO.iterator().next();
        accService.calculateAvg(ratingDTO, avgDTO);


        HttpEntity<RatingDTO> entity = new HttpEntity<RatingDTO>(ratingDTO);
        String response = template.postForObject("http://localhost:8332/addRating", entity, String.class);

        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/unapproved", method = RequestMethod.GET)
    public ResponseEntity<List<RatingDTO>> getAllUnapprovedRatings(){

        ResponseEntity<List<RatingDTO>> response = template.exchange(
                "http://localhost:8334/getAllUnapprovedRatings",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<RatingDTO>>(){});

        List<RatingDTO> ratings = response.getBody();

        return new ResponseEntity(ratings, HttpStatus.OK);

    }

    @RequestMapping(value = "/approve", method = RequestMethod.PUT)
    public ResponseEntity<?> approveComment(@RequestBody RatingIdDTO id){

        try{
            template.put("http://localhost:8335/approveComment", id);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.OK);

    }

}
