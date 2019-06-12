package com.xml.MegaTravelAgent.soap.config;

import com.xml.MegaTravelAgent.soap.client.ReservationClient;
import com.xml.MegaTravelAgent.soap.reqres.GetAccommodationUnitRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import com.xml.MegaTravelAgent.soap.client.AccommodationUnitClient;


@Configuration
public class SoapClientConfiguration {

   	private static final String RESERVATION_DEFAULT_URI = "http://reservation-service/ws";
    private static final String ACCOMMODATION_DEFAULT_URI = "http://accommodation-service/ws";


    @Bean
	public Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		// this package must match the package in the <generatePackage> specified in
		// pom.xml
		marshaller.setContextPath(GetAccommodationUnitRequest.class.getPackage().getName());
		return marshaller;
	}

	@Bean
	public AccommodationUnitClient accommodationClient(Jaxb2Marshaller marshaller) {
		AccommodationUnitClient client = new AccommodationUnitClient();
		client.setDefaultUri(ACCOMMODATION_DEFAULT_URI);
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		return client;
	}

	@Bean
	public ReservationClient reservationClient(Jaxb2Marshaller marshaller) {
		ReservationClient client = new ReservationClient();
		client.setDefaultUri(RESERVATION_DEFAULT_URI);
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		return client;
	}




}