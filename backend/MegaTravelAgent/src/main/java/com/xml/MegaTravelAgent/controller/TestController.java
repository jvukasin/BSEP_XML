package com.xml.MegaTravelAgent.controller;

import com.xml.MegaTravelAgent.dto.CityDTO;
import com.xml.MegaTravelAgent.model.City;
import com.xml.MegaTravelAgent.model.Reservation;
import com.xml.MegaTravelAgent.service.CityService;
import com.xml.MegaTravelAgent.soap.client.IAccommodationUnitClient;
import com.xml.MegaTravelAgent.soap.client.IReservationClient;
import com.xml.MegaTravelAgent.soap.reqres.FetchReservationsResponse;
import com.xml.MegaTravelAgent.soap.reqres.GetAccommodationUnitResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/soapTest")
public class TestController {

	@Autowired
	IAccommodationUnitClient auClient;

	@Autowired
	IReservationClient resClient;

	@Autowired
	CityService cityService;



	@RequestMapping(value = "/au", method = RequestMethod.GET)
	public String testAu() throws IOException {


		GetAccommodationUnitResponse response = auClient.getAccommodationUnit(new Long(1));



		return response.getAccommodationUnit().getName();
	}

	@RequestMapping(value = "/res", method = RequestMethod.GET)
	public String testRes() throws IOException {


		FetchReservationsResponse response = resClient.fetchAgentsReservations(new Long(55));

		List<Reservation> reservations = response.getReservation();

		// test print
		for (Reservation r: reservations) {
			System.out.println("Reservation id: " + r.getId() + ", price: " + r.getPrice());
		}


		return "ok";
	}

	@RequestMapping(value = "/cities", method = RequestMethod.GET)
	public HashMap<Long, CityDTO> cities() throws IOException {

		return cityService.getCities();

	}
	
}
