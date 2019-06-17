package com.xml.MegaTravelAgent.soap.client;

import com.xml.MegaTravelAgent.model.AccommodationUnit;
import com.xml.MegaTravelAgent.soap.reqres.*;

public interface IAccommodationUnitClient {

    GetAccommodationUnitResponse getAccommodationUnit(Long accommodationUnitId);

    PostAccommodationUnitResponse createAccommodationUnit(AccommodationUnit au, Long agentId);

    EditAccommodationUnitResponse editAccommodationUnit(AccommodationUnit au, Long agentId);

    DeleteAccommodationUnitResponse deleteAccommodationUnit(Long accommodationUnitId, Long agentId);

    GetAccommodationSettingsResponse getAccommodationSettings();
}
