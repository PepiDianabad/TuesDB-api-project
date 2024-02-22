package com.example.tuesdb.models;

import jakarta.persistence.*;


import java.util.Set;

@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    private String password;

    @ManyToMany
    private Set<Permission> permissions;

    @ManyToMany
    private Set<Groups> groups;


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {this.name = name;}

    public String getPassword(){return password; }

    public void setPassword(String password){this.password = password; }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermission(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Groups> getGroups(){return groups; }

    public void setGroups(Set<Groups> groups){ this.groups = groups; }
}
