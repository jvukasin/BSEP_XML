package com.xml.MegaTravelAgent.dto;

import com.xml.MegaTravelAgent.model.Image;

public class ImageDTO {

    private Long id;
    private String imageUrl;

    public ImageDTO() {
    }

    public ImageDTO(Long id, String imageUrl) {
        this.id = id;
        this.imageUrl = imageUrl;
    }

    public ImageDTO(Image image)
    {
        id = image.getId();
        imageUrl = image.getImageUrl();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
