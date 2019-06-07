package com.xml.MegaTravelAgent.soap.client;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import com.xml.MegaTravelAgent.soap.reqres.GetAccommodationUnitRequest;
import com.xml.MegaTravelAgent.soap.reqres.GetAccommodationUnitResponse;


public class AccommodationUnitClient extends WebServiceGatewaySupport {
	
	public GetAccommodationUnitResponse getAccommodationUnit() {
		GetAccommodationUnitRequest request = new GetAccommodationUnitRequest();
		GetAccommodationUnitResponse response = (GetAccommodationUnitResponse) getWebServiceTemplate()
				.marshalSendAndReceive("https://localhost:8081/ws/accommodation_unit", request,
						new SoapActionCallback(
								"http://spring.io/guides/gs-producing-web-service/GetCountryRequest"));
		return response;
	}
	
}
