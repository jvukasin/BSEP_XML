package com.megatravel.accommodationservice.soap.endpoints;


import com.megatravel.accommodationservice.soap.reqres.*;

public interface IAccommodationUnitEndpoint {

    GetAccommodationUnitResponse getAccommodationUnit(GetAccommodationUnitRequest request);

    PostAccommodationUnitResponse createAccommodationUnit(PostAccommodationUnitRequest request);

    EditAccommodationUnitResponse editAccommodationUnit(EditAccommodationUnitRequest request);

    DeleteAccommodationUnitResponse deleteAccommodationUnit(DeleteAccommodationUnitRequest request);

    GetAccommodationSettingsResponse getAccommodationSettingsResponse(GetAccommodationSettingsRequest request);



}
