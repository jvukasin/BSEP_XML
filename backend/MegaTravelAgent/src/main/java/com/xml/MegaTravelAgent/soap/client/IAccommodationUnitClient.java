package com.xml.MegaTravelAgent.soap.client;

import com.xml.MegaTravelAgent.model.AccommodationUnit;
import com.xml.MegaTravelAgent.soap.reqres.*;

public interface IAccommodationUnitClient {

    GetAccommodationUnitResponse getAccommodationUnit(Long accommodationUnitId);

    PostAccommodationUnitResponse createAccommodationUnit(AccommodationUnit au, String agentUsername);

    EditAccommodationUnitResponse editAccommodationUnit(AccommodationUnit au, String agentUsername);

    DeleteAccommodationUnitResponse deleteAccommodationUnit(Long accommodationUnitId, String agentUsername);

    GetAccommodationSettingsResponse getAccommodationSettings();

    GetAURatingsResponse getAURatings(Long accommodationUnitId);
}
