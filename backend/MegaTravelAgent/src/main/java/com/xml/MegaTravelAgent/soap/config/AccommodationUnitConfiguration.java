package com.xml.MegaTravelAgent.soap.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import com.xml.MegaTravelAgent.soap.client.AccommodationUnitClient;


@Configuration
public class AccommodationUnitConfiguration {

//	@Bean
//	public Jaxb2Marshaller marshaller() {
//		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
//		// this package must match the package in the <generatePackage> specified in
//		// pom.xml
//		marshaller.setContextPath("soap.wsdl");
//		return marshaller;
//	}
//
//	@Bean
//	public AccommodationUnitClient accommodationClient(Jaxb2Marshaller marshaller) {
//		AccommodationUnitClient client = new AccommodationUnitClient();
//		client.setDefaultUri("http://localhost:8081/ws");
//		client.setMarshaller(marshaller);
//		client.setUnmarshaller(marshaller);
//		return client;
//	}
	
	

}