package com.portfolio.josebackend.Dto;

import javax.validation.constraints.NotBlank;

public class DtoProyecto {

    @NotBlank
    private String image;
    @NotBlank
    private String name;
    @NotBlank
    private String date;
    @NotBlank
    private String description;
    @NotBlank
    private String url;

    public DtoProyecto() {
    }

    public DtoProyecto(String image, String name, String date, String description, String url) {
        this.image = image;
        this.name = name;
        this.date = date;
        this.description = description;
        this.url = url;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
