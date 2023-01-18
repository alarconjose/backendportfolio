package com.portfolio.josebackend.Dto;

import javax.validation.constraints.NotBlank;

public class DtoSocial {

    @NotBlank
    private String name;
    @NotBlank
    private String image;

    public DtoSocial() {
    }

    public DtoSocial(String name, String image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
