package com.example.tuesdb.dtos;

import com.example.tuesdb.models.Person;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Valid
public class PersonDto {

    private Long id;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String email;

    private Set<BikeDto> bikes = new HashSet<>();

    public PersonDto(Person p) {
        this.id = p.getId();
        this.email = p.getEmail();
        this.firstName = p.getFirstName();
        this.lastName = p.getLastName();
        this.bikes = Optional.of(p.getBikes())
                .orElse(Collections.emptySet())
                .stream()
                .map(b -> new BikeDto(b))
                .collect(Collectors.toSet());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<BikeDto> getBikes() {
        return bikes;
    }

    public void setBikes(Set<BikeDto> bikes) {
        this.bikes = bikes;
    }
}
