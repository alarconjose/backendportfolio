package com.portfolio.josebackend.Dto;

import javax.validation.constraints.NotBlank;

public class DtoLaboral {

    @NotBlank
    private String image;
    @NotBlank
    private String name;
    @NotBlank
    private String title;
    @NotBlank
    private String date;
    @NotBlank
    private String description;

    public DtoLaboral() {
    }

    public DtoLaboral(String image, String name, String title, String date, String description) {
        this.image = image;
        this.name = name;
        this.title = title;
        this.date = date;
        this.description = description;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

}
