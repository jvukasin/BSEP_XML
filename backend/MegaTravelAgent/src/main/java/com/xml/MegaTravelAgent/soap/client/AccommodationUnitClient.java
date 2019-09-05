package com.xml.MegaTravelAgent.soap.client;

import com.xml.MegaTravelAgent.model.AccommodationUnit;
import com.xml.MegaTravelAgent.soap.reqres.*;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;


public class AccommodationUnitClient extends WebServiceGatewaySupport implements IAccommodationUnitClient {

	private static final String ENDPOINT_URI = "https://localhost:8440/accommodation-service/ws";


	@Override
	public GetAccommodationUnitResponse getAccommodationUnit(Long accommodationUnitId) {

		System.out.println("getAccommodationUnit client");

		GetAccommodationUnitRequest request = new GetAccommodationUnitRequest();

		// temporary test
		request.setId(accommodationUnitId);


		GetAccommodationUnitResponse response = (GetAccommodationUnitResponse) getWebServiceTemplate()
				.marshalSendAndReceive(ENDPOINT_URI, request);

		return response;
	}


	@Override
	public PostAccommodationUnitResponse createAccommodationUnit(AccommodationUnit au, String agentUsername) {



		PostAccommodationUnitRequest request = new PostAccommodationUnitRequest();

		// temporary test
		request.setAgentUsername(agentUsername);
		request.setAccommodationUnit(au);

		PostAccommodationUnitResponse response = (PostAccommodationUnitResponse) getWebServiceTemplate()
				.marshalSendAndReceive(ENDPOINT_URI, request);

		return response;
	}

	@Override
	public EditAccommodationUnitResponse editAccommodationUnit(AccommodationUnit au, String agentUsername) {

		System.out.println("editAccommodationUnit client");

		PostAccommodationUnitRequest request = new PostAccommodationUnitRequest();

		request.setAccommodationUnit(au);
		request.setAgentUsername(agentUsername);

		EditAccommodationUnitResponse response = (EditAccommodationUnitResponse) getWebServiceTemplate()
				.marshalSendAndReceive(ENDPOINT_URI, request);

		return response;

	}

	@Override
	public DeleteAccommodationUnitResponse deleteAccommodationUnit(Long accommodationUnitId, String agentUsername) {

		System.out.println("editAccommodationUnit client");

		DeleteAccommodationUnitRequest request = new DeleteAccommodationUnitRequest();

		request.setAccommodationUnitId(accommodationUnitId);
		request.setAgentUsername(agentUsername);

		DeleteAccommodationUnitResponse response = (DeleteAccommodationUnitResponse) getWebServiceTemplate()
				.marshalSendAndReceive(ENDPOINT_URI, request);

		return response;

	}

	@Override
	public GetAccommodationSettingsResponse getAccommodationSettings() {


		System.out.println("getAccommodationSettingsResponse client");

		GetAccommodationSettingsRequest request = new GetAccommodationSettingsRequest();

		// temporary test
		request.setAgentUsername("");


		GetAccommodationSettingsResponse response = (GetAccommodationSettingsResponse) getWebServiceTemplate()
				.marshalSendAndReceive(ENDPOINT_URI, request);

		return response;
	}

	@Override
	public GetAURatingsResponse getAURatings(Long accommodationUnitId) {


		System.out.println("getAURatings client");

		GetAURatingsRequest request = new GetAURatingsRequest();
		request.setAccommodationUnitId(accommodationUnitId);

		GetAURatingsResponse response = (GetAURatingsResponse) getWebServiceTemplate()
				.marshalSendAndReceive(ENDPOINT_URI, request);

		return response;
	}


}
