package com.portfolio.josebackend.Dto;

import javax.validation.constraints.NotBlank;

public class DtoEducacion {

    @NotBlank
    private String image;
    @NotBlank
    private String school;
    @NotBlank
    private String title;
    @NotBlank
    private String date;
    @NotBlank
    private String description;

    public DtoEducacion() {
    }

    public DtoEducacion(String image, String school, String title, String date, String description) {
        this.image = image;
        this.school = school;
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

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
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
