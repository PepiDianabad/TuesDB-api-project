package com.example.tuesdb.dtos;

import com.example.tuesdb.models.Bike;
import jakarta.validation.constraints.NotBlank;

public class BikeDto {

    private Long id;

    @NotBlank
    private String make;

    @NotBlank
    private String model;

    private Long personId;

    public BikeDto(Bike b) {
        this.id = b.getId();
        this.make = b.getMake();
        this.model = b.getModel();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }
}
