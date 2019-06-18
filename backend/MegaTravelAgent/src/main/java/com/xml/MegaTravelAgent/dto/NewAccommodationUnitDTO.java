package com.xml.MegaTravelAgent.dto;

import java.util.List;

public class NewAccommodationUnitDTO {

    private String name;
    private String description;
    private String type;
    private int capacity;
    private int cancellationPeriod;
    private double defaultPrice;
    private int category;
    private List<AmenityDTO> amenities;
    private List<ImageDTO> images;
    private LocationDTO location;
    private UserInfoDTO agent;
}
