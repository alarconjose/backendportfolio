package com.portfolio.josebackend.Dto;

import javax.validation.constraints.NotBlank;

public class DtoHabilidad {

    @NotBlank
    private String name;
    @NotBlank
    private int value;

    public DtoHabilidad() {
    }

    public DtoHabilidad(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

}
