package com.portfolio.josebackend.Dto;

import javax.validation.constraints.NotBlank;

public class DtoBanner {

    @NotBlank
    private String name;
    @NotBlank
    private String backImage;
    @NotBlank
    private String photo;
    @NotBlank
    private String about;
    @NotBlank
    private String title;

    public DtoBanner() {
    }

    public DtoBanner(String name, String backImage, String photo, String about, String title) {
        this.name = name;
        this.backImage = backImage;
        this.photo = photo;
        this.about = about;
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBackImage() {
        return backImage;
    }

    public void setBackImage(String backImage) {
        this.backImage = backImage;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
