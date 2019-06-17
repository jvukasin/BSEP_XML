package com.megatravel.reservationservice.dto;

public class AmenityDTO {

    private Long id;
    private String name;
    private String faIcon;

    public AmenityDTO() {
    }

    public AmenityDTO(Long id, String name, String faIcon) {
        this.id = id;
        this.name = name;
        this.faIcon = faIcon;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFaIcon() {
        return faIcon;
    }

    public void setFaIcon(String faIcon) {
        this.faIcon = faIcon;
    }
}
