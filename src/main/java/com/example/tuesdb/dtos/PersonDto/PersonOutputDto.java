package com.example.tuesdb.dtos.PersonDto;


import com.example.tuesdb.models.Person;
import com.example.tuesdb.models.Permission;
import com.example.tuesdb.models.Groups;


import java.util.Set;

public class PersonOutputDto {
    private Long id;
    private String name;
    private Set<Permission> permissions;
    private Set<Groups> groups;

    public PersonOutputDto() {

    }

    public PersonOutputDto(Person person) {
        this.id = person.getId();
        this.name = person.getName();
        this.permissions = person.getPermissions();
        this.groups = person.getGroups();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Groups> getGroups() {
        return groups;
    }

    public void setGroups(Set<Groups> groups) {
        this.groups = groups;
    }
}
