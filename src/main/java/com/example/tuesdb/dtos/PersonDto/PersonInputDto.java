package com.example.tuesdb.dtos.PersonDto;

import com.example.tuesdb.models.Person;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Valid
public class PersonInputDto {

    private String username;
    private String password;
    private String name;
    private Set<String> permissions;
    private Set<String> groups;

    public PersonInputDto() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<String> permissions) {
        this.permissions = permissions;
    }

    public Set<String> getGroups() {
        return groups;
    }

    public void setGroups(Set<String> groups) {
        this.groups = groups;
    }
}
