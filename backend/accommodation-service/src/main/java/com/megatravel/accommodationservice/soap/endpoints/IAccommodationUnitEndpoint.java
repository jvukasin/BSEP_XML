package com.megatravel.accommodationservice.soap.endpoints;


import com.megatravel.accommodationservice.soap.reqres.GetAccommodationUnitRequest;
import com.megatravel.accommodationservice.soap.reqres.GetAccommodationUnitResponse;
import com.megatravel.accommodationservice.soap.reqres.PostAccommodationUnitRequest;
import com.megatravel.accommodationservice.soap.reqres.PostAccommodationUnitResponse;

public interface IAccommodationUnitEndpoint {

    GetAccommodationUnitResponse getAccommodationUnit(GetAccommodationUnitRequest request);

    PostAccommodationUnitResponse createAccomodationUnit(PostAccommodationUnitRequest request);
}
